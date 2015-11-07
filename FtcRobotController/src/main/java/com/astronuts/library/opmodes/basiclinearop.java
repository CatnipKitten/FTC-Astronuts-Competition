package com.astronuts.library.opmodes;

import com.astronuts.library.RobotData;
import com.astronuts.library.chudsCode.SafeSnooze;
import com.astronuts.library.movement.Drive;
import com.astronuts.library.movement.EncoderMotor;
import com.astronuts.library.movement.InitEncoder;
import com.astronuts.library.movement.InitServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class basiclinearop extends LinearOpMode {
    DcMotor motorRight; //Initializes the motors
    DcMotor motorLeft;
    final static double motorMaxPower = 20; //Value is represented as a percent

    Servo leftServo; //Initializes the servos
    Servo rightServo;
    InitServo servoLeft = new InitServo(leftServo, 0.1, 0.65, 0.01); //Initializes the servos with the max ranges and delta value
    InitServo servoRight = new InitServo(rightServo, 0.0, 0.7, 0.01);

    EncoderMotor left = new EncoderMotor(motorLeft); //Initializes the encoders for the motors
    EncoderMotor right = new EncoderMotor(motorRight);

    InitEncoder encoder = new InitEncoder(left, right, motorMaxPower); //Enables easy use of the encoders

    @Override
    public void runOpMode() throws InterruptedException {
        motorRight = hardwareMap.dcMotor.get("motor_right"); //Maps the motors to the hardware
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorLeft.setDirection(DcMotor.Direction.REVERSE); //Reverses the direction of the left motor

        leftServo = hardwareMap.servo.get("servo_left"); //Maps the servos
        rightServo = hardwareMap.servo.get("servo_right");
        servoLeft.init(); //Sets the servos to the minimum position
        servoRight.init();

        waitForStart(); //Waits for the start button to be pressed
        SafeSnooze.snooze(RobotData.timeDelay, 's'); //If the user defined a delay through the buttons, this will cause the robot to wait.

        encoder.moveManual(); //Moves the robot with the aforementioned power level.
        encoder.moveManual(0.2); //Moves with a custom power level.

        Drive.driveTo(5, 5, "c", left, right);
    }
}
