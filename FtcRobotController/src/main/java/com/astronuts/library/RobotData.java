package com.astronuts.library;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class RobotData {
    //Information for buttons, except nobody wants to use them
    public static int teamColor = 0; //Default value; 0 is blue, 1 is red
    public static String blueTeam = "Current Team: Blue";
    public static String redTeam = "Current Team: Red";
    public static int teamPosition = 0; //Default value; 0 is right, 1 is left
    public static String rightPos = "Current Position: Right";
    public static String leftPos = "Current Position: Left";
    public static int timeDelay = 0; //Time value in seconds
    public static String timeDelay0 = "Current Time Delay: 0";
    public static String timeDelay5 = "Current Time Delay: 5";
    public static String timeDelay10 = "Current Time Delay: 10";
    public static String timeDelay15 = "Current Time Delay: 15";

    //Strings for the robot config file
    public static final String lower_elbow = "lower_elbow";
    public static final String shoulder = "shoulder";
    public static final String upper_elbow = "upper_elbow";

    public static final String color_servo = "color_servo";
    public static final String left_button = "left_button";
    public static final String right_button = "right_button";
    public static final String left_lever = "left_lever";
    public static final String right_lever = "right_lever";

    public static final String right_drive = "right_drive";
    public static final String left_drive = "left_drive";

    public static final String ultrasonic_sensor = "ultrasonic_sensor";
    public static final String light_sensor = "light_sensor";
    public static final String color_sensor = "color_sensor";
    public static final String cdim = "cdim";

    //Calibration values for sensors.
    //TODO: Find correction values at tournament.
    public static final double blueMultiplicityValue = 1.0;
    public static final double redMultiplicityValue = 1.0;
    public static final double greenMultiplicityValue = 1.0;
    public static final double ultrasonicCorrectionValue = 1.0;

    //Movement data
    public static final double encoderTicksPerRevolution = 1680;
    //TODO: Calculate distance (in centimeters please) in one rev
    public static final double distancePerRevolution = 10;
    //TODO: Calculate angle theta (in degrees please) in one rev
    public static final double degreesPerRevolution = 10;
}
