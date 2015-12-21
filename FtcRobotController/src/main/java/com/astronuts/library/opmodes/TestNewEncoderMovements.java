package com.astronuts.library.opmodes;

import com.astronuts.library.MovementManager.Component;
import com.astronuts.library.MovementManager.MovementManager;
import com.astronuts.library.RobotData;
import com.astronuts.library.movement.EncoderMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.astronuts.library.MovementManager.newdrive.drive;

/**
 * Created by maxine on 12/21/15.
 */
public class TestNewEncoderMovements extends OpMode {
    DcMotor left;
    DcMotor right;

    EncoderMotor encoderRight;
    EncoderMotor encoderLeft;

    drive Drive;

    Component DriveTrain;

    private int driveID = 0;

    @Override
    public void init() {

        left = hardwareMap.dcMotor.get(RobotData.left_drive);
        right = hardwareMap.dcMotor.get(RobotData.right_drive);
        right.setDirection(DcMotor.Direction.REVERSE);

        encoderRight = new EncoderMotor(right);
        encoderLeft = new EncoderMotor(left);

        Drive = new drive(encoderLeft, encoderRight);

        DriveTrain = new Component(driveID, Drive);

        MovementManager.Register(DriveTrain);

        MovementManager.Case = 0;

    }

    @Override
    public void loop() {
        switch(MovementManager.Case){
            case 0:
                MovementManager.DriveByDistance(driveID, -64.77, 'c');
                MovementManager.completionTest(driveID);
                break;
            case 1:
                MovementManager.turnByAngle(driveID, 145, 'r');
                MovementManager.completionTest(driveID);
                break;
        }
        telemetry.addData("Stuff", MovementManager.Case);
    }
}
