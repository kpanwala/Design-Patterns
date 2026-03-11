//https://www.geeksforgeeks.org/dsa/maximize-sum-of-minimum-and-maximum-of-all-groups-in-distribution/
// Given an array arr[], and an integer N. The task is to maximize the sum of minimum and maximum of each group in a distribution of the array elements in N groups where the size of each group is given in an array b[] of size N.  

import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[])
    {
        int N = 3;
    
        // Size of array a[]
        int siz = 9;
    
        int a[] = { 17, 12, 11, 9, 8, 8, 5, 4, 3 };
        int b[] = { 2, 3, 4 };
    
        // Function Call
        System.out.println(findMaxSumOfMinMax(a, b, 9, N));
    }
    
    static int findMaxSumOfMinMax(int a[], int b[], int size, int n){
        Arrays.stream(a)
            .boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
            
        Arrays.stream(b)
            .boxed()
            .sorted()
            .mapToInt(Integer::intValue)
            .toArray();
            
        int ans=0;
        int j=n;
        
        for(int i=0;i<n;i++){
            ans+=a[i];
            if(b[i]==1){
                ans+=a[i];    
            }
            else{
                ans+=a[j+b[i]-2];
                j+=b[i]-1;
            }
        }
        
        return ans;
    }
}