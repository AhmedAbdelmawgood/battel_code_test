package mySecondPlayer;

import battlecode.common.*;
public class RoboScout extends RobotPlayer{
	public static enum Modes{TRACK,RANDOM,TURRENT;};
	public static Modes mode = Modes.RANDOM;
	public static void init() throws GameActionException{
		// findInstruction();
		// if (mode == Modes.RANDOM){
		// 	theDirection = getRandomDirection();
		// }
	}
	public static void tryTurn() throws GameActionException{
		theDirection = Sensors.getSafeDirection();
		moveForward(theDirection);
	}
	// public static 	
}