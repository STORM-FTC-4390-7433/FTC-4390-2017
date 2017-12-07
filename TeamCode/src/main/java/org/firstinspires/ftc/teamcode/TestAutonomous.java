package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC-4390 on 12/5/2017.
 */

@Autonomous(name="TestAutonomous", group="Autonomous")
public class TestAutonomous extends LinearOpMode{

    private ColorSensor sensor = null;
    private Servo leftArmServo = null;

    public void runOpMode() {
        sensor = hardwareMap.colorSensor.get("jewelSensor");
        leftArmServo = hardwareMap.servo.get("leftArmServo");
        while (true) {
            leftArmServo.setPosition(sensor.red() > sensor.blue() ? 0.4 : 0.6);
        }
    }

}