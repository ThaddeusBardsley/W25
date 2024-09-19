
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
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


    public void drive (double YPower, double XPower, double HeadingPower, double Speed){

        //
        fl.setPower(-(YPower - XPower + HeadingPower) * Speed);
        //
        fr.setPower((YPower + XPower - HeadingPower) * Speed);
        //
        bl.setPower(-(YPower + XPower + HeadingPower) * Speed);
        //backwards
        br.setPower((YPower - XPower - HeadingPower) * Speed);

    }

//    public void testDrive (double speed) {
//        fr.setPower(speed);
//        fl.setPower(-speed);
//        br.setPower(speed);
//        bl.setPower(-speed);
//    }




}
