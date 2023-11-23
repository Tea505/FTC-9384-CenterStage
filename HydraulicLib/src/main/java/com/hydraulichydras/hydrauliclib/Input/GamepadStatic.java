package com.hydraulichydras.hydrauliclib.Input;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Gamepad Static class works as a utility class of static methods that provide functionality related to gamepad input handling.
 */
public class GamepadStatic {

    public enum Input {
        NONE,
        START,
        BACK,
        A,
        B,
        X,
        Y,
        DPAD_UP,
        DPAD_DOWN,
        DPAD_LEFT,
        DPAD_RIGHT,
        LEFT_BUMPER,
        RIGHT_BUMPER,
        LEFT_STICK_BUTTON,
        RIGHT_STICK_BUTTON
    }

    public GamepadStatic() {}

    public static Input gamepadToEnum(Gamepad gamepad) {
        if (gamepad.dpad_up) return setDPadUp();
        if (gamepad.dpad_down) return setDPadDown();
        if (gamepad.dpad_left) return setDPadLeft();
        if (gamepad.dpad_right) return setDPadRight();
        if (gamepad.a) return setA();
        if (gamepad.b) return setB();
        if (gamepad.x) return setX();
        if (gamepad.y) return setY();
        if (gamepad.start) return setStart();
        if (gamepad.back) return setBack();
        if (gamepad.left_bumper) return setLeftBumper();
        if (gamepad.right_bumper) return setRightBumper();
        if (gamepad.left_stick_button) return setLeftStickButton();
        if (gamepad.right_stick_button) return setRightStickButton();

        return Input.NONE;
    }

    public static boolean isButtonPressed(Gamepad gamepad, Input button) {
        switch (button) {
            case DPAD_UP:
                return gamepad.dpad_up;
            case DPAD_DOWN:
                return gamepad.dpad_down;
            case DPAD_LEFT:
                return gamepad.dpad_left;
            case DPAD_RIGHT:
                return gamepad.dpad_right;
            case A:
                return gamepad.a;
            case B:
                return gamepad.b;
            case X:
                return gamepad.x;
            case Y:
                return gamepad.y;
            case START:
                return gamepad.start;
            case BACK:
                return gamepad.back;
            case LEFT_BUMPER:
                return gamepad.left_bumper;
            case RIGHT_BUMPER:
                return gamepad.right_bumper;
            case LEFT_STICK_BUTTON:
                return gamepad.left_stick_button;
            case RIGHT_STICK_BUTTON:
                return gamepad.right_stick_button;
            case NONE:
                return false;
            default:
                return false;
        }
    }

    public static Input setDPadUp() {
        return Input.DPAD_UP;
    }

    public static Input setDPadDown() {
        return Input.DPAD_DOWN;
    }

    public static Input setDPadLeft() {
        return Input.DPAD_LEFT;
    }

    public static Input setDPadRight() {
        return Input.DPAD_RIGHT;
    }

    public static Input setA() {
        return Input.A;
    }

    public static Input setB() {
        return Input.B;
    }

    public static Input setX() {
        return Input.X;
    }

    public static Input setY() {
        return Input.Y;
    }

    public static Input setStart() {
        return Input.START;
    }

    public static Input setBack() {
        return Input.BACK;
    }

    public static Input setLeftBumper() {
        return Input.LEFT_BUMPER;
    }

    public static Input setRightBumper() {
        return Input.RIGHT_BUMPER;
    }

    public static Input setLeftStickButton() {
        return Input.LEFT_STICK_BUTTON;
    }

    public static Input setRightStickButton() {
        return Input.RIGHT_STICK_BUTTON;
    }

}