    package main;

    public class Managers {
        public static HistoryManager getDefaultHistory() {
            return new InMemoryHistoryManager(); // Возвращаем объект InMemoryHistoryManager
        }
    }