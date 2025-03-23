package main;

import java.util.*;
public class Epic extends Task {
    private List<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask);
    }

    @Override
    public String toString() {
        return "Эпик{" +
                "id=" + getId() +
                ", имя='" + getName() + '\'' +
                ", описание='" + getDescription() + '\'' +
                ", статус=" + getStatus() +
                ", подзадачи=" + subtasks +
                '}';
    }
}