package com.astronuts.library.newdrive;

import com.astronuts.library.RobotData;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Zach on 11/26/2015.
 */
public class sensors implements Runnable {
    private ColorSensor colorSensor;
    private UltrasonicSensor ultrasonicSensor;
    private LightSensor lightSensor;

    private double blueColorValue;
    private double greenColorValue;
    private double redColorValue;

    private double lightValue;
    private double ultrasonicValue;

    //TODO: Calculate values
    private double blueColorValueMultiplicity = RobotData.blueMultiplicityValue;
    private double greenColorValueMultiplicity = RobotData.greenMultiplicityValue;
    private double redColorValueMultiplicity = RobotData.redMultiplicityValue;
    private double ultrasonicSubtractionValue = RobotData.ultrasonicCorrectionValue;

    public sensors(ColorSensor colorSensor, UltrasonicSensor ultrasonicSensor, LightSensor lightSensor) {
        this.colorSensor = colorSensor;
        this.ultrasonicSensor = ultrasonicSensor;
        this.lightSensor = lightSensor;
    }

    //Returns the values, since they were declared private.
    //The reason they're private is so
    //other applications don't accidentally overwrite them.

    public double getBlueColorValue() { return blueColorValue; }
    public double getGreenColorValue() { return greenColorValue; }
    public double getRedColorValue() { return redColorValue; }
    public double getLightValue() { return lightValue; }
    public double getUltrasonicValue() { return ultrasonicValue; }

    //Default method that runs when the thread is started. However, it will not continuously run,
    //which is what the while(true) loops does.
    @Override
    public void run() {
        while(true){
            colorFinder();
            ultrasonicFinder();
            lightFinder();
        }
    }

    //Updates the
    private void colorFinder(){
        double red = colorSensor.red();
        double blue = colorSensor.blue();
        double green = colorSensor.green();

        redColorValue = red * redColorValueMultiplicity;
        blueColorValue = blue * blueColorValueMultiplicity;
        greenColorValue = green * greenColorValueMultiplicity;
    }

    private void ultrasonicFinder(){
        //Gets
        double ultrasonic = ultrasonicSensor.getUltrasonicLevel();
        ultrasonicValue = ultrasonic - ultrasonicSubtractionValue;
    }

    private void lightFinder(){
        //Gets the amount of light. getLightDetected() scales the input from 0-1.
        double light = lightSensor.getLightDetected();
    }
}
