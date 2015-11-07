package com.astronuts.library.opmodes;

import com.astronuts.library.sensors.colorsensor.CScorrection;
import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by CSTL User on 10/24/2015.
 */
public class K9LinearOp extends LinearOpMode {

    double armPos;
    double clawPos;
    double servoRightPos;
    double seroLeftPos;

    DcMotor motorRight;
    DcMotor motorLeft;
    Servo servoClaw;
    Servo servoArm;
    Servo servoRight;
    Servo servoLeft;
    ColorSensor colorSensor;
    UltrasonicSensor ultraSonic;


    @Override
    public void runOpMode() throws InterruptedException {
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        servoArm = hardwareMap.servo.get("servo_arm");
        servoClaw = hardwareMap.servo.get("servo_claw");
        servoLeft = hardwareMap.servo.get("servo_left");
        servoRight = hardwareMap.servo.get("servo_right");
        ultraSonic = hardwareMap.ultrasonicSensor.get("ultrasonic_sensor");
        colorSensor = hardwareMap.colorSensor.get("color_sensor");

        CScorrection cscorrection = new CScorrection();
        UltrasonicDistance ultrasonicDistance = new UltrasonicDistance(ultraSonic);

        waitForStart();

        while(true) {

            ultrasonicDistance.getdistance('i');
            cscorrection.getColors(colorSensor);
            float redValue = cscorrection.redCorrected;
            float blueValue = cscorrection.blueCorrected;
            float greenValue = cscorrection.greenCorrected;

            telemetry.addData("Ultrasonic_Sensor_Centimeters", ultrasonicDistance.getdistance('c'));
            telemetry.addData("Ultrasonic_Sensor_Inches", ultrasonicDistance.getdistance('i'));
            telemetry.addData("Red_Value", redValue);
            telemetry.addData("Blue_Value", blueValue);
            telemetry.addData("Green_Value", greenValue);
            telemetry.addData("Red_Raw", colorSensor.red());
            telemetry.addData("Blue_Raw", colorSensor.blue());
            telemetry.addData("Green_Raw", colorSensor.green());
            waitForNextHardwareCycle();
        }
    }
}
