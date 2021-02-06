/**
 * @Author: Georgia Petroff
 * @Project: Bob driver controlled with autonomated shooting
 * @Start: 01/12/21
 * @Last: 01/12/21
 */

package org.firstinspires.ftc.teamcode.Bob;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@TeleOp
@SuppressWarnings({"unused"})
public class DC_Automated_copy extends OpMode {
    //hardware variables
    private DcMotor driveRF;//drive wheel located RIGHT FRONT
    private DcMotor driveRB;//drive wheel located RIGHT BACK
    private DcMotor driveLF;//drive wheel located LEFT FRONT
    private DcMotor driveLB;//drive wheel located LEFT BACK
    private DcMotor Shooter;
    private DcMotor mainTreads;
    private DcMotor backTreads;
    private Servo bandHolder;
    //private CRServo extendContinuous;
    //private Servo rotateArm;
    //private Servo clampArm;

    //other variables
    double sSpeed=0, xPow, yPow, rxPow;
    boolean shooterRunning;

    @Override
    public void init()
    {
        driveRF = hardwareMap.get(DcMotor.class, "driveRF");
        driveRB = hardwareMap.get(DcMotor.class, "driveRB");
        driveLF = hardwareMap.get(DcMotor.class, "driveLF");
        driveLB = hardwareMap.get(DcMotor.class, "driveLB");
        Shooter = hardwareMap.get(DcMotor.class, "Shooter");
        mainTreads = hardwareMap.get(DcMotor.class, "mainTreads");
        backTreads = hardwareMap.get(DcMotor.class, "backTreads");
        bandHolder = hardwareMap.get(Servo.class, "bandHolder");
        //extendContinuous = hardwareMap.get(CRServo.class, "extendContinuous");
        //rotateArm = hardwareMap.get(Servo.class, "rotateArm");
        //clampArm = hardwareMap.get(Servo.class, "clampArm");

        //clockwise
        driveRF.setDirection(DcMotor.Direction.FORWARD);
        driveRB.setDirection(DcMotor.Direction.FORWARD);
        //counter-clockwise
        driveLF.setDirection(DcMotor.Direction.REVERSE);
        driveLB.setDirection(DcMotor.Direction.REVERSE);

        //prep encoders
        driveRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }//end init method

    @Override
    public void loop()
    {
        double p2x, p2y;

        p2x = gamepad2.left_stick_x;
        p2y = gamepad2.left_stick_y;

        //drive train
        xPow = -gamepad1.left_stick_x;
        yPow = -gamepad1.left_stick_y;
        rxPow = gamepad1.right_stick_x;
        driveRF.setPower(yPow + xPow - rxPow);
        driveRB.setPower(yPow - xPow - rxPow);
        driveLF.setPower(yPow - xPow + rxPow);
        driveLB.setPower(yPow + xPow + rxPow);

        Shooter.setPower(sSpeed);

        //Conveyor belt On/Off
        if (gamepad1.right_bumper) {
            mainTreads.setPower(1);
            backTreads.setPower(-1);
            if (gamepad1.x && !gamepad1.left_bumper) {
                mainTreads.setPower(-1);
                backTreads.setPower(1);
            }
        }
        if (!gamepad1.right_bumper) {
            mainTreads.setPower(0);
            backTreads.setPower(0);
        }

        //Shooter
        if (gamepad1.a)
        {
            if(!shooterRunning) {sSpeed=-0.4;shooterRunning=true;}
            else {sSpeed=0;shooterRunning=false;}
        }
        else if (gamepad1.b)
        {
            if(!shooterRunning) {sSpeed=-0.45;shooterRunning=true;}
            else {sSpeed=0;shooterRunning=false;}
        }
        else if (gamepad1.x)
        {
            if(!shooterRunning) {sSpeed=-0.3;shooterRunning=true;}
            else {sSpeed=0;shooterRunning=false;}
        }
        else if (gamepad1.y)
        {
            if(!shooterRunning) {sSpeed=-0.19;shooterRunning=true;}
            else {sSpeed=0;shooterRunning=false;}
        }

        //Band Holder
        if (gamepad2.a && !gamepad2.left_bumper && !gamepad2.right_bumper) {
            bandHolder.setPosition(0.4);
        }
        if (gamepad2.b && !gamepad2.left_bumper && !gamepad2.right_bumper) {
            bandHolder.setPosition(-0.3);
        }

        //Wobble Goal Arm
        //Extend/Retract
        if (gamepad2.left_bumper) {
            //extendContinuous.setPower(-p2y);
        }
        //Rotate
        if(gamepad2.right_bumper) {
            if (gamepad2.a) {
                //rotateArm.setPosition(0);
            }
            if (gamepad2.b) {
                //rotateArm.setPosition(1);
            }
            //if (gamepad2.x) {
            //  clampArm.setPosition(0.75);
            //}
            //if (gamepad2.y) {
            //  clampArm.setPosition(0);
            //}
        }

        telemetry.addData("Status", "Running");
        telemetry.addData("ENCDR-RF", driveRF.getCurrentPosition());
        telemetry.addData("ENCDR-RB", driveRB.getCurrentPosition());
        telemetry.addData("ENCDR-LF", driveLF.getCurrentPosition());
        telemetry.addData("ENCDR-LB", driveLB.getCurrentPosition());
        telemetry.update();
    }//end loop method

}//end DC_Automated class