package tests;

import main.Epic;
import main.Subtask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubtaskTest {

    @Test
    public void testSubtaskEqualsById() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        epic.setId(1);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", epic);
        subtask1.setId(2);

        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", epic);
        subtask2.setId(2);

        Assertions.assertTrue(subtask1.equals(subtask2), "Подзадачи равны");
    }

    @Test
    public void testSubtaskNotEqualsById() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        epic.setId(1);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", epic);
        subtask1.setId(2);

        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", epic);
        subtask2.setId(3);

        Assertions.assertFalse(subtask1.equals(subtask2), "Подзадачи не равны");
    }

    @Test
    public void testSubtaskCannotBeItsOwnEpic() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        epic.setId(1);

        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", epic);
        subtask.setId(2);

        assertThrows(IllegalArgumentException.class, () -> {
            subtask.setEpic(epic);
        }, "Ошибка! Подзадачу нельзя сделать своим же эпиком");
    }
}