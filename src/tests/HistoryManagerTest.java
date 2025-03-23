package tests;

import main.HistoryManager;
import main.Task;
import main.InMemoryHistoryManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {

    HistoryManager historyManager = new InMemoryHistoryManager();
    @Test
    public void testHistoryManagerPreservesTaskData() {

        Task task1 = new Task("Задача 1", "Описание 1");
        Task task2 = new Task("Задача 1", "Описание 1");
        task1.setId(1);
        task2.setId(1);

        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "История");
        Assertions.assertEquals(task1, history.get(0), "Данные истории");

        assertEquals(1, historyManager.getHistory().size(), "В истории должна остаться только одна задача");
    }
}