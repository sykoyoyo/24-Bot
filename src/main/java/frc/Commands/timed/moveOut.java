package frc.Commands.timed;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
public class moveOut extends CommandBase{
    private final DriveSubsystem driveMotor;
    private final double duration;
    private final Timer timer;

    public moveOut(DriveSubsystem driveMotorRef, double seconds) {
        this.driveMotor = driveMotorRef;
        this.duration = seconds;
        this.timer = new Timer();
        addRequirements(this.driveMotor);
    }

@Override
public void initialize() {
   this.timer.start();
   this. timer.reset();
}
@Override
public void execute() {
    this.driveMotor.driveArcade(-1, 0); 
}
@Override
public void end(boolean interrupted) {
    this.timer.stop();
    this.driveMotor.setPower(0);
}
@Override
public boolean isFinished() {
    return (this.timer.get() > this.duration);
}
}