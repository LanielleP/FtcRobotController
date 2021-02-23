package org.firstinspires.ftc.teamcode.Resources;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Class created by Lanielle Pavlik, all code stolen from Georgia Petroff and improved upon. :)
 */
public class UsefulMethods {

    private static ElapsedTime runtime = new ElapsedTime();
    private String constructorMode;
    private DcMotor driveRF, driveLF, driveRB, driveLB;
    private DcMotor Shooter, mainTreads, backTreads;



    public UsefulMethods(DcMotor driveRF, DcMotor driveLF, DcMotor driveRB, DcMotor driveLB) {
        this.driveRF = driveRF;
        this.driveLF = driveLF;
        this.driveRB = driveRB;
        this.driveLB = driveLB;
        constructorMode = "move";
    }//ends UsefulMethods constructor, use this constructor to move

    public UsefulMethods(DcMotor Shooter, DcMotor mainTreads, DcMotor backTreads) {
        this.Shooter = Shooter;
        this.mainTreads = mainTreads;
        this.backTreads = backTreads;
        constructorMode = "shoot";
    }//ends UsefulMethods Constructor, use this constructor to shoot



    public void move(int ticks, double power, int direction) {
        if(constructorMode.equals("move")) {
            activateEncoders();
            switch (direction) {
                case 1:
                    driveRF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(0.0);
                        driveLF.setPower(0.0);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 2:
                    driveRF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(-ticks);
                    driveLF.setTargetPosition(-ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(-power);
                        driveLF.setPower(-power);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 3:
                    ticks = -ticks;
                    driveLF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    while (driveLF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(0.0);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(0.0);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 4:
                    ticks = -ticks;
                    power = -power;
                    driveRF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    driveLF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 5:
                    ticks = -ticks;
                    power = -power;
                    driveRF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(0.0);
                        driveLF.setPower(0.0);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 6:
                    driveRF.setTargetPosition(-ticks);
                    driveRB.setTargetPosition(ticks);
                    driveLF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(-ticks);
                    while (driveRF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(-power);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(-power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 7:
                    driveLF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    while (driveLF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(0.0);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(0.0);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                default:
                    driveRF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    driveLF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
            }//ends switch statement
            resetEncoders();
        }//ends if
    }//ends (the better) move method

    public void resetEncoders() {
        if(constructorMode.equals("move")) {
            driveRF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveRB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveLF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveLB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }//ends if
    }//ends resetEncoders

    private void activateEncoders() {
        if(constructorMode.equals("move")) {
            driveRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            driveRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            driveLF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            driveLB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }//ends if
    }//ends activateEncoders

    public void shoot(double sSpeed) {
        Shooter.setPower(sSpeed);
        wait(5.0);
        mainTreads.setPower(1);
        backTreads.setPower(-1);
        Shooter.setPower(sSpeed);
        wait(2.0);
        mainTreads.setPower(0);
        mainTreads.setPower(0);
        Shooter.setPower(0);
    }//ends shoot method



    public void move(int ticks, double power, int direction,
        DcMotor driveRF, DcMotor driveLF, DcMotor driveRB, DcMotor driveLB) {
        if(constructorMode.equals("move")) {
            activateEncoders();
            switch (direction) {
                case 1:
                    driveRF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(0.0);
                        driveLF.setPower(0.0);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 2:
                    driveRF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(-ticks);
                    driveLF.setTargetPosition(-ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(-power);
                        driveLF.setPower(-power);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 3:
                    ticks = -ticks;
                    driveLF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    while (driveLF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(0.0);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(0.0);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 4:
                    ticks = -ticks;
                    power = -power;
                    driveRF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    driveLF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 5:
                    ticks = -ticks;
                    power = -power;
                    driveRF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(0.0);
                        driveLF.setPower(0.0);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 6:
                    driveRF.setTargetPosition(-ticks);
                    driveRB.setTargetPosition(ticks);
                    driveLF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(-ticks);
                    while (driveRF.getCurrentPosition() >= ticks) {
                        driveRF.setPower(-power);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(-power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                case 7:
                    driveLF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    while (driveLF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(0.0);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(0.0);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
                default:
                    driveRF.setTargetPosition(ticks);
                    driveRB.setTargetPosition(ticks);
                    driveLF.setTargetPosition(ticks);
                    driveLB.setTargetPosition(ticks);
                    while (driveRF.getCurrentPosition() <= ticks) {
                        driveRF.setPower(power);
                        driveRB.setPower(power);
                        driveLF.setPower(power);
                        driveLB.setPower(power);
                    }
                    driveRF.setPower(0.0);
                    driveRB.setPower(0.0);
                    driveLF.setPower(0.0);
                    driveLB.setPower(0.0);
                    break;
            }//ends switch statement
            resetEncoders();
        }//ends if
    }//ends  move method


    public static void shoot(double sSpeed, DcMotor Shooter, DcMotor mainTreads, DcMotor backTreads) {
        Shooter.setPower(sSpeed);
        wait(5.0);
        mainTreads.setPower(1);
        backTreads.setPower(-1);
        Shooter.setPower(sSpeed);
        wait(2.0);
        mainTreads.setPower(0);
        mainTreads.setPower(0);
        Shooter.setPower(0);
    }//ends shoot method

    public static void wait(double seconds) {
        runtime.reset();
        boolean thisIsAUselessVariable;
        while (runtime.seconds() < seconds) thisIsAUselessVariable=true;
    }//ends wait method

}//ends UsefulMethods class