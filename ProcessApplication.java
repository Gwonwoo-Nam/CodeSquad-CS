import java.util.ArrayList;
import java.util.List;

public class ProcessApplication {

    public static List<Process> initProcessList() {
        List<Process> processList = new ArrayList<>();

        processList.add(new Process("A",3,10));
        processList.add(new Process("B",5, 15));
        processList.add(new Process("C",7, 20));
        processList.add(new Process("D",10, 40));
        processList.add(new Process("E",15, 70));
        processList.add(new Process("F",21,80));

        return processList;
    }

    public static void main(String[] args) {
        List<Process> processList = initProcessList();

        CpuScheduler cpuScheduler = new CpuScheduler(ThreadOption.MULTI_THREAD);
        cpuScheduler.run(processList);
    }
}
