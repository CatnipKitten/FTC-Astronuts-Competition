package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/25/2015.
 */
public class CompetionBotTest2 extends OpMode {
    final static float MOTOR_MAX_POWER = (float) 1.0;

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor leftLift;
    DcMotor rightLift;

    @Override
    public void init () {
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorRight = hardwareMap.dcMotor.get("motor_right");
        leftLift = hardwareMap.dcMotor.get ("left_lift");
        rightLift = hardwareMap.dcMotor.get ("right_lift");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop () {
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;
        boolean leftbumper = gamepad1.left_bumper;
        float lefttrigger = gamepad1.left_trigger;
        boolean rightbumper = gamepad1.right_bumper;
        float righttrigger = gamepad1.right_trigger;

        left = left * MOTOR_MAX_POWER;
        right = right * MOTOR_MAX_POWER;
        lefttrigger = lefttrigger * MOTOR_MAX_POWER;
        righttrigger = righttrigger * MOTOR_MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        left = Range.clip(left, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        lefttrigger = Range.clip(lefttrigger, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        righttrigger = Range.clip(righttrigger, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);

        motorLeft.setPower(left);
        motorRight.setPower(right);
        leftLift.setPower(lefttrigger);
        rightLift.setPower(righttrigger);

        if (leftbumper) {
            leftLift.setPower(-1.0);
        }
        else {
            leftLift.setPower(0.0);
        }
        if (rightbumper) {
            rightLift.setPower(-1.0);
        }
        else {
            rightLift.setPower(0.0);
        }
        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power", right);
        telemetry.addData("3-left lift positive", leftbumper);
        telemetry.addData("4-left lift negative", lefttrigger);
        telemetry.addData("5-right lift positive", rightbumper);
        telemetry.addData("6-right lift negative", righttrigger);
    }
}
