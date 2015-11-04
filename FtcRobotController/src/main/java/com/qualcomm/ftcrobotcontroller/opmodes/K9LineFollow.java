package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 10/24/2015.
 */
public class K9LineFollow extends OpMode {
    DcMotor motorLeft;
    DcMotor motorRight;

    float left;
    float right;

    LightSensor lightSensor;

    final static float MAX_POWER_LEFT = (float) 1.0 ;
    final static float MAX_POWER_RIGHT = (float) 1.0;

    @Override
    public void init() {
        motorLeft = hardwareMap.dcMotor.get("motor_left");
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        lightSensor = hardwareMap.lightSensor.get("light_sensor");
        lightSensor.enableLed(true);
    }

    @Override
    public void loop() {
        int light = lightSensor.getLightDetectedRaw();
        telemetry.addData("5-LightSensor", light);
        double left = 0;
        double right = 0;

        if (light < 90) {
            left = .3;
            right = -.1;
            motorLeft.setPower(left);
            motorRight.setPower(right);
        } else {
            left = -.3;
            right = .15;
            motorLeft.setPower(left);
            motorRight.setPower(right);

        }
        left = Range.clip(left, -MAX_POWER_RIGHT, MAX_POWER_RIGHT);
        right = Range.clip(right, -MAX_POWER_LEFT, MAX_POWER_LEFT);

        telemetry.addData("1-motor left power", motorLeft);
        telemetry.addData("2-motor right power",motorRight);
    }
}
