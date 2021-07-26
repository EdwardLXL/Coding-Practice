/*
7. Reverse Integer (https://leetcode.com/problems/reverse-integer/): Given a 32-bit signed integer, reverse digits of an integer.

Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
Example 4:

Input: x = 0
Output: 0
 

Constraints:

-231 <= x <= 231 - 1

*/


// Math solution: Pull the digits and take care of the overflow
// Time: O(logx), 1ms
// Space: O(1), 36.6mb
class Solution {
    public int reverse(int x) {
        int ans = 0;
        
        while(x != 0) {
            int tail = x % 10;
            int newAns = ans * 10 + tail;
            
            // !!: Take care the overflow by checking if result is as we expected when adding newAns
            if((newAns - tail) / 10 != ans) return 0;
            ans = newAns;
            
            x /= 10;
        }
        
        return ans;
    }
}




/****************************************************************************************************/

public class Solution {
    public int reverse(int x) {
        long res = 0;
		for (; x != 0; x /= 10)
			res = res * 10 + x % 10;
		return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0: (int) res;
    }
}

/********************************************************************************************/





































