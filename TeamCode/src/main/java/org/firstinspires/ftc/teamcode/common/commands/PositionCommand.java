package org.firstinspires.ftc.teamcode.common.commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.hydraulichydras.hydrauliclib.Path.Localizer;
import com.hydraulichydras.hydrauliclib.Path.Drivetrain;
import com.hydraulichydras.hydrauliclib.Geometry.Pose;

import org.firstinspires.ftc.teamcode.common.Hardware.RobotHardware;
@Config
public class PositionCommand extends CommandBase {
    Localizer localizer;
    Drivetrain drivetrain;
    Pose targetPose;

    // X cord pid
    public static double xP = 0.0385;
    public static double xD = 0.005;

    // y cord pid
    public static double yP = 0.0385;
    public static double yD = 0.005;

    // heading pid
    public static double hP = 0.75;
    public static double hD = 0.02;

    public static double kStatic = 0.05;

    public static PIDFController xController = new PIDFController(xP, 0.0, xD, 0);
    public static PIDFController yController = new PIDFController(yP, 0.0, yD, 0);
    public static PIDFController hController = new PIDFController(hP, 0.0, hD, 0);

    public static double ALLOWED_TRANSLATIONAL_ERROR = 1; // inches
    public static double ALLOWED_HEADING_ERROR = 0.03; // radians

    private RobotHardware robot = RobotHardware.getInstance();

    private ElapsedTime timer;
    private ElapsedTime stable;

    public PositionCommand(Drivetrain drivetrain, Localizer localizer, Pose targetPose) {
        this.drivetrain = drivetrain;
        this.localizer = localizer;
        this.targetPose = targetPose;
    }

    /**
     *
     */
    @Override
    public void execute() {
        if (timer == null) timer = new ElapsedTime();
        if (stable == null) stable = new ElapsedTime();

        Pose robotPose = localizer.getPos();

        Pose powers = getPower(robotPose);
        drivetrain.set(powers);
    }

    @Override
    public boolean isFinished() {
        Pose robotPose = localizer.getPos();
        Pose delta = targetPose.subtract(robotPose);
        System.out.println(delta.toVec2D().magnitude() + " " + delta.heading);

        if (delta.toVec2D().magnitude() > ALLOWED_TRANSLATIONAL_ERROR
                || Math.abs(delta.heading) > ALLOWED_HEADING_ERROR) {
            stable.reset();
        }

        return timer.milliseconds() > 5000 || stable.milliseconds() > 150;
    }

    public Pose getPower(Pose robotPose) {
        Pose delta = targetPose.subtract(robotPose);

        delta.x = Math.signum(delta.x) * Math.sqrt(Math.abs(delta.x));
        delta.y = Math.signum(delta.y) * Math.sqrt(Math.abs(delta.y));

        double xPower = xController.calculate(0, delta.x);
        double yPower = yController.calculate(0, delta.y);
        double hPower = -hController.calculate(0, delta.heading);

        double x_rotated = xPower * Math.cos(robotPose.heading) - yPower * Math.sin(robotPose.heading);
        double y_rotated = xPower * Math.sin(robotPose.heading) + yPower * Math.cos(robotPose.heading);

        if (Math.abs(x_rotated) < 0.01) x_rotated = 0;
        else x_rotated += kStatic * Math.signum(x_rotated);
        if (Math.abs(y_rotated) < 0.01) y_rotated = 0;
        else y_rotated += kStatic * Math.signum(y_rotated);
        if (Math.abs(hPower) < 0.01) hPower = 0;
        else hPower += kStatic * Math.signum(hPower);


        // todo replace first 12 with voltage
        return new Pose((y_rotated / robot.getVoltage() * 12.5) / (Math.sqrt(2) / 2), x_rotated / robot.getVoltage() * 12.5, hPower / robot.getVoltage() * 12.5);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.set(new Pose());
    }
}
