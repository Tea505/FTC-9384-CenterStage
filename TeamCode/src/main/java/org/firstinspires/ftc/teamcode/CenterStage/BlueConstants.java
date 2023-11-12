package org.firstinspires.ftc.teamcode.CenterStage;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
public class BlueConstants {

    // VELOCITY AND ACCELERATION
    public static final int MAX_VEL = 100;
    public static final int MAX_ACCEL = 100;
    public static final int FAST_VEL = 74;
    public static final int FAST_ACCEL = 74;
    public static final int VELO = 58;
    public static final int ACCEL = 58;

    public static final TrajectoryVelocityConstraint Vel0 = SampleMecanumDrive.getVelocityConstraint
            (FAST_VEL,
                    Math.toRadians(180), Math.toRadians(180));
    public static final TrajectoryAccelerationConstraint Accel0 = SampleMecanumDrive.getAccelerationConstraint
            (FAST_ACCEL);

    /* ======= COORDINATE CONSTANTS ======= */

        /* === LEFT SIDE === */

    // LEFT TRAJECTORY

    // TEAM PROP LOCATION == LEFT BACKDROP

    // TEAM PROP LOCATION == MIDDLE BACKDROP

    // TEAM PROP LOCATION == RIGHT BACKDROP

        /* === RIGHT SIDE === */

    // RIGHT TRAJECTORY
    // take in account that "UNLOAD" for RIGHT is also positioning

    // TEAM PROP LOCATION == LEFT BACKDROP

    // TEAM PROP LOCATION == MIDDLE BACKDROP

    // TEAM PROP LOCATION == RIGHT BACKDROP

    /* ======= END COORDINATE CONSTANTS  ======= */
}