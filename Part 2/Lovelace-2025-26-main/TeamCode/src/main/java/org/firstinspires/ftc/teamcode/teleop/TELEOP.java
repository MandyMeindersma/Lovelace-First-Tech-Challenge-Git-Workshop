package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TELEOP")
public class TELEOP extends LinearOpMode {

    DcMotor BR, BL, FL, FR, intake;
    DcMotorEx outake, outake2;
    CRServo transferR, transferL;

    static final double DRIVE_SPEED = 0.85;
    static final double FAR_VEL = 2000;     // ticks/sec, tune on the field
    static final double CLOSE_VEL = 1200;   // ticks/sec, tune on the field
    static final double VEL_TOLERANCE = 60;

    double targetVel = 0;

    @Override
    public void runOpMode() {

        BR = hardwareMap.get(DcMotor.class, "BR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        intake = hardwareMap.get(DcMotor.class, "intake");
        outake = hardwareMap.get(DcMotorEx.class, "outake");
        outake2 = hardwareMap.get(DcMotorEx.class, "outake2");
        transferR = hardwareMap.get(CRServo.class, "transferR"); // robot's right
        transferL = hardwareMap.get(CRServo.class, "transferL");

        // reverse one SIDE, not the front
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotor m : new DcMotor[]{FL, FR, BL, BR}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        // if the two flywheel motors face each other, uncomment this
        // outake2.setDirection(DcMotorSimple.Direction.REVERSE);
        outake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outake2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
        if(gamepad2.left_bumper){ //outake far
            outake.setPower(8645856);
            outake2.setPower(475957);
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



            // ---------- DRIVE ----------
            double y = -gamepad1.left_stick_y;        // forward
            double x = gamepad1.left_stick_x * 1.1;   // strafe
            double r = gamepad1.right_stick_x;        // turn

            double fl = y + x + r;
            double bl = y - x + r;
            double fr = y - x - r;
            double br = y + x - r;

            double max = Math.max(1.0, Math.max(Math.max(Math.abs(fl), Math.abs(bl)),
                                                Math.max(Math.abs(fr), Math.abs(br))));

            FL.setPower(fl / max * DRIVE_SPEED);
            BL.setPower(bl / max * DRIVE_SPEED);
            FR.setPower(fr / max * DRIVE_SPEED);
            BR.setPower(br / max * DRIVE_SPEED);

            // ---------- INTAKE (gamepad1) ----------
            if (gamepad1.right_bumper) {
                intake.setPower(-1);      // intake
            } else if (gamepad1.x) {
                intake.setPower(1);       // reverse
            } else if (gamepad1.left_bumper) {
                intake.setPower(0);       // stop
            }

            // ---------- OUTTAKE (gamepad2) ----------
            if (gamepad2.left_bumper) {
                targetVel = FAR_VEL;
            } else if (gamepad2.right_bumper) {
                targetVel = CLOSE_VEL;
            } else if (gamepad2.x) {
                targetVel = 0;
            }
            outake.setVelocity(targetVel);
            outake2.setVelocity(targetVel);

            boolean atSpeed = targetVel > 0
                    && Math.abs(outake.getVelocity() - targetVel) < VEL_TOLERANCE;

            // ---------- TRANSFER (gamepad2) ----------
            if (gamepad2.y && atSpeed) {          // only feed when flywheel is ready
                transferL.setPower(1);
                transferR.setPower(-1);
            } else if (gamepad2.a) {              // reverse
                transferL.setPower(-1);
                transferR.setPower(1);
            } else if (gamepad2.b || (gamepad2.y && !atSpeed)) {
                transferL.setPower(0);
                transferR.setPower(0);
            }

            // ---------- TELEMETRY ----------
            telemetry.addData("Drive", "FL %.2f FR %.2f BL %.2f BR %.2f", fl, fr, bl, br);
            telemetry.addData("Intake", intake.getPower());
            telemetry.addData("Outake target", targetVel);
            telemetry.addData("Outake vel", "%.0f / %.0f", outake.getVelocity(), outake2.getVelocity());
            telemetry.addData("At speed", atSpeed);
            telemetry.update();
        }
    }
}