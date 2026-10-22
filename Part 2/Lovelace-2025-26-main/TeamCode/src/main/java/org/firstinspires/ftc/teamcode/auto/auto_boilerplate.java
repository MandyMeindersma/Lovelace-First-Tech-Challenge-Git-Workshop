package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.movement.MOV_cam.tag_ID;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.movement.MOV_cam;

import java.util.Objects;

@Autonomous
public class auto_boilerplate extends LinearOpMode {
    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    @Override
    public void runOpMode(){
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        FL.setDirection(DcMotor.Direction.FORWARD);
        FR.setDirection(DcMotor.Direction.REVERSE); //FR AND BR ARE IN REVERSE !! !! !! !!
        BL.setDirection(DcMotor.Direction.FORWARD); //we think... so for now just put it like this
        BR.setDirection(DcMotor.Direction.REVERSE);

        MOV_cam.vid(hardwareMap); //this calls the function called vid, whcih initializes the camera
        waitForStart();

        //YOU NEED TO ADD CODE HERE.
        //THIS CODE SHOULD GET YOU TO THE MIDDLE OF THE FIELD (i did on the line D-E)
        //FROM HERE, THE ROBOT CAN SENSE THE APRIL TAG
        if (MOV_cam.is_ID()) { //there is an april tag in front of you
            String tagName = MOV_cam.tag_ID(); //we assign the april tag id to this variable
            telemetry.addData("Detected Tag:", tagName); //print
        } else {
            telemetry.addLine("No tag detected"); //if there is no april tag
            //add how to get to the april tag, do later.
        }
        telemetry.update();
        sleep(100); // small delay
        //stop camera
        MOV_cam cam = new MOV_cam();
        sleep(2000);
        String APRT = tag_ID(); //assign string aprt to the tag id that you sense
        cam.stopCamera();
        if (Objects.equals(APRT, "GPP")) {
            //code
        } else if (Objects.equals(APRT, "PPG")) {
            //code
        } else if (Objects.equals(APRT, "PGP")) {
            //code
        }
    }



}
