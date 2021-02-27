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
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Resources.RingDetector;
import org.firstinspires.ftc.teamcode.Resources.UsefulMethods;

@TeleOp(name="Wobble Goal Controlled")
public class WobbleGoal_Controlled extends LinearOpMode {

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
        Servo rotateArm = hardwareMap.get(Servo.class, "rotateArm");
        Servo clampArm = hardwareMap.get(Servo.class, "clampArm");

        UsefulMethods methods = new UsefulMethods(driveRF,driveLF,driveRB,driveLB);
        methods.resetEncoders();//stuff

        telemetry.addData("Status", "Initialized");

        waitForStart();

        double sSpeed = 0.45;
        bandHolder.setPosition(0.4);//drop back conveyor belt

        //TODO make program work
        //get the ring position
        RingDetector.RingPosition ringNum =  ringDetector.getPosition();


        //extend arm out
        extendContinuous.setPower(-1);
        UsefulMethods.wait(5.0);
        extendContinuous.setPower(0);

        //grab arm and tilt
        clampArm.setPosition(0.4);
        rotateArm.setPosition(0.3);

        //PROGRAM TO LAUNCH INTO HIGH GOAL, POWER SHOT, OR WHATEVER IT ENDS UP HITTING
        methods.move(1500, 0.25, 0);
        methods.move(500, 1, 4);//back (to shake back belt loose)
        methods.move(1300, 0.26, 0);//forward (to shoot)
        UsefulMethods.shoot(sSpeed,Shooter,mainTreads,backTreads);

        /*position C*/ if(ringNum == RingDetector.RingPosition.FOUR) {
            telemetry.addData("Rings: ", ringNum);
            telemetry.update();
            //move forward to be closer
            methods.move(1500, 0.25, 0);

            //rotate to angle left
            driveRF.setPower(-1);
            driveLF.setPower(1);
            driveRB.setPower(-1);
            driveLB.setPower(1);
            UsefulMethods.wait(0.25);

            //move to box C and drop wobble goal
            methods.move(1500, 0.25, 2);
            clampArm.setPosition(0);
            rotateArm.setPosition(0);

            //park and retract arm
            methods.move(2000, 0.25, 6);
            extendContinuous.setPower(1);
            UsefulMethods.wait(5.0);
            extendContinuous.setPower(0);
        } /*position B*/ else if (ringNum == RingDetector.RingPosition.ONE) {
            telemetry.addData("Rings: ", ringNum);
            telemetry.update();
            //move to box B and drop wobble goal
            methods.move(1600, 0.25, 0);
            clampArm.setPosition(0);
            rotateArm.setPosition(0);

            //park and retract arm
            methods.move(500, 0.25, 4);
            extendContinuous.setPower(1);
            UsefulMethods.wait(5.0);
            extendContinuous.setPower(0);
        } /*position A*/ else {
            telemetry.addData("Rings: ", ringNum);
            telemetry.update();
            //move to box A and drop wobble goal
            methods.move(2000, 0.25, 0);//was 200 ticks
            clampArm.setPosition(0);
            rotateArm.setPosition(0);

            //move over to release wobble goal
            methods.move(500, 0.25, 2);

            //park and retract arm
            methods.move(1500, 0.25, 4);//was 400 ticks
            extendContinuous.setPower(1);
            UsefulMethods.wait(5.0);
            extendContinuous.setPower(0);
        }//ends if, else-if, else

    }//ends runOpMode

}//ends class