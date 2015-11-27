package com.astronuts.library.opmodefinal;

import com.astronuts.library.Drive;
import com.astronuts.library.movement.EncoderMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

public class Tester_1 extends LinearOpMode {

    DcMotor LeftTemp;
    DcMotor RightTemp;

    EncoderMotor MotorLeft;
    EncoderMotor MotorRight;

    ColorSensor colorSensor;
    UltrasonicSensor ultrasonicSensor;
    LightSensor lightSensor;

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
