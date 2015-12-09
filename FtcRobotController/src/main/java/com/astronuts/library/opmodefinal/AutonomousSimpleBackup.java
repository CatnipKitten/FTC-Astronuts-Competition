package com.astronuts.library.opmodefinal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Preescoot on 11/21/15.
 */
public class AutonomousSimpleBackup extends LinearOpMode{
    DcMotor right;
    DcMotor left;
    final static double motorMaxPower = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        right = hardwareMap.dcMotor.get("right_drive");
        left = hardwareMap.dcMotor.get("left_drive");
        right.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        Thread.sleep(5000);

        left.setPower(motorMaxPower);
        right.setPower(motorMaxPower);

        Thread.sleep(4000);

        left.setPower(0.0);
        right.setPower(0.0);
    }
}
