// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.lib.PIDGains;
import java.lang.Math;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final double kDriveDeadband = 0.15;
    public static final double kTriggerButtonThreshold = 0.5;
  }
    
    public static final class Drivetrain {
        public static final int kFrontLeftCanId = 1;
        public static final int kFrontRightCanId = 3;
        public static final int kRearLeftCanId = 2;
        public static final int kRearRightCanId = 4;
 
        public static final boolean kFrontLeftInverted = false;
        public static final boolean kFrontRightInverted = true;
        public static final boolean kRearLeftInverted = false;
        public static final boolean kRearRightInverted = true;

        public static final int kCurrentLimit = 55;

        public static final double kTurningScale = 0.5;
    }

    public static final class Arm {
      public static final int kArmCanId = 5;
      public static final boolean kArmInverted = true;
      public static final int kCurrentLimit = 40;
  
      public static final double kSoftLimitReverse = -1.15;
      public static final double kSoftLimitForward = 0.0;
  
      public static final double kArmGearRatio = (1.0 / 75.0) * (28.0 / 50.0) * (16.0 / 64.0);
      public static final double kPositionFactor =
          kArmGearRatio
              * 2.0
              * Math.PI; // multiply SM value by this number and get arm position in radians
      public static final double kVelocityFactor = kArmGearRatio * 2.0 * Math.PI / 60.0;
      public static final double kArmFreeSpeed = 5676.0 * kVelocityFactor;
      public static final double kArmZeroCosineOffset =
          1.342; // radians to add to converted arm position to get real-world arm position (starts at
      // ~76.9deg angle)
      public static final ArmFeedforward kArmFeedforward =
          new ArmFeedforward(0.0, 3.0, 12.0 / kArmFreeSpeed, 0.0);
      public static final PIDGains kArmPositionGains = new PIDGains(2.5, 0.0, 0.0);
      public static final TrapezoidProfile.Constraints kArmMotionConstraint =
          new TrapezoidProfile.Constraints(1.0, 2.0);
  
      public static final double kHomePosition = 0.0;
      public static final double kScoringPosition = 0.0;
      public static final double kIntakePosition = -1.1;
    }
  
    public static final class Intake {
      public static final int kCanId = 7;
      public static final boolean kMotorInverted = true;
      public static final int kCurrentLimit = 80;
  
      public static final PIDGains kPositionGains = new PIDGains(1.0, 0.0, 0.0);
      public static final double kPositionTolerance = 0.5;
  
      public static final double kIntakePower = 1;
  
      public static final double kRetractDistance = -1;
  
      public static final double kShotFeedTime = 1.0;
      public static final double kShotFeedTime2 = 2.0;
    }
    public static final class Launcher {
      public static final int kTopCanId = 14;
      public static final int kBottomCanId = 9;
      public static final int kBottomCanId2 = 8;
      public static final int kCurrentLimit = 80;
  
      public static final double kTopPower = 0.5;
      public static final double kBottomPower = 0.8;
    }
      public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        // Constraint for the motion profiled robot angle controller
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
      }
    
      public static final class NeoMotorConstants {
        public static final double kFreeSpeedRpm = 5676;
      }
}
