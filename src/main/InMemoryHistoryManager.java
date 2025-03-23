package main;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        remove(task.getId());
        history.add(task); //
        if (history.size() > 10) { //
            history.remove(0);
        }
    }

    public void remove(int id) {
        history.removeIf(task -> task.getId() == id);
    }

    @Override
    public List<Task> getHistory() {
        Set<Task> uniqueList = new LinkedHashSet<>(history);
        return new ArrayList<>(uniqueList);
    }

    @Override
    public boolean contains(Task task) {
        return history.stream().anyMatch(t -> t.getId() == task.getId());
    }
}