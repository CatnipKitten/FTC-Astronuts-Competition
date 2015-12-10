package com.astronuts.library.opmodefinal;

import com.astronuts.library.RobotData;
import com.astronuts.library.chudsCode.SafeSnooze;
import com.astronuts.library.movement.Drive;
import com.astronuts.library.movement.EncoderMotor;
import com.astronuts.library.movement.InitEncoder;
import com.astronuts.library.movement.InitServo;
import com.astronuts.library.sensors.colorsensor.CScorrection;
import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.robot.Robot;

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
public class AutonomousFINALFORBOTHTEAM extends OpMode {
    //Creates the motor objects and power variable
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorArmLower;
    DcMotor motorArmUpper;
    DcMotor shoulder;
    final static double motorMaxPower = 100;

    //Creates Encoder objects
    EncoderMotor leftEncoder;
    EncoderMotor rightEncoder;
    InitEncoder encoder;

    //Creates the servo objects
    Servo left;
    Servo right;
    Servo colorServo;
    InitServo leftServo;
    InitServo rightServo;
    InitServo colorArm;

    //Creates the sensor objects
    LightSensor lightSensor;
    UltrasonicSensor ultrasonic;
    ColorSensor color;
    double distance;

    int flag = 1;

    //Sets variables for new instances of used classes
    UltrasonicDistance ultrasonicDistance;
    CScorrection cscorrection;

    @Override
    public void init() {
        //Maps the sensors.
        ultrasonic = hardwareMap.ultrasonicSensor.get(RobotData.ultrasonic_sensor);
        lightSensor = hardwareMap.lightSensor.get(RobotData.light_sensor);
        color = hardwareMap.colorSensor.get(RobotData.color_sensor);

        //Maps the servos.
        left = hardwareMap.servo.get(RobotData.left_button);
        right = hardwareMap.servo.get(RobotData.right_button);
        colorServo = hardwareMap.servo.get(RobotData.color_servo);

        //Maps the motors.
        motorRight = hardwareMap.dcMotor.get(RobotData.left_drive);
        motorLeft = hardwareMap.dcMotor.get(RobotData.right_drive);
        motorArmLower = hardwareMap.dcMotor.get(RobotData.lower_elbow);
        motorArmUpper = hardwareMap.dcMotor.get(RobotData.upper_elbow);
        shoulder = hardwareMap.dcMotor.get(RobotData.shoulder);
        //Reverses the Left Motor.
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //Initializes Encoders
        leftEncoder = new EncoderMotor(motorLeft);
        rightEncoder = new EncoderMotor(motorRight);
        encoder = new InitEncoder(leftEncoder, rightEncoder, motorMaxPower);

        //Initializes Servos
        leftServo = new InitServo(left, 0.0, 0.65, 0.01);
        rightServo = new InitServo(right, 0.0, 0.7, 0.01);
        colorArm = new InitServo(colorServo, 0.0, 1.0, 0.01);

        //Starts new instances of used classes
        cscorrection = new CScorrection();
        ultrasonicDistance = new UltrasonicDistance(ultrasonic);
    }

    @Override
    public void loop() {
        //Initializes the servos
        leftServo.init();
        rightServo.init();
        colorArm.init();


        //Moves the robot close to the white line
        Drive.driveByDistance(27, 'i', leftEncoder, rightEncoder);
        if (RobotData.teamColor == 1) {
            Drive.turnByAngle(-145, leftEncoder, rightEncoder);
        } else {
            Drive.turnByAngle(145, leftEncoder, rightEncoder);
        }
        Drive.driveByDistance(82, 'i', leftEncoder, rightEncoder);

        //Variables for things on the floor
        double whiteLine = 0.49;
        double colorDiff = 1.21;


        //Turns the robot to be along the white line
        while (lightSensor.getLightDetected() > whiteLine) {
            if (RobotData.teamColor == 1) {
                Drive.turnByAngle(-10, leftEncoder, rightEncoder);
            } else {
                Drive.turnByAngle(10, leftEncoder, rightEncoder);
            }

        }
        //Stops the robot at a certain distance away from the walls
        switch(flag) {
            case 1:
                if (ultrasonicDistance.getdistance('r') > 0) {
                    distance = ultrasonicDistance.getdistance('i');
                }
                if (distance > 12.5) {
                    motorLeft.setPower(-motorMaxPower);
                    motorRight.setPower(-motorMaxPower);
                } else if (distance < 12) {
                    motorLeft.setPower(motorMaxPower);
                    motorRight.setPower(motorMaxPower);
                }
                if (distance < 12.5 && distance > 12) {
                    motorLeft.setPower(0.0);
                    motorRight.setPower(0.0);
                    flag = 2;
                }
                break;
        }
        //Moves the servo down and gets the color value
        colorArm.move(1.0);
        cscorrection.getColors(color);

        //CHANGE ME!!!! I'M A PLACE HOLDER!!!!!
        int distanceFromArmToButton = 0;

        //Senses the color of one side of the beacon and decides which color
        if (RobotData.teamColor == 1) {
            if (cscorrection.redCorrected / cscorrection.blueCorrected > colorDiff) {
                colorArm.move(0.0);
                leftServo.move(0.65);
                Drive.driveByDistance(distanceFromArmToButton, 'i', leftEncoder, rightEncoder);
            } else {
                colorArm.move(0.0);
                rightServo.move(0.7);
                Drive.driveByDistance(distanceFromArmToButton, 'i', leftEncoder, rightEncoder);
            }
        } else {
            if (cscorrection.redCorrected / cscorrection.blueCorrected < colorDiff) {
                colorArm.move(0.0);
                leftServo.move(0.65);
                Drive.driveByDistance(distanceFromArmToButton, 'i', leftEncoder, rightEncoder);
            } else {
                colorArm.move(0.0);
                rightServo.move(0.7);
                Drive.driveByDistance(distanceFromArmToButton, 'i', leftEncoder, rightEncoder);
            }
        }
    }
}

