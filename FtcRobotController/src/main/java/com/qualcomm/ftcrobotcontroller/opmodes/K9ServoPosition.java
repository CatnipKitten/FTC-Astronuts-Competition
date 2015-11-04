package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * finding out the servo positions for the modified K9
 * Created by robotics on 10/24/2015.
 */
public class K9ServoPosition extends OpMode{
    final static double LEFT_MIN_RANGE = 0.1;
    final static double LEFT_MAX_RANGE = 0.65;
    final static double RIGHT_MIN_RANGE = 0.0;
    final static double RIGHT_MAX_RANGE = 0.7;

    double leftPosition = LEFT_MIN_RANGE;
    double rightPosition = RIGHT_MAX_RANGE;

    double leftDelta = 0.01;
    double rightDelta = 0.01;

    Servo left;
    Servo right;

    @Override
    public void init () {

        left = hardwareMap.servo.get("servo_left");
        right = hardwareMap.servo.get("servo_right");

        left.setPosition(leftPosition);
        right.setPosition(rightPosition);
    }
    @Override
    public void loop() {
        if (gamepad1.a) {
            leftPosition += leftDelta;
        }
        if (gamepad1.y) {
            leftPosition -= leftDelta;
        }
        if (gamepad1.x) {
            rightPosition += rightDelta;
        }
        if (gamepad1.b) {
            rightPosition -= rightDelta;
        }

        leftPosition = Range.clip(leftPosition, LEFT_MIN_RANGE, LEFT_MAX_RANGE);
        rightPosition = Range.clip(rightPosition, RIGHT_MIN_RANGE, RIGHT_MAX_RANGE);

        left.setPosition(leftPosition);
        right.setPosition(rightPosition);

        telemetry.addData("1-left position", leftPosition);
        telemetry.addData("2-right position", rightPosition);
    }
}