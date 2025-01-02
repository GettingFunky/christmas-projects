package gr.christmas;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Project 01 out of a total of 5 Projects
 *
 * This Java program reads a list of integers (between 7 and 49 numbers with values from 1 to 49)
 * from a file and stores them in an array. After sorting the array, the program generates all possible
 * combinations of six numbers (6-tuples) from the sorted array. Then, it applies several filters to each
 * combination to ensure the following constraints are satisfied:
 *
 * 1. No more than 4 even numbers.
 * 2. No more than 4 odd numbers.
 * 3. No more than 2 consecutive numbers.
 * 4. No more than 3 numbers with the same last digit.
 * 5. No more than 3 numbers in the same tens group (i.e., same first digit when divided by 10).
 *
 * Valid combinations are written to a file in the format of six numbers per line.
 * The program is based on the requirements from the project brief and utilizes various helper methods
 * to filter the combinations.
 *
 * Input File: The input file contains integers between 1 and 49, and there are between 7 and 49 integers.
 * Output File: The output file will contain all valid combinations, formatted with six numbers per line.
 *
 * The sample .txt file used is a Project01ChristmasIn.txt file with the following content, but any array can be used:
 * 1 5 8 9 11 18 22 24 26 29 30 35 36 44 45
 * It generates a Project01ChristmasOut.txt file with a total of 2981 lines.
 *
 * Author: [Vaggelis Theodorakis]
 */
public class Project01 {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("C:/Users/User/Documents/Java_Various_Test_Files/Project01ChristmasIn.txt"));
             PrintStream ps = new PrintStream("C:/Users/User/Documents/Java_Various_Test_Files/Project01ChristmasOut.txt", StandardCharsets.UTF_8)) {

            final int GROUP_SIZE = 6;
            int[] inputNumbers = new int[49];
            int pivot = 0;
            int[] result = new int[GROUP_SIZE];
            int num;
            int window;

            while (in.hasNextInt() && pivot <= 48) {
                inputNumbers[pivot++] = in.nextInt();
            }

            int[] numbers = Arrays.copyOfRange(inputNumbers, 0, pivot);
            Arrays.sort(numbers);

            window = pivot - GROUP_SIZE;
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    result[0] = numbers[i];
                                    result[1] = numbers[j];
                                    result[2] = numbers[k];
                                    result[3] = numbers[l];
                                    result[4] = numbers[m];
                                    result[5] = numbers[n];

                                    if (!isEvenGE(result, 5) && !isOddGE(result, 5) && !consecutive(result)
                                            && !sameEnding(result, 4) && !sameTen(result, 3) ) {
                                        ps.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                        System.out.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the number of evens is greater than or
     * equal to a threshold limit.
     *
     * @param arr
     *          the input array.
     * @param threshold
     *          the upper limit of the constraint.
     * @return
     *          true, if the number of evens is greater
     *          than or equal to a threshold limit, false
     *          otherwise.
     */
    public static boolean isEvenGE(int[] arr, int threshold) {
        long evenCount = 0;

        evenCount = Arrays.stream(arr)
                .filter(num -> num % 2 == 0)
                .count();

        return evenCount >= threshold;
    }

    /**
     * Returns true if the number of odds is greater than or
     * equal to a threshold limit.
     *
     * @param arr
     *          the input array.
     * @param threshold
     *          the upper limit of the constraint.
     * @return
     *          true, if the number of odds is greater
     *          than or equal to a threshold limit, false
     *          otherwise.
     */
    public static boolean isOddGE(int[] arr, int threshold) {
        long oddsCount = 0;

        oddsCount = Arrays.stream(arr)
                .filter(num -> num % 2 != 0)
                .count();

        return oddsCount >= threshold;
    }

    /**
     * Returns true if at least three numbers are consecutive.
     *
     * @param arr
     *      the input array.
     * @return
     *      true, if at least three numbers are consecutive.
     */
    public static boolean consecutive(int[] arr) {
        int consecutiveCount = 0;

        for (int i = 0; i <= 3; i++) {
            if (arr[i] == arr[i + 1] - 1 && arr[i] == arr[i + 2] - 2) {
                consecutiveCount++;
                break;
            }
        }

        return consecutiveCount >= 1;
    }

    /**
     * Returns true if threshold or more numbers have the same ending.
     *
     * @param arr
     *      the input array.
     * @param threshold
     *      the threshold.
     * @return
     *      true, if GE (Greater or Equal) to threshold numbers
     *      have the same ending.
     */
    public static boolean sameEnding(int[] arr, int threshold) {
        int[] endings = new int[10];

        for (int num : arr) {
            endings[num % 10]++;
        }

        return Arrays.stream(endings).anyMatch(e -> e >= threshold);
    }

    /**
     * Returns true if threshold or more numbers are in the
     * same ten.
     *
     * @param arr
     *      the input array.
     * @param threshold
     *      the threshold.
     * @return
     *      true, if GE (Greater or Equal) to threshold numbers
     *      are in the same ten.
     */
    public static boolean sameTen(int[] arr, int threshold) {
        int[] ten = new int[5];

        for (int num : arr) {
            ten[num/10]++;
        }

        return Arrays.stream(ten).anyMatch(t -> t >= threshold);
    }

}
