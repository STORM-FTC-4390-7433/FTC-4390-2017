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

@Autonomous(name="JewelAutonomous", group="Autonomous")
public class JewelAutonomous extends LinearOpMode{

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

        // Move forward
        while (runtime.seconds() < .5) {
            leftMotor1.setPower(.1);
            leftMotor2.setPower(.1);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }

        // Stop
        leftMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor1.setPower(0);
        rightMotor2.setPower(0);

        // Display output of the color sensor reading
        runtime.reset();
        while (runtime.seconds() < 20) {
            telemetry.addData("Color Number", "Red: " + jewelSensor.red() + ", Blue: " + jewelSensor.blue());
            // telemetry.addData("Color Number", jewelSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER));
            telemetry.update();
        }



        // If jewel sensor reads red at a reading of 20 or over
        if (jewelSensor.red() >= 20) {
            try {
                thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


/*        while (opModeIsActive()) {
            waitForStart();

            jewelSensor.enableLed(true); // Turn light on detect color off of regular objects

            telemetry.addData("Color Number", jewelSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER));
            telemetry.update();

            while (opModeIsActive()) {
                telemetry.addData("Color Red", sensor.red());
                telemetry.addData("Color Blue", sensor.blue());
                telemetry.update();
                leftArmServo.setPosition(sensor.red() > sensor.blue() ? 0.4 : 1.0);
                //leftArmServo.setPosition(sensor.red() > sensor.blue());
            }
        }*/
    }
}
