// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.ReplanningConfig;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.util.ReplanningConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.GeometryUtil;
import com.pathplanner.lib.commands.FollowPathRamsete;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.controllers.PPRamseteController;


public class DriveSubsystem extends SubsystemBase {
  private CANSparkMax m_frontLeftMotor;
  private CANSparkMax m_frontRightMotor;
  private CANSparkMax m_rearLeftMotor;
  private CANSparkMax m_rearRightMotor;

  /** Creates a new DrivetrainSubsystem. */
  public DriveSubsystem() {
    m_frontLeftMotor  = new CANSparkMax(Constants.Drivetrain.kFrontLeftCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_frontLeftMotor.setInverted(Constants.Drivetrain.kFrontLeftInverted);
    m_frontLeftMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
    m_frontLeftMotor.setIdleMode(IdleMode.kBrake);
    m_frontLeftMotor.burnFlash();

    m_frontRightMotor = new CANSparkMax(Constants.Drivetrain.kFrontRightCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_frontRightMotor.setInverted(Constants.Drivetrain.kFrontRightInverted);
    m_frontRightMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
    m_frontRightMotor.setIdleMode(IdleMode.kBrake);
    m_frontRightMotor.burnFlash();

    m_rearLeftMotor   = new CANSparkMax(Constants.Drivetrain.kRearLeftCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_rearLeftMotor.setInverted(Constants.Drivetrain.kRearLeftInverted);
    m_rearLeftMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
    m_rearLeftMotor.setIdleMode(IdleMode.kBrake);
    m_rearLeftMotor.burnFlash();

    m_rearRightMotor  = new CANSparkMax(Constants.Drivetrain.kRearRightCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_rearRightMotor.setInverted(Constants.Drivetrain.kRearRightInverted);
    m_rearRightMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
    m_rearRightMotor.setIdleMode(IdleMode.kBrake);
    m_rearRightMotor.burnFlash();

    //AutoBuilder.configureRamsete(
            //this::getPose, // Robot pose supplier
            //this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
            //this::getCurrentSpeeds, // Current ChassisSpeeds supplier
            //this::drive, // Method that will drive the robot given ChassisSpeeds
            //new ReplanningConfig(), // Default path replanning config. See the API for the options here
            //() -> {
              // Boolean supplier that controls when the path will be mirrored for the red alliance
              // This will flip the path being followed to the red side of the field.
              // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

              //var alliance = DriverStation.getAlliance();
              //if (alliance.isPresent()) {
                //return alliance.get() == DriverStation.Alliance.Red;
              //}
              //return false;
            //},
            //this // Reference to this subsystem to set requirements
    //);
  }

  public void driveArcade(double _straight, double _turn) {
    double left  = MathUtil.clamp(_straight + _turn, -1.0, 1.0);
    double right = MathUtil.clamp(_straight - _turn, -1.0, 1.0);

    m_frontLeftMotor.set(left);
    m_frontRightMotor.set(right);
    m_rearLeftMotor.set(left);
    m_rearRightMotor.set(right);
  }
  public void setPower(double _power) {
  }

      
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    //builder.addDoubleProperty("Setpoint", () -> m_setpoint, (val) -> m_setpoint = val);
    //builder.addBooleanProperty("At Setpoint", () -> atSetpoint(), null);
    //addChild("Controller", m_controller);
  }
}
