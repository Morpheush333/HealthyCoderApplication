package realestateapp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest {

    @Nested
    class GetApartment {

        @ParameterizedTest(name = "area = {0}, price = {1}")
        @CsvSource(value = {"35, 150000", "120, 750000", "120, 1200000"})
        void should_ReturnApartment_When_CorrectInput(double apartmentArea, int apartmentPrice) {
//        given
            double area = apartmentArea;
            BigDecimal price = BigDecimal.valueOf(apartmentPrice);

//        when
            Apartment apartment = new Apartment(area, price);

//        then
            assertAll(
                    () -> assertEquals(area, apartment.getArea()),
                    () -> assertEquals(price, apartment.getPrice())
            );
        }
    }
}