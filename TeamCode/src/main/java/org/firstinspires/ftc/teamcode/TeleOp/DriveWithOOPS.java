package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Sensors.SparkFunOTOS;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.OOPS;

//this chunk of code establishes that it is a OpMode, and creates the neccecary parameters for it.
//it also creates the name and group for the code

@TeleOp(name = "TeleOpBlank", group = "Iterative Opmode")


//disables this specific program so it dosnt show up on the android
//when using this template, delete this

public class DriveWithOOPS extends OpMode {

    //set up variables here and call subsystems

    private Drivetrain drivetrain;

    SparkFunOTOS myOtos;
    double x;
    double y;
    double h;

    int[] obstaclesy = { 3 };
    //5
    int[] obstaclesx = { 3 };
    //1

    double c = 0.001;


    double oopsYOutput;
    double oopsXOutput;

    int factor = 1;


    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();


    public void init() {

        //this code runs when you press the init button
        //code like localaziation (origin, x, y, z) goes here

        drivetrain = new Drivetrain(hardwareMap);


        myOtos = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");
        myOtos.setLinearUnit(DistanceUnit.INCH);
        myOtos.setAngularUnit(AngleUnit.DEGREES);

        //SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(-3.5, 5, 0);
        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 0);
        myOtos.setOffset(offset);

        myOtos.setLinearScalar(1.01951);
        myOtos.setAngularScalar(1.00763666667);

        myOtos.calibrateImu();
        myOtos.resetTracking();
        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, Math.PI / 2);
        myOtos.setPosition(currentPosition);
//        myOtos.getPosition().y = 0;
//        myOtos.getPosition().x = 0;
//        myOtos.getPosition().h = 0;

    }

    public void init_loop() {

        //code to loop while in the init stage

    }

    public void start() {

        //this code runs when you go from the init stage to the run stage

    }

    public void loop() {

        double trueForwards = (gamepad1.left_stick_y - oopsYOutput);
        double trueStrafe = (gamepad1.left_stick_x - oopsXOutput);

        //the majority of you code will go in here.
        //anything like drive code will be here
        //this section takes a piece of code and repeatedly calls it.

        //drivetrain.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x, gamepad1.right_trigger);
        drivetrain.Drive(trueForwards, trueStrafe, -gamepad1.right_stick_x, gamepad1.right_trigger);

        oopsYOutput = 0;
        oopsXOutput = 0;

        for (int i = 0; i < obstaclesy.length; i++) {
            double yDis = (y - obstaclesy[i]);
            double xDis = (x - obstaclesx[i]);

            oopsYOutput = oopsYOutput + yDis / (Math.sqrt((yDis * yDis) + (xDis * xDis)) * ((c / (xDis * xDis) + (yDis * yDis)) * factor));
            oopsXOutput = oopsXOutput + xDis / (Math.sqrt((yDis * yDis) + (xDis * xDis)) * ((c / (xDis * xDis) + (yDis * yDis)) * factor));


            //dashboardTelemetry.addData("OOPSX",xDis / (Math.sqrt((yDis * yDis) + (xDis * xDis)) * ((c / (xDis * xDis) + (yDis * yDis))*-1)));

        }

        dashboardTelemetry.addData("OOPSY", oopsYOutput);
        dashboardTelemetry.addData("OOPSX", oopsXOutput);

        x = myOtos.getPosition().x;
        y = myOtos.getPosition().y;
        h = myOtos.getPosition().h;

        telemetry.addData("x", x);
        telemetry.addData("y", y);
        telemetry.addData("heading", h);

        telemetry.addLine("running code....");

        dashboardTelemetry.addData("x = ", x);
        dashboardTelemetry.addData("y = ", y);
        dashboardTelemetry.addData("heading = ", h);
        dashboardTelemetry.update();

        telemetry.update();
    }

    public void stop() {

        //code to run when you stop the program

    }



}
