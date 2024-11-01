package org.firstinspires.ftc.teamcode.TeleOp.DriveCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSystem;

//this chunk of code establishes that it is a OpMode, and creates the neccecary parameters for it.
//it also creates the name and group for the code

@TeleOp(name = "TeleOpBlank", group = "Iterative Opmode")

@Disabled
//disables this specific program so it dosnt show up on the android
//when using this template, delete this

public class TestFunctions extends OpMode {
    private IntakeSystem intake;

    //set up variables here and call subsystems

    public void init() {

        intake = new IntakeSystem();

        //this code runs when you press the init button
        //code like localaziation (origin, x, y, z) goes here

    }

    public void init_loop() {

        //code to loop while in the init stage

    }

    public void start() {

        //this code runs when you go from the init stage to the run stage

    }

    public void loop() {

        intake.spin(gamepad1.right_trigger);

        //the majority of you code will go in here.
        //anything like drive code will be here
        //this section takes a piece of code and repeatedly calls it.

    }

    public void stop() {

        //code to run when you stop the program

    }



}
