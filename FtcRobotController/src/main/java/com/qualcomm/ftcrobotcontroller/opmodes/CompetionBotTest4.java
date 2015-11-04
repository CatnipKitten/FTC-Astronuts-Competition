package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/26/2015.
 */
public class CompetionBotTest4 extends OpMode {
    final static float MOTOR_MAX_POWER = (float) 1.0;

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor leftLift;
    DcMotor rightLift;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        leftLift = hardwareMap.dcMotor.get("left_lift");
        rightLift = hardwareMap.dcMotor.get("right_lift");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop(){
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;
        float leftL = gamepad2.left_stick_y;
        float rightL = gamepad2.right_stick_y;

        left = left * MOTOR_MAX_POWER;
        right = right * MOTOR_MAX_POWER;
        leftL = leftL * MOTOR_MAX_POWER;
        rightL = rightL * MOTOR_MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        left = Range.clip(left, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        leftL = Range.clip(leftL, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        rightL = Range.clip(rightL, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);

        motorLeft.setPower(left);
        motorRight.setPower(right);
        leftLift.setPower(leftL);
        rightLift.setPower(rightL);

        telemetry.addData("1-drive left power", left);
        telemetry.addData("2-drive right power", right);
        telemetry.addData("3-left lift power", leftL);
        telemetry.addData("4-right lift power", rightL);
    }
}
