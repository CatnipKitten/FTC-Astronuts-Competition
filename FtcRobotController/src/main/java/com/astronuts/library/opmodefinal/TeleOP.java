package com.astronuts.library.opmodefinal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/28/2015.
 */
public class TeleOP extends OpMode {
    final static float MOTOR_MAX_POWER = (float) 1.0;

    final static double LEFT_BUTTON_MIN_RANGE = 0.0;
    final static double LEFT_BUTTON_MAX_RANGE = 0.79;
    final static double RIGHT_BUTTON_MIN_RANGE = 0.2;
    final static double RIGHT_BUTTON_MAX_RANGE = 1.0;

    final static double LEFT_LEVER_MIN_RANGE =0.0;
    final static double LEFT_LEVER_MAX_RANGE = 1.0;
    final static double RIGHT_LEVER_MIN_RANGE = 0.0;
    final static double RIGHT_LEVER_MAX_RANGE = 1.0;

    final static double CSMIN_RANGE = 0.0;
    final static double CSMAX_RANGE = 1.0;

    double leftButtonPosition = LEFT_BUTTON_MIN_RANGE;
    double rightButtonPosition = RIGHT_BUTTON_MAX_RANGE;
    double leftLeverPosition = LEFT_LEVER_MIN_RANGE;
    double rightLeverPosition = RIGHT_LEVER_MAX_RANGE;
    double csPosition = CSMAX_RANGE;

    double leftDelta = 0.01;
    double rightDelta = 0.01;
    double csDelta = 0.01;

    DcMotor rightDrive;
    DcMotor leftDrive;
    DcMotor upperElbow;
    DcMotor lowerElbow;
    DcMotor shoulder;/*
    DcMotor basket;*/
    Servo leftButton;
    Servo rightButton;
    Servo leftLever;
    Servo rightLever;
    Servo colorServo;

    @Override
    public void init() {
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        upperElbow = hardwareMap.dcMotor.get("upper_elbow");
        lowerElbow = hardwareMap.dcMotor.get("lower_elbow");
        shoulder = hardwareMap.dcMotor.get("shoulder");
        /*basket = hardwareMap.dcMotor.get("basket");*/
        leftButton = hardwareMap.servo.get("left_button");
        rightButton = hardwareMap.servo.get("right_button");
        leftLever = hardwareMap.servo.get("left_lever");
        rightLever = hardwareMap.servo.get("right_lever");
        colorServo = hardwareMap.servo.get("color_servo");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftButton.setPosition(leftButtonPosition);
        rightButton.setPosition(rightButtonPosition);
        leftLever.setPosition(leftLeverPosition);
        rightLever.setPosition(rightLeverPosition);
        colorServo.setPosition(csPosition);
    }

    @Override
    public void loop(){
        float leftD = gamepad1.left_stick_y;
        float rightD = gamepad1.right_stick_y;
        float upperE = -gamepad2.left_stick_y;
        float lowerE = -gamepad2.left_stick_y;
        float shoulderF = -gamepad2.right_stick_y;
        /*float rightL2 = gamepad2.right_stick_y;*/

        rightD = (float)scaleInput(rightD);
        leftD =  (float)scaleInput(leftD);
        upperE = (float)scaleInput(upperE);
        lowerE = (float)scaleInput(lowerE);
        shoulderF = (float)scaleInput(shoulderF);

        /*leftD = leftD * MOTOR_MAX_POWER;
        rightD = rightD * MOTOR_MAX_POWER;
        upperE = upperE * MOTOR_MAX_POWER;
        lowerE = lowerE * MOTOR_MAX_POWER;
        shoulderF = shoulderF * MOTOR_MAX_POWER;
        rightL2 = rightL2 * MOTOR_MAX_POWER;*/

        if (gamepad1.b) {
            leftButtonPosition += leftDelta;
        }
        if (gamepad1.y) {
            leftButtonPosition -= leftDelta;
        }
        if (gamepad1.x) {
            rightButtonPosition += rightDelta;
        }
        if (gamepad1.a) {
            rightButtonPosition -= rightDelta;
        }
        if (gamepad1.dpad_left) {
            csPosition += csDelta;
        }
        if (gamepad1.dpad_right) {
            csPosition -= csDelta;
        }
        if (gamepad2.b) {
            leftLeverPosition += leftDelta;
        }
        if (gamepad2.y) {
            leftLeverPosition -= leftDelta;
        }
        if (gamepad2.x) {
            rightLeverPosition += rightDelta;
        }
        if (gamepad2.a) {
            rightLeverPosition -= rightDelta;
        }
        /*if (gamepad1.dpad_up) {
            basket.setPower(1.0);
        }
        if (gamepad1.dpad_down) {
            basket.setPower(-1.0);
        }
        if (!gamepad1.dpad_up && !gamepad1.dpad_down) {
            basket.setPower(0.0);
        }*/

        rightD = Range.clip(rightD, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        leftD = Range.clip(leftD, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        upperE = Range.clip(upperE, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        lowerE = Range.clip(lowerE, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        shoulderF = Range.clip(shoulderF, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        /*rightL2 = Range.clip(rightL2, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);*/
        leftButtonPosition = Range.clip(leftButtonPosition, LEFT_BUTTON_MIN_RANGE, LEFT_BUTTON_MAX_RANGE);
        rightButtonPosition = Range.clip(rightButtonPosition, RIGHT_BUTTON_MIN_RANGE, RIGHT_BUTTON_MAX_RANGE);
        leftLeverPosition = Range.clip(leftLeverPosition, LEFT_LEVER_MIN_RANGE, RIGHT_LEVER_MAX_RANGE);
        rightLeverPosition = Range.clip(rightLeverPosition, RIGHT_LEVER_MIN_RANGE, RIGHT_LEVER_MAX_RANGE);
        csPosition = Range.clip(csPosition, CSMIN_RANGE, CSMAX_RANGE);

        leftDrive.setPower(leftD);
        rightDrive.setPower(rightD);
        upperElbow.setPower(upperE);
        lowerElbow.setPower(lowerE);
        shoulder.setPower(shoulderF);
        /*rightLift2.setPower(rightL2);*/

        leftButton.setPosition(leftButtonPosition);
        rightButton.setPosition(rightButtonPosition);
        leftLever.setPosition(leftLeverPosition);
        rightLever.setPosition(rightLeverPosition);
        colorServo.setPosition(csPosition);

        telemetry.addData("1-drive left power", leftD);
        telemetry.addData("2-drive right power", rightD);
        telemetry.addData("3-left lift power", upperE);
        telemetry.addData("4-left lift 2 power", lowerE);
        telemetry.addData("5-right lift power", shoulderF);
        /*telemetry.addData("6-right lift 2 power", rightL2);
        telemetry.addData("7-basket motor positive", gamepad1.dpad_up);
        telemetry.addData("8-basket motor negative", gamepad1.dpad_down);*/
        telemetry.addData("9-left button servo position", leftButtonPosition);
        telemetry.addData("10-right button servo position", rightButtonPosition);
        telemetry.addData("11-left lever servo position", leftLeverPosition);
        telemetry.addData("12-right lever servo postion", rightLeverPosition);
        telemetry.addData("13-color sensor servo position", csPosition);
    }
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}