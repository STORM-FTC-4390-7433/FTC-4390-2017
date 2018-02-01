package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC-4390 on 1/9/2018.
 */

//This autonomous is to be used when the robot starts off closer to the middle of arena
//as opposed to the spot that is farthest to the side

@Autonomous(name="RealAutonomousRight", group="Autonomous")
public class RealAutonomousRight extends LinearOpMode {
    private DcMotor leftMotor1 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor2 = null;
    private DcMotor leftArmMotor = null;
    private DcMotor rightArmMotor = null;
    private DcMotor centralArmMotor = null;
    private Servo leftArmServo = null;
    private Servo rightArmServo = null;
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        waitForStart();
        leftMotor1 = hardwareMap.dcMotor.get("leftMotor1");
        rightMotor1 = hardwareMap.dcMotor.get("rightMotor1");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");
        leftArmMotor = hardwareMap.dcMotor.get("leftArmMotor");
        rightArmMotor = hardwareMap.dcMotor.get("rightArmMotor");
        centralArmMotor = hardwareMap.dcMotor.get("centralArmMotor");
        leftArmServo = hardwareMap.servo.get("leftArmServo");
        rightArmServo = hardwareMap.servo.get("rightArmServo");

        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);


        runtime.reset();
        while (runtime.seconds() < 1) { // Turn left
            leftMotor1.setPower(-.1);
            leftMotor2.setPower(-.1);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }
//        runtime.reset();
//        while (runtime.seconds() < .5) { // Move forward
//            leftMotor1.setPower(.1);
//            leftMotor2.setPower(.1);
//            rightMotor1.setPower(.2);
//            rightMotor2.setPower(.2);
//        }
        runtime.reset();
        while (runtime.seconds() < 1) { // Move forward
            leftMotor1.setPower(.35);
            leftMotor2.setPower(.35);
            rightMotor1.setPower(.2);
            rightMotor2.setPower(.2);
        }
    }

}
