package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/25/2015.
 */
public class CompetionBotTest1 extends OpMode {

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
    public void loop (){
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;
        boolean x = gamepad1.x;
        boolean y = gamepad1.y;
        boolean a = gamepad1.a;
        boolean b = gamepad1.b;

        left = left * MOTOR_MAX_POWER;
        right = right * MOTOR_MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        left = Range.clip(left, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);

        motorLeft.setPower(left);
        motorRight.setPower(right);

        if (x) {
            leftLift.setPower(1.0);
        }
        else {
            leftLift.setPower(0.0);
        }
        if (y) {
            leftLift.setPower(-1.0);
        }
        else {
            leftLift.setPower(0.0);
        }
        if (a){
            rightLift.setPower(1.0);
        }
        else {
            rightLift.setPower(0.0);
        }
        if (b) {
            rightLift.setPower(-1.0);
        }
        else {
            rightLift.setPower(0.0);
        }

        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power", right);
        telemetry.addData("3-left lift positive", x);
        telemetry.addData("4-left lift negative", y);
        telemetry.addData("5-right lift positive", a);
        telemetry.addData("6-right lift negative", b);
    }
}
