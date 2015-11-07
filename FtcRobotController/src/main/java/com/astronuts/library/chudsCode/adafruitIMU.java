/*package com.astronuts.library.chudsCode;

import android.util.Log;

import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class adafruitIMU {

    private static volatile double[] rollAngle = new double[2], pitchAngle = new double[2], yawAngle = new double[2];

    public static Imu IMU;

    public static void init(HardwareMap cnHardwareMap,String DeviceName) throws RobotCoreException{

        IMU = new Imu(cnHardwareMap, DeviceName, (byte) (Imu.BNO055_ADDRESS_A * 2), (byte) (Imu.OPERATION_MODE_IMU));

        IMU.startIMU();
    }

    public static double getValues(String Type){

        double Value;

        IMU.getIMUGyroAngles(rollAngle, pitchAngle, yawAngle);

        switch (Type){

            case "Yaw":

                Value = yawAngle[0];

                break;
            case "Pitch":

                Value = pitchAngle[0];

                break;
            case "Roll":

                Value = rollAngle[0];

                break;
            case "yaw":

                Value = yawAngle[0];

                break;
            case "pitch":

                Value = pitchAngle[0];

                break;
            case "roll":

                Value = rollAngle[0];

                break;
            case "Heading":

                Value = yawAngle[0];

                break;
            case "heading":

                Value = yawAngle[0];

                break;

            default:

                Log.i("AdafruitImu", "Invalid ID");
                Value = Double.parseDouble(null);

                break;
        }

        return Value;

    }

}
*/