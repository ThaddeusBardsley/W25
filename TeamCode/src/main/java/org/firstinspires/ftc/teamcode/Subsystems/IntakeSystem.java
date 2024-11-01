package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSystem {
    CRServo servo;
    public void intakeSystem(HardwareMap hardwareMap) {
        servo = hardwareMap.get(CRServo.class, "servo");
    }

    public void spin(double powerInput) {
        servo.setPower(powerInput);
    }
}