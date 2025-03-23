package main;

import java.util.List;

public interface TaskManager {
    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    List<Task> getAllTasks();

    Task getTaskById(int id);

    void updateTask(Task task);

    void deleteTaskById(int id);

    void deleteAllTasks();

    void updateEpicStatus(Epic epic);

    HistoryManager getHistoryManager();
}
