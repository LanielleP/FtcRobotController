package org.firstinspires.ftc.teamcode.Shirley;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp
@SuppressWarnings({"unused"})
public class ShirleyDpad extends OpMode {
    //prepares needed hardware
    private DcMotor driveRF;//drive wheel located RIGHT FRONT
    private DcMotor driveRB;//drive wheel located RIGHT BACK
    private DcMotor driveLF;//drive wheel located LEFT FRONT
    private DcMotor driveLB;//drive wheel located LEFT BACK

    //runs once when the user hits the INIT button
    @Override
    public void init() {
        //assigning each variable to its class
        driveRF = hardwareMap.get(DcMotor.class, "driveRF");
        driveRB = hardwareMap.get(DcMotor.class, "driveRB");
        driveLF = hardwareMap.get(DcMotor.class, "driveLF");
        driveLB = hardwareMap.get(DcMotor.class, "driveLB");

        //set the direction for each motor
        //clockwise
        driveRF.setDirection(DcMotor.Direction.FORWARD);
        driveRB.setDirection(DcMotor.Direction.FORWARD);
        //counter-clockwise
        driveLF.setDirection(DcMotor.Direction.REVERSE);
        driveLB.setDirection(DcMotor.Direction.REVERSE);

        //reset any encoder values
        driveRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //give each motor an encoder
        driveRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    //runs repeatedly when the user hits PLAY, until hitting STOP
    @Override
    public void loop() {
        //variables to act as power sources
        double /*x-axis power xPow,*/ /*y-axis power yPow*,//*strafing x-axis power*/rxPow;

        //fetch values for power sources depending on how the dpad is moved
        double up = gamepad1.dpad_up ? 0.25 : 0;
        double right = gamepad1.dpad_right ? 0.25 : 0;
        double down = gamepad1.dpad_down ? 0.25 : 0;
        double left = gamepad1.dpad_left ? 0.25 : 0;

        //xPow = -gamepad1.left_stick_x;
        //yPow = -gamepad1.left_stick_y;

        rxPow = gamepad1.right_stick_x;

        //give the motors power based on the joystick input
        //math is math, just trust me
        //with the way we have the wheels, some of these are different than the source
        driveRF.setPower( -rxPow + up - down + right - left );//yPow - xPow - rxPow
        driveRB.setPower( -rxPow + up - down - right + left );//yPow + xPow - rxPow
        driveLF.setPower( rxPow  + up - down - right + left);//yPow + xPow + rxPow
        driveLB.setPower( rxPow  + up - down + right - left);//yPow - xPow + rxPow

        telemetry.addData("Status", "Running");
        telemetry.addData("ENCDR-RF", driveRF.getCurrentPosition());
        telemetry.addData("ENCDR-RB", driveRB.getCurrentPosition());
        telemetry.addData("ENCDR-LF", driveLF.getCurrentPosition());
        telemetry.addData("ENCDR-LB", driveLB.getCurrentPosition());
        telemetry.update();
    }
}