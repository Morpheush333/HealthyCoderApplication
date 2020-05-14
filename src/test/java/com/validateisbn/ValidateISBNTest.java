package com.validateisbn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    @ParameterizedTest (name = "isbn = {0}")
    @CsvSource(value = {"0007420099", "1473231078", "1473231116"})
    void should_ReturnTrue_When_ISBNIsCorrect(String isbn){
        ValidateISBN validateISBN = new ValidateISBN();

        boolean result = validateISBN.checkISBN(isbn);

        assertTrue(result);
    }

    @Test
    void should_ReturnFalse_When_ISBNIsIncorrect(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0007420098");

        assertFalse(result);
    }

    @Test
    void should_ThrowNumberFormatException_When_NumberIsNot10DigitLong(){
        ValidateISBN validateISBN = new ValidateISBN();

        Executable executable = () -> validateISBN.checkISBN("123456789");

        assertThrows(NumberFormatException.class, executable);
    }
}