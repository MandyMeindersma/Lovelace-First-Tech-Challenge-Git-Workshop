import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name = "cameracheck", group = "Vision")
public class cameracheck extends LinearOpMode {
    private VisionPortal myVisionPortal;
    private AprilTagProcessor myAprilTagProcessor;
    public String APRT = "";

    @Override
    public void runOpMode() {
        // Initialize the AprilTag processor and VisionPortal
        initAprilTag();

        // Add helpful telemetry messages for setup
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();

        /*positive x - too far right
        negative x - too far left


         */
        // Wait for the match to begin
        waitForStart();

        // Run your OpMode
        while (opModeIsActive()) {
            // Get the list of AprilTags detected in the current frame
            List<AprilTagDetection> currentDetections = myAprilTagProcessor.getDetections();

            telemetry.addData("Detections", currentDetections.size());
            for (AprilTagDetection tag : currentDetections) {
                telemetry.addData("Tag ID", tag.id);
                if (tag.id == 21){
                    APRT = "GPP";
                } else if (tag.id == 22){
                    APRT = "PGP";
                } else if (tag.id == 23){
                    APRT = "ppg";
                }
                telemetry.addData("Position (x,y,z)", "%.2f, %.2f, %.2f",
                        tag.ftcPose.x, tag.ftcPose.y, tag.ftcPose.z);
            }
            telemetry.update();
            sleep(20);

            myAprilTagProcessor = new AprilTagProcessor.Builder().build();

            // Build the VisionPortal
            VisionPortal.Builder myVisionPortalBuilder = new VisionPortal.Builder();

            // Choose camera source (webcam or phone)

            myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "CAM"));


            // Add the AprilTag processor
            myVisionPortalBuilder.addProcessor(myAprilTagProcessor);

            // Build and start the VisionPortal
            myVisionPortal = myVisionPortalBuilder.build();
        }
    }

    private void initAprilTag() {
        // Create the AprilTag processor
        myAprilTagProcessor = new AprilTagProcessor.Builder().build();

        // Build the VisionPortal
        VisionPortal.Builder myVisionPortalBuilder = new VisionPortal.Builder();

        // Choose camera source (webcam or phone)

        myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "CAM"));


        // Add the AprilTag processor
        myVisionPortalBuilder.addProcessor(myAprilTagProcessor);

        // Build and start the VisionPortal
        myVisionPortal = myVisionPortalBuilder.build();
    }
}
