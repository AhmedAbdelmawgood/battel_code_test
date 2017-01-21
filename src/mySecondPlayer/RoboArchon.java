package mySecondPlayer;

import battlecode.common.*;

public class RoboArchon extends RobotPlayer{
	// TryActivateNeutral
	// TryToBuild
	static RobotType[] buildOrder;
	static int counter  = 0;
	static int id;
	static int lastRoundNumber = 0;
	static int lastRoundBuild = 0;
	static boolean build = false; 
	// static int dir_num;
	public static void init() throws GameActionException{
		setBuildOrder();
		lastRoundNumber = rc.getRoundNum();
		System.out.println("Before");
		System.out.println(lastRoundNumber);
		System.out.println(lastRoundBuild);
		System.out.println("After");
		if (lastRoundNumber - lastRoundBuild >= 2){
			build = true;
		}else{
			build = false;
		}
		if (build == true){
			tryBuild();
			lastRoundBuild = lastRoundNumber;
		}
		electLeader();
		sendSignal();
	}
	public static void tryTurn() throws GameActionException{
        // int fate = rand.nextInt(1000);
        // Direction dir = directions[fate%8];
        // if (rc.senseRubble(rc.getLocation().add(dir)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH){
        //     rc.clearRubble(dir);
        // }else if(rc.canMove(dir)){
        //     rc.move(dir);
        // }
        moveForward(theDirection);	
	}

	public static void tryBuild() throws GameActionException{
		System.out.println("Try Building");
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
			rc.broadcastMessageSignal(0,0,2500);
			// signals = rc.emptySignalQueue();
			// System.our.println()

		}
	}
// ////////////////////////////////////Signaling ////////////////////////////////
	public static void sendSignal() throws GameActionException{
		if(id != 0)
			return;
		rc.broadcastMessageSignal(ours.ordinal(),theDirection.ordinal(),250);
	}
}
