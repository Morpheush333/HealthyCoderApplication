package realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {

//    Apartment apartment;
//
//    @BeforeEach
//    void setUp(){
//        this.apartment = new Apartment(100, new BigDecimal(100000));
//    }

    @Nested
    class RateApartment {

        @ParameterizedTest(name = "area = {0}, price = {1}, expectedValue = {2}")
        @CsvSource(value = {"40, 200000, 0", "70, 500000, 1", "120, 1250000, 2"})
        void should_ReturnCorrectRating_When_CorrectApartment(double area, int price, int expected) {
//        given
            Apartment apartment = new Apartment(area, new BigDecimal(price));

//        when
            int ratingResult = ApartmentRater.rateApartment(apartment);

//        then
            assertEquals(expected, ratingResult);

        }

        @Test
        void should_ReturnErrorValue_When_IncorrectApartment() {
//        given
            Apartment apartment = new Apartment(0, new BigDecimal(250000));

//        when
            int ratingResult = ApartmentRater.rateApartment(apartment);

//        then
            assertEquals(-1, ratingResult);
        }
    }

    @Nested
    class CalculateAverageRating {

        @Test
        void should_AverageValue_When_CorrectApartmentList() {
//        given
            List<Apartment> apartments = new ArrayList<>();
            Apartment apartment = new Apartment(70, new BigDecimal(500000));
            for (int i = 0; i < 10; i++) {
                apartments.add(apartment);
            }

//        when
            double ratingResult = ApartmentRater.calculateAverageRating(apartments);

//        then
            assertEquals(1.0, ratingResult);
        }

        @Test
        void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
//        given
            List<Apartment> apartments = new ArrayList<>();

//        when
            Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);

//        then
            assertThrows(RuntimeException.class, executable);
        }
    }
}