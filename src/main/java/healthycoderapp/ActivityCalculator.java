package healthycoderapp;

public class ActivityCalculator {

    private static final int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkOutSession){

        if(weeklyCardioMin < 0 || weeklyWorkOutSession < 0){
            throw new RuntimeException();
        }

        int totalMinutes = weeklyCardioMin + weeklyWorkOutSession * WORKOUT_DURATION_MIN;
        double avgDailyActivityMinutes = totalMinutes / 7.0;

        if(avgDailyActivityMinutes < 20){
            return "bad";
        } else if (avgDailyActivityMinutes < 40){
            return "average";
        }
        return "good";
    }
}
