package io.github.javaasasecondlanguage.homework01.mergeintervals;

import java.util.Arrays;

public class IntervalsMerger {
    /**
     * Given array of intervals,
     * merge overlapping intervals and
     * sort them by start in ascending order
     * Interval is defined as [start, end] where start < end
     * <p>
     * Examples:
     * [[1,3][2,4][5,6]] -> [[1,4][5,6]]
     * [[1,2][2,3]] -> [[1,3]]
     * [[1,4][2,3]] -> [[1,4]]
     * [[5,6][1,2]] -> [[1,2][5,6]]
     *
     * @param intervals is a nullable array of pairs [start, end]
     * @return merged intervals
     * @throws IllegalArgumentException if intervals is null
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("intervals is null");
        }
        if (intervals.length == 0) {
            return intervals;
        }


        simpleSort(intervals);

        int[][] newIntervals = new int[intervals.length][2];

        int baseIndex = 0;
        int newBaseIndex = 0;
        int compareIndex = 1;

        while (true) {
            if (compareIndex >= intervals.length) {
                newIntervals[newBaseIndex] = intervals[baseIndex];
                newBaseIndex++;
                break;
            }

            if (intervals[baseIndex][1] >= intervals[compareIndex][1]) {
                compareIndex++;
                continue;
            }

            if (intervals[baseIndex][1] >= intervals[compareIndex][0]
                    && intervals[baseIndex][1] < intervals[compareIndex][1]) {
                intervals[baseIndex][1] = intervals[compareIndex][1];
                compareIndex++;
                continue;
            }

            if (intervals[baseIndex][1] < intervals[compareIndex][0]) {
                newIntervals[newBaseIndex] = intervals[baseIndex];
                newBaseIndex++;
                baseIndex = compareIndex;
                compareIndex++;
            }

        }
        return Arrays.copyOfRange(newIntervals, 0, newBaseIndex);

    }

    private static void simpleSort(int[][] sortArray) {
        int[] temp;
        for (int i = 1; i < sortArray.length; i++) {
            int j = i;
            while (j > 0 && sortArray[j - 1][0] > sortArray[j][0]) {
                temp = sortArray[j - 1];
                sortArray[j - 1] = sortArray[j];
                sortArray[j] = temp;
                j--;
            }
        }
    }
}
