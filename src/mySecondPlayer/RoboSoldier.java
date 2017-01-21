package mySecondPlayer;

import battlecode.common.*;
public class RoboSoldier extends RobotPlayer{
	static double health = 0;
	static int builderId = -1;
	static boolean retreating = false;
	public static void init() throws GameActionException{
		health = rc.getHealth();
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
	private static void findInstruction() throws GameActionException{
		Signal[] incomingSignals = rc.emptySignalQueue();
		Signal m = null;
		for (int messageOrd = 0; messageOrd < incomingSignals.length; messageOrd++) {
			m = incomingSignals[messageOrd];
			if (ours.ordinal() == m.getMessage()[0])
				break;
		}
		if (m == null)
			return;
		// System.out.println("Came here");
		// System.out.println("Came here");
		// System.out.println("Came here");
		// System.out.println("Came here");
		// System.out.println("Came here");
		MapLocation senderLocation = m.getLocation();
		Direction targetDirection = Direction.values()[ m.getMessage()[1] ];
		MapLocation goTo = senderLocation.add(targetDirection.dx*3,targetDirection.dy*3);
		theDirection = rc.getLocation().directionTo(goTo);	
	}
	private static void tryAttack() throws GameActionException{
		if(allEnemeies.size()>0){
			if( rc.isWeaponReady() )
				rc.attackLocation(allEnemeies.get(0).location);
		}
	}
}