package com.astronuts.library.opmodes;

import com.astronuts.library.sensors.colorsensor.CScorrection;
import com.astronuts.library.sensors.ultrasonic.UltrasonicDistance;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.astronuts.library.RobotData;

public class TestSensors extends OpMode{

    ColorSensor colorSensor;
    LightSensor lightSensor;
    UltrasonicSensor ultrasonicSensor;
    DeviceInterfaceModule cdim;

    public static final int LED_CHANNEL = 5;

    @Override
    public void init(){

        ultrasonicSensor = hardwareMap.ultrasonicSensor.get(RobotData.ultrasonic_sensor);
        lightSensor = hardwareMap.lightSensor.get(RobotData.light_sensor);
        colorSensor = hardwareMap.colorSensor.get(RobotData.color_sensor);
        cdim = hardwareMap.deviceInterfaceModule.get(RobotData.cdim);
    }

    public void loop(){
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(LED_CHANNEL, false);


        CScorrection csCorrection = new CScorrection();
        UltrasonicDistance ultrasonicDistance = new UltrasonicDistance(ultrasonicSensor);

        csCorrection.getColors(colorSensor);

        telemetry.addData("Ultrasonic Sensor (c):", ultrasonicDistance.getdistance('c'));
        telemetry.addData("Light Sensor:", lightSensor.getLightDetected());
        telemetry.addData("Color Sensor Blue:", csCorrection.blueCorrected);
        telemetry.addData("Color Sensor Red:", csCorrection.redCorrected);
        telemetry.addData("Color Sensor Green:", csCorrection.greenCorrected);
    }
}
