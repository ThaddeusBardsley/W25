package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Sensors.SparkFunOTOS;


public class OOPS extends OpMode {

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

        //in terms of inches

        //setOpMode(this);
        //could need this, we'll see

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

    public void loop() {

        //begin OOPS


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

}
