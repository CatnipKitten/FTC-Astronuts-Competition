/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.astronuts.library.opmodes.ColorSensorCode;
import com.astronuts.library.opmodes.TestSensors;
import com.astronuts.library.opmodes.UltrasonicAndLineTest;
import com.astronuts.library.opmodes.UltrasonicSensorTest;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import com.astronuts.library.opmodefinal.*;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {
  public void register(OpModeManager manager) {
    manager.register("LinearOP", AutonomousFINALFORBOTHTEAM.class);
    manager.register("TeleOP", TeleOP.class);
    manager.register("Sensor Test", TestSensors.class);
    manager.register("AutonomousBackUpBLUETEAM RED TEAM", AutonomousFINALFORBOTHTEAM.class);
    //manager.register("AutonomousBackUpBLUETEAM Backup BLUE TEAM", AutomonousBackUpBLUETEAM.class);
    manager.register("AutonomousBackUpBLUETEAM Backup RED TEAM", AutonomousBackUpREDTEAM.class);
    manager.register("Backup backup", AutonomousSimpleBackup.class);
    manager.register("Ultrasonic Distance Test", UltrasonicSensorTest.class);
    manager.register("ColorSensorCode", ColorSensorCode.class);
    manager.register("Ultrasonic And Line", UltrasonicAndLineTest.class);
    manager.register("Tester_1", Tester_1.class);
  }
}
