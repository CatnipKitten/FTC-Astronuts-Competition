/*package com.astronuts.library.opmodes;

import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * This OpMode is responsible for converting the ultrasonic value and displaying it on the Android
 * phones. It calls the method from the Ultrasonic Distance class to convert the data into
 * Centimeters and Inches.
 *
 * Created by Prescott on 10/7/15.
 * Last edited by Prescott on 10/21/15.

public class UltrasonicSensorTest extends OpMode {
    //Initializes the Ultrasonic Sensor
    UltrasonicSensor ultrasonicsensor;
    //Creates a variable that will be able to make a new instance of the Ultrasonic Distance Class.
    UltrasonicDistance test;

    @Override
    public void init () {
        //Maps the Ultrasonic Sensor.
        ultrasonicsensor = hardwareMap.ultrasonicSensor.get("ultrasonic_sensor");

        //Creates the new instance of the Class.
        test = new UltrasonicDistance();
    }
    //Starts a loop that will last as long as the program is running. It will run the Method from
    //the class and show the data on the phones.
    public void loop () {
        //Starts the Method from the class.
        test.getdistance(ultrasonicsensor);

        //Shows data from the sensor that has been converted through the method in the class.
        telemetry.addData("Centimeters", test.centimeters);
        telemetry.addData("Inches", test.inches);
    }
}
*/