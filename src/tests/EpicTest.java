package tests;

import main.Epic;
import main.Subtask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {

    @Test
    public void testEpicCannotBeAddedToItself() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        epic.setId(1);

        assertThrows(IllegalArgumentException.class, () -> {
            epic.addSubtask(new Subtask("Подзадача 1", "Описание 1", epic));
        }, "Ошибка! Эпик добавил сам себя");
    }
}