package main;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new ArrayList<>(); // Список для хранения истории

    @Override
    public void add(Task task) {
        history.add(task); // Добавляем задачу в историю
        if (history.size() > 10) { // Если размер списка превышает 10
            history.remove(0); // Удаляем самый старый элемент
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history); // Возвращаем копию списка
    }
}