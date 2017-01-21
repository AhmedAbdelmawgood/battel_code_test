package mySecondPlayer;
import battlecode.common.*;
import java.lang.Math;

public class Sensors extends RobotPlayer{
	// public static void senseLoss(Location locs){
	// }
	// static int totalEnemeiesPower = 0; 
	static RobotInfo[] allEnemeies;
	// public static MapLocation leaderLocation = null;

	public static Direction getSafeDirection() throws GameActionException{
		MapLocation centerDmg = centerOfDamage();
		int dangerDirectionInt = myCurrentLocation.directionTo(centerDmg).ordinal();
		int safeDirectionInt = (dangerDirectionInt + 4) % 8 ;
		return Direction.values()[safeDirectionInt];
	}
	public static MapLocation centerOfDamage() throws GameActionException{
		totalEnemeiesPower();
		int x = -1;
		int y = -1;
		int prevPower = -1;
		for(RobotInfo robot: allEnemeies){
			MapLocation lo = robot.location;
			if (x < 0){
				x = lo.x;
				y = lo.y;
				prevPower = (int)robot.attackPower;
				continue;
			}
			int newAttackPower = (int)robot.attackPower;
			int totalPower = (prevPower + newAttackPower);
			if (totalPower <= 0)
				continue;
			x = (x * prevPower + lo.x * newAttackPower)/ totalPower;
			y = (y * prevPower + lo.y * newAttackPower)/ totalPower;
			prevPower = newAttackPower;
		}
		return new MapLocation(x,y); 

	} 
	public static void totalEnemeiesPower(){
		allEnemeies = rc.senseHostileRobots(myCurrentLocation,rc.getType().sensorRadiusSquared);
		for(RobotInfo robot: allEnemeies){
			totalEnemeiesPower += robot.attackPower;
		}
	}
	public static void totalAlliesPower(){
		RobotInfo[] allAllies = rc.senseNearbyRobots(rc.getType().sensorRadiusSquared,ours);
		for(RobotInfo robot: allEnemeies){
			totalAlliesPower += robot.attackPower;
		}
	}

}