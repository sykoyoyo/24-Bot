// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.Commands.autos.moveoutauto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.Sendable;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ArmSubsystem m_arm = new ArmSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final LauncherSubsystem m_launcher = new LauncherSubsystem();
  private final SendableChooser<Command> autoChooser;
  
  public Command getAutonomousCommand() {
    return new PathPlannerAuto("Example Auto");
  }

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings(); 
   // set the arm subsystem to run the "runAutomatic" function continuously when no other command
    // is running
    m_arm.setDefaultCommand(new RunCommand(() -> m_arm.runAutomatic(), m_arm));

    // set the intake to stop (0 power) when no other command is running
    m_intake.setDefaultCommand(new RunCommand(() -> m_intake.setPower(0.0), m_intake));

    // configure the launcher to stop when no other command is running
    m_launcher.setDefaultCommand(new RunCommand(() -> m_launcher.stopLauncher(), m_launcher));

    
    this.autoChooser = new SendableChooser<Command>();
    this.autoChooser.addOption("Move Out", new moveoutauto(m_robotDrive) {
      
    });
  }
 
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //set up the drivetrain command that runs all the time
    m_robotDrive.setDefaultCommand(new RunCommand(
      () -> 
        m_robotDrive.driveArcade(
          MathUtil.applyDeadband(- m_driverController.getLeftY()*.5, Constants.OIConstants.kDriveDeadband),
          MathUtil.applyDeadband(m_driverController.getLeftX()*Constants.Drivetrain.kTurningScale, Constants.OIConstants.kDriveDeadband)), m_robotDrive));

// set up arm preset positions
new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value)
.onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kScoringPosition)));
new Trigger(
    () ->
        m_driverController.getLeftTriggerAxis()
            > Constants.OIConstants.kTriggerButtonThreshold)
.onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kIntakePosition)));
new JoystickButton(m_driverController, XboxController.Button.kStart.value)
.onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kHomePosition)));

// intake controls (run while button is held down, run retract command once when the button is
// released)
new Trigger(
    () ->
        m_driverController.getRightTriggerAxis()
            > Constants.OIConstants.kTriggerButtonThreshold)
.whileTrue(new RunCommand(() -> m_intake.setPower(Constants.Intake.kIntakePower), m_intake))
.onFalse(m_intake.retract());

new JoystickButton(m_driverController, XboxController.Button.kY.value)
.whileTrue(new RunCommand(() -> m_intake.setPower(-1.0)))
.whileTrue(new RunCommand(() -> m_launcher.feedIntake(), m_launcher));

// launcher controls (button to pre-spin the launcher and button to launch)
new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value)
.whileTrue(new RunCommand(() -> m_launcher.runLauncher(), m_launcher));

new JoystickButton(m_driverController, XboxController.Button.kA.value)
.onTrue(m_intake.feedLauncher(m_launcher));

new JoystickButton(m_driverController, XboxController.Button.kX.value)
.whileTrue(new RunCommand(() -> m_intake.setPower(.60)))
.whileTrue(new RunCommand(() -> m_launcher.feedIntake(), m_launcher));

new JoystickButton(m_driverController, XboxController.Button.kB.value)
.whileTrue(new RunCommand(() -> m_intake.setPower(1)))
.whileTrue(new RunCommand(() -> m_launcher.amp(), m_launcher));
}
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
}
