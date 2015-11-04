package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/24/2015.
 */
public class K9Control extends OpMode {
    /*Declare servos*/
    Servo arm;
    Servo claw;
    /*Declare motors*/
    DcMotor motorRight;
    DcMotor motorLeft;

    /*Set the min and max values for the arm servo*/
    final static double ARM_MIN_RANGE = 0.4;
    final static double ARM_MAX_RANGE = 0.94;

    /*Set the min and max values for the claw servo*/
    final static double CLAW_MIN_RANGE = 0.5;
    final static double CLAW_MAX_RANGE = 1.0;

    /*Initial position of servos*/
    double armPosition = ARM_MIN_RANGE;
    double clawPosition = CLAW_MIN_RANGE;

    /*the rate at which the servo moves*/
    double armDelta = 0.01;
    double clawDelta = 0.01;

    final static float MOTOR_MAX_POWER= (float)1.0;

    @Override
    public void init () {

        arm = hardwareMap.servo.get("servo_arm");
        claw = hardwareMap.servo.get("servo_claw");
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorRight = hardwareMap.dcMotor.get("motor_right");

        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override

    public void loop() {
        if (gamepad1.a) {
            armPosition += armDelta;
        }
        if (gamepad1.y) {
            armPosition -= armDelta;
        }
        if (gamepad1.x) {
            clawPosition += clawDelta;
        }
        if (gamepad1.b) {
            clawPosition -= clawDelta;
        }

        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);

        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        left = left * MOTOR_MAX_POWER;
        right = right * MOTOR_MAX_POWER;

        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

        right = Range.clip(right, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        left = Range.clip(left, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);

        motorLeft.setPower(left);
        motorRight.setPower(right);

        telemetry.addData("3-arm position", armPosition);
        telemetry.addData("4-claw position", clawPosition);

        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power",right);
    }
}
