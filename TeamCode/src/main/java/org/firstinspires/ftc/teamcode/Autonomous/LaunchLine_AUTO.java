/**
 * @Author: Georgia Petroff
 * @Project: Basic autonomous to move forward
 * @Start: 11/20/20
 * @Last: 12/07/20
 */

package org.firstinspires.ftc.teamcode.Autonomous;

import android.content.res.Resources;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resources.UsefulMethods;

@Disabled
@Autonomous
public class LaunchLine_AUTO extends LinearOpMode {
    //prepares needed hardware
    private DcMotor driveRF;//drive wheel located RIGHT FRONT
    private DcMotor driveRB;//drive wheel located RIGHT BACK
    private DcMotor driveLF;//drive wheel located LEFT FRONT
    private DcMotor driveLB;//drive wheel located LEFT BACK

    private DcMotor Shooter;
    private DcMotor mainTreads;
    private DcMotor backTreads;
    private Servo bandHolder;
    private CRServo extendContinuous;
    private CRServo rotateArm;
    private Servo clampArm;

    private ElapsedTime runtime = new ElapsedTime();
    private UsefulMethods methods = new UsefulMethods(driveRF, driveLF, driveRB, driveLB, Shooter, mainTreads, backTreads);

    @Override
    public void runOpMode() throws InterruptedException {
        //assigning each variable to its class
        driveRF = hardwareMap.get(DcMotor.class, "driveRF");
        driveRB = hardwareMap.get(DcMotor.class, "driveRB");
        driveLF = hardwareMap.get(DcMotor.class, "driveLF");
        driveLB = hardwareMap.get(DcMotor.class, "driveLB");

        //set the direction for each motor
        //clockwise
        driveRF.setDirection(DcMotor.Direction.FORWARD);
        driveRB.setDirection(DcMotor.Direction.FORWARD);

        //cataDoor.setDirection(Servo.Direction.FORWARD);
        //counter-clockwise
        driveLF.setDirection(DcMotor.Direction.REVERSE);
        driveLB.setDirection(DcMotor.Direction.REVERSE);

        //reset any encoder values
        resetEncoders();

        //give each motor an encoder
        activateEncoders();

        Shooter = hardwareMap.get(DcMotor.class, "Shooter");
        mainTreads = hardwareMap.get(DcMotor.class, "mainTreads");
        backTreads = hardwareMap.get(DcMotor.class, "backTreads");
        bandHolder = hardwareMap.get(Servo.class, "bandHolder");
        extendContinuous = hardwareMap.get(CRServo.class, "extendContinuous");
        rotateArm = hardwareMap.get(CRServo.class, "rotateArm");
        clampArm = hardwareMap.get(Servo.class, "clampArm");

        telemetry.addData("Status", "Initialized");

        waitForStart();

        double sSpeed;

        bandHolder.setPosition(0.4);

        methods.move(6850, 0.6, 0, driveRF, driveLF, driveRB, driveLB);

        sSpeed = 0.25;
        Shooter.setPower(sSpeed);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.75)
        {
            telemetry.update();
        }

        mainTreads.setPower(1);
        backTreads.setPower(-1);
        Shooter.setPower(sSpeed);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.5)
        {
            telemetry.update();
        }
        mainTreads.setPower(0);
        backTreads.setPower(0);
        Shooter.setPower(0);

        methods.move(2000, 0.6, 4,driveRF,driveLF,driveRB,driveLB);
    }

    /**
     * @Pre: all four drive wheels have been initialized and declared
     * @Param: int ticks - the absolute value of amount of ticks that the axel will be spinning per motor
     *        double power - range 0 to 1.0
     *        int direction - range 0 to 7 (use the directions established below)
     * @Post: encoder positions of drive motors
     * @Return: none (void)
     *
     *
     * Here's how the directions work according to a clock:
     * 1: 1:30
     * 2: 3:00
     * 3: 4:30
     * 4: 6:00
     * 5: 7:30
     * 6: 9:00
     * 7: 10:30
     * (0) default: 12:00
     *
     * For explanation of code workings, look at the comments on the default case
     */

    /**
     * @Pre: all four drive wheels have been declared and initialized
     * @Param: none
     * @Post: none
     * @Return: none (void)
     *
     * Will activate all four drive wheels to run with encoders
     */
    private void activateEncoders()
    {
        driveRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * @Pre: all four drive wheels have been declared and initialized
     * @Param: none
     * @Post: none
     * @Return: none (void)
     *
     * Will reset the current data for all four drive wheels' encoders
     */
    private void resetEncoders()
    {
        driveRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
