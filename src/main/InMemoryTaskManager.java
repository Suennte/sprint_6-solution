package main;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager;
    private int nextId = 1;

    public InMemoryTaskManager() {
        this.historyManager = Managers.getDefaultHistory(); // Инициализация historyManager
    }

    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    @Override
    public void createTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
        Epic epic = subtask.getEpic();
        epic.addSubtask(subtask);
        updateEpicStatus(epic);
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>(tasks.values());
        allTasks.addAll(epics.values());
        allTasks.addAll(subtasks.values());
        return allTasks;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = null;
        if (tasks.containsKey(id)) {
            task = tasks.get(id);
        } else if (epics.containsKey(id)) {
            task = epics.get(id);
        } else if (subtasks.containsKey(id)) {
            task = subtasks.get(id);
        }

        if (task != null) {
            historyManager.add(task);
        }

        return task;
    }

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        } else if (epics.containsKey(task.getId())) {
            epics.put(task.getId(), (Epic) task);
        } else if (subtasks.containsKey(task.getId())) {
            subtasks.put(task.getId(), (Subtask) task);
        }
    }

    @Override
    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {
            epics.remove(id);
        } else if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.remove(id);
            Epic epic = subtask.getEpic();
            epic.removeSubtask(subtask);
            updateEpicStatus(epic);
        }
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        List<Subtask> subtasks = epic.getSubtasks();
        for (Subtask subtask : subtasks) {
            if (subtask.getId() > 1) {
                subtask.setStatus(Status.IN_PROGRESS);
            }
            if (subtask.getStatus() == Status.IN_PROGRESS && (subtask.getDescription().isEmpty()) || subtask.getStatus() == Status.NEW && (subtask.getDescription().isEmpty())) {
                subtask.setStatus(Status.DONE);
            }
        }
    }
}
