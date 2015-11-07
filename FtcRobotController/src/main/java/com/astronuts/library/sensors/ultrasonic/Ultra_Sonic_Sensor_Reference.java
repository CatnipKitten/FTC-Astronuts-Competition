

package com.astronuts.library.sensors.ultrasonic;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
//import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.LightSensorValueFinder;
//import com.qualcomm.robotcore.hardware.TouchSensor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.Range;
/**
 * Created by Zayle on 10/07/15.
 */
public class Ultra_Sonic_Sensor_Reference extends OpMode {

    UltrasonicSensor ultrasonicSensor;

    @Override
    public void init () {

     ultrasonicSensor = hardwareMap.ultrasonicSensor.get("ultrasonic_sensor");
 }
    @Override
    public void loop () {

        double ultrasonic = ultrasonicSensor.getUltrasonicLevel();

        telemetry.addData("4-UltrasonicSensor", ultrasonic);
    }
}
