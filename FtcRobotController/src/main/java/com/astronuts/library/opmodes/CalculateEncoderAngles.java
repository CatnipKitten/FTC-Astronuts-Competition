package com.astronuts.library.opmodes;

import com.astronuts.library.MovementManager.Component;
import com.astronuts.library.MovementManager.MovementManager;
import com.astronuts.library.MovementManager.newdrive.drive;
import com.astronuts.library.RobotData;
import com.astronuts.library.movement.EncoderMotor;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by maxine on 12/21/15.
 */
public class CalculateEncoderAngles extends OpMode {

    DcMotor right;
    DcMotor left;

    EncoderMotor encoderLeft;
    EncoderMotor encoderRight;

    drive Drive;
    Component DriveTrain;
    private int driveID = 0;

    private int flag = 0;

    @Override
    public void init() {

        left = hardwareMap.dcMotor.get(RobotData.right_drive);
        right = hardwareMap.dcMotor.get(RobotData.left_drive);
        right.setDirection(DcMotor.Direction.REVERSE);

        encoderLeft = new EncoderMotor(left);
        encoderRight = new EncoderMotor(right);

        Drive = new drive(encoderLeft, encoderRight);
        DriveTrain = new Component(driveID, Drive);

        MovementManager.Register(DriveTrain);
        MovementManager.Case = 0;
    }

    @Override
    public void loop() {

        switch(MovementManager.Case) {
            case 0:
                MovementManager.DriveByDistance(driveID, 50, 'c');
                MovementManager.completionTest(driveID);
                DbgLog.msg("In Case 0");
                break;
            case 1:
                MovementManager.DriveByDistance(driveID, 50, 'c');
                MovementManager.completionTest(driveID);
                DbgLog.msg("In Case 1");
                break;
            case 2:
                MovementManager.DriveByDistance(driveID, 50, 'c');
                MovementManager.completionTest(driveID);
                DbgLog.msg("In Case 2");
                break;
        }
        telemetry.addData("stuff", MovementManager.Case);
    }
}
