/*
package com.droidrank.sample;

import java.util.Map;

*/
/**
 * Created by deepak.mandhani on 05/03/18.
 *//*


public class ZeroRectangle {

    // This function basically finds largest 0
// sum aubarray in temp[0..n-1]. If 0 sum
// does't exist, then it returns false. Else
// it returns true and sets starting and
// ending indexes as starti and endj.
    boolean sumZero(int temp[], int starti, int endj, int n)
    {
        Map<Integer, Integer> presum;
        int sum = 0; // Initialize sum of elements

        int max_length = 0;
        for (int i = 0; i < n; i++)
        {
            sum += temp[i];

            if (temp[i] == 0 && max_length == 0)
            {
            starti = i;
            endj = i;
                max_length = 1;
            }
            if (sum == 0)
            {
                if (max_length < i + 1)
                {
                starti = 0;
                endj = i;
                }
                max_length = i + 1;
            }

            // Look for this sum in Hash table
            if (presum.find(sum) != presum.end())
            {
                // store previous max_length so
                // that we can check max_length
                // is updated or not
                int old = max_length;

                // If this sum is seen before,
                // then update max_len
                max_length = max(max_length, i - presum[sum]);

                if (old < max_length)
                {
                    // If max_length is updated then
                    // enter and update start and end
                    // point of array
                *endj = i;
                *starti = presum[sum] + 1;
                }
            }
            else

                // Else insert this sum with
                // index in hash table
                presum[sum] = i;
        }

        // Return true if max_length is non-zero
        return (max_length != 0);
    }

    // The main function that finds Largest rectangle
// sub-matrix in a[][] whose sum is 0.
    void sumZeroMatrix(int a[][MAX], int row, int col)
    {
        int temp[row];

        // Variables to store the final output
        int fup = 0, fdown = 0, fleft = 0, fright = 0;
        int sum;
        int up, down;
        int maxl = INT_MIN;

        // Set the left column
        for (int left = 0; left < col; left++)
        {

            for (int right = left; right < col; right++)
            {

                for (int i = 0; i < row; i++)
                    temp[i] += a[i][right];
                boolean sum = sumZero(temp, &up, &down, row);
                int ele = (down - up + 1) * (right - left + 1);

                if (sum && ele > maxl)
                {
                    fup = up;
                    fdown = down;
                    fleft = left;
                    fright = right;
                    maxl = ele;
                }
            }
        }

        if (fup == 0 && fdown == 0 && fleft == 0 &&
                fright == 0 && a[0][0] != 0)
            return;

        for (int j = fup; j <= fdown; j++)
        {
            for (int i = fleft; i <= fright; i++)
                System.out.println(a[j][i]);
        }
    }
}
*/
