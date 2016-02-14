package controllers;

import extendeds.Controller;
import extendeds.Subcontrol;

public class RunnerPoints extends Controller {
    /*
    ***ERROR CODES***
    1      Not enough points
	2      Nothing in position
	3      Wall in postion
	4      Apple sauce in postion
	5      Attempting to move out of bounds
	7      Banana in postion 

	*/
    
    static boolean hidden;
    static boolean suspended;
	static int xLocation;
	static int yLocation;
	static int points;
	static int hideTimeout;
	static int suspendTimeout;
	
	public void timeouts(){
	    if (RunnerPoints.hidden == true){
	        RunnerPoints.hideTimeout--;
	        if (RunnerPoints.hideTimeout < 0){
	            RunnerPoints.hidden = false;
	        }
	    } 
	    if (RunnerPoints.suspended == true){
	        RunnerPoints.suspendTimeout--;
	        if (RunnerPoints.suspendTimeout < 0){
	            RunnerPoints.suspended = false;
	        }
	    }
	}
	
	public int pickUpBanana(int yLocation, int xLocation){
		if (RunnerPoints.points == 0) return 1; //f not enough points, returns 1
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 0) return 2; //if there is nothing in position, returns 2
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 1) return 3; //if there is a wall in positon, return 3
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 3) return 4; //if there is apple sauce in postion return 4
		Subcontrol.MAZE.map[yLocation][xLocation] = 0; //sets map location with banana to "free"
		RunnerPoints.points--; //deducts 1 point
		return 0;
	}

	public int move(int direction) { // 1 = up, 2 = right, 3 = down, 4 = left
		if (RunnerPoints.points == 0) return 1;
		if (RunnerPoints.xLocation == 9 && direction == 2) return 5; //out of bounds, return 5
		if (RunnerPoints.xLocation == 0 && direction == 4) return 5; //out of bounds
		if (RunnerPoints.yLocation == 9 && direction == 3) return 5; // out of bounds
		if (RunnerPoints.yLocation == 0 && direction == 1) return 5; //out of bounds
		if (direction == 1) {
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation-1][RunnerPoints.xLocation] == 1) return 3; //if there is a wall in postion return 3.
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation-1][RunnerPoints.xLocation] == 3) return 4; //if there is apple sauce in position return 4
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation-1][RunnerPoints.xLocation] == 2){
				Subcontrol.MAZE.map[RunnerPoints.yLocation-1][RunnerPoints.xLocation] = 0;
				RunnerPoints.suspended = true;
			}
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation-1][RunnerPoints.xLocation] == 0){
				RunnerPoints.yLocation--;
			}
		}
		else if (direction == 2) {
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation+1] == 1) return 3; //if there is a wall in postion return 3.
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation+1] == 3) return 4; //if there is apple sauce in position return 4
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation+1] == 2){
				Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation+1] = 0;
				RunnerPoints.suspended = true;
			}
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation+1] == 0){
				RunnerPoints.xLocation++;
			}
		}
		else if (direction == 3) {
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation+1][RunnerPoints.xLocation] == 1) return 3; //if there is a wall in postion return 3.
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation+1][RunnerPoints.xLocation] == 3) return 4; //if there is apple sauce in position return 4
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation+1][RunnerPoints.xLocation] == 2){
				Subcontrol.MAZE.map[RunnerPoints.yLocation+1][RunnerPoints.xLocation] = 0;
				RunnerPoints.suspended = true;
			}
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation+1][RunnerPoints.xLocation] == 0){
				RunnerPoints.yLocation++;
			}
		}
		else if (direction == 4) {
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation-1] == 1) return 3; //if there is a wall in postion return 3.
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation-1] == 3) return 4; 
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation-1] == 2){
				Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation-1] = 0;
				RunnerPoints.suspended = true;
				RunnerPoints.suspendTimeout = 1;
			}
			if (Subcontrol.MAZE.map[RunnerPoints.yLocation][RunnerPoints.xLocation-1] == 0){
				RunnerPoints.xLocation--;
			}
		}
		RunnerPoints.points--;
		return 0;
	}


	public int c4(int yLocation, int xLocation){
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 3) return 4;//if there is apple sauce in position return 4
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 2) return 7; //if there is banana in position return 7
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 0) return 2; //if there is nothing in postion return 2
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 1) {
			Subcontrol.MAZE.map[yLocation][xLocation] = 0;
			RunnerPoints.points = RunnerPoints.points-3;
			
		}
		return 0;
	}

	public int hide(){
		if (RunnerPoints.points < 10) return 1;
		RunnerPoints.points = (RunnerPoints.points)-10;
		RunnerPoints.hidden = true;
		RunnerPoints.hideTimeout = 2;
		return 0;
	}





	public static void main(String[] args) {

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shouldEndGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}
}
