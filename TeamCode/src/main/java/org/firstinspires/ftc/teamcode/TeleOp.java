package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
    private CRServo leftArmServo = null;
    private Servo rightArmServo = null;

    public void init() {
        leftMotor1 = hardwareMap.dcMotor.get("leftMotor1");
        rightMotor1 = hardwareMap.dcMotor.get("rightMotor1");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");
        leftArmMotor = hardwareMap.dcMotor.get("leftArmMotor");
        rightArmMotor = hardwareMap.dcMotor.get("rightArmMotor");
        centralArmMotor = hardwareMap.dcMotor.get("centralArmMotor");
        leftArmServo = hardwareMap.crservo.get("leftArmServo");
        rightArmServo = hardwareMap.servo.get("rightArmServo");


        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);

    }

    public void loop() {
        float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;

        //move the whole arm up or down
        if (gamepad2.y) {
            leftArmMotor.setPower(.5);
            rightArmMotor.setPower(-.5);
        } else if (gamepad2.a) {
            leftArmMotor.setPower(-.2);
            rightArmMotor.setPower(.2);
        } else {
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
        if (gamepad2.left_bumper) {
            leftArmServo.setPower(0.3);
        } else if (gamepad2.left_trigger > 0) {
            leftArmServo.setPower(-0.3);
        } else {
            leftArmServo.setPower(0);
        }

        //rotate the right arm in or out
        if (gamepad2.right_bumper)
            rightArmServo.setPosition(0.3);
        else if (gamepad2.right_trigger > 0)
            rightArmServo.setPosition(0);


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
        // Define the values for the scale. Notice how the values don't change by a lot at the
        // beginning but change a lot at the higher levels? This means that there will be a
        // small change in speed when you move the stick a little bit, but a large change
        // in speed as you push more. You can change these values to adjust that.
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

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