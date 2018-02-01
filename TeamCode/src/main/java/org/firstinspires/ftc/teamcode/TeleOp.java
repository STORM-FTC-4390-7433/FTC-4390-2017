package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by FTC-4390 on 11/8/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {
    private DcMotor leftMotor1 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor2 = null;
    private DcMotor leftArmMotor = null;
    private DcMotor rightArmMotor = null;
    private DcMotor centralArmMotor = null;
    private Servo leftArmServo = null;
    private Servo rightArmServo = null;

    public void init() {

        // Get motor and servo names
        leftMotor1 = hardwareMap.dcMotor.get("leftMotor1");
        rightMotor1 = hardwareMap.dcMotor.get("rightMotor1");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");
        leftArmMotor = hardwareMap.dcMotor.get("leftArmMotor");
        rightArmMotor = hardwareMap.dcMotor.get("rightArmMotor");
        centralArmMotor = hardwareMap.dcMotor.get("centralArmMotor");
        leftArmServo = hardwareMap.servo.get("leftArmServo");
        rightArmServo = hardwareMap.servo.get("rightArmServo");

        //leftArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);

    }

    public void loop() {
        float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;

        //move the whole arm up or down
        if (gamepad2.y) { // Up
            leftArmMotor.setPower(-.5);
            rightArmMotor.setPower(.5);
        } else if (gamepad2.a) { // Down
            leftArmMotor.setPower(.05);
            rightArmMotor.setPower(-.05);
        } else { // Stop
            leftArmMotor.setPower(0);
            rightArmMotor.setPower(0);
        }

        //move the whole arm in or out
        if (gamepad2.dpad_up) {
            centralArmMotor.setPower(.5);
        } else if (gamepad2.dpad_down) {
            centralArmMotor.setPower(-.5);
        } else {
            centralArmMotor.setPower(0);
        }

        //rotate the left arm in or out
        if (gamepad2.left_bumper)
            leftArmServo.setPosition(1);
        else if (gamepad2.left_trigger > 0)
            leftArmServo.setPosition(.75);

        //rotate the right arm in or out
        if (gamepad2.right_bumper)
            rightArmServo.setPosition(0);
        else if (gamepad2.right_trigger > 0)
            rightArmServo.setPosition(.25);

        throttleRight = Range.clip(throttleRight, -1, 1);
        throttleLeft = Range.clip(throttleLeft, -1, 1);


        throttleRight = (float) scaleInput(throttleRight);
        throttleLeft = (float) scaleInput(throttleLeft);



        leftMotor1.setPower(throttleLeft);
        rightMotor1.setPower(throttleRight);
        leftMotor2.setPower(throttleLeft);
        rightMotor2.setPower(throttleRight);

    }

    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.10, 0.15, 0.17, 0.20, 0.25, 0.30,
                0.35, 0.40, 0.45, 0.50, 0.55, 0.60, 0.65, 0.70, 0.75, 0.80, 0.85, 0.90, 0.95, 1.00, 1.00};

        //Original scaleInput value array

           /*double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };*/

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}