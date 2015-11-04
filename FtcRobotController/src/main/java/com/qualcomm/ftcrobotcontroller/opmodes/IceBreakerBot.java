package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by robotics on 9/9/2015.
 * Robot control: IceBreakerBot tank dive w/ 2 wheel motors per gamepad stick, and buttons y and a
 * for basket control
 */
public class IceBreakerBot extends OpMode {
    final static float MOTOR_MAX_POWER = (float) 1.0;
    /*setting up the variable "MOTOR_MAX_POWER" for the drive motors*/

    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    DcMotor basket;
    /*declaring the motors*/

    @Override
    public void init(){
        motorRight1 = hardwareMap.dcMotor.get("motor_right1");
        motorRight2 = hardwareMap.dcMotor.get("motor_right2");
        motorLeft1 = hardwareMap.dcMotor.get("motor_left1");
        motorLeft2 = hardwareMap.dcMotor.get("motor_left2");
        basket = hardwareMap.dcMotor.get("basket");
        /*matching the config file to the names in the code*/

        motorLeft1.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);
        /*reversing the direction of the left drive motors*/
    }
    @Override
    public void loop (){
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;
        boolean buttonY = gamepad1.y;
        boolean buttonA = gamepad1.a;

        left = left * MOTOR_MAX_POWER;
        right = right * MOTOR_MAX_POWER;

        right = Range.clip(right, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);
        left = Range.clip(left, -MOTOR_MAX_POWER, MOTOR_MAX_POWER);

        motorLeft1.setPower(left);
        motorLeft2.setPower(left);
        motorRight1.setPower(right);
        motorRight2.setPower(right);
        /*setting the power for the drive motors*/

        if (buttonY) {
            basket.setPower(1.0);
            /*if button y is pressed, the basket motor will move 1.0*/
        }
        else {
            basket.setPower(0.0);
            /*if button y is not pressed, the basket will not move*/
        }
        if (buttonA){
            basket.setPower(-1.0);
            /*if button a is pressed, the basket will move -1.0*/
        }
        else{
            basket.setPower(0.0);
            /*if button a is not pressed, the basket will not move*/
        }

        telemetry.addData("1-motor left power", left);
        telemetry.addData("2-motor right power", right);
        /*showing the */
    }
}