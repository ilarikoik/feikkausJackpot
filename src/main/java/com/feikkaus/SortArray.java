package com.feikkaus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortArray {

    // EUROJACKPOT
    public static List<String> sortHashMapSecondary(HashMap<String, Integer> givenlist) {

        // Muutetaan HashMap listaksi
        List<Map.Entry<String, Integer>> list = new ArrayList<>(givenlist.entrySet());

        // Järjestetään arvon mukaan laskevasti
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        int firstMax = list.get(0).getValue(); // suurin arvo
        int secondMax = list.size() > 1 ? list.get(1).getValue() : firstMax; // toinen suurin arvo

        System.out.println("Kaksi suurinta avainta arvoineen TAI yhtä monesti tulevat (ensimmäinen useimmiten): ");
        List<String> topKeys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry.getValue() >= firstMax || entry.getValue() >= secondMax) {
                topKeys.add(entry.getKey() + "--> " + entry.getValue() + " x");
            }
        }

        return topKeys;
    }

    public static List<String> sortHashMapPrimary(HashMap<String, Integer> givenlist) {

        // Muutetaan HashMap listaksi
        List<Map.Entry<String, Integer>> list = new ArrayList<>(givenlist.entrySet());

        // Järjestetään arvon mukaan laskevasti
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        int fifthMax = list.size() > 1 ? list.get(4).getValue() : 0;

        System.out.println("Viisi useimmiten tulevaa TAI yhtä monesti tulevat (ensimmäinen usemmiten): ");
        List<String> topKeys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // otetaan avain
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry.getValue() >= fifthMax) {

                topKeys.add(entry.getKey() + "--> " + entry.getValue() + " x");
                // topKeys.add("K: " + entry.getKey() + "V: " + entry.getValue());
                // topKeys.add(entry.getKey());
            }
        }

        return topKeys;
    }

    // LOTTO

    public static List<String> sortHashMapPrimaryLotto(HashMap<String, Integer> givenlist) {

        // Muutetaan HashMap listaksi
        List<Map.Entry<String, Integer>> list = new ArrayList<>(givenlist.entrySet());

        // Järjestetään arvon mukaan laskevasti ( sort ei tee uutta listaa mutta sorted
        // tekee)
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        int seventhMax = list.size() > 1 ? list.get(6).getValue() : 0;

        System.out.println("Seitsemän useimmiten tulevaa TAI yhtä monesti tulevat (ensimmäinen usemmiten): ");
        List<String> topKeys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // otetaan avain
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry.getValue() >= seventhMax) {

                topKeys.add(entry.getKey() + "--> " + entry.getValue() + " kertaa");
            }
        }

        return topKeys;
    }

    public static List<String> sortHashMapSecondaryLotto(HashMap<String, Integer> givenlist) {
        // Muutetaan HashMap listaksi
        List<Map.Entry<String, Integer>> list = new ArrayList<>(givenlist.entrySet());
        // Järjestetään arvon mukaan laskevasti
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        int firstMax = list.get(0).getValue(); // suurin arvo

        System.out.println("Useimmiten tuleva lisänumero TAI yhtä monesti tulevat (ensimmäinen useimmiten): ");
        List<String> topKeys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry.getValue() >= firstMax) {
                topKeys.add(entry.getKey() + "--> " + entry.getValue() + " kertaa");
            }
        }

        return topKeys;
    }

    public static List<String> sortHashMapPlusLotto(HashMap<String, Integer> givenlist) {
        // Muutetaan HashMap listaksi
        List<Map.Entry<String, Integer>> list = new ArrayList<>(givenlist.entrySet());
        // Järjestetään arvon mukaan laskevasti
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        int firstMax = list.get(0).getValue(); // suurin arvo

        System.out.println("Useimmiten tuleva Plusnumero TAI yhtä monesti tulevat (ensimmäinen useimmiten): ");
        List<String> topKeys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry.getValue() >= firstMax) {
                topKeys.add(entry.getKey() + "--> " + entry.getValue() + " kertaa");
            }
        }

        return topKeys;
    }
}
