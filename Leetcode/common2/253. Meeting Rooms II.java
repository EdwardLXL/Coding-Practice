/**
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106

*/




/**

253. Meeting Rooms II (https://leetcode.com/problems/meeting-rooms-ii/description/):
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.


*/


// Heap (Sort + Greedy) solution: put the earlest-begin interval at the first available room
// Time: O(nlogn), 7ms
// Space: O(n), 37.1mb
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // !!: Corner case
        if(intervals.length == 0) return 0;
        
        // Sort the intervals by beginning time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Greedy: put the next interval into the non-confict room with ealiest available time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(0);
        for(int[] interval : intervals) {
            int available = minHeap.peek();
            if(available <= interval[0]) {
                // Use the existing room in heap
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        
        return minHeap.size();
    }
}



/*****************************************************************************************************************************/


// Heap (Sort + Greedy) solution: put the earlest-begin interval at the first available room
// proof: In the only case we break the pair, the algorithm still return false
//     [ ]
// [        ]
// Time: O(nlogn), 2ms
// Space: O(n), 36.4mb
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int rooms = 0;
        
        // Extract begin & end times
        int[] begin = new int[intervals.length];
        int[] end = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++) {
            begin[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        // Sort the begin & end time seperately
        Arrays.sort(begin);
        Arrays.sort(end);
        
        // Greedy: last is the index of earlest ending time in all rooms
        // **: If the new begin is later than the time then we need a new room
        int last = 0;
        for(int i = 0; i < intervals.length; i++) {
            if(begin[i] < end[last]) {
                // Put this interval into a new room
                rooms++;
            } else {
                // Compatible with at least one room
                last++;
            }
        }
        
        return rooms;
    }
}


/**************************************************************************************************************/



import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0253_MeetingRoomsII {

	public static int minMeetingRooms(int[][] m) {
		Line[] lines = new Line[m.length];
		for (int i = 0; i < m.length; i++) {
			lines[i] = new Line(m[i][0], m[i][1]);
		}
		Arrays.sort(lines, new StartComparator());
		PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			while (!heap.isEmpty() && heap.peek().end <= lines[i].start) {
				heap.poll();
			}
			heap.add(lines[i]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

	public static class Line {
		public int start;
		public int end;

		public Line(int s, int e) {
			start = s;
			end = e;
		}
	}

	public static class StartComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.start - o2.start;
		}

	}

	public static class EndComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.end - o2.end;
		}

	}

}





/*********************************************************************************************/































