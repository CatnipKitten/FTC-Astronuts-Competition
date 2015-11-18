package com.astronuts.library.opmodefinal;

import com.astronuts.library.RobotData;
import com.astronuts.library.chudsCode.SafeSnooze;
import com.astronuts.library.sensors.colorsensor.CScorrection;
import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Preescoot on 11/18/15.
 */
public class AutonomousBackUpBLUETEAM extends LinearOpMode{
    //Initializes the motors and power
    DcMotor motorRight;
    DcMotor motorLeft;
    final static double motorMaxPower = .2;


    //Initializes the servos
    Servo leftServo;
    Servo rightServo;
    Servo colorArm;
    double servoDelta = 0.01;
    double startPos = 0.0;
    double endLeft = 0.65;
    double endRight = 0.7;
    double endColor = 1.0;

    //Initializes the sensors.
    LightSensor lightSensor;
    UltrasonicSensor ultrasonic;
    ColorSensor color;
    DeviceInterfaceModule cdim;

    //Sets variable that is used for the color sensor channel.
    static final int LED_CHANNEL = 5;



    //Starts Initialization.
    @Override
    public void runOpMode () throws InterruptedException {
        //Maps the sensors.
        ultrasonic = hardwareMap.ultrasonicSensor.get("ultrasonic_sensor");
        lightSensor = hardwareMap.lightSensor.get("light_sensor");
        color = hardwareMap.colorSensor.get("color_sensor");

        //Maps the servos.
        leftServo = hardwareMap.servo.get("left_button");
        rightServo = hardwareMap.servo.get("right_button");
        colorArm = hardwareMap.servo.get("color_servo");


        //Maps the Device Interface Module
        cdim = hardwareMap.deviceInterfaceModule.get("Device Interface Module1");
        //Sets the channel for the color sensor.
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(LED_CHANNEL, false);

        //Maps the motors.
        motorRight = hardwareMap.dcMotor.get("right_drive");
        motorLeft = hardwareMap.dcMotor.get("left_drive");
        //Reverses the Left Motor.
        motorRight.setDirection(DcMotor.Direction.REVERSE);

        leftServo.setPosition(startPos);
        rightServo.setPosition(startPos);
        colorArm.setPosition(startPos);

        CScorrection cscorrection = new CScorrection();
        UltrasonicDistance ultrasonicDistance = new UltrasonicDistance(ultrasonic);

        //************************START!!!!!!!*************************
        waitForStart(); //Starts the actual program.

        //Allows the use of a delay.
        SafeSnooze.snooze(RobotData.timeDelay, 's');

        double whiteLine = .34;

        motorLeft.setPower(motorMaxPower);
        motorRight.setPower(motorMaxPower);

        Thread.sleep(3000);

        motorLeft.setPower(0);
        motorRight.setPower(0);

        while (lightSensor.getLightDetected() < whiteLine) {
            motorRight.setPower(-motorMaxPower);
            motorLeft.setPower(motorMaxPower);
        }

        while (ultrasonicDistance.getdistance('i') > 12) {
            motorRight.setPower(motorMaxPower);
            motorLeft.setPower(motorMaxPower);
        }

        colorArm.setPosition(endColor);

        if (cscorrection.redCorrected/cscorrection.blueCorrected < 1.2) {
            rightServo.setPosition(endRight);
            colorArm.setPosition(0.0);
            while (ultrasonicDistance.getdistance('i') >= 6.9) {
                motorRight.setPower(motorMaxPower);
                motorLeft.setPower(motorMaxPower);
            }
        }else {
            leftServo.setPosition(endLeft);
            colorArm.setPosition(0.0);
            while (ultrasonicDistance.getdistance('i') >= 6.9) {
                motorRight.setPower(motorMaxPower);
                motorLeft.setPower(motorMaxPower);
            }
        }

    }
}
