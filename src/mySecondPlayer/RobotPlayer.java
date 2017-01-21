package mySecondPlayer;

import battlecode.common.*;

import java.util.Random;
import java.util.ArrayList;

public class RobotPlayer {
    static Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST,
                                     Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};
    // static Direction default_direction = directions[2];
    static RobotType[] robotTypes = {RobotType.SCOUT, RobotType.SOLDIER, RobotType.SOLDIER, RobotType.SOLDIER,
                                    RobotType.GUARD, RobotType.GUARD, RobotType.VIPER, RobotType.TURRET};
    static RobotController rc;
    static Random rand = new Random();
    static Team ours,enemey;
    static MapLocation myCurrentLocation;
    static int[] allDirectionsInt = {0,1,-1,2,-2,3,-3,4};
    static Direction theDirection = directions[0];
    static ArrayList<MapLocation> visitedLocs = new ArrayList<MapLocation>();
    static ArrayList<RobotInfo> allEnemeies = new ArrayList<RobotInfo>();   
    
    public static void run(RobotController rcIn){
        rc = rcIn;
        while(true){
            try{
                ours = rc.getTeam();
                enemey = ours.opponent();
                myCurrentLocation = rc.getLocation();
                repeat();
                Clock.yield();
            }
            catch(GameActionException e){
                e.printStackTrace();
            }
        }
    }
    public static void repeat() throws GameActionException{
        RobotInfo[] zombiesEnemey = rc.senseNearbyRobots(rc.getType().attackRadiusSquared,Team.ZOMBIE);
        RobotInfo[] robotEnemey = rc.senseNearbyRobots(rc.getType().attackRadiusSquared,enemey);
        allEnemeies.clear();
        appendEnemeies(zombiesEnemey);
        appendEnemeies(robotEnemey);
        RobotType type = rc.getType();
        if (type == RobotType.ARCHON){
            RoboArchon.init();
            RoboArchon.tryTurn();            
        }else if(type == RobotType.SOLDIER){
            RoboSoldier.init();
            RoboSoldier.tryTurn();
        }else{
            if (allEnemeies.size() > 0){
                if(rc.isWeaponReady())
                    rc.attackLocation(allEnemeies.get(0).location);
            }else{
                if ( rc.isCoreReady() ){
                    Direction dir = getRandomDirection();
                    if ( rc.canMove(dir) ){
                        rc.move(dir);
                    }

                }
            }
        }//
    }
    public static void moveForward(Direction ahead) throws GameActionException{
        if (rc.isCoreReady() == false){
            return;
            }
        for(int i: allDirectionsInt){
            Direction possibleDirection = Direction.values()[ (ahead.ordinal() + i + 8)%8 ];
            MapLocation nextLocation = rc.getLocation().add(possibleDirection);
            if (rc.canMove(possibleDirection) && visitedLocs.contains(nextLocation) == false) {
                rc.move(possibleDirection);
                visitedLocs.add(nextLocation);
                if (visitedLocs.size() > 25)
                    visitedLocs.remove(0);
                return;
            }
        }

    }    
    public static Direction getRandomDirection(){
        int fate = rand.nextInt(1000);
        return directions[fate%8]; 
    }
    public static void appendEnemeies(RobotInfo[] collec){
        for(RobotInfo enemey: collec){
            allEnemeies.add(enemey);
        }
    }
}