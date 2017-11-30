package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;


@Autonomous(name="MainAutonomous", group="Autonomous")  // @Autonomous(...) is the other common choice
public class AutonomousMain extends LinearOpMode {

    /* Declare OpMode members. */

    private boolean hardCode = true;

    private ElapsedTime runtime = new ElapsedTime();
    //private DcMotor shooterLeft = null;
    //private DcMotor shooterRight = null;
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;
    //private DcMotor sweeper = null;
    //private DcMotor conveyor = null;
    private CRServo leftArmServo = null;
    private CRServo rightArmServo = null;
    private ColorSensor jewelColorSensor = null;
    private int programState = 1;

    static final double     COUNTS_PER_MOTOR_REV    = 1368 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.8 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        // leftMotor  = hardwareMap.dcMotor.get("left motor");
        // rightMotor = hardwareMap.dcMotor.get("right motor");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        leftMotor1 = hardwareMap.dcMotor.get("leftMotor1");
        rightMotor1 = hardwareMap.dcMotor.get("rightMotor1");
        leftMotor2 = hardwareMap.dcMotor.get("leftMotor2");
        rightMotor2 = hardwareMap.dcMotor.get("rightMotor2");
        leftArmServo = hardwareMap.crservo.get("leftArmServo");
        rightArmServo = hardwareMap.crservo.get("rightArmServo");

        //shooterLeft = hardwareMap.dcMotor.get("shooterLeft");
        /*shooterRight = hardwareMap.dcMotor.get("shooterRight");
        sweeper = hardwareMap.dcMotor.get("sweeper");
        conveyor = hardwareMap.dcMotor.get("conveyor");
*/

        // eg: Set the drive motor directions:
        // Reverse the motor that runs backwards when connected directly to the battery
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        runtime.reset();

        if(hardCode == true) {
            telemetry.addData("Status", "Resetting Encoders");    //
            telemetry.update();
            leftMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            idle();
            leftMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addData("Path0", "Starting at %7d :%7d",
                    leftMotor1.getCurrentPosition(),
                    leftMotor2.getCurrentPosition(),
                    rightMotor1.getCurrentPosition(),
                    rightMotor2.getCurrentPosition());
            telemetry.update();
            encoderDrive(DRIVE_SPEED, 32.25, 32.25, 15);  // S1: Forward 47 Inches with 5 Sec timeout

            hardCode = false;
        }

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
            /*shooterLeft.setPower(0.37);
            shooterRight.setPower(0.37);*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //conveyor.setPower(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*conveyor.setPower(0);
            shooterLeft.setPower(0);
            shooterRight.setPower(0);*/
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            leftMotor1.setPower(1);
            rightMotor1.setPower(1);
            leftMotor2.setPower(1);
            rightMotor2.setPower(1);
            try {
                Thread.sleep(625);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            leftMotor1.setPower(0);
            rightMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor2.setPower(0);
            programState = 0;

            requestOpModeStop();



            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) throws InterruptedException {
        int newLeftTarget;
        int newRightTarget;


        // Ensure that the opmode is still active
        if (opModeIsActive()) {


            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor1.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor1.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftTarget = leftMotor2.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightMotor2.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            leftMotor1.setTargetPosition(newLeftTarget);
            rightMotor1.setTargetPosition(newRightTarget);
            leftMotor2.setTargetPosition(newLeftTarget);
            rightMotor2.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            leftMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            leftMotor1.setPower(Math.abs(speed));
            rightMotor1.setPower(Math.abs(speed));
            leftMotor2.setPower(Math.abs(speed));
            rightMotor2.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor1.isBusy() && rightMotor1.isBusy())) {


                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftMotor1.getCurrentPosition(),
                        rightMotor1.getCurrentPosition());
                telemetry.update();


                // Allow time for other processes to run.
                idle();
            }


            // Stop all motion;
            leftMotor1.setPower(0);
            rightMotor1.setPower(0);


            // Turn off RUN_TO_POSITION
            leftMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //  sleep(250);   // optional pause after each move
        }
    }
}
