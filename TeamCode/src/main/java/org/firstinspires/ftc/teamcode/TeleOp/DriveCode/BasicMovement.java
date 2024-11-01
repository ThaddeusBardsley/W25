

package org.firstinspires.ftc.teamcode.TeleOp.DriveCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;


@TeleOp(name="BasicMovement", group="Iterative Opmode")

public class BasicMovement extends OpMode
{
    // Declare OpMode members.
    private Drivetrain drivetrain;

    /*
     * Code to run ONCE when the driver hits INIT
     */

    public void init() {

        telemetry.addData("Status", "init");

        drivetrain = new Drivetrain(hardwareMap);


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
        //drivetrain.drive(gamepad1.left_stick_y);

        drivetrain.Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x, gamepad1.right_trigger);
        //drivetrain.testDrive(gamepad1.left_stick_y);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    public void stop() {
        telemetry.addData("Status", "Flatlined");
    }

}
