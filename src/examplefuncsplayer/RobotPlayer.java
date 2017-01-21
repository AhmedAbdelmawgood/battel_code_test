package examplefuncsplayer;


import battlecode.common.*;

import java.util.Random;
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
                        if(isItDangerous() == false){
                            RobotType typeToBuild = robotTypes[0];
                            if (rc.hasBuildRequirements(typeToBuild)) {
                                Direction dirToBuild = directions[rand.nextInt(8)];
                                for (int i = 0; i < 8; i++) {
                                    // If possible, build in this direction
                                    if (rc.canBuild(dirToBuild, typeToBuild)) {
                                        rc.build(dirToBuild, typeToBuild);
                                        break;
                                    } else {
                                        // Rotate the direction to try
                                        dirToBuild = dirToBuild.rotateLeft();
                                    }
                                }
                            }//end of if has build requirements
                        }
                        else{
                            
                        }
                        int fate = rand.nextInt(1000);
                        Direction dir = directions[fate%8];
                        if (rc.senseRubble(rc.getLocation().add(dir)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH){
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
        else if (rc.getType() == RobotType.SOLDIER){
            try{}
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            while(true){
                try{
                    while(true){
                        // if(senseEnemey(rc.getLocation(r))
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();    
                }  
            }
        }
        else if (rc.getType() == RobotType.SCOUT){
            try{}
            catch(Exception e){System.out.println(e.getMessage());
            e.printStackTrace();
            }
            while(true){
                try{
                    if (rc.isCoreReady()){
                        
                        getNearbyZombies();
                        System.out.println("Zombieyala");
                        
                        if(isItDangerous() == false){
                            RobotType typeToBuild = robotTypes[0];
                            if (rc.hasBuildRequirements(typeToBuild)) {
                                Direction dirToBuild = directions[rand.nextInt(8)];
                                for (int i = 0; i < 8; i++) {
                                    // If possible, build in this direction
                                    if (rc.canBuild(dirToBuild, typeToBuild)) {
                                        rc.build(dirToBuild, typeToBuild);
                                        break;
                                    } else {
                                        // Rotate the direction to try
                                        dirToBuild = dirToBuild.rotateLeft();
                                    }
                                }
                            }//end of if has build requirements
                        }
                        else{
                            
                        }
                        int fate = rand.nextInt(1000);
                        Direction dir = directions[fate%8];
                        if (rc.senseRubble(rc.getLocation().add(dir)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH){
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
    public static boolean isItDangerous(){
        return false;
    }
    static RobotController rc;
    static RobotInfo[] nearbyEnemies = null;
    static RobotInfo[] nearbyZombies = null;
    static RobotInfo[] nearbyHostiles = null;
    static RobotInfo[] nearbyAllies = null;
    static MapLocation[] nearbyParts = null;
    static MapLocation here = rc.getLocation();
        
        
     //To sense NearbyEnemies
    public RobotInfo[] getNearbyEnemies( )
    {
        if (nearbyEnemies != null)
            return nearbyEnemies;
        
        nearbyEnemies = rc.senseNearbyRobots(rc.getType().sensorRadiusSquared,rc.getTeam().opponent() );
        return nearbyEnemies;
    }
    //To sense NearbyAllies
    public static RobotInfo[] getNearbyAllies()
    {
        if (nearbyAllies != null)
            return nearbyAllies;
        
        nearbyAllies = rc.senseNearbyRobots(rc.getType().sensorRadiusSquared, rc.getTeam());
        return nearbyAllies;
    }
    //To sense NearbyZombies 
    public static RobotInfo[] getNearbyZombies()
    {
        if (nearbyZombies != null)
            return nearbyZombies;
        
        nearbyZombies = rc.senseNearbyRobots(rc.getType().sensorRadiusSquared, Team.ZOMBIE);
        return nearbyZombies;
    }
    
     

}