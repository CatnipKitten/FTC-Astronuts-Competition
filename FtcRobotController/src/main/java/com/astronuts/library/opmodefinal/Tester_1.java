package com.astronuts.library.opmodefinal;

import com.astronuts.library.movement.EncoderMotor;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Choood and Camus on 11/11/2015.
 */
public class Tester_1 extends LinearOpMode {

    DcMotor LeftTemp;
    DcMotor RightTemp;

    EncoderMotor MotorLeft;
    EncoderMotor MotorRight;



    @Override
    public void runOpMode() throws InterruptedException {
        LeftTemp = hardwareMap.dcMotor.get("left_drive");
        RightTemp = hardwareMap.dcMotor.get("right_drive");

        RightTemp.setDirection(DcMotor.Direction.REVERSE);

        MotorLeft = new EncoderMotor(LeftTemp);
        MotorRight = new EncoderMotor(RightTemp);

        waitForStart();

        MotorRight.move(1400, 0.5);
        MotorLeft.move(1400, 0.5);

        MotorRight.move(1400, -0.5);
        MotorLeft.move(1400, 0.5);

        MotorRight.move(1400, 0.5);
        MotorLeft.move(1400, -0.5);

        MotorRight.move(1400, 0.5);
        MotorLeft.move(1400, 0.5);

        waitForNextHardwareCycle();


    }


}
