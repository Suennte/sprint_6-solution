package tests;

import main.InMemoryTaskManager;
import main.Task;
import main.Epic;
import main.Subtask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    @Test
    public void testAddAndFindTasks() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Task task = new Task("Задача 1", "Описание 1");
        Epic epic = new Epic("Эпик 1", "Описание 1");
        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", epic);

        taskManager.createTask(task);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask);

        assertTrue(taskManager.getTaskById(task.getId()) != null, "Задача должна быть найдена по id");
        assertTrue(taskManager.getTaskById(epic.getId()) != null, "Эпик должен быть найден по id");
        assertTrue(taskManager.getTaskById(subtask.getId()) != null, "Подзадача должна быть найдена по id");
    }

    @Test
    public void testGeneratedIdDoesNotConflict() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("Задача 1", "Описание 1");
        task1.setId(1);

        Task task2 = new Task("Задача 2", "Описание 2");
        task2.setId(2);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        assertTrue(task1.getId() != task2.getId(), "Сгенерированные id не должны конфликтовать");
    }

    @Test
    public void testTaskUnchangedAfterAdding() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Task originalTask = new Task("Задача 1", "Описание 1");
        taskManager.createTask(originalTask);

        Task retrievedTask = taskManager.getTaskById(originalTask.getId());

        Assertions.assertEquals(originalTask.getName(), retrievedTask.getName(), "Имя задачи должно остаться неизменным");
        Assertions.assertEquals(originalTask.getDescription(), retrievedTask.getDescription(), "Описание задачи должно остаться неизменным");
        Assertions.assertEquals(originalTask.getStatus(), retrievedTask.getStatus(), "Статус задачи должно остаться неизменным");
    }

    @Test
    public void testTaskModificationInHistory() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Задача 1", "Описание 1");
        taskManager.createTask(task);

        taskManager.getTaskById(task.getId());

        task.setName("Описание 2");
        taskManager.updateTask(task);
        
        List<Task> history = taskManager.getHistoryManager().getHistory();
        assertEquals(1, history.size(), "История должна содержать одну задачу");
    }

}