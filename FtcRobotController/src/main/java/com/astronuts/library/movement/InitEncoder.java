package com.astronuts.library.movement;

public class InitEncoder {
    private EncoderMotor encoderMotorLeft; //Variable holder for the encoder motor
    private EncoderMotor encoderMotorRight;
    private double power; //Variable for the power level

    //Constructor
    public InitEncoder(EncoderMotor encoderMotorLeft, EncoderMotor encoderMotorRight, double power) {
        this.encoderMotorLeft = encoderMotorLeft;
        this.encoderMotorRight = encoderMotorRight;
        this.power = power;
    }

    //Move function
    public void move(double distance, char unit) {
        Drive.driveByDistance(distance, unit, power, encoderMotorLeft, encoderMotorRight);
    }

    //Move manually function
    public void moveManual(){
        encoderMotorLeft.moveManual(power);
        encoderMotorRight.moveManual(power);
    }

    //Move manually with a power level
    public void moveManual(double powerLevel){
        encoderMotorLeft.moveManual(powerLevel);
        encoderMotorRight.moveManual(powerLevel);
    }
}
