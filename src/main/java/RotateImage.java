import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotateImage {
    /*You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

https://leetcode.com/problems/rotate-image/

This solution is more complicated than simply matrix[i][j] = matrix[j][i], but is much faster and less memo
     */

    public void rotate(int[][] matrix) {

        int tempCurr; int tempPrev;

        //Assuming matrix is symmetrical
        int m = matrix.length;
        int n = m-1;

        if(m == 1){
            return;
        }

        int iters = (int) Math.floor(((double) m)/2);


        for(int j = 0; j < iters; j++){
            for(int i = 0; i < n - 2*j; i++){

                tempPrev = matrix[j+i][n-j];
                matrix[j+i][n-j] = matrix[j][j+i];

                tempCurr = matrix[n - j][n - j -i];
                matrix[n - j][n - j -i] = tempPrev;
                tempPrev = tempCurr;

                tempCurr = matrix[n - j -i][j];
                matrix[n - j -i][j] = tempPrev;

                matrix[j][j+i] = tempCurr;
            }
        }


    }

    @Test
    public void rotate2D(){
        int[][] input = new int[][]{{1,2},{3,4}};

        int[][] expected = new int[][] {{3,1},{4,2}};

        rotate(input);

        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(input));

    }

    @Test
    public void rotate3D(){
        int[][] input = new int[][]{{1,2,3},{4,5,6},{7,8,9}};

        int[][] expected = new int[][] {{7,4,1}, {8,5,2}, { 9,6,3}};

        rotate(input);

        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(input));

    }

    @Test
    public void rotate4D(){

        int[][] input = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        int[][] expected = new int[][]{{ 15,13,2,5}, { 14,3,4,1}, { 12,6,8,9},{16,7,10,11}};

        rotate(input);

        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(input));


    }
}
