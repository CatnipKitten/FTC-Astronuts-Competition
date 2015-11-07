package com.astronuts.library.chudsCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class CalabrationMode extends OpMode{

    /**Imported Methods*/

    public void init() {

        telemetry.addData("WARNING", "Running this file will overwrite the existing calibration file.");

    }

    public void loop() {



    }

}
