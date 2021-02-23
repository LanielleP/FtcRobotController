/**
 * @Author: Lanielle Pavlik     mostly :)
 * @Project: Autonomous to move wobble goal, launch rings, and park
 * @Start: 02/22/21
 * Currently, this will be a very rough program until it can be finished and tested.
 * DO NOT ATTEMPT TO RUN
 */

//TODO do everything
//TODO write code for arm stuff
//TODO make sure values work and stuff still shoots

package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Resources.RingDetector;
import org.firstinspires.ftc.teamcode.Resources.UsefulMethods;

@Autonomous(name="Wobble Goal")
public class WobbleGoal_AUTO extends LinearOpMode {

    @Override
    public void runOpMode() {

        //creating/initializing motors and servos and stuff (oh my!)
        RingDetector ringDetector;
        ringDetector =  RingDetector.init(hardwareMap, "WEBCAM", true);
        DcMotor driveRF = hardwareMap.get(DcMotor.class, "driveRF");//motors
        DcMotor driveRB = hardwareMap.get(DcMotor.class, "driveRB");
        DcMotor driveLF = hardwareMap.get(DcMotor.class, "driveLF");
        DcMotor driveLB = hardwareMap.get(DcMotor.class, "driveLB");
        driveRF.setDirection(DcMotor.Direction.FORWARD);
        driveRB.setDirection(DcMotor.Direction.FORWARD);
        driveLF.setDirection(DcMotor.Direction.REVERSE);
        driveLB.setDirection(DcMotor.Direction.REVERSE);
        DcMotor Shooter = hardwareMap.get(DcMotor.class, "Shooter");
        DcMotor mainTreads = hardwareMap.get(DcMotor.class, "mainTreads");
        DcMotor backTreads = hardwareMap.get(DcMotor.class, "backTreads");
        Servo bandHolder = hardwareMap.get(Servo.class, "bandHolder");//servos
        CRServo extendContinuous = hardwareMap.get(CRServo.class, "extendContinuous");
        CRServo rotateArm = hardwareMap.get(CRServo.class, "rotateArm");
        Servo clampArm = hardwareMap.get(Servo.class, "clampArm");
        UsefulMethods methods = new UsefulMethods(driveRF,driveLF,driveRB,driveLB);
        methods.resetEncoders();//stuff
        telemetry.addData("Status", "Initialized");

        waitForStart();

        double sSpeed = 0.45;
        bandHolder.setPosition(0.4);//drop back conveyor belt

        //TODO make program work
        RingDetector.RingPosition ringNum =  ringDetector.getPosition();
        if(ringNum == RingDetector.RingPosition.FOUR) {
            //do stuff
        } else if (ringNum == RingDetector.RingPosition.ONE) {
            //do stuff
        } else {
            //do stuff
        }//ends if, else-if, else

        //PROGRAM TO LAUNCH INTO HIGH GOAL, POWER SHOT, OR WHATEVER IT ENDS UP HITTING
        //TODO edit
        methods.move(1500, 0.5, 0);
        methods.move(500, 1, 4);//back (to shake back belt loose)
        methods.move(1300, 0.6, 0);//forward (to shoot)
        UsefulMethods.shoot(sSpeed,Shooter,mainTreads,backTreads);
        //TODO test the following line: 1900 may need to be changed to 600
        methods.move(1900, 0.6, 0);
    }//ends runOpMode

}//ends class