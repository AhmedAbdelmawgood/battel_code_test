package mySecondPlayer;

import battlecode.common.*;
public class RoboSoldier extends RobotPlayer{
	static double health = 0;
	static int builderId = -1;
	static boolean retreating = false;
	public static void init() throws GameActionException{
		health = rc.getHealth();
		totalEnemeiesPower = 0;
		totalAlliesPower = 0;		
		if (builderId < 0)
			assignBuilderId();
		findInstruction();		
		// if (retreating == false)
		// 	tryAttack();
		// else
		// 	retreat();

	}
	public static void tryTurn() throws GameActionException{
		tryAttack();
		moveForward(theDirection);
	}
	private static void assignBuilderId() throws GameActionException{
		RobotInfo[] robots = rc.senseNearbyRobots(rc.getType().sensorRadiusSquared,ours);
		for(RobotInfo rob:robots){
			if (rob.type == RobotType.ARCHON)
				builderId = rob.ID;
		}
	}

	private static void tryAttack() throws GameActionException{
		if(allEnemeies.size()>0){
			if( rc.isWeaponReady() )
				rc.attackLocation(allEnemeies.get(0).location);
		}
	}
}