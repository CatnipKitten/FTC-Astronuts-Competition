package com.astronuts.library.opmodes;

import com.astronuts.library.sensors.colorsensor.CScorrection;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * This OpMode is responsible for taking the original values from the color sensor and running it
 * through the Method that will correct the raw Red, Green, and Blue data.
 *
 * Created by Baylee on 10/7/2015.
 * Last edited by Prescott on 11/4/15
 */

public class ColorSensorCode extends OpMode {
    //Makes a variable that stores the values from the color sensor.
    ColorSensor colorSensor;

    //Creates a variable for the Device Interface Module that will allow us to set the color sensor
    //to a specific channel.
    DeviceInterfaceModule cdim;

    //Sets a variable that will later set the channel for the color sensor.
    static final int LED_CHANNEL = 5;

    //Makes a variable that will be able to create a new instance of the CScorrection Class Method.
    CScorrection cscore;

    @Override
    public void init() {
        //Gets the path to the Color Sensor through the Config file.
        colorSensor = hardwareMap.colorSensor.get("color_sensor");

        //Maps the Device Interface Module.
        cdim = hardwareMap.deviceInterfaceModule.get("dim");

        //Sets the color sensor's channel to 5.
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(LED_CHANNEL, false);

        //Makes the new instance of the Method.
        cscore = new CScorrection();
    }

    //Starts a loop that will run as long as the program is running. It will run the Method from the
    //class and show the data on the phones.
    @Override
    public void loop() {
        //Starts the Method from the class.
        cscore.getColors(colorSensor);

        //Displays the Data on the phones.
        telemetry.addData("Red", cscore.redCorrected);
        telemetry.addData("Green", cscore.greenCorrected);
        telemetry.addData("Blue ", cscore.blueCorrected);

    }
}
