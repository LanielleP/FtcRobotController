package org.firstinspires.ftc.teamcode.Shirley;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
@SuppressWarnings({"unused"})
public class TinyShirley extends OpMode
{
    private DcMotor frontDrive;
    private DcMotor backDrive;

    @Override
    public void init()
    {
        frontDrive = hardwareMap.get(DcMotor.class, "frontDrive");
        backDrive = hardwareMap.get(DcMotor.class, "backDrive");

        frontDrive.setDirection(DcMotor.Direction.FORWARD);
        backDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        frontDrive.setPower(gamepad1.left_stick_y);
        backDrive.setPower(gamepad1.right_stick_y);

        telemetry.addData("Status", "Running");
        telemetry.update();
    }
}