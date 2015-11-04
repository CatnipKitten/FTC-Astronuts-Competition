package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Robot control: teleop, throttle = left stick, direction = right stick
 * Created by robotics on 10/24/2015.
 */
public class K9AltDrive extends OpMode {
    final static float MAX_POWER = (float) 1.0;
    final static float MOTOR_MAX = (float) 1.0;

    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft = hardwareMap.dcMotor.get("motor_left") ;

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {

        float throttle = -gamepad1.left_stick_y;
        float direction = -gamepad1.right_stick_x;
        float left = throttle + direction * MAX_POWER;
        float right = throttle - direction * MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX, MOTOR_MAX) ;
        left = Range.clip(left, -MOTOR_MAX, MOTOR_MAX) ;

        motorLeft.setPower(left);
        motorRight.setPower(right);

        telemetry.addData("1-motor left power",left);
        telemetry.addData("2-motor right power", right);
    }
}
