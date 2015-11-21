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
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Preescoott on 11/18/15.
 */
public class AutonomousBLUETEAM extends LinearOpMode {
    //Initializes the motors and power
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorArmLower;
    DcMotor motorArmUpper;
    DcMotor shoulder;
    final static double motorMaxPower = 20;

    EncoderMotor left;
    EncoderMotor right;

    //Initializes the servos
    Servo leftServo;
    Servo rightServo;
    Servo colorArm;

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
        motorArmLower = hardwareMap.dcMotor.get("lower_elbow");
        motorArmUpper = hardwareMap.dcMotor.get("upper_elbow");
        shoulder = hardwareMap.dcMotor.get("shoulder");
        //Reverses the Left Motor.
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //Initializes Encoders
        left = new EncoderMotor(motorLeft);
        right = new EncoderMotor(motorRight);
        InitEncoder encoder = new InitEncoder(left, right, motorMaxPower);

        InitServo finalLeft = new InitServo(leftServo, 0.0, 0.65, 0.01);
        InitServo finalRight = new InitServo(rightServo, 0.0, 0.7, 0.01);
        InitServo finalColor = new InitServo(colorArm, 0.0, 1.0, 0.01);

        //Imports the Color Sensor and Ultrasonic Sensor classes
        CScorrection cscorrection = new CScorrection();
        UltrasonicDistance ultrasonicDistance = new UltrasonicDistance(ultrasonic);

        //************************************START!!!!!!************************************
        waitForStart();

        //Allows the use of a delay.
        SafeSnooze.snooze(RobotData.timeDelay, 's');

        //Initializes the servos
        finalLeft.init();
        finalRight.init();
        finalColor.init();

        //Moves the robot over to the line.
        Drive.driveByDistance(27, 'i', left, right);
        Drive.turnByAngle(145, left, right);
        Drive.driveByDistance(82, 'i', left, right);

        //Change me!
        double whiteLine = 0.34;
        double colorDiff = 1.21;

        //Turns the robot to be along the line
        while(lightSensor.getLightDetected() < whiteLine){
            Drive.turnByAngle(10, left, right);
        }
        //Stops the robot at a certain distance away from the walls
        while(ultrasonicDistance.getdistance('i') <= 12){
            Drive.driveByDistance(1, 'c', left, right);
        }

        //Moves the servos down
        finalColor.move(1.0);
        cscorrection.getColors(color);

        //*****************CHANGE ME!!!! I'M A PLACE HOLDER!!!!!******************
        int distanceFromArmToButton = 0;

        //Senses the color of one side of the beacon and decides which color
        if (cscorrection.redCorrected/cscorrection.blueCorrected < colorDiff) {
            finalColor.move(0.0);
            finalLeft.move(0.65);
            Drive.driveByDistance(distanceFromArmToButton, 'i', left, right);
        }else {
            finalColor.move(0.0);
            finalRight.move(0.7);
            Drive.driveByDistance(distanceFromArmToButton, 'i', left, right);
        }

    }
}
