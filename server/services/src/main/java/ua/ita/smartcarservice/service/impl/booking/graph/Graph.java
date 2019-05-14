package ua.ita.smartcarservice.service.impl.booking.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("Graph")
public class Graph{

    @Autowired
    private WorkDependencyService workDependencyService;

    private long[] masks;
    private ArrayList[] graph;

    public ArrayList[] getGraph() {
        if(graph == null){
            graph = createGraph();
        }
        return graph;
    }

    public long[] getMasks() {
        return masks;
    }

    private ArrayList[] createGraph(){

        int graphSize = workDependencyService.findGraphSize();

        Map<String, WorkType> requiredTime = workDependencyService.getDistinctSkillByName();

        List<WorkDependency> graphNode = workDependencyService.findAll();

        graph = new ArrayList[graphSize + 1];
        for(int i = 0; i<graph.length;i++){
            graph[i] = new ArrayList();
        }
        masks = new long[graphSize + 1];

        graphNode.forEach(node ->{
            graph[(int)node.getMainWork().getWorkId().longValue()]
                    .add(new Node(node.getDependentWork().getWorkId(),
                            requiredTime.get(node.getDependentWork().getName()).getRequiredTime()));

            graph[(int)node.getDependentWork().getWorkId().longValue()]
                    .add(new Node(node.getMainWork().getWorkId(),
                            requiredTime.get(node.getMainWork().getName()).getRequiredTime()));

            masks[(int)node.getMainWork().getWorkId().longValue()]|=(int)node.getDependentWork().getWorkId().longValue();
        });

        return graph;
    }
}
