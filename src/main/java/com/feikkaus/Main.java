package com.feikkaus;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String[] words = { "abc", "car", "ada", "racecar", "cool" };
        // int[] nums = { 1, 2, 3, 4, 11 };
        int[] nums = { 0, 1, 2, 4, 5, 7 };

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Liisa", 5);
        scores.put("Matti", 10);
        scores.put("Teppo", 7);
        System.out.println(streamSort(scores));
    }

    public static List<java.util.Map.Entry<String, Integer>> streamSort(Map<String, Integer> scores) {

        // muutetaan map listaksi ja stream avulla sitten päästään sorttaamaan valuen
        // mukaan
        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        // sorttaa valuen mukaan
        list = scores.entrySet().stream().sorted((a, b) -> a.getValue().compareTo(b.getValue())).toList();

        // filteröi v
        // list = scores.entrySet().stream().filter(entry -> entry.getValue() <
        // 10).toList();

        return list;
    }

    public static List<String> summaryRanges(int[] nums) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            while (i + 1 < nums.length && (nums[i] + 1) == nums[i + 1]) {
                i++;
            }
            if (start != nums[i]) {
                list.add(start + "->" + nums[i]);
            } else {
                list.add(start + "");
            }

        }
        return list;
    }

    // public static int[] searchRange(int[] nums, int target) {

    // for (int i = 0; i < nums.length; i++) {
    // if(nums[i] == target){
    // if()
    // }
    // }
    // System.out.println("loppu");
    // return nums;

    // }

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