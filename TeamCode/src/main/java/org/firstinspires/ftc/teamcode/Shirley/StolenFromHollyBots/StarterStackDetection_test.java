/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * FTC TEAM 12731 Separated the Pipeline class into it's
 */

package org.firstinspires.ftc.teamcode.Shirley.StolenFromHollyBots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Starter Stack Detection", group="1")
public class StarterStackDetection_test extends LinearOpMode
{
    RingDetector ringDetector = null;

    protected String CAMERA_SYSTEM                 = "webcam 1";  // can be PHONE or WEBCAM

    @Override
    public void runOpMode()
    {
        ringDetector =  RingDetector.init(hardwareMap, CAMERA_SYSTEM, true);
        waitForStart();

        while (opModeIsActive())
        {
            if (ringDetector != null) {
                telemetry.addData("Analysis", ringDetector.getAnalysis());
                telemetry.addData("Position", ringDetector.getPosition());
                telemetry.update();
            } else {
                telemetry.addData("Analysis","No Ring Detector configured");
            }

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }
    }
}