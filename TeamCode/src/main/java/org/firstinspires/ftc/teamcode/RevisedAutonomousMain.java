package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

/**
 * Created by FTC-4390 on 12/5/2017.
 */

@Autonomous(name="RevisedAutonomousMain", group="Autonomous")
public class RevisedAutonomousMain extends LinearOpMode{

    // Declare the motors, sensors, servos, and some variables
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    private DcMotor leftArmMotor = null;
    private DcMotor rightArmMotor = null;
    private DcMotor centralArmMotor = null;
    private Servo rightArmServo = null;
    private ColorSensor jewelSensor = null;
    //private ColorSensor testSensor = null;
    private ElapsedTime runtime = new ElapsedTime();
    private ColorSensor sensor = null;
    private Servo leftArmServo = null;
    Thread thread = null;

    @Override
    public void runOpMode() {

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

        // Set directions for each motor,
        // FORWARD is positive values,
        // REVERSE is negative values
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);

        // Set position for the servos
        leftArmServo.setPosition(0);
        rightArmServo.setPosition(0);

        // Set up telemetry to update the status
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        telemetry.addData("Status", "Running");
        telemetry.update();

        // Get jewelSensor name
        jewelSensor = hardwareMap.colorSensor.get("jewelSensor");

        // Update telemetry to add sensor
        telemetry.addData("Status", "Added sensor");
        telemetry.update();

        jewelSensor.enableLed(true); // Turn light on detect color off of regular objects

        // Add data onto phone updating the status for LED on color sensor
        telemetry.addData("Status", "Enabled LED");
        telemetry.update();

        // Reset the runtime
        runtime.reset();

        // Rotate the claw downward
        while (runtime.seconds() < 0.5) {
            rightArmMotor.setPower(.1);
            leftArmMotor.setPower(-.1);
        }

        // If jewel sensor reads red at a reading of 20 or over
        runtime.reset();
        while (runtime.seconds() <= 20) {

            // Allow color sensor to detect jewel color for five seconds
            sleep(5000);

            // After color is detected, set position for right arm servo
            if (jewelSensor.red() >= jewelSensor.blue()) {

            }
            else if (jewelSensor.blue() >= jewelSensor.red()) {
                leftArmServo.setPosition(1.0);
            }

            // Move back from jewel station
            rightMotor1.setPower(.05);
            rightMotor2.setPower(.05);
            leftMotor1.setPower(-.05);
            leftMotor2.setPower(-.05);

        }


    }
}
