package org.firstinspires.ftc.teamcode.Shirley;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Shirley.StolenFromHollyBots.RingDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp(name = "Incognito Camera", group = "Iterative Opmode")
public class RobotCameraLinearOp extends LinearOpMode {

    int width = 320; // height and width of the camera, make sure whatever size you choose is supported by the camera.
    int height = 240;

    RingDetector pipeline = RingDetector.init(hardwareMap, "WEBCAM", true);
    //Note, your pipeline class MUST extend OpenCvPipeline. I can send part of my pipeline class if you're unsure of how to create one

    OpenCvWebcam phoneCam;


    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "<name of webcam in the hardware map>");
        phoneCam.setPipeline(pipeline);
        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                phoneCam.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }//ends onOpened
        });//ends parameters for openCameraDeviceAsync

    }//ends runOpMode method

}//ends RobotCameraLinearOp.java