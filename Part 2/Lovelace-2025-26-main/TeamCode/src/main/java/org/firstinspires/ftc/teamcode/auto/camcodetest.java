package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.movement.MOV_cam.tag_ID;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.movement.MOV_cam;
import org.firstinspires.ftc.robotcore.external.Telemetry;


import java.util.Objects;

@Autonomous
public class camcodetest extends LinearOpMode {
    @Override
    public void runOpMode(){
        MOV_cam.vid(hardwareMap); //this calls the function called vid, whcih initializes the camera
        waitForStart();

        if (MOV_cam.is_ID()) { //there is an april tag in front of you
            String tagName = MOV_cam.tag_ID(); //we assign the april tag id to this variable
            telemetry.addData("Detected Tag:", tagName); //print
            telemetry.update();

        } else {
            telemetry.addLine("No tag detected"); //if there is no april tag
            //add how to get to the april tag, do later.
            telemetry.update();

        }
        sleep(100); // small delay
        //stop camera
        MOV_cam cam = new MOV_cam();

        String APRT = tag_ID(); //assign string aprt to the tag id that you sense

        if (Objects.equals(APRT, "GPP")) {
            telemetry.addData("gpp sensed !!", APRT);
            telemetry.update();
        } else if (Objects.equals(APRT, "PPG")) {
            telemetry.addData("ppg sensed !!", APRT);
            telemetry.update();
        } else if (Objects.equals(APRT, "PGP")) {
            telemetry.addData("pgp sensed !!", APRT);
            telemetry.update();
        } else {
            telemetry.addData("none sensed !!", APRT);
            telemetry.update();
        }

        cam.stopCamera();
    }



}
