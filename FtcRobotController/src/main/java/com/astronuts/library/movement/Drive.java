package com.astronuts.library.movement;

public class Drive {
    @Deprecated
    public static void driveByDistance(double distance, char unit, EncoderMotor left, EncoderMotor
            right) {
        driveByDistance(distance, unit, 0.2, left, right);
    }
    @Deprecated
    public static void driveByDistance(double distance, char unit, double power, EncoderMotor left,
                                 EncoderMotor right) {
        switch(unit){
            case'c':
                left.move(driveByDistanceCalculator(distance), power);
                right.move(driveByDistanceCalculator(distance), power);
            case'i':
                distance*=0.39701;
                left.move(driveByDistanceCalculator(distance), power);
                right.move(driveByDistanceCalculator(distance), power);
        }


    }
    @Deprecated
    public static void turnByAngle(int angle, EncoderMotor left, EncoderMotor right) {
        turnByAngle(angle, 0.2, left, right);
    }
    @Deprecated
    public static void turnByAngle(int angle, double power, EncoderMotor left, EncoderMotor
            right) {
        left.move(turnByAngleCalculator(angle), power);
        right.move(turnByAngleCalculator(-angle), power);
    }
    @Deprecated
    public static void driveTo(float finalX, float finalY, String unit, EncoderMotor left,
                             EncoderMotor right) {
        driveTo(finalX, finalY, unit, 0.2, left, right);
    }
    @Deprecated
    public static void driveTo(float finalX, float finalY, String unit, double power, EncoderMotor
            left,
                        EncoderMotor right){
        float currentX = 0;
        float currentY = 0;
        float h;
        float a;
        float theta = 0;

        h = (float) Math.sqrt(Math.pow((finalY - currentY), 2) + Math.pow((finalX - currentX), 2));
        a = finalX - currentX;
        theta = (float) Math.acos(a/h);

        turnByAngle((int) theta, power, left, right);
        driveByDistance((int) h, 'i', left, right);


    }
    @Deprecated
    private static double radianConversion(double degree){
        double radian; //Variable placeholder

        radian = degree * (Math.PI / 180); //Converts from degrees to radians
        //radian = Math.atan2(Math.sin(radian), Math.cos(radian)); //Finds the arctan of the sine
        // of the radians measure and the cosine of the radians measure

        return radian; //Returns the radians measure to the caller
    }
    @Deprecated
    private static int turnByAngleCalculator(int angle){
        return (angle * 1442) / (150);
    }
    @Deprecated
    private static int driveByDistanceCalculator(double distance){
        return (int) (distance * 1442) / 50;

    }
}
