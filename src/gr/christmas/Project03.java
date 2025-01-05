/**
 * Project 03 - Character Frequency Analysis
 * (out of a total of 5 Projects)
 *
 * This Java program reads a text file character by character, counting the occurrences of each character,
 * and stores the data in a 128x2 array. The first column represents ASCII codes, and the second column stores
 * the frequency of each character. Whitespace and non-ASCII characters are ignored.
 *
 * The program outputs:
 * 1. A frequency analysis sorted alphabetically by characters.
 * 2. A frequency analysis sorted by frequency in descending order.
 * 3. The total number of unique characters found in the file.
 *
 * Input File: A text file named `Project03LoremIn.txt` located in the specified path.
 * Output File: A text file named `Project03LoremOut.txt` containing the results.
 *
 * Author: [Vaggelis Theodorakis]
 */
package gr.christmas;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Project03 {
    public static void main(String[] args) {
        try (
                Scanner in = new Scanner(new File("C:/Users/User/Documents/Java_Various_Test_Files/Project03LoremIn.txt"), StandardCharsets.UTF_8);
                PrintStream ps = new PrintStream("C:/Users/User/Documents/Java_Various_Test_Files/Project03LoremOut.txt", StandardCharsets.UTF_8)
        ) {

            int[][] arrayMain = new int[128][2];

            for (int i = 0; i < 128; i++) {
                arrayMain[i][0] = i; // Store ASCII code
                arrayMain[i][1] = 0; // Set initial frequency to 0
            }

            // Read the input file character by character
            while (in.hasNextLine()) {
                String line = in.nextLine();
                for (char c : line.toCharArray()) {
                    if (!Character.isWhitespace(c) && c < 128) { // Ignore whitespaces and non-ASCII characters
                        arrayMain[c][1]++; // Increment frequency for the character
                    }
                }
            }

            // Filter out characters with a frequency of 0
            int[][] filteredArray = Arrays.stream(arrayMain)
                    .filter(row -> row[1] > 0)
                    .toArray(int[][]::new);

            // Sort by ASCII code (alphabetically by character)
            Arrays.sort(filteredArray, (a, b) -> Integer.compare(a[0], b[0]));

            // Output sorted results by character
            ps.println("Frequency Analysis Sorted by Character:");
            System.out.println("Frequency Analysis Sorted by Character:");
            for (int[] row : filteredArray) {
                ps.printf("Character: %c, Frequency: %d%n", (char) row[0], row[1]);
                System.out.printf("Character: %c, Frequency: %d%n", (char) row[0], row[1]);
            }

            // Sort by frequency in descending order
            Arrays.sort(filteredArray, (a, b) -> Integer.compare(b[1], a[1]));

            // Output sorted results by frequency
            ps.println("\nFrequency Analysis Sorted by Frequency:");
            System.out.println("\nFrequency Analysis Sorted by Frequency:");
            for (int[] row : filteredArray) {
                ps.printf("Character: %c, Frequency: %d%n", (char) row[0], row[1]);
                System.out.printf("Character: %c, Frequency: %d%n", (char) row[0], row[1]);
            }

            // Calculate the number of unique characters
            long uniqueChars = Arrays.stream(arrayMain).filter(row -> row[1] > 0).count();

            // Output the total number of unique characters
            ps.println("\nAdditional Statistics:");
            System.out.println("\nAdditional Statistics:");
            ps.println("Total Number of Unique Characters: " + uniqueChars);
            System.out.println("Total Number of Unique Characters: " + uniqueChars);

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }
}
