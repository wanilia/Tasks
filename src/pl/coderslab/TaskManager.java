package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        Path file = Paths.get("tasks.csv");

        /**
         * ODCZYT LICZBY ZADAŃ UMIESZCZONYCH W PLIKU
         */

        int tasksCounter = 0;

        try {
            for (String line : Files.readAllLines(file)) {
                tasksCounter += 1;
            }
        } catch (
                IOException e) {
            System.out.println("Brak pliku!");
            e.printStackTrace();
        }

        /**
         * WYPEŁNIENIE TABLICY DANYMI Z PLIKU
         */

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


        } catch (
                IOException e) {
            System.out.println("Brak pliku!");
        }


        printMenu(tasksCounter, tasks);


        surway(tasksCounter, tasks);

    }

    private static void surway(int tasksCounter, String[][] tasks) {
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
                exit();
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


        System.out.print("\n" + ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);

        for (int i = 0; i < 4; i++) {
            System.out.println(commands[i]);
        }
        System.out.println();

        surway(tasksCounter, tasks);
    }

    private static void addTask(int tasksCounter, String[][] tasks) {
        System.out.println("Sekcja jeszcze nieobsługiwana");

        /*
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        System.out.println("Please add task description");
        Scanner description = new Scanner(System.in);
        String taskDescription = description.next();

        tasks[tasks.length - 1][0] = taskDescription;

        System.out.println(Arrays.toString(tasks[2]));






        System.out.println("przed");

        System.out.println("po");
        for (int i = 0; i < tasksCounter; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tasks[i][j]);
                System.out.print("  ");
            }
            System.out.println();


//////////////////////////////////////////////////

            System.out.println("Podaj liczbę całkowitą:");
            Scanner scan = new Scanner(System.in);
            try {
                int number = scan.nextInt();
                System.out.println(number);
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawne dane");
            }

            System.out.println("Is your task important? "
                    + ConsoleColors.NEUTRAL_UNDERLINED + "t" + ConsoleColors.RESET + "rue/"
                    + ConsoleColors.NEUTRAL_UNDERLINED + "f" + ConsoleColors.RESET + "alse");

            Scanner scan2 = new Scanner(System.in);
            try {
                boolean number = scan2.nextBoolean();
                System.out.println(number);
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawne dane");
            }

            printMenu(tasksCounter, tasks);
        }
        */
    }

    private static void removeTask(int tasksCounter, String[][] tasks) {
        System.out.println("Opcja \"remove\" nie jest jeszcze obsługiwana");

        System.out.print("\n\n");
        printMenu(tasksCounter, tasks);
    }

    private static void listTasks(int tasksCounter, String[][] tasks) {


        for (int i = 0; i < tasksCounter; i++) {
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

    private static void exit() {
        System.out.println(ConsoleColors.RED + "Bye, bye.");
        System.exit(0);
    }
}