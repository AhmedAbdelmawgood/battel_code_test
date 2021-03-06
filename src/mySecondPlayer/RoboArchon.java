package mySecondPlayer;

import battlecode.common.*;

public class RoboArchon extends RobotPlayer{
	// TryActivateNeutral
	// TryToBuild
	static RobotType[] buildOrder;
	static int counter  = 0;
	static int id;
	// // static int lastRoundNumber = 0;
	// // static int lastRoundBuild = 0;
	// static boolean build = false; 
	// static int dir_num;
	public static void init() throws GameActionException{
		electLeader();
		setBuildOrder();
		tryActivate();
			// System.out.println("Hello This is Leader");
		// lastRoundNumber = rc.getRoundNum();
		totalEnemeiesPower = 0;
		totalAlliesPower = 0;
		int roundNum = rc.getRoundNum();
		if (roundNum > 400 ){
			if(rc.getRoundNum()%3 == 0){
				if (rc.isCoreReady())
					tryBuild();			
			}
		}
		else
			tryBuild();			
		sendSignal();
		if(id != 0)
			findInstruction();
		else
			theDirection = Sensors.getSafeDirection();

		// System.out.println(theDirection);
	}
	public static void tryTurn() throws GameActionException{
        // int fate = rand.nextInt(1000);
        // Direction dir = directions[fate%8];
        // if (rc.senseRubble(rc.getLocation().add(dir)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH){
        //     rc.clearRubble(dir);
        // }else if(rc.canMove(dir)){
        //     rc.move(dir);
        // }
        if (id != 0)
        	findInstruction();
        moveForward(theDirection);
	}

	public static void tryBuild() throws GameActionException{
		// System.out.println("Try Building");
		if (counter < buildOrder.length){
			RobotType typeToBuild = buildOrder[counter];
			if(rc.hasBuildRequirements(typeToBuild)){
				Direction dir = directions[0];
				if( rc.canBuild(dir,typeToBuild) ){
					rc.build(dir,typeToBuild);
				counter++;
				}

			}
		}else{
			counter = 0;
		}
		// System.out.println("This is coutner");
		// System.out.println(counter);
	}
	public static void setBuildOrder(){
		buildOrder = new RobotType[]{
						RobotType.SCOUT,
						RobotType.SOLDIER,
						RobotType.SOLDIER,
						RobotType.VIPER,
						// RobotType.VIBER,
		};
	}
	private static void electLeader() throws GameActionException{
		if (rc.getRoundNum() !=0)
			return;
		Signal[] signals = rc.emptySignalQueue();
		if (signals.length == 0 ){
			id = 0;
			rc.broadcastMessageSignal(ours.ordinal(),0,100);
			// signals = rc.emptySignalQueue();
			// System.our.println()
		}else{
			for (Signal s:signals){
				if (s.getMessage()[0] == ours.ordinal()){
					leaderLocation = signals[0].getLocation();
					return;
				}
			}
		}
	}
// ////////////////////////////////////Signaling ////////////////////////////////
	public static void sendSignal() throws GameActionException{
		if(id != 0)
			return;
		rc.broadcastMessageSignal(ours.ordinal(),theDirection.ordinal(),500);
	}
	public static void tryActivate() throws GameActionException{
		RobotInfo[] neut = rc.senseNearbyRobots(2,Team.NEUTRAL);
		if (neut.length == 0)
			return;
		for(RobotInfo robot :neut){
			rc.activate(robot.location);
		}
		
	}
}
