package gr.christmas;

/**
 * Project 02 - Maximum Sum Subarray
 * (out of a total of 5 Projects)
 *
 * This Java program solves the Maximum Sum Subarray problem using Kadane's Algorithm, which is an optimal
 * linear time solution (O(n)) to find the maximum sum of a contiguous subarray.
 *
 * The input to the program is an array of integers, and the goal is to find the contiguous subarray
 * with the largest sum. This is accomplished by iterating through the array while maintaining a "local maximum"
 * and a "global maximum" at each step. The "local maximum" represents the largest sum of a subarray that ends
 * at the current element, while the "global maximum" keeps track of the overall largest sum found so far.
 *
 * Time Complexity: O(n), where n is the size of the input array.
 * The program processes each element of the array exactly once, making it highly efficient.
 *
 * Example:
 * For the input array {-2, 1, -3, 4, -1, 2, 1, -5, 4},
 * the maximum sum subarray is {4, -1, 2, 1}, with a sum of 6.
 *
 * Author: [Vaggelis Theodorakis]
 */
public class Project02 {

    public static void main(String[] args) {
        // Example input array, hard coded
        int[] arrayMain = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // Initialization of local and global maximum values
        int localMax = arrayMain[0];
        int globalMax = arrayMain[0];

        // Iterate through the array starting from the second element
        for (int i = 1; i < arrayMain.length; i++) {
            // Update local maximum: either the current element alone or the current element + previous local max
            localMax = Math.max(arrayMain[i], localMax + arrayMain[i]);

            // Update global maximum: the larger of the previous global max and the current local max
            globalMax = Math.max(globalMax, localMax);
        }

        System.out.println("The maximum sum of a contiguous subarray is: " + globalMax);
    }
}
