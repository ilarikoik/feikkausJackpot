package com.feikkaus;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String[] words = { "abc", "car", "ada", "racecar", "cool" };
        // int[] nums = { 1, 2, 3, 4, 11 };
        int[][] nums = {
                { 1, 2, 4 },
                { 3, 3, 1 }
        };

        int[] students = { 1, 1, 0, 0 };
        int[] sandiwches = { 0, 1, 0, 1 };

        // System.out.println(numberOfPairs(new int[] { 1, 3, 2, 1, 3, 2, 2 }));
    }

    //

    public static int countStudents(int[] students, int[] sandwiches) {

        List<Integer> stud = Arrays.stream(students)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> sandw = Arrays.stream(
                sandwiches)
                .boxed()
                .collect(Collectors.toList());

        int count = 0;

        while (true) {
            if (count >= stud.size()) {
                break;
            }
            if (stud.get(0) != sandw.get(0)) {
                stud.add(stud.get(0));
                stud.remove(0);
                count++;
            } else {
                stud.remove(0);
                sandw.remove(0);
                count = 0;
            }
        }
        return count;
    }

    public static boolean areOccurrencesEqual(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        int value = map.get(s.charAt(0));
        for (int v : map.values()) {
            if (v != value) {
                return false;
            }
        }
        return true;

    }

    public static boolean canAliceWin(int[] nums) {
        int under = 0;
        int over = 0;

        for (int n : nums) {
            if (n < 10) {
                under += n;
            } else {
                over += n;
            }
        }
        return under != over;
    }

    public static boolean isBalanced(String num) {
        int pair = 0;
        int single = 0;
        for (int i = 0; i < num.length(); i++) {
            int value = num.charAt(i) - '0';
            if (i % 2 == 0) {
                pair += value;
            } else {
                single += value;
            }
        }
        return single == pair;
    }

    public static String firstPalindrome(String[] words) {

        // for (String word : words) {
        // char[] chars = word.toCharArray();
        // int left = 0;
        // int right = chars.length - 1;
        // while (left <= right) {
        // char temp = chars[left];
        // chars[left] = chars[right];
        // chars[right] = temp;
        // left++;
        // right--;
        // }
        // // String reverse = new String(chars);
        // String reverse = String.valueOf(chars);
        // if (reverse.equals(word)) {
        // return word;
        // }
        // }

        // for (String word : words) {
        // char[] chars = word.toCharArray();
        // String reverse = "";
        // for (int i = chars.length - 1; i >= 0; i--) {
        // reverse += chars[i];
        // }
        // if (reverse.equals(word)) {
        // return word;
        // }
        // }
        // return "";
        // for (String word : words) {
        // StringBuffer sb = new StringBuffer(word);
        // String reverse = sb.reverse().toString();
        // if (reverse.equals(word)) {
        // return word;
        // }
        // }
        for (String word : words) {
            StringBuffer sb = new StringBuffer(word);
            String reverse = sb.reverse().toString();
            if (reverse.equals(word)) {
                return word;
            }
        }
        return "";

    }

    public static int countPairs(List<Integer> nums, int target) {
        int less = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (0 <= i & i < j && j < nums.size()) {
                    if ((nums.get(i) + nums.get(j)) < target) {
                        less++;
                    }
                }
            }
        }
        return less;

    }

    public static List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].contains(String.valueOf(x))) {
                result.add(i);
            }
        }
        return result;
    }

}