package me.sivieri.aoc2024.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

// copied from https://github.com/MattHeard/k-Combinations--Java-/blob/master/KCombinations.java
@SuppressWarnings("unchecked")
public class KCombinations {

    public static <T> ArrayList<T[]> enumKCombos(
            Class<T> type,
            T[] array,
            int k
    ) {

        // Create an empty ArrayList to store all the k-combinations.
        // The k-combinations are stored as int arrays.
        ArrayList<T[]> comboList = new ArrayList<>();

        // The process of enumerating the k-combinations can be done with a
        // recursive function where each recursion is passed a shorter array and
        // a smaller value of k. The base case of k = 1, and any larger values
        // call the recursive function with a decremented value of k.

        assert(k > 0);

        if (k > 1) {

            assert(array.length >= k);

            // Store the first member of the array.
            T[] first = (T[]) Array.newInstance(type, 1);
            first[0] = array[0];
            array = Arrays.copyOfRange(array, 1, array.length);

            while (array.length + 1 >= k) {
                ArrayList<T[]> subComboList;
                // Call the recursive function and temporarily store the
                //   returned arrays.
                subComboList = enumKCombos(type, array, k - 1);

                // Concatenate the stored first member onto the front of the
                //   returned arrays.
                T[] subArray;
                for (T[] ints : subComboList) {
                    subArray = ints;
                    T[] concatenated = (T[]) Array.newInstance(type, subArray.length + 1);
                    concatenated[0] = first[0];
                    System.arraycopy(subArray, 0, concatenated, 1, subArray.length);
                    comboList.add(concatenated);
                }

                // Add the newly-concatenated arrays to the comboList.
                // Replace first with array[0].
                first[0] = array[0];

                // Splice array to remove the first member.
                array = Arrays.copyOfRange(array, 1, array.length);
            }
        } else {
            // Return the individual members of array as individual 1-member
            //   arrays.
            for (int i = 0; i < array.length; i++) {
                comboList.add(Arrays.copyOfRange(array, i, i + 1));
            }
        }

        return comboList;
    }

}
