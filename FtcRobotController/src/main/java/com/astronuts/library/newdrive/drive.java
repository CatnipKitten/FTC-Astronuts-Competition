package com.astronuts.library.newdrive;

import com.astronuts.library.RobotData;
import com.astronuts.library.movement.EncoderMotor;

public class drive {
    private EncoderMotor encoderLeft;
    private EncoderMotor encoderRight;

    public drive(EncoderMotor encoderLeft, EncoderMotor encoderRight) {
        this.encoderLeft = encoderLeft;
        this.encoderRight = encoderRight;
    }

    //Default drive in centimeters
    public void driveByDistance(double distance){ driveByDistance(distance, 'c'); }
    public void driveByDistance(double distance, char unit){
        switch(unit){
            case'i':
                distance *= 2.54;
                break;
            default:
                break;
        }
        encoderLeft.move(distanceToEncoderTicks(distance), 0.5);
        encoderRight.move(distanceToEncoderTicks(distance), 0.5);
    }

    //Defaults to turning 180 degrees
    public void turnByAngle(double amt, char dir){ turnByAngle(amt, dir, 'd'); }
    public void turnByAngle(double amt, char dir, char unit){
        double var = amt; //Acts as a holder for the data
        switch(unit){
            case'r':
                var = Math.toDegrees(amt);
                break;
            case'd':
            default: var = amt;
                break;
        }
        if(dir == 'l') {
            encoderLeft.move(-degreesToEncoderTicks(var), 0.5);
            encoderRight.move(degreesToEncoderTicks(var), 0.5);
        }
        else{
            encoderLeft.move(degreesToEncoderTicks(var), 0.5);
            encoderRight.move(-degreesToEncoderTicks(var), 0.5);
        }
    }
    private int distanceToEncoderTicks(double amt){
        //TODO: calculate how far one revolution is in cm
        double t;
        t = (RobotData.encoderTicksPerRevolution * amt) / RobotData.distancePerRevolution;
        return (int) Math.round(t);
    }
    private int degreesToEncoderTicks(double amt){
        //TODO: Calculate how far one revolution is in degrees
        double t;
        t = (RobotData.encoderTicksPerRevolution * amt) / RobotData.degreesPerRevolution;
        return (int) Math.round(t);
    }
    public boolean isDone(){
        boolean complete = true;
        if(!(this.encoderLeft.isDone && this.encoderRight.isDone)){
            complete = false;
        }
        return complete;
    }
}
