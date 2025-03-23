package tests;

import main.HistoryManager;
import main.Task;
import main.InMemoryHistoryManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    @Test
    public void testHistoryManagerPreservesTaskData() {
        HistoryManager historyManager = new InMemoryHistoryManager();

        Task task = new Task("Задача 1", "Описание 1");
        task.setId(1);

        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "История");
        Assertions.assertEquals(task, history.get(0), "Данные истории");
    }
}