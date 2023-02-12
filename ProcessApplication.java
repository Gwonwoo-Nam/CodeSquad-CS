import java.util.ArrayList;
import java.util.List;

public class ProcessApplication {

    public static List<Process> initProcessList() {
        List<Process> processList = new ArrayList<>();

        processList.add(new Process("A",3,10));
        processList.add(new Process("B",4, 15));
        processList.add(new Process("C",5, 15));
        processList.add(new Process("D",6, 20));
        processList.add(new Process("E",7, 20));
        processList.add(new Process("F",8,25));

        return processList;
    }

    public static void main(String[] args) {
        List<Process> processList = initProcessList();

        CpuScheduler cpuScheduler = new CpuScheduler(ThreadOption.MULTI_THREAD);
        cpuScheduler.run(processList);
    }
}
