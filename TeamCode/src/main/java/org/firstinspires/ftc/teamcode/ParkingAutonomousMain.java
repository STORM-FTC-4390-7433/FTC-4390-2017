package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

/**
 * Created by FTC-4390 on 11/28/2017.
 */
@Autonomous(name="ParkingAutonomous", group="Autonomous")
public class ParkingAutonomousMain extends LinearOpMode {
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    private DcMotor leftArmMotor = null;
    private DcMotor rightArmMotor = null;
    private DcMotor centralArmMotor = null;
    private Servo leftArmServo = null;
    private Servo rightArmServo = null;
    private ModernRoboticsI2cColorSensor jewelSensor = null;
    //private ColorSensor testSensor = null;
    private ElapsedTime runtime = new ElapsedTime();
    HardwareK9bot robot = new HardwareK9bot();

    public void runOpMode() {
        waitForStart();
        leftMotor1 = hardwareMap.dcMotor.get("leftMotor1");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        rightMotor1 = hardwareMap.dcMotor.get("rightMotor1");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");
        leftArmMotor = hardwareMap.dcMotor.get("leftArmMotor");
        rightArmMotor = hardwareMap.dcMotor.get("rightArmMotor");
        centralArmMotor = hardwareMap.dcMotor.get("centralArmMotor");
        leftArmServo = hardwareMap.servo.get("leftArmServo");
        rightArmServo = hardwareMap.servo.get("rightArmServo");
        jewelSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "jewelSensor");
        //testSensor = hardwareMap.colorSensor.get("jewelSensor");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

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

        runtime.reset();
        // Move forward
        while (runtime.seconds() < .5) {
            leftMotor1.setPower(-.1);
            leftMotor2.setPower(-.1);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }

        jewelSensor.enableLed(true); // Turn light on detect color off of regular objects

        telemetry.addData("Color Number", jewelSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER));
        telemetry.update();

        while (opModeIsActive()) {
            waitForStart();

            jewelSensor.enableLed(true); // Turn light on detect color off of regular objects

            telemetry.addData("Color Number", jewelSensor.readUnsignedByte(ModernRoboticsI2cColorSensor.Register.COLOR_NUMBER));
            telemetry.update();

        }

        /*while (opModeIsActive()) {
            telemetry.addData("Color", "%.1f", testSensor.red());
            telemetry.update();
        }*/

        runtime.reset();

        while (runtime.seconds() < 1.5) { // Turn Left
            leftMotor1.setPower(-.1);
            leftMotor2.setPower(-.1);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }

        // Stop
        leftMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor1.setPower(0);
        rightMotor2.setPower(0);
        runtime.reset();

        while (runtime.seconds() < 1) { // Move forward from balance stone
            leftMotor1.setPower(.35);
            leftMotor2.setPower(.35);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }

        // Stop
        leftMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor1.setPower(0);
        rightMotor2.setPower(0);



        /*while (runtime.seconds() < 3) { // Move to parking zone
            leftMotor1.setPower(.5);
            leftMotor2.setPower(.5);
            rightMotor1.setPower(.5);
            rightMotor2.setPower(.5);
        }

        // Stop
        leftMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor1.setPower(0);
        rightMotor2.setPower(0);*/

        /*testServo = hardwareMap.servo.get("testServo");
        testServo.setPosition(0);
        runtime.reset();
        while (runtime.seconds() < 3) {
            //do nothing
        }
        testServo.setPosition(1);*/

    }



}
