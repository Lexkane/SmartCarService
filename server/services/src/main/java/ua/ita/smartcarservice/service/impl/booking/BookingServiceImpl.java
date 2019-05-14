package ua.ita.smartcarservice.service.impl.booking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.*;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.entity.booking.TimePoint;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.booking.BookingRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkersSkillRepository;
import ua.ita.smartcarservice.service.booking.BookingService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Integer DAYS_WHEN_WE_FIND_FREE_TIME= 14;
    private static final Integer WORKS_DAY_START_HOURS = 10;
    private static final Integer WORKS_DAY_END_HOURS = 18;
    private static final Integer MINUTES_IN_WORK_DAY = 480;
    private static final Long DEFAULT_WORKER_ID = -1L;


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private WorkersSkillRepository workersSkillRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Override
    public void addBooking(NewBookingDto newBookingDto) {
        Car car = carRepository.getOne(newBookingDto.getCarId());
        List<WorkTime> bookingToAdd = new ArrayList <>();
        LocalDateTime start = parseDate(newBookingDto.getStart());

        //get info about works and workers
        List<Long> worksId = newBookingDto.getWorksInfo().stream().map(WorkInfoDto::getWorkId).collect(Collectors.toList());
        List<Long> workersId =  newBookingDto.getWorkersId().stream().map(Long::valueOf).collect(Collectors.toList());

        //get all works object using worksId
        List<WorkType> worksTypes = workTypeRepository.findAllById(worksId);

        //get all dependency between workers and skills
        List<WorkersSkill> workersSkills = workersSkillRepository.findByWorkAndWorker(workersId, worksId);

        //connect work with workers and add new booking
        newBookingDto.getWorksInfo().forEach(workInfoDto -> {
            WorkTime workTime = new WorkTime();
            workTime.setStartBooking(findTimeToSchedule(start, workInfoDto.getStart()));
            workTime.setEndBooking(findTimeToSchedule(start, workInfoDto.getEnd()));
            workTime.setCar(car);
            workTime.setWorker(
                    userRepository.getOne(searchWorkerToWork(workersSkills, worksTypes, workInfoDto.getWorkId())));
            workTime.setWork(workTypeRepository.getOne(workInfoDto.getWorkId()));

            bookingToAdd.add(workTime);
        });

        bookingRepository.saveAll(bookingToAdd);
    }

    /**
     * Called when report's added successfully, update report id in workers schedule
     * @param reportDto - report's info
     * @param reportEntity - report
     */
    @Override
    public void updateReportsId(ReportDto reportDto, ReportEntity reportEntity) {
        bookingRepository.updateReportsId(parseDate(reportDto.getStartTime()),
                parseDate(reportDto.getEndTime()), reportDto.getCarId(), reportEntity);
    }

    /**
     * Main method which return free time to booking
     * @param bookingDto - info about booking
     * @return - free time
     */
    @Override
    public List<LocalDateTime> findTimeToBooking(BookingDto bookingDto) {
        List<Long> workersId = bookingDto.getWorkersId().stream().map(Long::valueOf).collect(Collectors.toList());
        LocalDate time = LocalDate.parse(bookingDto.getTime());

        List<WorkTimeDto> timeToWork = findTimeWhenWork(workersId, time);

        return findFreeTime(findAllTimePoints(timeToWork, time), bookingDto.getNeedTime());
    }

    /**
     * This method's find time when workers are working
     * @param workersId - workers id
     * @param time - search start time
     * @return list of Dto objects which have info about workers time
     */
    private List<WorkTimeDto> findTimeWhenWork(List<Long> workersId, LocalDate time) {
        LocalDate end = time.plusDays(DAYS_WHEN_WE_FIND_FREE_TIME);
        LocalDateTime startTime = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10, 0);
        LocalDateTime endTime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 18, 0);

        return bookingRepository.findTimeWhenWork(workersId, startTime, endTime).stream()
                .map(workTime -> (getWorkTimeDto(workTime))).collect(Collectors.toList());
    }

    /**
     * Time Point - it is place in time, when workers start or end do their work
     * @param timeToWork - time when workers are working
     * @param time - search start time
     * @return
     */
    private List<TimePoint> findAllTimePoints(List<WorkTimeDto> timeToWork, LocalDate time) {
        List<TimePoint> timePoints = new ArrayList <>();

        //start and end of works day time
        for (int i = 0; i < DAYS_WHEN_WE_FIND_FREE_TIME; i++) {
            timePoints.add(new TimePoint(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 10, 0).plusDays(i), true));
            timePoints.add(new TimePoint(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 18, 0).plusDays(i), false));
        }

        timeToWork.forEach(workTimeDto -> {
            timePoints.add(new TimePoint(workTimeDto.getStartBooking(), true));
            timePoints.add(new TimePoint(workTimeDto.getEndBooking(), false));
        });

        Collections.sort(timePoints);
        return timePoints;
    }

    /**
     * This method's return free time
     * @param timePoints - places in time
     * @param timeToNeedInMinute - required time
     * @return - free time
     */
    private List<LocalDateTime> findFreeTime(List<TimePoint> timePoints, int timeToNeedInMinute) {
        TimePoint start = timePoints.get(0);
        int workNow = -1;
        int remainderOfTime = 0;

        for (int i = 0; i < timePoints.size(); i++) {
            if (timePoints.get(i).isPosition()) {
                // out time point is start of working day
                if (timePoints.get(i).getTime().getHour() == WORKS_DAY_START_HOURS) {
                    // we have remainder time from last day
                    if (remainderOfTime != 0) {
                        int delta = getDeltaTimeInMinute(timePoints.get(i).getTime(), timePoints.get(i + 1).getTime());
                        // we have a lot of free time before next time point, so we find free time
                        if (delta >= remainderOfTime) {
                            return returnFreeTime(start.getTime(), timePoints.get(i).getTime().plusMinutes(remainderOfTime));
                        // we don't have a lot of time but next time point is end of works day, so we can find free time in next day
                        } else if (delta < remainderOfTime && timePoints.get(i + 1).getTime().getHour() == WORKS_DAY_END_HOURS) {
                            remainderOfTime -= MINUTES_IN_WORK_DAY;
                        }
                        // we don't have a lot of free time
                        else {
                            remainderOfTime = 0;
                        }
                    }
                    // out time point is start of work but isn't start of working day day
                } else {
                        if (getDeltaTimeInMinute(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute
                                && workNow == 0) {
                            return returnFreeTime(start.getTime(), start.getTime().plusMinutes(timeToNeedInMinute));
                        }
                }
                workNow++;
            }
            else {
                // our time point in end of working day
                if(timePoints.get(i).getTime().getHour() == WORKS_DAY_END_HOURS){
                    // if we don't have remainder time and don't have work with will end in next day
                    // we check time point before(it must be, because out time point is the end of day)
                    // and place between now and last time point is free
                    if(remainderOfTime == 0 && workNow == 0){
                        //we have a lot of free time
                        if(getDeltaTimeInMinute(timePoints.get(i - 1).getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute){
                            return returnFreeTime(timePoints.get(i - 1).getTime(), timePoints.get(i - 1).getTime().plusMinutes(timeToNeedInMinute));
                        }
                        // we don't have a lot of time so we have remainder in next day
                        else {
                            remainderOfTime = timeToNeedInMinute - getDeltaTimeInMinute(timePoints.get(i - 1).getTime(), timePoints.get(i).getTime());
                        }
                    }
                }
                // we can find free time only in future because our time point in end of work
                else {
                    start = timePoints.get(i);
                }
                workNow--;
            }
        }

        return Collections.EMPTY_LIST;

    }

    private List<LocalDateTime> returnFreeTime(LocalDateTime start, LocalDateTime end){
        List<LocalDateTime> freeTime = new ArrayList <>();
        freeTime.add(start);
        freeTime.add(end);
        return freeTime;
    }

    /**
     *This method find time using only work hours
     * @param time - time to start
     * @param requiredTime - time to need
     * @return - end of work's time
     */
    private LocalDateTime findTimeToSchedule(LocalDateTime time, int requiredTime) {

        LocalDateTime end = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 18,0);
        LocalDateTime start = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10,0).plusDays(1);

        if(getDeltaTimeInMinute(time, end)>=requiredTime){
            return time.plusMinutes(requiredTime);
        }
        else {
            requiredTime-= getDeltaTimeInMinute(time, end);
        }

        return start.plusDays(requiredTime / MINUTES_IN_WORK_DAY)
                .plusMinutes(requiredTime % MINUTES_IN_WORK_DAY);
    }

    private WorkTimeDto getWorkTimeDto(WorkTime workTime) {
        WorkTimeDto workTimeDto = new WorkTimeDto();
        workTimeDto.setStartBooking(workTime.getStartBooking());
        workTimeDto.setEndBooking(workTime.getEndBooking());
        return workTimeDto;
    }

    private int getDeltaTimeInMinute(LocalDateTime first, LocalDateTime second) {
        return (second.getHour() - first.getHour()) * 60 + (second.getMinute() - first.getMinute());
    }

    /**
     * This method connect work with skill and find worker whith must do this work
     * @param workersSkills - skills object
     * @param worksType - works object
     * @param workId - work id
     * @return - worker id
     */
    private Long searchWorkerToWork(List<WorkersSkill> workersSkills, List<WorkType> worksType, Long workId) {
        Long skillId = DEFAULT_WORKER_ID;
        for(WorkType wt : worksType){
            if(wt.getWorkId().equals(workId)){
                skillId = wt.getSkill().getSkillId();
            }
        }

        for (WorkersSkill w : workersSkills){
            if(w.getSkill().getSkillId().equals(skillId)){
                return w.getWorkerId().getId();
            }
        }
        return DEFAULT_WORKER_ID;
    }

    private LocalDateTime parseDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }
}

