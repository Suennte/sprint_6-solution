package main;

import java.util.*;

public class Main {
    static Scanner scanner;
    private static TaskManager taskManager = new InMemoryTaskManager();
    private static HistoryManager historyManager = taskManager.getHistoryManager();

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Что вы хотите сделать?");
            System.out.println("1 - Новая задача");
            System.out.println("2 - Список задач");
            System.out.println("3 - Поиск задачи по индекатору");
            System.out.println("4 - Удаление всех задач");
            System.out.println("5 - Обновление задачи");
            System.out.println("6 - Удаление задачи по индекатору");
            System.out.println("7 - Список задач по статусу");
            System.out.println("8 - История задач");
            System.out.println("0 - Выход");
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    createTask();
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    findTaskById();
                    break;
                case 4:
                    taskManager.deleteAllTasks();
                    break;
                case 5:
                    updateTask();
                    break;
                case 6:
                    deleteTaskById();
                    break;
                case 7:
                    listTasksByStatus();
                    break;
                case 8:
                    showHistory();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверное число!");
            }
        }
    }

    private static void createTask() {
        System.out.println("Введите тип задачи (1 - Task, 2 - Epic, 3 - Subtask):");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите название задачи:");
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();

        switch (type) {
            case 1:
                taskManager.createTask(new Task(taskName, description));
                break;
            case 2:
                taskManager.createEpic(new Epic(taskName, description));
                break;
            case 3:
                System.out.println("Введите id эпика:");
                int epicId = scanner.nextInt();
                scanner.nextLine();
                Task task = taskManager.getTaskById(epicId);
                if (task instanceof Epic) {
                    Epic epic = (Epic) task;
                    taskManager.createSubtask(new Subtask(taskName, description, epic));
                } else {
                    System.out.println("Ошибка: Задача с id " + epicId + " не является эпиком.");
                }
                break;
            default:
                System.out.println("Неверный тип задачи.");
        }
    }

    private static void listTasks() {
        System.out.println("Список задач:");
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }
    }

    private static void findTaskById() {
        System.out.println("Введите индекатор задачи:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Task task = taskManager.getTaskById(id);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("Задача с таким id не найдена.");
        }
    }

    private static void updateTask() {
        System.out.println("Введите индекатор задачи:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Task task = taskManager.getTaskById(id);
        if (task != null) {
            System.out.println("Введите новое название задачи:");
            String newName = scanner.nextLine();
            System.out.println("Введите новое описание задачи:");
            String newDescription = scanner.nextLine();
            task.setName(newName);
            task.setDescription(newDescription);
            taskManager.updateTask(task);
        } else {
            System.out.println("Задача с таким id не найдена.");
        }
    }

    private static void deleteTaskById() {
        System.out.println("Введите индекатор задачи:");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskManager.deleteTaskById(id);
    }

    private static void listTasksByStatus() {
        System.out.println("Введите статус задачи (1 - NEW, 2 - IN_PROGRESS, 3 - DONE):");
        int status = scanner.nextInt();
        scanner.nextLine();
        Status taskStatus = Status.values()[status - 1];
        for (Task task : taskManager.getAllTasks()) {
            if (task.getStatus() == taskStatus) {
                System.out.println(task);
            }
        }
    }

    private static void showHistory() {
        System.out.println("История просмотров:");
        List<Task> history = historyManager.getHistory();
        if (history.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            for (Task task : history) {
                System.out.println(task);
            }
        }
    }
}