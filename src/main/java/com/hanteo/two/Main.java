package com.hanteo.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(4, new int[]{1,2,3}));
        System.out.println(solution(10, new int[]{2,5,3,6}));
        System.out.println(solution(3, new int[]{2,5,6}));
        System.out.println(solution(0, new int[]{2,5,6}));
        System.out.println(solution(1, new int[]{2,5,4,8,6}));
    }
    private static int solution(int sum, int[] coins){
        if(sum == 0) return 0;

        int[] dynamicPrograming = new int[sum+1];
        dynamicPrograming[0] = 1;

        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];

            for(int j = 1; j < sum + 1; j++) {
                if(j >= coin) {
                    dynamicPrograming[j] += dynamicPrograming[j - coin];
                }
            }
        }

        return dynamicPrograming[sum];
    }
}
