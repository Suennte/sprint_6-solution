package tests;

import main.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskEqualsById() {
        Task task1 = new Task("Задача 1", "Описание 1");
        task1.setId(1);

        Task task2 = new Task("Задача 2", "Описание 2");
        task2.setId(1);

        Assertions.assertTrue(task1.equals(task2), "Задачи равны");
    }

    @Test
    public void testTaskNotEqualsById() {
        Task task1 = new Task("Задача 1", "Описание 1");
        task1.setId(1);

        Task task2 = new Task("Задача 2", "Описание 2");
        task2.setId(2);

        Assertions.assertFalse(task1.equals(task2), "Задачи не равны");
    }
}