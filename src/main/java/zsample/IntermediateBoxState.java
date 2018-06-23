package zsample;

public class IntermediateBoxState {
    private int taskCount = 0;

    public IntermediateBoxState addTaskCount() {
        taskCount++;
        return this;
    }

    public String getTaskCount() {
        return "State_task_count = " + String.valueOf(taskCount);
    }
}
