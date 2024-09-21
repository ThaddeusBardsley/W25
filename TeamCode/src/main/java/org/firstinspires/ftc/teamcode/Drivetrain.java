
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
//import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.hardwareMap;
//import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.multTelemetry;
//import static org.firstinspires.ftc.teamcode.Utilities.OpModeUtils.setOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drivetrain {
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    public Drivetrain (HardwareMap hardwareMap) {

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

    }


    public void drive (double YPower, double XPower, double HeadingPower,  double Slow){

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

        telemetry.addData( "Y vector = ", + YVector);
        telemetry.addData( "X vector = ", + XVector);
        telemetry.addData( "Heading vector = ", + HVector);

    }

    public void driveDO(double drive, double strafe, double turn, double slow, double heading){
        Vector2d driveVector = new Vector2d(strafe, drive);
        Vector2d rotatedVector = driveVector.rotate(Math.toRadians(heading));

        drive = rotatedVector.y;
        strafe = rotatedVector.x;



        if (slow > 0.05){
            fl.setPower((drive + strafe + turn) * -0.5);
            fr.setPower((drive - strafe - turn) * -0.5);
            bl.setPower((drive - strafe + turn) * -0.5);
            br.setPower((drive + strafe - turn) * -0.5);
        } else {
            fl.setPower((drive + strafe + turn) * -1);
            fr.setPower((drive - strafe - turn) * -1);
            bl.setPower((drive - strafe + turn) * -1);
            br.setPower((drive + strafe - turn) * -1);
        }

    }

//    public void testDrive (double speed) {
//        fr.setPower(speed);
//        fl.setPower(-speed);
//        br.setPower(speed);
//        bl.setPower(-speed);
//    }




}
