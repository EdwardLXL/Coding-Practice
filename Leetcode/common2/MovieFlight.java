/*
I had 2 question on my online assesment, however I remeber only the first one. My code passed only 10 test out of 13. I did a sorting and then found the best pair with 2 for loops

Question:
You are on a flight and wanna watch two movies during this flight.
You are given List<Integer> movieDurations which includes all the movie durations.
You are also given the duration of the flight which is d in minutes.
Now, you need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).

Find the pair of movies with the longest total duration and return they indexes. If multiple found, return the pair with the longest movie.

Example 1:

Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d = 250
Output: [0, 6]
Explanation: movieDurations[0] + movieDurations[6] = 90 + 125 = 215 is the maximum number within 220 (250min - 30min)

*/



// https://leetcode.com/discuss/interview-question/313719/Amazon-or-Online-Assessment-2019-or-Movies-on-Flight
// Sort + Two pointer solution
// Time: O(nlogn)
// Space: O(1)
public class Main {
    
    public static List<Integer> findMovies(int[] list, int target) {
        target -= 30;
        List<Integer> ans = new ArrayList<>();
        ans.add(-1);
        ans.add(-1);
        
        // Sort the movies by length
        int[][] movies = new int[list.length][2];
        for(int i = 0; i < list.length; i++) {
            movies[i][0] = list[i]; // Movie duration
            movies[i][1] = i;       // Original index of the movie
        }
        Arrays.sort(movies, (a, b) -> a[0] - b[0]);
        // for(int i = 0; i < movies.length; i++) {
        //     System.out.println("d: " + movies[i][0] + " i: " + movies[i][1]);
        // }
        
        // Two pointer: shrink from begin/end
        int l = 0, r = list.length -1;
        int max = 0;
        while(l < r) {
            if(movies[l][0] + movies[r][0] > target) {
                r--;
            } else {
                if(movies[l][0] + movies[r][0] > max) {
                    max = movies[l][0] + movies[r][0];
                    ans.set(0, movies[l][1]);
                    ans.set(1, movies[r][1]);
                }
                l++;
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findMovies(new int[]{90, 85, 75, 60, 120, 150, 125}, 250));
        System.out.println(findMovies(new int[]{90, 85, 75, 60, 155, 150, 125}, 250));
        System.out.println(findMovies(new int[]{90, 85, 75, 60, 120, 110, 110, 150, 125}, 250));
        System.out.println(findMovies(new int[]{95, 85, 75, 60, 120, 110, 110, 150, 125}, 250));
        System.out.println(findMovies(new int[]{1, 10, 25, 35, 60}, 90));
        System.out.println(findMovies(new int[]{20, 50, 40, 25, 30, 10}, 90));
        System.out.println(findMovies(new int[]{5, 55, 40, 20, 30, 30}, 90));
    }
}


/**************************************************************************************/

//python code

/*
The differences between Find Pair With Given Sum and Movies on Flight are:

1.Find Pair With Given Sum asks us to find exactly the target, that's why using a map in Find Pair With Given Sum is a good idea. While Movies on Flight asks us to find value closest to target.
2.Find Pair With Given Sum gives valid input, while in Movies on Flight you need to handle input with no answer.

Therefore I have developed two different approaches to tackle each of them:

Find Pair With Given Sum
1. dictionary store key = target - num[i], value = index i
2. maintain maximum to record current ans with larget number

*/

def findSum(nums, target):
    target -= 30
    map = {}
    maximum = -1
    ans = [-1,-1]
    for i in range(len(nums)):
        if nums[i] not in map:
            map[target - nums[i]] = i
        else:
            if nums[i] > maximum or target - nums[i] > maximum:
                ans[0] = map[nums[i]]
                ans[1] = i
                maximum = max(nums[i],target - nums[i])
    if ans != [-1,-1]:
        return ans
    else:
        return []

# [20, 50, 40, 25, 30, 10],90 | [1, 5]
# [1, 10, 25, 35, 60], 90 | [2, 3]
# [50, 20, 10, 40, 25, 30], 90 | [0, 2]
print (findSum([50, 20, 10, 40, 25, 30], 90))


/*
Movies on Flight
1. maintain a copy of movieDuration and sort it.
2. use two pointers from left(biggest ones) + right (smallest ones), if the sum is smaller than d and then larger than the current maximum, then we find a solution.
3. update ans with the index, here we have to refer the index to the original movieDuration array.
4. len(movieDurations) - movieDurations[::-1].index(newM[j]) - 1] for the second index is to prevent returning the same indices when duplicate elements are provided (see test case 5)

*/

def moviesOnFlight(movieDurations, d):
    d = d-30
    newM = movieDurations
    newM = sorted(newM, reverse = True)
    maximum = 0
    ans = []
    for i in range(len(newM)):
        for j in range(len(newM)-1, i, -1):
            sum = newM[i]+ newM[j]
            if sum <= d:
                if sum > maximum:
                    maximum = newM[i] + newM[j]
                    ans = [movieDurations.index(newM[i]),len(movieDurations) - movieDurations[::-1].index(newM[j]) - 1]
            else:
                break
    
    return sorted(ans)

# [90, 85, 75, 60, 120, 150, 125], 250 | [0,6]
# [90, 85, 75, 60, 120, 150, 125], 50 | []
# [90, 85, 75, 60, 120, 150, 125], 220 | [3,6]
# [10, 50, 60] , 120 | [0,2]
# [90, 85, 75, 60, 120,110,110, 150, 125] , 250 | [5, 6]

print (moviesOnFlight([90, 85, 75, 60, 120,110,110, 150, 125] , 250))










