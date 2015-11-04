package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 9/16/2015 by Jacinda
 */
public class ControlServo {
    Telemetry telemetry;

    Servo servo;
    float SERVO_MIN_RANGE;
    float SERVO_MAX_RANGE;

    ControlServo(Telemetry telemetry, Servo servo, float SERVO_MIN_RANGE, float SERVO_MAX_RANGE)
    {
        this.telemetry=telemetry;
        this.servo=servo;
        this.SERVO_MIN_RANGE=SERVO_MIN_RANGE;
        this.SERVO_MAX_RANGE=SERVO_MAX_RANGE;
    }
    public void move(float position){
        float pos = Range.clip(position, SERVO_MIN_RANGE, SERVO_MAX_RANGE);
        servo.setPosition(pos);
        telemetry.addData("Servo Position", position);
    }
}
