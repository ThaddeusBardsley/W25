package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    DcMotor SlidesLeft;
    DcMotor SLidesRight;

    public void slides (HardwareMap hardwareMap) {

        SlidesLeft = hardwareMap.get(DcMotor.class, "SlidesLeft");
        SLidesRight = hardwareMap.get(DcMotor.class, "SlidesRight");
    }

    public void extend(double powerInput) {

        SlidesLeft.setPower(powerInput);
        SLidesRight.setPower(powerInput);
    }

}
