package gr.christmas;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Project 05 - Theatre Management System
 * (out of a total of 5 Projects)
 *
 * This Java program simulates a simple theatre management system where users can:
 * 1. Book a seat.
 * 2. Cancel an already booked seat.
 * 3. View the seating arrangement after every action.
 * 4. Exit the program when done.
 *
 * Features:
 * - The theatre consists of 12 columns (A to L) and 30 rows, represented as a 2D boolean array.
 * - Users are prompted to select a column (A-L) and a row (1-30) for booking or cancellation.
 * - Input validation ensures users select valid rows and columns.
 * - The program keeps track of the seating arrangement and prints the theatre after each operation.
 * - The seats' statuses are displayed as 'X' for booked and 'O' for available.
 *
 * Assumptions:
 * - The theatre map is initialized with all seats available (false).
 * - Users are allowed to perform multiple operations until they choose to exit.
 * - Invalid seat positions or double booking/cancellation attempts are appropriately handled.
 *
 * Author: [Vaggelis Theodorakis]
 */
public class Project05 {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    boolean[][] theatreMap = new boolean[12][30];
    int userChoice = 0;
    char userColumn = 'A';
    int userRow = 1;

//    for (int i = 0; i < 12; i++) {
//        for (int j = 0; j < 30; j++) {
//            theatreMap[i][j] = false;
//            }
//        }

    System.out.println("*".repeat(20));
    System.out.println("Theatre Management App");
    System.out.println("*".repeat(20));


    while (userChoice != 3) {
        System.out.println("Please choose an option");
        System.out.println("1. Book a seat");
        System.out.println("2. Cancel an already booked seat");
        System.out.println("3. Exit the program");
        userChoice = getValidInt(1, 3);

        switch (userChoice) {
            case 1:
                System.out.println("Specify the column you would like to book");
                userColumn = getValidChar('A', 'L');
                System.out.println("Specify the row you would ike to book");
                userRow = getValidInt(1, 30);
                book(userColumn, userRow, theatreMap);
                printTheatre(theatreMap);
                break;
            case 2:
                System.out.println("Specify the column you would like to cancel");
                userColumn = getValidChar('A', 'L');
                System.out.println("Specify the row you would like to cancel");
                userRow = getValidInt(1, 30);
                cancel(userColumn, userRow, theatreMap);
                printTheatre(theatreMap);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid Choice");
                break;

        }
        }
        System.out.println("Thank you for using the Theatre Management App");
    }

    public static int getValidInt(int low, int high) {
        int userChoice = 0;
        while (true) {
            try {
                System.out.printf("Please select an option from %d to %d\n", low, high);
                userChoice = sc.nextInt();
                if (userChoice < low || userChoice > high) {
                    System.out.printf("Please select an option from %d to %d\n", low, high);
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please provide a valid integer");
                sc.nextLine();
            }
        }
        return userChoice;
    }
    //Didn't hard code "A" to "L", in case that columns are removed/added in future theatre renovation etc
    public static char getValidChar(char low, char high) {
        char userChoice = ' ';
        while (true) {
            try {
                System.out.printf("Please select an option from %s to %s\n", low, high);
                userChoice = sc.next().charAt(0);
                if (userChoice < low || userChoice > high) {
                    System.out.printf("Please select an option from %s to %s\n", low, high);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please provide a valid character.");
                sc.nextLine();
            }
        }
        return userChoice;
    }

    public static void book(char column, int row, boolean[][] theatreMap) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;
        if (!theatreMap[colIndex][rowIndex]) {
            theatreMap[colIndex][rowIndex] = true;
            System.out.printf("Seat %s%d was successfully booked\n", column, row);
        } else {
            System.out.printf("Seat %s%d is already booked\n", column, row);
        }
    }

    public static void cancel(char column, int row, boolean[][] theatreMap) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;
        if (theatreMap[colIndex][rowIndex]) {
            theatreMap[colIndex][rowIndex] = false;
            System.out.printf("Reservation for seat %s%d was successfully cancelled\n", column, row);
        } else {
            System.out.printf("Seat %s%d is not booked\n", column, row);
        }
    }

    // Displays the current seating arrangement
    public static void printTheatre(boolean[][] theatreMap) {
        System.out.println("   A B C D E F G H I J K L");
        for (int i = 0; i < theatreMap[0].length; i++) {
            System.out.printf("%2d ", i + 1);
            for (boolean[] booleans : theatreMap) {
                System.out.print(booleans[i] ? "X " : "O ");
            }
            System.out.println();
        }
    }
}
