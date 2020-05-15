package com.validateisbn;


public class ValidateISBN {

    public boolean checkISBN(String isbn) {

        if (isbn == null) return false;

        int total13 = 0;

        if (isbn.length() == 13) {
            for (int i = 0; i < 13; i++) {
                if (i % 2 == 0) {
                    total13 += Character.getNumericValue(isbn.charAt(i));
                } else {
                    total13 += Character.getNumericValue(isbn.charAt(i)) * 3;
                }
            }
            if (total13 % 10 == 0) {
                return true;
            } else {
                return false;
            }
        }

        else{

            if (isbn.length() != 10) throw new NumberFormatException("isbn should has 10 digits");

            int total = 0;

            for (int i = 0; i < 10; i++) {
                if (!Character.isDigit(isbn.charAt(i))) {
                    if (i == 9 && isbn.charAt(i) == 'X') {
                        total += 10;
                    } else {
                        throw new NumberFormatException("isbn must be number");
                    }
                } else {
                    total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
                }
            }

            if (total % 11 == 0) {
                return true;
            }
            return false;
        }
    }
}