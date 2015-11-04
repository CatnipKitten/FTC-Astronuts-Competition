package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/25/2015.
 */
public class CompetionBotTest3 extends OpMode {
    final static float MOTOR_MAX_POWER = (float) 1.0;
    /*establishes the max power of the left and right motors*/

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
        float leftL = gamepad1.left_trigger;
        float rightL = gamepad1.right_trigger;
        boolean leftL2 = gamepad1.left_bumper;
        boolean rightL2 = gamepad1.right_bumper;

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

        if (leftL2) {
            leftLift.setPower(-1.0);
        }
        else{
            leftLift.setPower(0.0);
        }
        if (rightL2) {
            rightLift.setPower(-1.0);
        }
        else {
            rightLift.setPower(0.0);
        }

        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power", right);
    }
}
