import java.util.*;

/*
Weight Sorting Algorithm: You have $n$ points with weights and positions. You can move points right by a distance dist[i]. Find the minimum operations to sort points by weights.
*/
class Main {
    public static void main(String[] args) {
        PointSorter ps = new PointSorter();
        // Test with the "Scrambled Case" from Example 1
        PointSorter.Point[] points = new PointSorter.Point[]{
            new PointSorter.Point(10, 5), 
            new PointSorter.Point(5, 2),
            new PointSorter.Point(20, 8),
            new PointSorter.Point(15, 4),
        };
        
        System.out.println("Minimum operations: " + ps.minOperationsToSort(points));
    }
}

class PointSorter {
    static class Point {
        int weight;
        int position;

        Point(int weight, int position) {
            this.weight = weight;
            this.position = position;
        }
        
        public String toString(){
            return this.weight+" : "+this.position;
        }
    }

    public int minOperationsToSort(Point[] points) {
        int n = points.length;
        if (n <= 1) return 0;

        // Step 1: Sort by weight
        Arrays.sort(points, (p1, p2) -> p1.weight-p2.weight);

        // Step 2: Extract positions
        int[] posArray = new int[n];
        for (int i = 0; i < n; i++) {
            posArray[i] = points[i].position;
        }

        // Step 3: Find LIS length
        int lisLength = findLIS(posArray);

        return n - lisLength;
    }

    int findLIS(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLIS = 1; // Start at 1 because a single element is an LIS

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]); // Update max for every element
        }
        return maxLIS;
    }
}