package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class BinRangeLookup {

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        printCountryAndCardType("680123");
    }

    public static void printCountryAndCardType(String prefixInput) {
        Map<String, Result> resultingMap = new HashMap<>();
        Result result = new Result();
        BinClassifier.classifiers.forEach(classifier -> {
            System.out.println("checking classifier: " + classifier.getPrefixRangeStart() + " - " + classifier.getPrefixRangeEnd());
            int lengthOfBinClassifierPrefix = classifier.getPrefixRangeStart().length();
            int lengthOfPrefixInput = prefixInput.length();
            int prefixRangeStart;
            int prefixRangeEnd;
            // trimming prefixRanges if necessary
            if (lengthOfPrefixInput < lengthOfBinClassifierPrefix) {
                System.out.println("Length of prefix is smaller than length of bin classifier prefix");
                prefixRangeStart = Integer.parseInt(classifier.getPrefixRangeStart().substring(0, lengthOfPrefixInput));
                prefixRangeEnd = Integer.parseInt(classifier.getPrefixRangeEnd().substring(0, lengthOfPrefixInput));
            } else {
                System.out.println("Length of prefix is larger or equal to length of bin classifier prefix");
                prefixRangeStart = Integer.parseInt(classifier.getPrefixRangeStart());
                prefixRangeEnd = Integer.parseInt(classifier.getPrefixRangeEnd());
            }
            // trimming prefixInput if necessary
            if (lengthOfPrefixInput > lengthOfBinClassifierPrefix) {
                int prefixInputInt = Integer.parseInt(prefixInput.substring(0, lengthOfBinClassifierPrefix));
                if (prefixInputInt >= prefixRangeStart && prefixInputInt <= prefixRangeEnd) {
                    // System.out.println(classifier.getCountry() + " - " + classifier.getType());
                    result.countryCodes.add(classifier.getCountry());
                    result.cardTypes.add(classifier.getType());
                }
            } else {
                int prefixInputInt = Integer.parseInt(prefixInput);
                if (prefixInputInt >= prefixRangeStart && prefixInputInt <= prefixRangeEnd) {
                    result.countryCodes.add(classifier.getCountry());
                    result.cardTypes.add(classifier.getType());
                    // System.out.println(classifier.getCountry() + " - " + classifier.getType());
                }
            }
        });
        resultingMap.put(prefixInput, result);
        // print Result: countries and card types
        System.out.print(resultingMap.get(prefixInput).countryCodes);
        System.out.print(resultingMap.get(prefixInput).cardTypes);

    }
}

class Result {

    Set<String> countryCodes;
    Set<String> cardTypes;

    public Result() {
        countryCodes = new HashSet<>();
        cardTypes = new HashSet<>();
    }
}

class BinClassifier {

    public static List<BinClassifier> classifiers = List.of(
            new BinClassifier("6706", "6708", "US", "credit"),
            new BinClassifier("68012200", "68012309", "GB", "debit"),
            new BinClassifier("670500", "670599", "US", "debit"),
            new BinClassifier("68012310", "68012399", "GB", "credit")
    );

    private String prefixRangeStart;
    private String prefixRangeEnd;
    private String country;
    private String type;

    public BinClassifier(String prefixRangeStart,
                         String prefixRangeEnd,
                         String country,
                         String type) {
        this.prefixRangeStart = prefixRangeStart;
        this.prefixRangeEnd = prefixRangeEnd;
        this.country = country;
        this.type = type;
    }

    public String getPrefixRangeStart() {
        return prefixRangeStart;
    }

    public String getPrefixRangeEnd() {
        return prefixRangeEnd;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }
}
