package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkFlex;

public class LauncherSubsystem extends SubsystemBase {

  private CANSparkFlex m_topMotor;
  private CANSparkMax m_bottomMotor;
  private CANSparkMax m_bottomMotor2;
  private boolean m_launcherRunning;
  private boolean m_launcherFeed;
  private boolean m_amp;


  /** Creates a new LauncherSubsystem. */
  public LauncherSubsystem() {
    // create two new SPARK MAXs and configure them
    m_topMotor =
        new CANSparkFlex(Constants.Launcher.kTopCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_topMotor.setInverted(true);
    m_topMotor.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_topMotor.setIdleMode(IdleMode.kBrake);

    m_topMotor.burnFlash();

    m_bottomMotor =
        new CANSparkMax(Constants.Launcher.kBottomCanId, CANSparkLowLevel.MotorType.kBrushless);
    m_bottomMotor.setInverted(true);
    m_bottomMotor.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_bottomMotor.setIdleMode(IdleMode.kBrake);

    m_bottomMotor.burnFlash();

    m_launcherRunning = false;
    m_launcherFeed = false;
    m_amp = false;
        
    m_bottomMotor2 =
        new CANSparkMax(Constants.Launcher.kBottomCanId2, CANSparkLowLevel.MotorType.kBrushless);
    m_bottomMotor2.setInverted(true);
    m_bottomMotor2.setSmartCurrentLimit(Constants.Launcher.kCurrentLimit);
    m_bottomMotor2.setIdleMode(IdleMode.kBrake);

    m_bottomMotor2.burnFlash();

    m_launcherRunning = false;
    m_launcherFeed = false;
    m_amp = false;
  }
  public void setPower(double _power) {
   }
  /**
   * Turns the launcher on. Can be run once and the launcher will stay running or run continuously
   * in a {@code RunCommand}.
   */
  public void runLauncher() {
    m_launcherRunning = true;
  }
  public void feedIntake() {
    m_launcherFeed = true;
  }
  public void amp() {
    m_amp = true;
  }
  /**
   * Turns the launcher off. Can be run once and the launcher will stay running or run continuously
   * in a {@code RunCommand}.
   */
  public void stopLauncher() {
    m_launcherRunning = false;
    m_launcherFeed = false;
    m_amp = false;
  }

  @Override
  public void periodic() { // this method will be called once per scheduler run
    // set the launcher motor powers based on whether the launcher is on or not
    if (m_launcherRunning) {
      m_topMotor.set(Constants.Launcher.kTopPower);
      m_bottomMotor.set(Constants.Launcher.kBottomPower);
      m_bottomMotor2.set(Constants.Launcher.kBottomPower);
    }
    else if (m_launcherFeed) {
      m_topMotor.set(-.5*Constants.Launcher.kTopPower);
      m_bottomMotor.set(-.5*Constants.Launcher.kBottomPower);
      m_bottomMotor2.set(-.5*Constants.Launcher.kBottomPower);
    }
    else if (m_amp) {
      m_topMotor.set(.5*Constants.Launcher.kTopPower);
      m_bottomMotor.set(.5*Constants.Launcher.kBottomPower);
      m_bottomMotor2.set(.5*Constants.Launcher.kBottomPower);
    } else {
      m_topMotor.set(0.0);
      m_bottomMotor.set(0.0);
      m_bottomMotor2.set(0.0);
    }
  }
}
