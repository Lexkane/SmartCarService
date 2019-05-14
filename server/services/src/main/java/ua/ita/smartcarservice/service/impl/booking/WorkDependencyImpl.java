package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.WorkInfoDto;
import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.booking.WorkDependencyRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;
import ua.ita.smartcarservice.service.impl.booking.graph.Node;
import ua.ita.smartcarservice.service.impl.booking.graph.Graph;
import ua.ita.smartcarservice.service.technicalservice.WorkTypeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkDependencyImpl implements WorkDependencyService {
    private final int NUMBER_OF_WORKS = 60;

    @Autowired
    private WorkDependencyRepository workDependencyRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private Graph graph;

    public List<WorkDependency> findAll() {
        return workDependencyRepository.findAll();
    }

    @Override
    public int findGraphSize() {
        return (int) workTypeRepository.findMaxId().longValue();
    }

    /**
     * Method that uses graph to find required time and workers schedule
     *
     * @param worksName list of selected works
     * @return - required time and workers schedule
     */
    public WorkInfo findWorkInfo(List<String> worksName) {

        WorkInfo workInfo = new WorkInfo();

        ArrayList[] graph = this.graph.getGraph();

        // get all node in sorted position
        PriorityQueue<Node> allNode = getAllEdgeInSortedPosition(worksName);

        Set<Long> needNode = getNeedNode(worksName);

        long masks[] = this.graph.getMasks();

        // remember which node were used
        boolean was[] = new boolean[graph.length];

        int requiredTime = 0;

        // go through node and take node with the biggest time value
        while (!allNode.isEmpty()) {
            long mask = 0;
            Node mainNode = allNode.poll();
            // check was this node checked
            if (was[(int) mainNode.getIndex()]) {
                continue;
            }
            // remembers, what we check this node
            was[(int) mainNode.getIndex()] = true;
            // set work info in list
            workInfo.setWorkInfo(new WorkInfoDto(mainNode.getIndex(), requiredTime, (int)(requiredTime + mainNode.getRequiredTime())));
            // increase required time
            requiredTime += mainNode.getRequiredTime();
            mask |= (1 << (mainNode.getIndex()));
            // go through nodes, which is connect with our main node
            for (int i = graph[(int) mainNode.getIndex()].size() - 1; i >= 0; i--) {
                Node dependentNode = (Node) graph[(int) mainNode.getIndex()].get(i);
                // check was this node checked
                if (was[(int) dependentNode.getIndex()] || !needNode.contains(dependentNode.getIndex())) {
                    continue;
                }
                boolean canAddToRes = true;
                // check is dependent node connect with all nodes selected earlier
                for (int j = 1; j <= NUMBER_OF_WORKS; j++) {
                    if ((mask & (1 << j)) == 1 && (masks[(int) dependentNode.getIndex()] & (1 << j)) != 1) {
                        canAddToRes = false;
                        break;
                    }
                }
                // select if can
                if (canAddToRes) {
                    was[(int) dependentNode.getIndex()] = true;
                    workInfo.setWorkInfo(new WorkInfoDto(dependentNode.getIndex(),
                            (int) (requiredTime - mainNode.getRequiredTime()),
                            (int) (requiredTime - mainNode.getRequiredTime() + dependentNode.getRequiredTime())));
                    mask |= (1 << dependentNode.getIndex());
                }
            }
        }
        workInfo.setRequiredTime(requiredTime);
        return workInfo;
    }


    private PriorityQueue<Node> getAllEdgeInSortedPosition(List<String> skillsName) {
        Map<String, WorkType> workByName = getDistinctSkillByName();

        PriorityQueue<Node> allNode = new PriorityQueue <>(new Node(-1, -1));

        skillsName.forEach(s -> allNode.add(new Node(workByName.get(s).getWorkId(), workByName.get(s).getRequiredTime())));

        return allNode;
    }


    public Map<String, WorkType> getDistinctSkillByName() {
        return workTypeService.findDistinctWorkByName();
    }

    private Set<Long> getNeedNode(List <String> skillsName) {
        Map<String, WorkType> skillsByName = getDistinctSkillByName();

        return skillsName.stream().map(s -> skillsByName.get(s).getWorkId()).collect(Collectors.toSet());
    }
}
