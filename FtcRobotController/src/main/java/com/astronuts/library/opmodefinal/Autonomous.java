package com.astronuts.library.opmodefinal;

import com.astronuts.library.RobotData;
import com.astronuts.library.chudsCode.SafeSnooze;
import com.astronuts.library.movement.Drive;
import com.astronuts.library.movement.EncoderMotor;
import com.astronuts.library.movement.InitEncoder;
import com.astronuts.library.movement.InitServo;
import com.astronuts.library.sensors.colorsensor.CScorrection;
import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * This is an autonomous program that makes the robot follow a white line that leads up to the red
 * and blue lights, finds out which color one side is, then uses logic to find the team's color,
 * and pushes one of the buttons based off of the team color.
 * (hopefully XD)
 *
 *
 * Created by Prescott on 10/28/15.
 * Last Edited by Prescott on 10/28/15.
 */
public class Autonomous extends LinearOpMode {
    //Initializes the motors and power
    DcMotor motorRight;
    DcMotor motorLeft;
    final static double motorMaxPower = 20;

    //Initializes the servos
    Servo leftServo;
    Servo rightServo;
    Servo colorArm;
    InitServo servoLeft = new InitServo(leftServo, 0.1, 0.65, 0.01);
    InitServo servoRight = new InitServo(rightServo, 0.0, 0.7, 0.01);
    InitServo servoColor = new InitServo(colorArm, 0.0, 1.0, .01);

    //Initializes the sensors.
    LightSensor lightSensor;
    UltrasonicSensor ultrasonic;
    ColorSensor color;
    DeviceInterfaceModule cdim;

    //Sets variable that is used for the color sensor channel.
    static final int LED_CHANNEL = 5;

    //Initailizes the encoders.
    EncoderMotor left = new EncoderMotor(motorLeft);
    EncoderMotor right = new EncoderMotor(motorRight);
    InitEncoder encoder = new InitEncoder(left, right, motorMaxPower);

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
        cdim = hardwareMap.deviceInterfaceModule.get("dim");
        //Sets the channel for the color sensor.
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(LED_CHANNEL, false);

        //Maps the motors.
        motorRight = hardwareMap.dcMotor.get("right_drive");
        motorLeft = hardwareMap.dcMotor.get("left_drive");
        //Reverses the Left Motor.
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //Imports the Color Sensor and Ultrasonic Sensor classes
        CScorrection cscorrection = new CScorrection();
        UltrasonicDistance ultrasonicDistance = new UltrasonicDistance(ultrasonic);

        waitForStart(); //Starts the actual program.
        //Allows the use of a delay.
        SafeSnooze.snooze(RobotData.timeDelay, 's');

        //Initializes the servos
        servoLeft.init();
        servoRight.init();
        servoColor.init();

        //Moves the robot over to the line.
        Drive.driveByDistance(27, 'i', left, right);
        Drive.turnByAngle(-145, left, right);
        Drive.driveByDistance(82, 'i', left, right);

        //Change me!
        int whiteline = 0;
        int colordiff = 0;

        //Turns the robot to be along the line
        while(lightSensor.getLightDetected() < whiteline){
            Drive.turnByAngle(-10, left, right);
        }
        //Stops the robot at a certain distance away from the walls
        while(ultrasonicDistance.getdistance('i') <= 11.5){
            Drive.driveByDistance(1, 'c', left, right);
        }

        //Moves the servos down
        servoColor.move(1.0);
        cscorrection.getColors(color);

        //Senses the color of one side of the beacon and decides which color
        if (cscorrection.blueCorrected/cscorrection.redCorrected > colordiff) {
            servoColor.move(0.0);
            servoLeft.move(0.65);
            //move robot forwards
        }else{
            servoColor.move(0.0);
            servoRight.move(.7);
            //move robot forwards
        }
        //Moves robot away from beacon.
        Drive.driveByDistance(-16, 'i', left, right);


        }
    }
