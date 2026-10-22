package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.hardware.movement.backward;
import static org.firstinspires.ftc.teamcode.hardware.movement.forward;
import static org.firstinspires.ftc.teamcode.hardware.movement.rotateLeft;
import static org.firstinspires.ftc.teamcode.hardware.movement.rotateRight;
import static org.firstinspires.ftc.teamcode.movement.MOV_cam.is_ID;
import static org.firstinspires.ftc.teamcode.movement.MOV_cam.tag_ID;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.movement.MOV_cam;

import java.util.Objects;

@Autonomous
public class blue_humanplayer extends LinearOpMode {

    DcMotor FR;
    DcMotor FL;
    DcMotor BR;
    DcMotor BL;

    @Override
    public void runOpMode() {

        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        FL.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.REVERSE); //FR AND BR ARE IN REVERSE !! !! !! !!
        BL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);


        MOV_cam.vid(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            forward(20, telemetry, BL, BR, FL, FR); //forward to middle of field

            if (MOV_cam.is_ID()) {
                String tagName = MOV_cam.tag_ID();
                telemetry.addData("Detected Tag:", tagName);
            } else {
                telemetry.addLine("No tag detected");
                //add how to get to the april tag, do later.
            }
            telemetry.update();
            sleep(100); // small delay
            //stop camera
            MOV_cam cam = new MOV_cam();
            cam.stopCamera();

            String APRT = tag_ID();
            if (Objects.equals(APRT, "GPP")) {
                //starting from position de 34
                // distances assuming each block is 2ft, or 24 in
                backward(36, telemetry, BL, BR, FL, FR);
                rotateRight(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                //INTAKE
                backward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(90, telemetry, BL, BR, FL, FR);
                forward(60, telemetry, BL, BR, FL, FR);
                rotateLeft(135, telemetry, BL, BR, FL, FR);//turn around to outtake
                backward(35, telemetry, BL, BR, FL, FR);
                //OUTTAKE
                backward(35, telemetry, BL, BR, FL, FR);
                rotateRight(135, telemetry, BL, BR, FL, FR); //turn back to normal
                backward(12, telemetry, BL, BR, FL, FR);
                rotateRight(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                //INTAKE PPG
                backward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                rotateRight(135, telemetry, BL, BR, FL, FR);
                backward(35, telemetry, BL, BR, FL, FR);
                //LAUNCH
            } else if (Objects.equals(APRT, "PPG")) {
// distances assuming each block is 2ft, or 24 in
                //starting from position de 34
                forward(12, telemetry, BL, BR, FL, FR);
                rotateRight(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                //INTAKE
                backward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(135, telemetry, BL, BR, FL, FR);//turn around to outtake
                backward(35, telemetry, BL, BR, FL, FR);
                //OUTTAKE
                backward(35, telemetry, BL, BR, FL, FR);
                rotateRight(135, telemetry, BL, BR, FL, FR); //turn back to normal
                //GO GET OTHER BALLS
                backward(36, telemetry, BL, BR, FL, FR);
                rotateRight(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                //INTAKE PPG
                backward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(90, telemetry, BL, BR, FL, FR);
                forward(36, telemetry, BL, BR, FL, FR);
                rotateRight(135, telemetry, BL, BR, FL, FR);
                backward(35, telemetry, BL, BR, FL, FR);
                //LAUNCH
                } else if (Objects.equals(APRT, "PGP")) {
                //starting from de 34
                //starting from position de 34
                backward(12, telemetry, BL, BR, FL, FR);
                rotateRight(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                //INTAKE
                backward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(90, telemetry, BL, BR, FL, FR);
                forward(36, telemetry, BL, BR, FL, FR);
                rotateLeft(135, telemetry, BL, BR, FL, FR);//turn around to outtake
                backward(35, telemetry, BL, BR, FL, FR);
                //OUTTAKE
                backward(35, telemetry, BL, BR, FL, FR);
                rotateRight(135, telemetry, BL, BR, FL, FR); //turn back to normal
                //GO GET OTHER BALLS
                backward(12, telemetry, BL, BR, FL, FR);
                rotateRight(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                //INTAKE PPG
                backward(12, telemetry, BL, BR, FL, FR);
                rotateLeft(90, telemetry, BL, BR, FL, FR);
                forward(12, telemetry, BL, BR, FL, FR);
                rotateRight(135, telemetry, BL, BR, FL, FR);
                backward(35, telemetry, BL, BR, FL, FR);
                //LAUNCHjj
            }
        }
    }
}
