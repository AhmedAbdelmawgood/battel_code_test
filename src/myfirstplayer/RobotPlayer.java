package myfirstplayer;
import battlecode.common.*;
import java.util.*;
public class RobotPlayer {
	public static void run(RobotController rc){
        Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST,
                Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};
        RobotType[] robotTypes = {RobotType.SCOUT, RobotType.SOLDIER, RobotType.SOLDIER, RobotType.SOLDIER,
                RobotType.GUARD, RobotType.GUARD, RobotType.VIPER, RobotType.TURRET};
        Random rand = new Random();
        int myAttackRange = 0;
        Team myteam = rc.getTeam();
        Team enemyTeam = myteam.opponent();
        if (rc.getType() == RobotType.ARCHON){
        	try{}
        	catch(Exception e){System.out.println(e.getMessage());
            e.printStackTrace();
            }
        	while(true){
        		try{
        			if (rc.isCoreReady()){
            			int fate = rand.nextInt(1000);
            			Direction dir = directions[fate%8];
            			if (rc.senseRubble(rc.getLocation().add(dir)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH ){
            				rc.clearRubble(dir);
            			}else if(rc.canMove(dir)){
            				rc.move(dir);
            			}
            		}
        		}catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
        			
        		}
        		Clock.yield();
        	}
            
        }
	}
}
