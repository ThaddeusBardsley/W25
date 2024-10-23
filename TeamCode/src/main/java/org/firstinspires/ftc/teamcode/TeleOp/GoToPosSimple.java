package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.LazerPositioning;
import org.firstinspires.ftc.teamcode.Subsystems.OOPS;

@TeleOp
public class GoToPosSimple extends OpMode {

    //private OOPS oops;

    private LazerPositioning lazer;

//    double[] positiony = { 1 };
//    //5
//    double[] positionx = { 1 };
//    //1

    double[][] targetPos = {
            {4,1}
    };

    double[][] currentPos = {
            {lazer.returnx(), lazer.returny()}
    };

    double currentXPosition = lazer.returnx();
    double currentYPosition = lazer.returny();

    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    public void init(){

        //oops = new OOPS();
        //oops.init();

        lazer = new LazerPositioning();
    }

    public void loop(){

        dashboardTelemetry.addData("current position = ", currentPos);
        dashboardTelemetry.addData("target position = ", targetPos);

        //oops.loop();

        if (Math.abs(currentPos[0][0] - targetPos[0][0]) <= 0.5){
                telemetry.addLine("Sucsess!");
                dashboardTelemetry.addLine("Sucsess!");

        } else {
            telemetry.addLine("fail!");
            dashboardTelemetry.addLine("fail!");
        }


    }
}
