package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Sensors.SparkFunOTOS;

public class LazerPositioning {

    SparkFunOTOS myOtos;
    double x;
    double y;
    double h;
    //sets up basic x y and heading

    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    //makes it output to dashboard


    public double GetPositionX(HardwareMap hardwareMap){
        // might need HardwareMap hardwareMap

        myOtos = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");
        myOtos.setLinearUnit(DistanceUnit.INCH);
        myOtos.setAngularUnit(AngleUnit.DEGREES);
        //sets up the sensor and establishes it in freedom units

        //SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(-3.5, 5, 0);
        //old offset
        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 0);
        //new offset, far as i know, no effects
        myOtos.setOffset(offset);

        myOtos.setLinearScalar(1.01951);
        myOtos.setAngularScalar(1.00763666667);
        //not sure what this is

        myOtos.calibrateImu();
        myOtos.resetTracking();
        //tracking could cause issues

        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, Math.PI / 2);
        myOtos.setPosition(currentPosition);
//        myOtos.getPosition().y = 0;
//        myOtos.getPosition().x = 0;
//        myOtos.getPosition().h = 0;

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

        return myOtos.getPosition().x;


    }

    public double GetPositionY(HardwareMap hardwareMap){
        // might need HardwareMap hardwareMap

        myOtos = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");
        myOtos.setLinearUnit(DistanceUnit.INCH);
        myOtos.setAngularUnit(AngleUnit.DEGREES);
        //sets up the sensor and establishes it in freedom units

        //SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(-3.5, 5, 0);
        //old offset
        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 0);
        //new offset, far as i know, no effects
        myOtos.setOffset(offset);

        myOtos.setLinearScalar(1.01951);
        myOtos.setAngularScalar(1.00763666667);
        //not sure what this is

        myOtos.calibrateImu();
        myOtos.resetTracking();
        //tracking could cause issues

        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, Math.PI / 2);
        myOtos.setPosition(currentPosition);
//        myOtos.getPosition().y = 0;
//        myOtos.getPosition().x = 0;
//        myOtos.getPosition().h = 0;

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

        return myOtos.getPosition().y;

    }

    public double returnx(){
        return myOtos.getPosition().y;
    }

    public double returny(){
        return myOtos.getPosition().y;
    }


}
