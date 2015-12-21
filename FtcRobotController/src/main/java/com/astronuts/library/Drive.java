package com.astronuts.library;

import com.astronuts.library.movement.EncoderMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class Drive implements Runnable {
    private EncoderMotor encoderLeft;
    private EncoderMotor encoderRight;
    private UltrasonicSensor ultrasonicSensor;
    private ColorSensor colorSensor;
    private LightSensor lightSensor;
    private Thread thread;
    private String threadName = "DriveThread";

    private double lightValue;
    private double ultrasonicValue;
    private double blueColorValue;
    private double redColorValue;
    private double greenColorValue;

    public Drive(EncoderMotor encoderLeft, EncoderMotor encoderRight, UltrasonicSensor
            ultrasonicSensor,
                 ColorSensor colorSensor, LightSensor lightSensor) {
        this.encoderLeft = encoderLeft;
        this.encoderRight = encoderRight;
        this.ultrasonicSensor = ultrasonicSensor;
        this.colorSensor = colorSensor;
        this.lightSensor = lightSensor;
    }

    public void run(){
        lightValue = lightSensor.getLightDetected();
        blueColorValue = colorSensor.blue();
        redColorValue = colorSensor.red();
        greenColorValue = colorSensor.green();
        colorValueCorrections();
        ultrasonicCorrections();
    }

    public void driveByDistance(double d, char unit){
        double distance = d;
        switch(unit){
            case'i':
                d *= 2.54;
                break;
            default:
                break;
        }
        distanceToEncoders(d);
    }

    private double distanceToEncoders(double v){
        //TODO: Calculate how far one revolution of the motors is in terms of encoder ticks
        //v =
        return v;
    }

    private void colorValueCorrections(){

    }

    private void ultrasonicCorrections(){

    }
}
