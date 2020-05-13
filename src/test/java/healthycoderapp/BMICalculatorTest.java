package healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void should_ReturnTrue_When_DietRecommended() {

//        given
        double weight = 86.5;
        double height = 1.65;

//        when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);

//        then
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_When_DietNotRecommended() {

//        given
        double weight = 52.0;
        double height = 1.65;

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