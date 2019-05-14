package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.sensors.common.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;
import ua.ita.smartcarservice.entity.sensors.enums.AggregationFunctions;
import ua.ita.smartcarservice.entity.sensors.enums.ChartSelections;
import ua.ita.smartcarservice.entity.sensors.enums.SensorProperties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartSensorRepositoryImpl<T extends BaseSensorEntity> implements ChartSensorRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SensorEntityFactory entityFactory;

    private CriteriaBuilder builder;

    private final int DEFAULT_LAST_RECORD_VALUE = 0;

    @Override
    public Integer findLastRecordValue(@Param("carId") long carId,
                                       @Param("sensorType") String sensorType) {

        builder = entityManager.getCriteriaBuilder();
        Class entityClass = entityFactory.getEntity(sensorType).getClass();
        CriteriaQuery criteria = builder.createQuery(entityClass);
        Root root = criteria.from(entityClass);

        Subquery subquery = criteria.subquery(entityClass);
        Root fromSubquery = subquery.from(entityClass);
        subquery.select(builder.max(fromSubquery.get(SensorProperties.ID.toString())));
        subquery.where(builder.equal(
                fromSubquery.get(SensorProperties.CAR.toString()).get(SensorProperties.ID.toString()), carId));

        Path value = root.get(SensorProperties.VALUE.toString());
        criteria.select(value);
        criteria.where(builder.in(root.get(SensorProperties.ID.toString())).value(subquery));

        List records = entityManager.createQuery(criteria).getResultList();
        return records.size() == 0 ? DEFAULT_LAST_RECORD_VALUE : (int)Math.round((double)records.get(0));
    }

    @Override
    public List<Object[]> findAllDataByPeriod(@Param("date") LocalDateTime date,
                                              @Param("carId") long carId,
                                              @Param("sensorType") String sensorType,
                                              @Param("selection") String selection,
                                              @Param("aggregation") String aggregation) {

        builder = entityManager.getCriteriaBuilder();
        Class entityClass = entityFactory.getEntity(sensorType).getClass();
        CriteriaQuery criteria = builder.createQuery(entityClass);
        Root root = criteria.from(entityClass);

        ChartCriteriaElementsProvider elementsProvider =
                new ChartCriteriaElementsProvider(carId, date, selection, aggregation, sensorType, root);

        Expression selectedPeriod = getSelectedPeriod(elementsProvider);
        Predicate[] predicates = getPredicates(elementsProvider);
        Expression[] selectionValues = getSelection(selectedPeriod, elementsProvider);

        criteria.select(builder.array(selectionValues));
        criteria.where(predicates);
        criteria.groupBy(selectedPeriod);
        criteria.orderBy(builder.asc(selectedPeriod));

        return entityManager.createQuery(criteria).getResultList();
    }

    private ChartSelections getChartSelection(String selection){
        switch (ChartSelections.getEnumByPeriod(selection)){
            case DAY:
                return ChartSelections.TIME;
            case MONTH:
                return ChartSelections.DAY;
            default:
                return ChartSelections.MONTH;
        }
    }

    private Expression getSelectedPeriod(ChartCriteriaElementsProvider elementsProvider) {
        return getPartOfDate(getChartSelection(elementsProvider.getSelection()).toString(),
                elementsProvider.getDatePath());
    }

    private Predicate[] getPredicates(ChartCriteriaElementsProvider elementsProvider) {
        Path<String> datePath = elementsProvider.getDatePath();
        LocalDateTime date = elementsProvider.getDate();

        Predicate equalCar = builder.equal(elementsProvider.getCarIdPath(), elementsProvider.getCarId());
        Predicate equalYear = builder.equal(getPartOfDate(ChartSelections.YEAR.toString(), datePath), date.getYear());
        Predicate equalMonth = builder.equal(getPartOfDate(ChartSelections.MONTH.toString(), datePath), date.getMonthValue());
        Predicate equalDay = builder.equal(getPartOfDate(ChartSelections.DAY.toString(), datePath), date.getDayOfMonth());

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(equalCar);
        predicates.add(equalYear);

        switch (ChartSelections.getEnumByPeriod(elementsProvider.getSelection())){
            case DAY:
                predicates.addAll(Arrays.asList(equalMonth, equalDay));
                break;
            case MONTH:
                predicates.add(equalMonth);
                break;
        }

        return predicates.toArray(new Predicate[]{});
    }

    private Expression getPartOfDate(String part, Path<String> datePath) {
        return builder.function(part, Integer.class, datePath);
    }

    private Expression[] getSelection(Expression selectedPeriod, ChartCriteriaElementsProvider elementsProvider) {
        Map<String, Path<Double>> mapOfValues = elementsProvider.getValuePath();
        List<Expression> list = new ArrayList<>();

        list.add(selectedPeriod);
        list.addAll(mapOfValues.values().stream()
                .map(value -> getAggregatedValue(value, elementsProvider.getAggregation())).collect(Collectors.toList()));
        return list.toArray(new Expression[]{});
    }

    private Expression getAggregatedValue(Path<Double> value, String aggregation) {
        switch (AggregationFunctions.getEnumByAggregation(aggregation)){
            case MIN:
                return builder.min(value);
            case MAX:
                return builder.max(value);
            default:
                return builder.avg(value);
        }
    }
}