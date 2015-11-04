package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by robotics on 8/19/2015.
 * Robot control: arm servo = buttons a and y, claw servo = buttons x and b
 */
public class k9_servo_contol extends OpMode {
    final static double ARM_MIN_RANGE = 0.4;
    final static double ARM_MAX_RANGE = 0.94;
/*establishes the minimum and maximum range of the arm servo*/

    final static double CLAW_MIN_RANGE = 0.5;
    final static double CLAW_MAX_RANGE = 1.0;
/*establishes the minimum and maximum range of the claw servo*/

    double armPosition = ARM_MIN_RANGE;
    double clawPosition = CLAW_MIN_RANGE;

    double armDelta = 0.01;
    double clawDelta = 0.01;

    Servo arm;
    Servo claw;

    @Override
    public void init () {

        arm = hardwareMap.servo.get("servo_arm");
        claw = hardwareMap.servo.get("servo_claw");

        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);
    }
    @Override

    public void loop(){
        if(gamepad1.a){
            armPosition += armDelta;
        }
        if (gamepad1.y){
            armPosition-= armDelta;
        }
        if (gamepad1.x){
            clawPosition+= clawDelta;
        }
        if (gamepad1.b){
            clawPosition-= clawDelta;
        }

        armPosition=Range.clip(armPosition,ARM_MIN_RANGE,ARM_MAX_RANGE);
        clawPosition=Range.clip (clawPosition,CLAW_MIN_RANGE,CLAW_MAX_RANGE);

        arm.setPosition(armPosition);
        claw.setPosition(clawPosition);

        telemetry.addData("3-arm position", armPosition);
        telemetry.addData("4-claw position", clawPosition);
    }
}
