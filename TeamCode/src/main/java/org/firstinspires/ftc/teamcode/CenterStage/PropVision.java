package org.firstinspires.ftc.teamcode.CenterStage;

import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class PropVision implements VisionProcessor {

    // Set resolution
    public int height = 720;
    public int width = 1280;

    // Create the dividers
    public int LEFT_LINE = (width / 3);
    public int RIGHT_LINE = (2 * (width / 3));

    // Default side and color
    Side side = Side.LEFT;
    public boolean isRed;

    // Telemetry variable
    public Telemetry telemetry;

    // CUSTOM LED LIGHTING HAHAHAHAHHA
    public static final Scalar COLOR = new Scalar(70, 255, 150);

    public PropVision(Telemetry telemetry) {
        this.telemetry = telemetry;
        this.isRed = true;
    }

    public PropVision(Telemetry telemetry, boolean isRed) {
        this.telemetry = telemetry;
        this.isRed = isRed;
    }



    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {

        Scalar LOW_HSV;
        Scalar HIGH_HSV;
        
        if (isRed) {
            // Red Values
            LOW_HSV = new Scalar(160, 50, 50);
            HIGH_HSV = new Scalar(180, 255, 255);
        } else {
            // Blue Values
            LOW_HSV = new Scalar(110, 50, 50);
            HIGH_HSV = new Scalar(120, 255, 255);
        }

        Mat mat = new Mat();

        // Convert to HSV
        Imgproc.cvtColor(frame, mat, Imgproc.COLOR_RGB2HSV);

        // mesh
        Mat mesh = new Mat();

        Core.inRange(mat, LOW_HSV,HIGH_HSV, mesh);

        // submat
        Mat LEFT = mesh.submat(height / 2, height,0, LEFT_LINE);
        Mat CENTER = mesh.submat(height / 2,height, LEFT_LINE, RIGHT_LINE);
        Mat RIGHT = mesh.submat(height / 2, height, RIGHT_LINE, width);

        // Draw left and right line to seperate the spike marks
        Imgproc.line(frame,new Point(LEFT_LINE,0), new Point(LEFT_LINE,height), COLOR,5);
        Imgproc.line(frame,new Point(RIGHT_LINE,0), new Point(RIGHT_LINE,height), COLOR,5);

        // Calculate the coefficient of each
        int LEFT_VAL = Core.countNonZero(LEFT);
        int CENTER_VAL = Core.countNonZero(CENTER);
        int RIGHT_VAL = Core.countNonZero(RIGHT);

        // Display side
        if (RIGHT_VAL > CENTER_VAL && RIGHT_VAL > LEFT_VAL) {
            side = Side.RIGHT;
        } else if (CENTER_VAL > LEFT_VAL && CENTER_VAL > RIGHT_VAL) {
            side = Side.CENTER;
        } else {
            side = Side.LEFT;
        }

        telemetry.addLine("LEFT: " + LEFT_VAL);
        telemetry.addLine("CENTER: " + CENTER_VAL);
        telemetry.addLine("RIGHT: " + RIGHT_VAL);
        telemetry.addLine("Side: " + side);
        telemetry.update();

        // return frames
        frame.copyTo(frame);
        // clear out the memory
        mat.release();
        mesh.release();
        LEFT.release();
        RIGHT.release();
        CENTER.release();

        return null;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }
}
