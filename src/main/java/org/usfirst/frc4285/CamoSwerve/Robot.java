// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4285.CamoSwerve;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4285.CamoSwerve.commands.*;
import org.usfirst.frc4285.CamoSwerve.subsystems.*;
import static org.usfirst.frc4285.CamoSwerve.RobotMap.navX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {


    public static double zeroHeading;
    public static double zeroAngle;
    
    public static Drive drive;
    public static OI oi;
  
    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        drive = new Drive();
    
        //OI is always last!!
        oi = new OI();

        chooser.setDefaultOption("Default Auto", new FieldCentricSwerveDrive());
        // chooser.addOption("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);

        zeroHeading = navX.getFusedHeading();
        zeroAngle = navX.getAngle();
    
    }

    /**
    * This function is called every robot packet, no matter the mode. Use
    * this for items like diagnostics that you want ran during disabled,
    * autonomous, teleoperated and test.
    *
    * <p>This runs after the mode specific periodic functions, but before
    * LiveWindow and SmartDashboard integrated updating.
    */

    @Override
    public void robotPeriodic() {

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();

        updateDashboard();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    /**
   * This function is called periodically during test mode.
   */
    @Override
    public void testPeriodic() {

    }

    private void updateDashboard() {
        SmartDashboard.putNumber("LF Steer Position", drive.getSteerLFEncoder());
        SmartDashboard.putNumber("LR Steer Position", drive.getSteerLREncoder());
        SmartDashboard.putNumber("RF Steer Position", drive.getSteerRFEncoder());
        SmartDashboard.putNumber("RR Steer Position", drive.getSteerRREncoder());
    
        // SmartDashboard.putNumber("NavXHeading", navX.getFusedHeading());
        // SmartDashboard.putNumber("NavX Angle", navX.getAngle());
        // SmartDashboard.putNumber("NavXCompass", navX.getCompassHeading());
        // SmartDashboard.putNumber("NavX Yaw", navX.getYaw());
        // SmartDashboard.putNumber("ZeroHeading", zeroHeading);
    
    }    
}
