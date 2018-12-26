/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Latch1", group="Linear Opmode")
@Disabled

public class OldAuto extends LinearOpMode
{

    // Declare OpMode members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor topLeft;
    private DcMotor dc2;
    private DcMotor dc3;
    private DcMotor dc4;
    private DcMotor latchMotor;
    private String status;

    @Override
    public void runOpMode()
    {
        //Equivalent of System.out.println("")
        telemetry.addData("Status", "Initialized");
        //Making it show up on the phone
        telemetry.update();
        //Try to hardwareMap this motor and if it doesn't work, catch the exception and print it
        try
        {
            topLeft = hardwareMap.get(DcMotor.class, "topLeft");
            status += "topLeft connected";
        }
        catch(Exception e)
        {
            telemetry.addData("Mistake: " + e, "");
        }
        try
        {
            dc2 = hardwareMap.get(DcMotor.class, "DC2");
            status += "DC2 connected";
        }
        catch(Exception e)
        {
            telemetry.addData("Mistake: " + e, "");
        }
        try
        {
            dc3 = hardwareMap.get(DcMotor.class, "DC3");
            status += "DC3 connected";
        }
        catch(Exception e)
        {
            telemetry.addData("Mistake: " + e, "");
        }
        try
        {
            dc4 = hardwareMap.get(DcMotor.class, "DC4");
            status += "DC4 connected";
        }
        catch(Exception e)
        {
            telemetry.addData("Mistake: " + e, "");
        }
        try
        {
            latchMotor = hardwareMap.get(DcMotor.class, "DC7");
            status += "Latch1 Motor connected";
        }
        catch(Exception e)
        {
            telemetry.addData("Mistake: " + e, "");
        }

        //Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //Run until the end of the 30 second autonomous mode (Driver presses STOP)
        while(runtime.time() <= 30) {


            while (opModeIsActive()) {

                // Setup a variable for each drive wheel to save power level for telemetry
                double leftPower;
                double rightPower;

                // Choose to drive using either Tank Mode, or POV Mode
                // Comment out the method that's not used.  The default below is POV.

                // POV Mode uses left stick to go forward, and right stick to turn.
                // - This uses basic math to combine motions and is easier to drive straight.
                double drive = -gamepad1.left_stick_y;
                double turn = gamepad1.right_stick_x;
                leftPower = Range.clip(drive + turn, -1.0, 1.0);
                rightPower = Range.clip(drive - turn, -1.0, 1.0);

                // Tank Mode uses one stick to control each wheel.
                // - This requires no math, but it is hard to drive forward slowly and keep straight.
                // leftPower  = -gamepad1.left_stick_y ;
                // rightPower = -gamepad1.right_stick_y ;

                // Send calculated power to wheels

                // Show the elapsed game time and wheel power.
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
                telemetry.update();
            }


            //TILE: 23.5 inches
            //MINERAL PLACEMENT: 29 inches in total with 14.5 inches in between each mineral
            //Constants [CURRENTLY PLACEHOLDER NUMBERS]
            double blocc = 2000; //Amount of ms to travel a tile horizontally || vertically
            double rotate90 = 3000; //Amount of ms to rotate 90 degrees
            //BEGIN OF AUTONOMOUS SEGMENT
            //UNLATCH CODE
            if(runtime.time() <= 3.4)
            {

                goRight(0.5);
                goUp(0.5);
                goLeft(0.5);
            }

            //BLOCK DETECTION
            //Booleans cause different paths
            //Will be set to true when certain path is taken
            //Left and right sides from the perspective of the robot

            //Getting code to realise which boolean statement becomes true



            //ASSIGNMENT OF WHICH BLOCK

            //RED ZONE ALIGNMENT

            //MOVEMENT TO RED ZONE

            //DROP MARKER CODE

            //GO TO CRATER
            telemetry.update();
            telemetry.addData("Runtime: ", runtime.time() + "");





        }
    }
    public void extendLatch(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            latchMotor.setPower(1);
        }
        latchMotor.setPower(0);
    }
    public void retractArm(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            latchMotor.setPower(-1);
        }
        latchMotor.setPower(0);
    }
    public void goUp(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            topLeft.setPower(1.0);
            dc2.setPower(-1.0);
            dc3.setPower(1.0);
            dc4.setPower(-1.0);
        }
        topLeft.setPower(0.0);
        dc2.setPower(0.0);
        dc3.setPower(0.0);
        dc4.setPower(0.0);

    }
    public void goDown(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            topLeft.setPower(-1.0);
            dc2.setPower(1.0);
            dc3.setPower(-1.0);
            dc4.setPower(1.0);
        }
        topLeft.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
    }
    public void goLeft(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            topLeft.setPower(-1);
            dc2.setPower(-1);
            dc3.setPower(1);
            dc4.setPower(1);
        }
        topLeft.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
    }
    public void goRight(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            topLeft.setPower(1);
            dc2.setPower(1);
            dc3.setPower(-1);
            dc4.setPower(-1);
        }
        topLeft.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
    }
    public void rotateCWise(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            topLeft.setPower(1);
            dc2.setPower(1);
            dc3.setPower(1);
            dc4.setPower(1);
        }
        topLeft.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
    }
    public void rotateCCWise(double time)
    {
        ElapsedTime ms = new ElapsedTime();
        ms.reset();
        while(ms.time()<=time)
        {
            topLeft.setPower(-1);
            dc2.setPower(-1);
            dc3.setPower(-1);
            dc4.setPower(-1);
        }
        topLeft.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
    }
    public void rotateCWiseNoNum(double power)
    {
        topLeft.setPower(power);
        dc2.setPower(power);
        dc3.setPower(power);
        dc4.setPower(power);
    }
    public void rotateCCWiseNoNum(double power)
    {
        topLeft.setPower(-power);
        dc2.setPower(-power);
        dc3.setPower(-power);
        dc4.setPower(-1*power);
    }
    public void rotateSTOP()
    {
        topLeft.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
    }
}
