import java.util.ArrayList;
import java.util.List;

public class Process extends Thread implements Comparable<Process> {
    private PCB pcb;
    private List<Thread> threads = new ArrayList<>();


    Process(String processId, int runningTime, int deadline) {
        pcb = new PCB(processId, runningTime, deadline);

        initThreads();
    }

    private void initThreads() {
        int threadNumbers = pcb.getRunningTime() / 2;
        for (int i = 0; i<threadNumbers;i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    executeThread();
                }
            }));
        }

        System.out.println(pcb.toString() + "- 스레드 " + threadNumbers + "개");
    }

    public void startThreads() {
        pcb.incrementCurrentTime();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.getMessage();
        }
        threads.stream().forEach(Thread::run);

    }

    public void execute() {
        setStatus(Status.RUNNING);
        pcb.incrementExecutionTime();
        pcb.incrementCurrentTime();
    }

    public void executeThread() {
        setStatus(Status.RUNNING);
        if (pcb.getRunningTime() > pcb.getExecutionTime()) {
            pcb.incrementExecutionTime();
        }
        if (pcb.getRunningTime() > pcb.getExecutionTime()) {
            pcb.incrementExecutionTime();
        }

    }

    public void incrementWaitingTime() {
        pcb.incrementWaitingTime();
    }

    public void terminateOrWait() {
        if (pcb.isTerminated()) {
            pcb.setStatus(Status.TERMINATED);
            return;
        }
        pcb.setStatus(Status.WAITING);
    }

    public void setStatus(Status status) {
        pcb.setStatus(status);
    }

    public boolean isTerminated() {
        return pcb.isTerminated();
    }

    public boolean isWaiting() {
        return pcb.getStatus() == Status.WAITING;
    }

    public int getWaitingTime() {
        return pcb.getWaitingTime();
    }

    public int getReturnTime() {
        return pcb.getExecutionTime() + pcb.getWaitingTime();
    }



    /**
     * deadline이 적게 남은 순서 정렬
     */
    @Override
    public int compareTo(Process target) {
        return (pcb.getPriority() >= target.pcb.getPriority()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return pcb.toString();
    }
}
