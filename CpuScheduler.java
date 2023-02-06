import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class CpuScheduler {

    private PriorityQueue<Process> readyQueue = new PriorityQueue<>(); //준비 Queue
    private PriorityQueue<Process> waitingQueue = new PriorityQueue<>(); //입출력 대기 Queue

    private List<Process> executionProcessList = new ArrayList<>();

    private Process recentExecution;

    public void run(List<Process> processList) {
        executionProcessList = initRandomProcess(processList);
        queueReady();
        display();

        while (true) {
            // waiting Queue에 모든 프로세스를 줄세운다.
            queueWaiting();

            // 모든 프로세스가 실행 완료되었는지 검사한다.
            if (waitingQueue.isEmpty()) {
                break;
            }

            //우선 순위 대기의 첫 번째 원소를 뽑는다.
            Process waitingProcess = waitingQueue.poll();
            //최근에 실행한 프로세스인데, 대기 큐에 다른 프로세스가 있을 때, 다른 프로세스를 먼저 실행
            if (!waitingQueue.isEmpty() && waitingProcess.equals(recentExecution)) {
                waitingProcess = waitingQueue.poll();
                //다시 집어넣기
                waitingQueue.add(recentExecution);
            }
            //준비 큐에 집어넣기
            readyQueue.add(waitingProcess);
            Process currentProcess = readyQueue.poll();

            //프로세스를 실행한다.
            currentProcess.execute();
            //나머지 프로세스의 대기시간 증가
            doWait();
            //프로세스 상태를 출력한다.
            display();

            currentProcess.terminateOrWait();

            //실행된 프로세스가 종료되지 않았으면 다시 대기 큐에 추가한다.
            if (!currentProcess.isTerminated()) {
                waitingQueue.add(currentProcess);
            }
            // 최근 실행 프로세스를 기억한다.
            recentExecution = currentProcess;
        }
        display();
        showStatistics();

    }

    public List<Process> initRandomProcess(List<Process> processList) {
        List<Process> executionProcessList = new ArrayList<>();
        Random random = new Random();
        //3개의 랜덤 프로세스 대기 큐에 등록
        while (executionProcessList.size() < 3) {
            Process randomProcess = processList.get(random.nextInt(6));
            if (!executionProcessList.contains(randomProcess)) {
                randomProcess.setStatus(Status.WAITING);
                waitingQueue.add(randomProcess);
                executionProcessList.add(randomProcess);
            }
        }
        return executionProcessList;
    }

    private void showStatistics() {
        StringBuffer sb = new StringBuffer();

        int[] waitingTimes = executionProcessList.stream()
                .mapToInt(p->p.getWaitingTime()).toArray();
        int[] executionTimes = executionProcessList.stream()
                .mapToInt(p->p.getReturnTime()).toArray();
        int totalWaitingTime = Arrays.stream(waitingTimes).reduce((a,b) -> a+b).getAsInt();
        int totalExecutionTime = Arrays.stream(executionTimes).reduce((a,b) -> a+b).getAsInt();

        sb.append("기한부 스케줄링 (deadline scheduling)이 종료되었습니다.\n");
        sb.append("평균 대기시간 = (");
        sb.append(executionProcessList.stream()
                .map(p->Integer.toString(p.getWaitingTime()))
                .reduce((str1, str2) -> str1 + " + " + str2).get());
        sb.append(") / ");
        sb.append(executionProcessList.size());
        sb.append(" = ");
        sb.append(String.format("%.2f",totalWaitingTime / (double)executionProcessList.size()));
        sb.append("sec\n");

        sb.append("평균 반환시간 = (");
        sb.append(executionProcessList.stream()
                .map(p->Integer.toString(p.getReturnTime()))
                .reduce((str1, str2) -> str1 + " + " + str2).get());
        sb.append(") / ");
        sb.append(executionProcessList.size());
        sb.append(" = ");
        sb.append(String.format("%.2f",totalExecutionTime / (double)executionProcessList.size()));
        sb.append("sec\n\n---");

        System.out.println(sb);
    }

    private void doWait() {
        for (Process p : executionProcessList) {
            if (p.isWaiting()) {
                p.incrementWaitingTime();
            }
        }
    }

    private void display() {
        for (Process p : executionProcessList) {
            System.out.println(p.toString());
        }
        System.out.println(".");
    }



    /**
     * 누적 동작 시간이 최대 동작 시간보다 작은 경우,
     * 대기 상태를 준비 상태로 변경한다.
     * 대기 상태는 입출력 대기나 다른 응답을 위해서 사용하지만,
     * 입출력을 다루지 않기 때문에 준비 상태로 바로 바꾸지 않고 대기 ⟹ 준비로 변경한다.
     */
    public void queueReady() {
        while (!waitingQueue.isEmpty()) {
            Process process = waitingQueue.poll();
            process.setStatus(Status.READY);
            readyQueue.add(process);
        }
    }

    public void queueWaiting() {
        while (!readyQueue.isEmpty()) {
            Process process = readyQueue.poll();
            process.setStatus(Status.WAITING);
            waitingQueue.add(process);
        }
    }

}
