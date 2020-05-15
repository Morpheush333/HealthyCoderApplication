package com.validateisbn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    @ParameterizedTest(name = "isbn = {0}")
    @CsvSource(value = {"0007420099", "1473231078", "1473231116"})
    void should_ReturnTrue_When_ISBNIsCorrect10Digits(String isbn) {
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.checkISBN(isbn);

        assertTrue(result);
    }

    @Test
    void should_ReturnFalse_When_10DigitsISBNIsIncorrect() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0007420098");

        assertFalse(result);
    }

    @ParameterizedTest(name = "isbn = {0}")
    @CsvSource(value = {"9780261103573", "9781408855690"})
    void should_ReturnTrue_When_ISBNIsCorrect13Digits(String isbn) {
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.checkISBN(isbn);

        assertTrue(result);
    }

    @Test
    void should_ReturnFalse_When_13DigitsISBNIsIncorrect() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("9781408855692");

        assertFalse(result);
    }

    @Test
    void should_ReturnTrue_When_10DigitsISBNEndWithX() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("012000030X");

        assertTrue(result);
    }

    @Test
    void should_ThrowNumberFormatException_When_NumberIsNot10DigitLong() {
        ValidateISBN validateISBN = new ValidateISBN();

        Executable executable = () -> validateISBN.checkISBN("123456789");

        assertThrows(NumberFormatException.class, executable);
    }

    @Test
    void should_ThrowNumberFormatException_When_ISBNIsNotNumber() {
        ValidateISBN validateISBN = new ValidateISBN();

        Executable executable = () -> validateISBN.checkISBN("abcdefghij");

        assertThrows(NumberFormatException.class, executable);
    }

    @Test
    void should_ReturnFalse_When_NumberIsNull() {
        ValidateISBN validateISBN = new ValidateISBN();

        Boolean result = validateISBN.checkISBN(null);

        assertFalse(result);
    }


}