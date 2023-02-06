import org.testng.annotations.Test;

import java.util.List;

public class CpuSchedulerTest {


    @Test
    public void 작동_테스트() {
        List<Process> processList = ProcessApplication.initProcessList();
        CpuScheduler cpuScheduler = new CpuScheduler();
        cpuScheduler.run(processList);
    }

    @Test
    public void 멀티스레드_작동_테스트() {
        List<Process> processList = ProcessApplication.initProcessList();
        ThreadScheduler threadScheduler = new ThreadScheduler();
        threadScheduler.run(processList);
    }

}
