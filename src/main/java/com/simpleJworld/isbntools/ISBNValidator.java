package com.simpleJworld.isbntools;

public class ISBNValidator {


    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {

        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isValid13DigitISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isAValid10DigitISBN(isbn);
        }

        throw new NumberFormatException("Should be ten digit ISBN but got " + isbn.length());

    }

    public boolean isAValid10DigitISBN(String isbn) {
        int total = 0;
        boolean is10DigitISBN;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if ((!Character.isDigit(isbn.charAt(i)))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("Should be ten digit ISBN but got " + isbn);
                }
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }
        }

        is10DigitISBN = (total % SHORT_ISBN_MULTIPLIER) == 0;
        return is10DigitISBN;
    }

    public boolean isValid13DigitISBN(String isbn) {
        int total = 0;
        boolean is13DigitISBN;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            total += Character.getNumericValue(isbn.charAt(i)) * (i % 2 == 0 ? 1 : 3);
        }
        is13DigitISBN = (total % LONG_ISBN_MULTIPLIER) == 0;
        return is13DigitISBN;
    }

}
