package com.astronuts.library.sensors.ultrasonic;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * This Class is responsible for reading the Ultrasonic Sensor and Computing corrected distances
 * in centimeters and inches. The corrected values are returned in the "centimeters" and "inches"
 * fields.
 *
 * Created by Prescott on 10/14/15.
 * Last edited by Prescott on 10/21/15.
 */
public class UltrasonicDistance {
    //Makes a variable that will be able to store the value for centimeters.
    protected double centimeters;
    //Makes a variable that will be able to store the value for inches.
    protected double inches;
    protected double raw;



    protected UltrasonicSensor ultrasonic;

    public UltrasonicDistance(UltrasonicSensor ultrasonic) {
        this.ultrasonic = ultrasonic;
    }


    //Creates Method for converting Ultrasonic Value to Centimeters and Inches.
    public double getdistance(char unit) {
        //Gets the raw value from the sensor.
        raw = ultrasonic.getUltrasonicLevel();
        double returnType = 1;
        //Converts raw data into Centimeters.
        centimeters = raw - 3;

        //Converts raw data into Inches.
        inches = centimeters / 2.54;

        switch(unit) {
            case'c':
                returnType = 1;
                break;
            case'i':
                returnType = 2;
                break;
            case'r':
                returnType = 3;
                break;
        }

        if (returnType == 1){
            return centimeters;
        }
        else if(returnType == 2){
            return inches;
        }else {
            return raw;
        }
    }
}
