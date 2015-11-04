package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by robotics on 10/24/2015.
 */
public class K9AutoDriveTest extends LinearOpMode {
    /*Declare motors*/
    DcMotor motorLeft;
    DcMotor motorRight;

    @Override
    public void runOpMode() throws InterruptedException {
        /*initialize motors*/
        motorRight =hardwareMap.dcMotor.get("motor_right");
        motorLeft =hardwareMap.dcMotor.get("motor_left");
        /*Reverse left drive motor */
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        /*Drive forwards*/
        motorLeft.setPower(0.5);
        motorRight.setPower(0.5);
        sleep(1900);//the # in the parenthesis is time

        /*Turn*/
        motorLeft.setPower(0.5);
        motorRight.setPower(-0.5);
        sleep(550);

        /*Drive forward again*/
        motorLeft.setPower(0.5);
        motorRight.setPower(0.5);
        sleep(1550);

        /*stop*/
        motorLeft.setPower(0.0);
        motorRight.setPower(0.0);

    }
}
