package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Effort {
    private final SimpleStringProperty taskType;
    private final SimpleDoubleProperty lastTimeSpent;
    private final SimpleDoubleProperty averageTimeSpent;

    public Effort(String taskType, double lastTimeSpent, double averageTimeSpent) {
        this.taskType = new SimpleStringProperty(taskType);
        this.lastTimeSpent = new SimpleDoubleProperty(lastTimeSpent);
        this.averageTimeSpent = new SimpleDoubleProperty(averageTimeSpent);
    }

    public String getTaskType() {
        return taskType.get();
    }

    public void setTaskType(String value) {
        taskType.set(value);
    }

    public double getLastTimeSpent() {
        return lastTimeSpent.get();
    }

    public void setLastTimeSpent(double value) {
        lastTimeSpent.set(value);
    }

    public double getAverageTimeSpent() {
        return averageTimeSpent.get();
    }

    public void setAverageTimeSpent(double value) {
        averageTimeSpent.set(value);
    }
}