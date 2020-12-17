package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        System.out.println();
        Path file = Paths.get("tasks.csv");

        int tasksCounter = tasksCounter(file); // Odczyt liczby zadań w pliku

        String[][] tasks = tableCreator(file, tasksCounter); // Wypełnienie tablicy danymi z pliku


        printMenu(tasksCounter, tasks);

        navigation(tasksCounter, tasks); // Odczyt komendy z klawiatury

    }


    private static int tasksCounter(Path file) {

        int tasksCounter = 0;

        try {
            for (String line : Files.readAllLines(file)) { // TODO Czy mogę jakoś usunąć to "line"?
                tasksCounter += 1;
            }
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Error! File does not exist.");
            System.exit(0);
        }
        return tasksCounter;
    }


    private static String[][] tableCreator(Path file, int tasksCounter) {
        String[][] tasks = new String[tasksCounter][3];
        // TODO W materiałach przed "String[][]" było jeszcze "static". Dlaczego?

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                for (int i = 0; i < tasksCounter; i++) {
                    String string = scan.nextLine();
                    String[] parts = string.split(", ");
                    tasks[i][0] = parts[0];
                    tasks[i][1] = parts[1];
                    tasks[i][2] = parts[2];
                }
            }
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Error! File does not exist.");
            System.exit(0);
        }
        return tasks;
    } // TODO Czym są inferred annotaitons?


    private static void navigation(int tasksCounter, String[][] tasks) {
        Scanner choice = new Scanner(System.in);
        String next = choice.next();

        switch (next) {
            case "a":
            case "A":
            case "add":
            case "ADD":
                addTask(tasksCounter, tasks);
                break;
            case "r":
            case "R":
            case "remove":
            case "REMOVE":
                removeTask(tasksCounter, tasks);
                break;
            case "l":
            case "L":
            case "list":
            case "LIST":
                listTasks(tasksCounter, tasks);
                break;
            case "e":
            case "E":
            case "exit":
            case "EXIT":
                System.out.println(ConsoleColors.RED + "Bye, bye.");
                System.exit(0);
                break;
            default:
                System.out.println("Please select a correct option");
        }
    }


    private static void printMenu(int tasksCounter, String[][] tasks) {
        String[] commands = new String[4];
        commands[0] = ConsoleColors.NEUTRAL_UNDERLINED + "a" + ConsoleColors.RESET + "dd";
        commands[1] = "                         " + ConsoleColors.NEUTRAL_UNDERLINED + "r" + ConsoleColors.RESET + "emove";
        commands[2] = "                         " + ConsoleColors.NEUTRAL_UNDERLINED + "l" + ConsoleColors.RESET + "ist";
        commands[3] = "                         " + ConsoleColors.NEUTRAL_UNDERLINED + "e" + ConsoleColors.RESET + "xit";


        System.out.print(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);

        for (int i = 0; i < 4; i++) {
            System.out.println(commands[i]);
        }
        System.out.println();

        navigation(tasksCounter, tasks);
    }


    private static String[][] addTask(int tasksCounter, String[][] tasks) {

        tasks = Arrays.copyOf(tasks, tasks.length + 1);

        tasks[tasks.length - 1] = new String[3];

        Scanner scan = new Scanner(System.in);

        System.out.println("Please add task description");
        tasks[tasks.length - 1][0] = scan.next();

        System.out.println("Please add task due date");
        tasks[tasks.length - 1][1] = scan.next();

        System.out.println("Is your task important? "
                + ConsoleColors.NEUTRAL_UNDERLINED + "t" + ConsoleColors.RESET + "rue/"
                + ConsoleColors.NEUTRAL_UNDERLINED + "f" + ConsoleColors.RESET + "alse");
        tasks[tasks.length - 1][2] = scan.next();


        System.out.print("\n\n");
        printMenu(tasksCounter, tasks);

        return tasks;
    }


    private static void removeTask(int tasksCounter, String[][] tasks) {
        System.out.println("Opcja \"remove\" nie jest jeszcze obsługiwana");

        System.out.print("\n\n");
        printMenu(tasksCounter, tasks);
    }


    private static void listTasks(int tasksCounter, String[][] tasks) {


        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tasks[i][j]);
                System.out.print("  ");
            }
            System.out.println();

        }
        System.out.print("\n\n");
        printMenu(tasksCounter, tasks);
    }
}