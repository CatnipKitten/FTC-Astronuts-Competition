package com.astronuts.library.MovementManager;

import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by Chooooooooood on 12/2/2015.
 */

public class MovementManager {

    public static int Case = 0;

    private static short[] idLS = new short[20];
    private static short idNUM = 0;
    private static String Tag = "MOVEMENT_MANAGER ";
    private static Telemetry telemetry;
    private static boolean Lock;
    private static Component[] ComponentList = new Component[20];

    public static void Register(Component newComponent){
        idLS[idNUM] = newComponent.ID;
        idNUM++;
        ComponentList[newComponent.ID] = newComponent;
    }

    public static void DriveByDistance(int ID, double Distance){DriveByDistance(ID, Distance, 'c');}
    public static void DriveByDistance(int ID, double Distance, char Unit){
        if(Lock == false){
            Lock = true;
            if(ComponentList[ID].DriveTrain == true){
                ComponentList[ID].driveByDistance(Distance, Unit);
            }else{
                telemetry.addData(Tag, "ID " + Integer.toString(ID) + " Is not a drive train!");
            }
        }else{
            telemetry.addData(Tag,"Case already in use, skipping...");
        }
    }

    public static void turnByAngle(int ID, double Amount, char Direction){turnByAngle(ID, Amount, Direction, 'd'); }
    public static void turnByAngle(int ID, double Amount, char Direction, char Unit){
        if(Lock == false){
            Lock = true;
            if(ComponentList[ID].DriveTrain == true){
                ComponentList[ID].turnByAngle(Amount, Direction, Unit);
            }else{
                telemetry.addData(Tag, "ID " + Integer.toString(ID) + " Is not a drive train!");
            }
        }else{
            telemetry.addData(Tag,"Case already in use, skipping...");
        }
    }

    public static void moveEncoder(int ID, int TargetDistance){moveEncoder(ID,TargetDistance,0.20);}
    public static void moveEncoder(int ID, int TargetDistance, double power){
        if(Lock == false){
            Lock = true;
            if(ComponentList[ID].DriveTrain == false){
                ComponentList[ID].moveEncoder(TargetDistance, power);
            }else{
                telemetry.addData(Tag, "ID " + Integer.toString(ID) + " Is a drive train! (Invalid)");
            }
        }else {
            telemetry.addData(Tag, "Case already in use, skipping...");
        }
    }

    public static void completionTest(){
        int i;
        boolean isComplete = true;
        for(i=0;i<=idNUM;i++){
            if(ComponentList[idLS[i]].Complete() == false) isComplete = false;
        }
        if(isComplete == true) Case++;
    }
}