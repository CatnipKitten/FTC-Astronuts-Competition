package com.astronuts.library.MovementManager;

import android.content.Context;
import android.widget.Toast;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Chooooooooood on 12/2/2015.
 */

public class MovementManager {

    public static int Case = 0;

    private static short buffer = 0;

    private static short[] idLS = new short[20];
    private static short idNUM = 0;
    private static String Tag = "MOVEMENT_MANAGER ";
    private static Telemetry telemetry;
    private static Component[] ComponentList = new Component[20];


    public static void Register(Component newComponent){
        ComponentList[newComponent.ID] = newComponent;
    }

    public static void DriveByDistance(int ID, double Distance){DriveByDistance(ID, Distance, 'c');}
    public static void DriveByDistance(int ID, double Distance, char Unit){

            if(ComponentList[ID].DriveTrain == true){
                ComponentList[ID].driveByDistance(Distance, Unit);
            }

    }

    public static void turnByAngle(int ID, double Amount, char Direction){turnByAngle(ID, Amount, Direction, 'd'); }
    public static void turnByAngle(int ID, double Amount, char Direction, char Unit){

            if(ComponentList[ID].DriveTrain == true) {
                ComponentList[ID].turnByAngle(Amount, Direction, Unit);
            }
    }

    public static void moveEncoder(int ID, int TargetDistance){moveEncoder(ID,TargetDistance,0.20);}
    public static void moveEncoder(int ID, int TargetDistance, double power){

            if(ComponentList[ID].DriveTrain == false) {
                ComponentList[ID].moveEncoder(TargetDistance, power);
            }
    }


    public static void completionTest(int ID) {


        //if(buffer == 0) {
            if (ComponentList[ID].isDone()) {

                Case++;

                buffer = 3;

                String str = String.format("DEBUG in completionTest case=%5d", Case);
                DbgLog.msg(str);

            }
        /*}else{
            buffer --;
        }
        */

    }
}