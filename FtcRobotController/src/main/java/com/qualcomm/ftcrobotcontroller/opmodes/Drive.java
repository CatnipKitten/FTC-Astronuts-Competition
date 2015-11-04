package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics on 9/9/2015.
 */

public class Drive {
    public void  sleep1Msec(int timeDelay){
        try{Thread.sleep(timeDelay); }
        catch (InterruptedException ex)
        {Thread.currentThread().interrupt(); }
    }
    public void move (DcMotor left, DcMotor right, float power, int milliseconds){
        left.setPower(power);
        right.setPower(power);


    }
}
