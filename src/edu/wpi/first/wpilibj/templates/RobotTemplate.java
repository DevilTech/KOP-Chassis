/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot 
{
    private final int PSENSOR = 3;
    
    AnalogChannel psensor;
    int i = 1;
    Talon left;
    Talon right;
    RobotDrive snotRod;
    Joystick joyous;
    Gyro gyro1;
    Gyro gyro2;
    double heading;
    boolean flag1;
    boolean flag2;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        left = new Talon(2);
        right = new Talon(1);
//        snotRod.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
//        snotRod.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        snotRod = new RobotDrive(left, right);
        joyous = new Joystick(1);
        snotRod.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        snotRod.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        //ALL WHEELS ARE ON REAR!!!!*****
        gyro1 = new Gyro(1);
        gyro2 = new Gyro(2);
    }   //  robotInit

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() 
    {
        i = 1;
        gyro1.reset();
        gyro2.reset();
        flag1 = false;
        flag2 = false;
        heading = gyro1.getAngle();
    }   //  autonomousInit

    /**
     * This function is called periodically during operator control
     */
    public void autonomousPeriodic() 
    {
        while (!cleared()){
        if (gyro1.getAngle() - heading >= 2){
            snotRod.tankDrive(-.4, -.6);
            System.out.println("Correct Right");
        }   
        if (gyro1.getAngle() - heading <= -2){
            snotRod.tankDrive(-.6,-.4);
            System.out.println("Correct Left");
        }
        else {
            snotRod.tankDrive(-.8, -.8);
        }
        }
        while (i <= 500){
            snotRod.tankDrive(-.5, -.5);
            System.out.println("Cleared");
            i++;
        }
        snotRod.tankDrive(0, 0);
        System.out.println("Cleared, Stopped Motors");
    }   //  autonomousPeriodic

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() 
    {
        gyro2.reset();
    }   //  teleopInit
    
    /**
     * This function is called periodically during test mode
     */
    public void teleopPeriodic() 
    {
//        double left = joyous.getRawAxis(2);
//        double right = joyous.getRawAxis(4);
        snotRod.arcadeDrive(joyous);
        System.out.println(gyro2.getAngle());
//        snotRod.tankDrive(left, right);
    }   //  teleopPeriodic
    
    /**
     * This function is called periodically during test mode
     */
    public void testInit() 
    {
    
    }   //  testInit
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    
    }   //  testPeriodic\
    /**
     * This function is called periodically during test mode
     */
    public void disabledInit() 
    {
        System.out.println("disabledInit");
        
    }   //  disabledInit
    /**
     * This function is called periodically during test mode
     */
    public void disabledPeriodic() 
    {      
    }   //  disabledPeriodic
    
    public boolean cleared(){
        if (gyro2.getAngle() > 3){
            flag1=true;
        }
        if (flag1){
            if (gyro2.getAngle() < -3) flag2 = true;
        }
        return flag2;
    }
}