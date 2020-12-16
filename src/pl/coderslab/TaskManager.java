package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        Path file = Paths.get("tasks.csv");

        printMenu();


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


            /** WYŚWIETLENIE LISTY ZADAŃ Z TABLICY */

            listTasks(tasksCounter, tasks);


        } catch (
                IOException e) {
            System.out.println("Brak pliku!");
        }
    }

    private static void listTasks(int tasksCounter, String[][] tasks) {
        System.out.println("list");

        for (int i = 0; i < tasksCounter; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tasks[i][j]);
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        String[] commands = new String[4];
        commands[0] = ConsoleColors.NEUTRAL_UNDERLINED + "a" + ConsoleColors.RESET + "dd";
        commands[1] = ConsoleColors.NEUTRAL_UNDERLINED + "r" + ConsoleColors.RESET + "emove";
        commands[2] = ConsoleColors.NEUTRAL_UNDERLINED + "l" + ConsoleColors.RESET + "ist";
        commands[3] = ConsoleColors.NEUTRAL_UNDERLINED + "e" + ConsoleColors.RESET + "xit";

        System.out.println(ConsoleColors.BLUE
                + "Please select an option:"
                + ConsoleColors.RESET);

        for (int i = 0; i < 4; i++) {
            System.out.println(commands[i]);
        }
        System.out.println();
    }
}