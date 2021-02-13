package com.simpleJworld.isbntools;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ISBNValidatorTest {

    @Test
    public void givenTenDigitValidISBN_whenValid_thenReturnTrue() {
        ISBNValidator isbnValidator = new ISBNValidator();

        boolean isTenDigitISBN = isbnValidator.checkISBN("0735211299");
        assertTrue(isTenDigitISBN);

        isTenDigitISBN = isbnValidator.checkISBN("5020138509");
        assertTrue(isTenDigitISBN);
    }

    @Test
    public void givenThirteenDigitValidISBN_whenValid_thenReturnTrue() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean isTenDigitISBN = isbnValidator.checkISBN("9780553213119");
        assertTrue(isTenDigitISBN);
    }

    @Test
    public void givenTenDigitValidISBNThatEndingWithX_whenValid_thenReturnTrue() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean isTenDigitISBN = isbnValidator.checkISBN("012000030X");
        assertTrue(isTenDigitISBN);
    }

    @Test
    public void givenTenDigitInvalidISBN_whenValid_thenReturnFalse() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean isTenDigitISBN = isbnValidator.checkISBN("0735211291");
        assertFalse(isTenDigitISBN);
        isTenDigitISBN = isbnValidator.checkISBN("1020138509");
        assertFalse(isTenDigitISBN);
    }

    @Test
    public void givenThirteenDigitInvalidISBN_whenInvalid_thenReturnFalse() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean isTenDigitISBN = isbnValidator.checkISBN("9780553213115");
        assertFalse(isTenDigitISBN);
    }

    @Test
    public void givenTenDigitInValidISBNThatEndingWithX_whenInValid_thenReturnFalse() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean isTenDigitISBN = isbnValidator.checkISBN("112000030X");
        assertFalse(isTenDigitISBN);
    }

    @Test
    public void givenNineDigitISBN_whenInvalid_thenThrowNumberFormatException() {
        ISBNValidator isbnValidator = new ISBNValidator();
        assertThrows(NumberFormatException.class, () -> isbnValidator.checkISBN("123456789"));
    }

    @Test
    public void givenString_whenContainsNonNumbers_thenThrowNumberFormatException() {
        ISBNValidator isbnValidator = new ISBNValidator();
        assertThrows(NumberFormatException.class, () -> isbnValidator.checkISBN("HelloWorld"));
    }


}