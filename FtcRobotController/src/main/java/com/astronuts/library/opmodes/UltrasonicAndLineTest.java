package com.astronuts.library.opmodes;

import android.widget.Switch;

import com.astronuts.library.RobotData;
import com.astronuts.library.movement.InitServo;
import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.robot.Robot;

public class UltrasonicAndLineTest extends OpMode{
    int flag = 1;

    UltrasonicSensor ultrasonicSensor;

    //LightSensor lightSensor;

    Servo color;
    Servo left;
    Servo right;

    double distance;

    DcMotor rightMotor;
    DcMotor leftMotor;
    double motorMaxPower = .1;

    InitServo leftButton;
    InitServo rightButton;
    InitServo colorArm;

    UltrasonicDistance ultrasonicDistance;

    //double light = .49;


    @Override
    public void init() throws NullPointerException {

        color = hardwareMap.servo.get(RobotData.color_servo);
        left = hardwareMap.servo.get(RobotData.left_button);
        right = hardwareMap.servo.get(RobotData.right_button);

        rightMotor = hardwareMap.dcMotor.get(RobotData.left_drive);
        leftMotor = hardwareMap.dcMotor.get(RobotData.right_drive);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftButton = new InitServo(left, 0.0, 0.79, 0.1);
        rightButton = new InitServo(right, 0.2, 1.0, 0.1);
        colorArm = new InitServo(color, 0.0, 1.0, 0.1);

        ultrasonicSensor = hardwareMap.ultrasonicSensor.get(RobotData.ultrasonic_sensor);

        ultrasonicDistance = new UltrasonicDistance(ultrasonicSensor);
    }

    @Override
    public void loop() throws NullPointerException{
        DbgLog.msg("Here1");
        telemetry.addData("distance", ultrasonicDistance.getdistance('i'));
        switch(flag){
            case 1:
                if(ultrasonicDistance.getdistance('r') > 0) {
                    distance = ultrasonicDistance.getdistance('i');
                }
                if(distance > 12.5){
                    DbgLog.msg("Here2");
                    leftMotor.setPower(-motorMaxPower);
                    rightMotor.setPower(-motorMaxPower);
                    DbgLog.msg("Here3");
                }else if(distance < 12){
                    DbgLog.msg("Here4");
                    leftMotor.setPower(motorMaxPower);
                    rightMotor.setPower(motorMaxPower);
                    DbgLog.msg("Here5");
                }
                if(distance < 12.5 && distance >12) {
                    rightMotor.setPower(0.0);
                    leftMotor.setPower(0.0);
                    flag = 2;
                }
                break;
        }

    }

}
