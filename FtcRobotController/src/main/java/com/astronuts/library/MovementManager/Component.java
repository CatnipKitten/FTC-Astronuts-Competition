package com.astronuts.library.MovementManager;

import com.astronuts.library.movement.EncoderMotor;
import com.astronuts.library.MovementManager.newdrive.drive;
import com.qualcomm.ftccommon.DbgLog;

/**
 * Created by Chooooooooood on 12/2/2015.
 */

public class Component {

    protected boolean DriveTrain;
    protected short ID;
   // protected boolean isDone;
    private drive drive;
    private EncoderMotor motor;

    //Component for individual motor
    public Component(int ID, EncoderMotor Motor){
        this.ID = (short) ID;
        this.DriveTrain = false;
        this.motor = Motor;
    }

    //Component for drive train
    public Component(int ID, drive Drivetrain){
        this.ID = (short) ID;
        this.DriveTrain = true;
        this.drive = Drivetrain;
    }

    protected void driveByDistance(double distance, char unit){
        this.drive.driveByDistance(distance, unit);
    }

    protected void turnByAngle(double amt, char dir, char unit) {
        this.drive.turnByAngle(amt, dir, unit);
    }

    protected void moveEncoder(int distance, double power){
        this.motor.move(distance, power);
    }

    protected boolean isDone(){
        if(this.DriveTrain == true) {
            if (this.drive.isDone()) {
                DbgLog.msg("in Component.isdone drivetrain true");
                return true;

            }
        }
        else {
            if (this.DriveTrain == false) {
                if (this.motor.isDone()) {
                    DbgLog.msg("in Component.isdone not drivetrain true");
                    return true;
                }
            }
        }
        DbgLog.msg("in Component.isdone false");
        return false;
    }


}
