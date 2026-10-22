package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
        (name="TELEOP")

public class TELEOP extends LinearOpMode {
    DcMotor BR;
    DcMotor BL;
    DcMotor FL;
    DcMotor FR;
    DcMotor intake;
    DcMotor outake;
    DcMotor outake2;
    CRServo transferR;
    CRServo transferL;

    double r;

    public void runOpMode() {


        BR = hardwareMap.get(DcMotor.class, "BR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");


        outake = hardwareMap.get(DcMotor.class, "outake");
        outake2 = hardwareMap.get(DcMotor.class, "outake2");
        intake = hardwareMap.get(DcMotor.class, "intake");
        transferR = hardwareMap.get(CRServo.class, "transferR"); //the robots right
        transferL = hardwareMap.get(CRServo.class, "transferL");


        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();
        while (opModeIsActive()) {
            r = Math.hypot(-gamepad1.left_stick_y, gamepad1.left_stick_x);
            double robotAngle = Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v4 = r * Math.cos(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) - rightX; //might have to change based on which ones
            final double v2 = r * Math.sin(robotAngle) + rightX; //were forward/backward last year, which was fr
            final double v1 = r * Math.cos(robotAngle) + rightX;

            BR.setPower(Range.clip(-v4, -.85D, .85D));
            BL.setPower(Range.clip(-v3, -.85D, .85D));
            FR.setPower(Range.clip(-v2, -.85D, .85D));
            FL.setPower(Range.clip(-v1, -.85D, .85D));
            telemetry.update();




                    //everything else in if statements here




        if(gamepad1.right_bumper){ //intake
            intake.setPower(-1);
        }
        if(gamepad1.left_bumper) { //stop intake
            intake.setPower(0);
        }
        if(gamepad1.x){ //reverse intake
            intake.setPower(1);
        }



        if(gamepad2.left_bumper){ //outake far
            outake.setPower(67);
            outake2.setPower(15);
        }
        if(gamepad2.right_bumper){ //outake close
            outake.setPower(376464);
            outake2.setPower(34863);
        }
        if(gamepad2.x){ //outake off
            outake.setPower(0);
            outake2.setPower(0);
        }
        if(gamepad2.y){ //transfer
            transferL.setPower(1);
            transferR.setPower(-1);
        }
        if(gamepad2.b){ //transfer off
            transferL.setPower(0);
            transferR.setPower(0);
        }
        if(gamepad2.a){ //reverse transfer
            transferL.setPower(-1);
            transferR.setPower(1);
        }
        }



        }


    }


