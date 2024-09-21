
package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
//import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
//import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
//import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.zLibraries.Vector2d;
//import org.firstinspires.ftc.teamcode.zLibraries.Utilities.Vector2d;

public class Drivetrain {
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    public double target;
    public double Kp;
    public double Ki;
    public double Kd;
    public double value;
    public double error;
    public double integral;
    public double dervative;

    public double inputTurn;

    public double targetAngle;
    public double releaseAngle;

    public Drivetrain (HardwareMap hardwareMap) {

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public double PIDCorrection(double Kp, double Ki, double Kd, double heading) {
        double lastError = error;
        double value = heading;
        double error = target - value;
        if (error>180){error-=360;}
        else if (error <-180){error+=360;}
        integral = integral + error;
        double derivative = error - lastError;
        return (error * Kp) + (integral * Ki) + (dervative * Kd);
    }

    public void drive(double drive) {
        fr.setPower(drive);
        fl.setPower(drive);
        br.setPower(drive);
        bl.setPower(drive);
    }

    public void strafe(double drive) {
        fl.setPower(drive);
        bl.setPower(-drive);
        fr.setPower(-drive);
        br.setPower(drive);
    }


    public void turn(double drive) {
        fl.setPower(drive);
        bl.setPower(drive);
        fr.setPower(-drive);
        br.setPower(-drive);
    }


    public void Drive (double YPower, double XPower, double HeadingPower,  double Slow){

        if (Slow > 0.05) {
            //
            fl.setPower(-(YPower - XPower + HeadingPower) * 0.5);
            //
            fr.setPower((YPower + XPower - HeadingPower) * 0.5);
            //
            bl.setPower(-(YPower + XPower + HeadingPower) * 0.5);
            //backwards
            br.setPower((YPower - XPower - HeadingPower) * 0.5);
        } else {
            //
            fl.setPower(-(YPower - XPower + HeadingPower) * 1);
            //
            fr.setPower((YPower + XPower - HeadingPower) * 1);
            //
            bl.setPower(-(YPower + XPower + HeadingPower) * 1);
            //backwards
            br.setPower((YPower - XPower - HeadingPower) * 1);
        }

        double YVector = YPower;
        double XVector = XPower;
        double HVector = HeadingPower;



    }

    public void driveDO(double drive, double strafe, double turn, double slow, double heading, double superSlow, boolean PIDon) {
        Vector2d driveVector = new Vector2d(strafe, drive);
        Vector2d rotatedVector = driveVector.rotate(Math.toRadians(heading));

        drive = rotatedVector.y;
        strafe = rotatedVector.x;

        if (PIDon) {
            turn = PIDCorrection(-0.05, 0, 0, heading);
        }

        if (turn != 0) {
            inputTurn = turn;
            releaseAngle = heading;
        } else {
            targetAngle = releaseAngle + 0.5;
//            inputTurn = PIDCorrection( 0.05, 0.0005, 0.01,targetAngle-heading);
            inputTurn = PIDCorrection(0.05, 0, 0.01, targetAngle - heading);


        }

//    public void testDrive (double speed) {
//        fr.setPower(speed);
//        fl.setPower(-speed);
//        br.setPower(speed);
//        bl.setPower(-speed);
//    }

        strafe = (strafe * -1);


        if (slow > 0.25) {
            fl.setPower((drive + strafe + (inputTurn)) * -0.5);
            fr.setPower((drive - strafe - (inputTurn)) * -0.5);
            bl.setPower((drive - strafe + (inputTurn)) * -0.5);
            br.setPower((drive + strafe - (inputTurn)) * -0.5);
        } else if (superSlow > 0.25) {
            fl.setPower((drive + strafe * 1.5 + inputTurn) * -0.25);
            fr.setPower((drive - strafe * 1.5 - inputTurn) * -0.25);
            bl.setPower((drive - strafe * 1.5 + inputTurn) * -0.25);
            br.setPower((drive + strafe * 1.5 - inputTurn) * -0.25);
        } else {
            fl.setPower((drive + strafe + inputTurn * 0.9) * -1);
            fr.setPower((drive - strafe - inputTurn * 0.9) * -1);
            bl.setPower((drive - strafe + inputTurn * 0.9) * -1);
            br.setPower((drive + strafe - inputTurn * 0.9) * -1);
        }


}
}
