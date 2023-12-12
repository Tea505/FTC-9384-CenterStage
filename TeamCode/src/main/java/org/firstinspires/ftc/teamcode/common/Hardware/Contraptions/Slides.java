package org.firstinspires.ftc.teamcode.common.Hardware.Contraptions;

import com.acmerobotics.dashboard.config.Config;
import com.hydraulichydras.hydrauliclib.Motion.MotionProfiledDcMotor;
import com.hydraulichydras.hydrauliclib.Util.Contraption;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
public class Slides extends Contraption {

    private MotionProfiledDcMotor leftSlide;
    private MotionProfiledDcMotor rightSlide;

    private TouchSensor high_limit;
    private TouchSensor low_limit;

    public static final double WHEEL_RADIUS = 0; // inches
    public static final double GEAR_RATIO = 1;
    private static final double TICKS_PER_REV = 0;

    public static double MAX_VEL = 100;
    public static double MAX_ACCEL = 100;
    public static double RETRACTION_MULTIPLIER = 1;

    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;

    public static double POS_REST = 0;
    public static double POS_GROUND = 0;
    public static double POS_LOW = 0;
    public static double POS_MEDIUM = 0;
    public static double POS_HIGH = 0;
    public static double POS_HIGH_AUTO = 0;

    public Slides(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void initialize(HardwareMap hwMap) {
        leftSlide = new MotionProfiledDcMotor(hwMap, "LeftCascade");
        leftSlide.setWheelConstants(WHEEL_RADIUS, GEAR_RATIO, TICKS_PER_REV);
        leftSlide.setMotionConstraints(MAX_VEL, MAX_ACCEL);
        leftSlide.setRetractionMultiplier(RETRACTION_MULTIPLIER);
        leftSlide.setPIDCoefficients(kP, kI, kD, kF);
        leftSlide.setTargetPosition(0);

        // leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        rightSlide = new MotionProfiledDcMotor(hwMap, "RightCascade");
        rightSlide.setWheelConstants(WHEEL_RADIUS, GEAR_RATIO, TICKS_PER_REV);
        rightSlide.setMotionConstraints(MAX_VEL, MAX_ACCEL);
        rightSlide.setRetractionMultiplier(RETRACTION_MULTIPLIER);
        rightSlide.setPIDCoefficients(kP, kI, kD, kF);
        rightSlide.setTargetPosition(0);

        // rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        high_limit = hwMap.get(TouchSensor.class, "high_Limit");
        low_limit = hwMap.get(TouchSensor.class, "low_Limit");
    }

    @Override
    public void loop(Gamepad gamepad2) {

        // Put loop blocks here.
        if (gamepad2.right_trigger > 0 && !high_limit.isPressed()) {
            // up
            leftSlide.setPower(gamepad2.right_trigger * -0.86);
            rightSlide.setPower(gamepad2.right_trigger * -0.86);
        } else if (gamepad2.left_trigger > 0 && !low_limit.isPressed()) {
            // down
            leftSlide.setPower(gamepad2.left_trigger * 0.8);
            rightSlide.setPower(gamepad2.left_trigger * 0.8);
        } else {
            leftSlide.setPower(0);
            rightSlide.setPower(0);
        }

    }


    // AUTO functions
    public void setMotionConstraints(double value) {
        leftSlide.setMotionConstraints(value, value);
        rightSlide.setMotionConstraints(value, value);
    }

    public void rest() {
        leftSlide.setTargetPosition(POS_REST);
        rightSlide.setTargetPosition(POS_REST);
    }

    public void extendGround() {
        leftSlide.setTargetPosition(POS_GROUND);
        rightSlide.setTargetPosition(POS_GROUND);
    }

    public void extendLow() {
        leftSlide.setTargetPosition(POS_LOW);
        rightSlide.setTargetPosition(POS_LOW);
    }

    public void extendMedium() {
        leftSlide.setTargetPosition(POS_MEDIUM);
        rightSlide.setTargetPosition(POS_MEDIUM);
    }

    public void extendHigh() {
        leftSlide.setTargetPosition(POS_HIGH);
        rightSlide.setTargetPosition(POS_HIGH);
    }

    public void extendHighAuto() {
        leftSlide.setTargetPosition(POS_HIGH_AUTO);
        rightSlide.setTargetPosition(POS_HIGH_AUTO);
    }

    public void extendToPosition(double pos) {
        leftSlide.setTargetPosition(pos);
        rightSlide.setTargetPosition(pos);
    }

    public void setPower(double power) {
        leftSlide.setPower(power);
        rightSlide.setPower(power);
    }

    public void update() {
        leftSlide.update();
        rightSlide.update();
    }

    public double getPosition() {
        return (leftSlide.getPosition() + rightSlide.getPosition()) / 2.0;
    }


    @Override
    public void telemetry(Telemetry telemetry) {
        telemetry.addData("positionLeft", leftSlide.getPosition());
        telemetry.addData("positionRight", rightSlide.getPosition());

        telemetry.addData("VelocityLeft", leftSlide.getVelocity());
        telemetry.addData("VelocityRight", rightSlide. getVelocity());

        telemetry.addData("currentLeft", leftSlide.getCurrent(CurrentUnit.AMPS));
        telemetry.addData("currentRight", rightSlide.getCurrent(CurrentUnit.AMPS));

    }
}


