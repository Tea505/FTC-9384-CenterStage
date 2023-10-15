package org.firstinspires.ftc.teamcode.CenterStage.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.teamcode.CenterStage.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.CenterStage.Hardware.Launcher;

@TeleOp (name = "Qualifier TeleOp \uD83D\uDE08")
public class QualifierMode extends LinearOpMode {

    DriveTrain driveTrain = new DriveTrain(this);
    Launcher launcher = new Launcher(this);

    @Override
    public void runOpMode() {
        driveTrain.init(hardwareMap);
        launcher.init(hardwareMap);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                driveTrain.RobotCentric(gamepad1);
                launcher.loop(gamepad1);


                launcher.telemetry(telemetry);
               // driveTrain.FieldCentric(gamepad1);
            }
        }
    }
}
