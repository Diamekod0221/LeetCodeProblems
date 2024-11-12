import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrogJump {

    /*
    A frog is crossing a river. The river is divided into some number of units, and at each unit,
    there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

    Given a list of stones positions (in units) in sorted ascending order, determine
    if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

    If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

    https://leetcode.com/problems/frog-jump/

    This is a joke solution using random jumps for n iterations. But it works quite well.
    */


    public static boolean canCross(int[] stones) {

        if(stones.length == 2){
            return stones[1] == 1;
        }

        int jump = 1;
        for(int iter = 0; iter < 20000; iter++) {
            System.out.println("Iteration count: " + iter);
            for (int i = 1; i < stones.length; ) {
                List<Integer> possibleJumps = new ArrayList<>(3);
                for(int j = 1; i+j < stones.length && (stones[i+j]-stones[i]) <= jump+1; j++){
                    if((stones[i+j]-stones[i]) >= jump-1 && (stones[i+j]-stones[i]) <= jump+1){
                        possibleJumps.add(i+j);
                    }
                }
                System.out.println("Current stone: " + stones[i] + "\n" + "possible jumps: " + possibleJumps.toString());
                if(possibleJumps.isEmpty()){
                    break;
                }
                else {
                    Random randomizer = new Random();
                    int temp = i;
                    i = possibleJumps.get(randomizer.nextInt(possibleJumps.size()));
                    jump = stones[i] - stones[temp];
                }
                if(i == stones.length-1){
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void easyTest(){
        int[] input = new int[]{0,1,2,3,4,5};
        boolean expected = true;

        assertEquals(expected, canCross(input));
    }

    @Test
    public void edgeCase1(){
        int[] input = new int[]{0,1};
        boolean expected = true;

        assertEquals(expected, canCross(input));
    }

    @Test
    public void hardTest(){
        int[] input = new int[]{0,1,2,3,4,8,9,11};
        boolean expected = false;

        assertEquals(expected, canCross(input));
    }

    @Test
    public void mediumTest(){
        int[] input = new int[]{0,1,3,5,6,8,12,17};
        boolean expected = true;

        assertEquals(expected, canCross(input));
    }
}
