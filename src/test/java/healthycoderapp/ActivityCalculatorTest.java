package healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ActivityCalculatorTest {

    @Test
    void should_ReturnBad_When_AvgBelow20(){

//        given
        int weeklyCardioMin = 40;
        int weeklyWorkOutSession = 1;

//        when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkOutSession);

//        then
        assertEquals("bad", actual);
    }

    @Test
    void should_ReturnAverage_When_AvgBetween20and40(){
//        given
        int weeklyCardioMin = 40;
        int weeklyWorkOutSession = 3;

//        when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkOutSession);

//        then
        assertEquals("average", actual);
    }

    @Test
    void should_ReturnGood_When_AvgAbove40(){
//        given
        int weeklyCardioMin = 40;
        int weeklyWorkOutSession = 7;

//        when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkOutSession);

//        then
        assertEquals("good", actual);
    }

    @Test
    void should_throwException_When_InputBelow0(){
//        given
        int weeklyCardioMin = -40;
        int weeklyWorkOutSession = 7;

//        when
        Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkOutSession);
//        then
        assertThrows(RuntimeException.class, executable);
    }

}