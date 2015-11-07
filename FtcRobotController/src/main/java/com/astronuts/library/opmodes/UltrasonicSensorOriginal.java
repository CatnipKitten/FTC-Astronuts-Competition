package com.astronuts.library.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * This OpMode is responsible for converting the ultrasonic values and displaying it on the Android
 * phones.
 *
 * Created by Prescott on 10/7/15.
 * Last Edited by Prescott on 10/21/15.
 */
public class UltrasonicSensorOriginal extends OpMode {
    //Stores values from the Ultrasonic Sensor.
    UltrasonicSensor ultrasonicsensor;

    @Override
    public void init () {
        //Maps the Ultrasonic Sensor.
        ultrasonicsensor = hardwareMap.ultrasonicSensor.get("ultrasonic_sensor");
    }
    public void loop () {
        //Gets Ultrasonic Value.
        double ultrasonic = ultrasonicsensor.getUltrasonicLevel();
        //Corrects Ultrasonic Values into Centimeters.
        double ultracorrected = ultrasonic - 3;
        //Converts the Centimeter values into Inches.
        double ultrainches = ultracorrected / 2.54;

        //Displays all the Data.
        telemetry.addData("Raw Data", ultrasonic);
        telemetry.addData("Centimeters", ultracorrected);
        telemetry.addData("Inches", ultrainches);
    }
}
