package com.astronuts.library.sensors.colorsensor;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * This Class is responsible for getting the values from the color sensor and converts it to a more
 * accurate value. The corrected values show the how strong the Blue, Red, and Green colors are on
 * the object it is reading.
 *
 * Created by Baylee on 10/14/2015.
 * Last Edited by Baylee on 10/14/15
 */
public class CScorrection {
    //Makes a variable that will store the corrected blue color sensor values.
    public float blueCorrected;

    //Makes a variable that will store the corrected green color sensor values.
    public float greenCorrected;

    //Makes a variable that will store the corrected red color sensor values.
    public float redCorrected;

    //Makes a variable that will be able to correct and calibrate the color sensor.
    float bluecorr = (float) 1.11657;
    float greencorr = (float) 1.1771;
    float redcorr = (float) 1.0;

    //Creates a Method that gets the color values and corrects them.
    public void getColors (ColorSensor colorSensor){
        //Stores the values from the color sensor.
        int blue = colorSensor.blue();
        int green = colorSensor.green();
        int red = colorSensor.red();

        //Corrects the values.
        blueCorrected = bluecorr * blue;
        greenCorrected = greencorr * green;
        redCorrected = redcorr * red;


    }
}

