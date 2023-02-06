public class PCB {
    private static int currentTime = 0;

    private String processId;

    private int runningTime;
    private int executionTime;
    private int waitingTime;
    private int deadline;

    private Status status;

    public int getRunningTime() {
        return runningTime;
    }

    PCB(String processId, int runningTime, int deadline) {
        this.runningTime = runningTime;
        this.deadline = deadline;
        this.processId = processId;
    }

    public boolean isTerminated() {
        return (executionTime == runningTime);
    }

    public void incrementExecutionTime() {
        executionTime++;

    }

    public void incrementCurrentTime() {
        currentTime++;
    }



    public void setStatus(Status status) {
        this.status = status;
    }

    public void incrementWaitingTime() {
        waitingTime++;
    }

    public int getPriority() {
        return this.deadline - currentTime;
    }

    public Status getStatus() {
        return status;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }



    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(processId);
        sb.append("(");
        sb.append(status);
        sb.append("), ");
        sb.append(executionTime);
        sb.append(" / ");
        sb.append(runningTime);
        sb.append("sec , waiting ");
        sb.append(waitingTime);
        if (status != Status.TERMINATED) {
            sb.append(" , remaining Time ");
            sb.append(getPriority());
            sb.append(" sec");
        }
        return sb.toString();
    }

}
