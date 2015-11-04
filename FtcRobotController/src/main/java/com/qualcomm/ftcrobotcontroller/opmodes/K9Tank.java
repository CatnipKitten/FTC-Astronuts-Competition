package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/24/2015.
 */
public class K9Tank extends OpMode {
    final static float MOTOR_MAX_POWER = (float) 1.0;
    /*establishes the max power of the left and right motors*/

    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft = hardwareMap.dcMotor.get("motor_left");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop(){
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;

        left = left * MOTOR_MAX_POWER;
        right = right * MOTOR_MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        left = Range.clip(left, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);

        motorLeft.setPower(left);
        motorRight.setPower(right);

        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power", right);
    }
}
