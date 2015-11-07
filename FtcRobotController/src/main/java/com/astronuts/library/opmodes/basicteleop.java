package com.astronuts.library.opmodes;

import com.astronuts.library.movement.InitServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class basicteleop extends OpMode{
    Servo leftServo; //Initializes servos
    Servo rightServo;
    InitServo servoLeft = new InitServo(leftServo, 0.1, 0.65, 0.01); //Defines the ranges for movement and the delta
    InitServo servoRight = new InitServo(rightServo, 0.0, 0.7, 0.01);

    DcMotor motorRight; //Initializes the motors
    DcMotor motorLeft;
    final static double motorMaxPower = 0.2; //Sets the motor's max power

    @Override
    public void init() {
        leftServo = hardwareMap.servo.get("servo_left"); //Maps the servos to the hardware
        rightServo = hardwareMap.servo.get("servo_right");
        servoRight.init(); //Sets the servos to the minimum positions
        servoLeft.init();

        motorRight = hardwareMap.dcMotor.get("motor_right"); //Maps the motors
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorRight.setDirection(DcMotor.Direction.REVERSE); //Reverses the direction of the right motor
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            servoLeft.move('+'); //Adds to the left servo's position
        }
        if(gamepad1.y){
            servoLeft.move('-'); //Subtracts from the left servo's position
        }
        if(gamepad1.x){
            servoRight.move('+'); //Adds to the right servo's position
        }
        if(gamepad1.b){
            servoRight.move('-'); //Subtracts from the right servo's position
        }

        double leftMotorPower = Range.clip(gamepad1.left_stick_y*motorMaxPower, -motorMaxPower, motorMaxPower); //Sets the motor power values for the current cycle
        double rightMotorPower = Range.clip(gamepad1.right_stick_y*motorMaxPower, -motorMaxPower, motorMaxPower);

        motorLeft.setPower(leftMotorPower); //Passes the motor power values to the motors
        motorRight.setPower(rightMotorPower);
    }
}
