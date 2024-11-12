
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Palindrome {
    //Given a string s, return the longest
    //palindromic
    //
    //substring
    // in s.
    //https://leetcode.com/problems/longest-palindromic-substring/

    String noPalindrome = "";


    public String longestPalindrome(String s){
        String longestEven = longestEvenPalindrome(s);
        String longestOdd = longestOddPalindrome(s);
        return longestEven.length() > longestOdd.length() ? longestEven : longestOdd;

    }

    public String longestEvenPalindrome(String s){String[] letterList = s.split("");

        String longestPalindrome = getShiftedPositionPalindrome(s, 1);
        if(longestPalindrome.length() < 2){
            return noPalindrome;
        }
        return longestPalindrome;
    }

    public String longestOddPalindrome(String s) {
        String longestPalindrome = getShiftedPositionPalindrome(s, 0);
        if(longestPalindrome.length() <= 2){
            return s.substring(0,1);
        }
        return longestPalindrome;
    }

    private String getShiftedPositionPalindrome(String s, int leftShift){
        String[] letterList = s.split("");

        String longestPalindrome ="";
        for(int i =0; i <= letterList.length; i++){
            int currentPalindrome = 1;
            int j = 1;

            boolean isPalindrome = true;
            while(isPalindrome){

                boolean hasSides = i-j+leftShift >=0 && i+j < letterList.length;
                if(hasSides){

                    boolean matchingSides = Objects.equals(letterList[i - j+leftShift], letterList[i + j]);
                    if(matchingSides){
                        currentPalindrome +=2 ;

                        if(currentPalindrome > longestPalindrome.length()){
                            longestPalindrome = s.substring(i - j + leftShift, i+j+1);
                            if(longestPalindrome.length() == letterList.length){
                                break;
                            }
                        }
                        j++;
                    }
                    else{
                        isPalindrome = false;
                    }
                }
                else{
                    isPalindrome = false;
                }
            }
        }
        return longestPalindrome;
    }


    @Test
    public void hasPalindromeTest(){
        String input = "babad";
        String output = "bab";

        assertEquals(output, longestPalindrome(input));
    }

    @Test
    public void noPalindromeTest(){
        String input = "asdvb";
        String output = "a";

        assertEquals(output, longestPalindrome(input));
    }

    @Test
    public void hasSubPalindromeTest(){
        String input = "asdfghjkklkk";
        String output = "kklkk";

        assertEquals(output, longestPalindrome(input));
    }

    @Test
    public void hasEvenPalindromeTest(){
        String input = "addca";
        String expected = "dd";

        assertEquals(expected, longestEvenPalindrome(input));
    }

    @Test
    public void wholeWordPalindromeOdd(){
        String input = "adida";

        assertEquals(input, longestOddPalindrome(input));
    }

    @Test
    public void wholeWordPalindromeEven(){
        String input = "adda";

        assertEquals(input, longestEvenPalindrome(input));
    }

}
