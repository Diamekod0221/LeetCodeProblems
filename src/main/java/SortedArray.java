import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortedArray {
    /*Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
    public int[] searchRange(int[] nums, int target){

        int[] returnRange = {-1, -1};

        if (nums.length < 1) {
            return returnRange;
        }

        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return returnRange;
            }
        }

        int lowerBoundleft = getLowerBound(nums, target);

        if(!(nums[lowerBoundleft] == target)){
            return returnRange;
        }

        if(lowerBoundleft == nums.length-1){
            if(nums[lowerBoundleft] == target){
                return new int[] {lowerBoundleft, lowerBoundleft};
            }
            else{
                return returnRange;
            }
        }

        int lowerBoundRight = getLowerBound(nums, target+1);

        if(nums[lowerBoundRight] == target + 1){
            lowerBoundRight -= 1;
        }

        return new int[] {lowerBoundleft, lowerBoundRight};
    }

    public static int getLowerBound(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;

        if(nums[0] >= target){
            return 0;
        }

        if(nums[right] < target){
            return right;
        }

        while(left+1 < right){

            int mid = (int) Math.floor((left+right) / 2);

            if(target <= nums[mid]){
                right = mid;
            }
            else{
                left = mid;
            }
        }

        if(nums[right] > target){
            right = left;
        }
        return right;
    }


    @Test
    public void getBound(){
        int[] input = {1,2,3,4,5,6};
        int target = 3;
        int expected = 2;

        assertEquals(expected, getLowerBound(input, target));
    }

    @Test
    public void outOfBounds(){
        int[] input = {1,2,3,4,5,6};
        int target = 8;
        int expected = input.length-1;

        assertEquals(expected, getLowerBound(input, target));
    }

    @Test
    public void validCase1(){
        int[] input = {1,2,5,6};
        int target = 4;
        int expected = 1;

        assertEquals(expected, getLowerBound(input, target));
    }

    @Test
    public void validCase2(){
        int[] input = {1,2,3,3,3,6};
        int target = 3;
        int expected = 2;

        assertEquals(expected, getLowerBound(input, target));
    }

    @Test
    public void edgeLeft(){
        int[] input = {1,6};
        int target = 1;
        int expected = 0;

        assertEquals(expected, getLowerBound(input, target));
    }

    @Test
    public void edgeRight(){
        int[] input = {1,6};
        int target = 6;
        int expected = 1;

        assertEquals(expected, getLowerBound(input, target));
    }

    @Test
    public void sameDuo(){
        int[] input = {2,2};
        int target = 2;
        int expected = 0;

        assertEquals(expected, getLowerBound(input, target));
    }



}


