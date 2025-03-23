package tests;

import main.Managers;
import main.HistoryManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagersTest {

    @Test
    public void testGetDefaultHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Assertions.assertNotNull(historyManager, "Метод проинициализирован");
    }
}