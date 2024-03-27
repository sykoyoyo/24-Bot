package frc.Commands.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.Commands.timed.moveOut;

public class moveoutauto extends SequentialCommandGroup{
    public moveoutauto(DriveSubsystem driveMotorRef) {
        super (
            new moveOut(driveMotorRef, 3)
        );
    }
}
