

package org.firstinspires.ftc.teamcode.TeleOp.DriveCode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;


@TeleOp(name="PidDrive", group="Iterative Opmode")

@Disabled
public class PidDrive extends OpMode
{
    // Declare OpMode members.
    private Drivetrain drivetrain;
    IMU gyro;

    /*
     * Code to run ONCE when the driver hits INIT
     */

    public void init() {

        telemetry.addData("Status", "init");

        drivetrain = new Drivetrain(hardwareMap);

        gyro = hardwareMap.get(IMU .class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        gyro.initialize(parameters);


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

        gyro.resetYaw();

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    public void loop() {
        telemetry.addData("Status", "loop");

        drivetrain.driveDO(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x,gamepad1.left_trigger,-gyro.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES), gamepad1.right_trigger, gamepad1.y);
        //drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x, 0.5);
        //drivetrain.testDrive(gamepad1.left_stick_y);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    public void stop() {
        telemetry.addData("Status", "Flatlined");

    }

}
