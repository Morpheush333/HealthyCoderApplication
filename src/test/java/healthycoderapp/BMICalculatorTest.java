package healthycoderapp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "prod";

    @Nested
    class IsDietRecommendedTest {

        @ParameterizedTest(name = "weight = {0}, height = {1}")
        @CsvFileSource(resources = "/original.csv", numLinesToSkip = 1)
        void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {

//        given
            double weight = coderWeight;
            double height = coderHeight;

//        when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

//        then
            assertTrue(recommended);
        }

        @ParameterizedTest(name = "weight = {0}, height = {1}")
        @CsvSource(value = {"52.0, 1.65", "49.5, 1.69", "63.5, 1.70"})
        void should_ReturnFalse_When_DietNotRecommended(Double coderWeight, Double coderHeight) {

//        given
            double weight = coderWeight;
            double height = coderHeight;

//        when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

//        then
            assertFalse(recommended);
        }

        @Test
        void should_ReturnArithmeticException_When_HeightZero() {

//        given
            double weight = 52.0;
            double height = 0;

//        when
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

//        then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    class FndCoderWithWorstBMITests {
        @Test
        void should_ReturnCoderWithWorseBMI_When_CoderListNotEmpty() {

//        given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 54));
            coders.add(new Coder(1.56, 93));
            coders.add(new Coder(1.77, 63));

//        when
            Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

//        then
            assertAll(
                    () -> assertEquals(1.56, coder.getHeight()),
                    () -> assertEquals(93, coder.getWeight())
            );
        }

        @Test
        void should_ReturnNull_When_CoderListIsEmpty() {

//        given
            List<Coder> coders = new ArrayList<>();

//        when
            Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

//        then
            assertNull(coder);
        }

        @Test
        void should_ReturnCoderWithWorstBMIIn500Ms_When_CoderLiseHas100000Elements() {

//        given
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            List<Coder> coders = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

//        when
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

//        then
            assertTimeout(Duration.ofMillis(500), executable);
        }
    }

    @Nested
    class GetBMIScoresTest {
        @Test
        void should_ReturnCorrectBMIScoreArray_When_CoderLiseNotEmpty() {

//        given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 54));
            coders.add(new Coder(1.56, 93));
            coders.add(new Coder(1.75, 69));
            double[] expected = {16.67, 38.21, 22.53};

//        when
            double[] bmiScores = BMICalculator.getBMIScores(coders);

//        then
            assertArrayEquals(expected, bmiScores);
        }
    }
}