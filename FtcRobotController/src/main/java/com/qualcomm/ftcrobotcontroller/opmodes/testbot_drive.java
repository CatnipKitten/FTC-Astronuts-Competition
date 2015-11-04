package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 9/30/2015.
 */
public class testbot_drive extends OpMode {
    final static float MAX_POWER = (float) 1.0;
    final static float MOTOR_MAX = (float) 1.0;

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor grab;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_right");
        motorLeft = hardwareMap.dcMotor.get("motor_left") ;
        grab = hardwareMap.dcMotor.get("grabber");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {

        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

        left = left * MAX_POWER;
        right = right * MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX, MOTOR_MAX) ;
        left = Range.clip(left, -MOTOR_MAX, MOTOR_MAX) ;

        motorLeft.setPower(left);
        motorRight.setPower(right);

        boolean left_bumper = gamepad1.left_bumper;
        boolean right_bumper = gamepad1.right_bumper;

        if(left_bumper){
           grab.setPower(1.0);
        }
        if(right_bumper){
            grab.setPower(-1.0);
        }
        if(!right_bumper && !left_bumper){
            grab.setPower(0.0);
        }

        telemetry.addData("1-motor left power",left);
        telemetry.addData("2-motor right power", right);
        telemetry.addData("left_bumper", left_bumper);
        telemetry.addData("right_bumper", right_bumper);
    }
}
