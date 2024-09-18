

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="MakeMove", group="Iterative Opmode")

public class MakeMove extends OpMode
{
    // Declare OpMode members.
    public Drivetrain drivetrain;

    /*
     * Code to run ONCE when the driver hits INIT
     */

    public void init() {
        telemetry.addData("Status", "init");

        drivetrain = new Drivetrain();


    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */

    public void init_loop() {
        telemetry.addData("Status", "init_loop");


    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */

    public void start() {
        telemetry.addData("Status", "start");

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    public void loop() {
        telemetry.addData("Status", "loop");

        drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, gamepad1.right_trigger);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    public void stop() {
        telemetry.addData("Status", "Flatlined");

    }

}
