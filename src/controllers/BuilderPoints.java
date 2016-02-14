package controllers;

import java.awt.Graphics2D;

import extendeds.Controller;
import extendeds.Subcontrol;

/*

****ERROR CODES****
11       Attempt to remove wall from where there is no wall
12       Attempt to put banana peel/build section on wall
13       Attempt to put section on apple peel/banana
14       Attempt to put banana peel on banana peel
15       Attempt to put banana peel on apple sauce
16       Attempt to spill apple sauce on apple sauce
17       Attempt to spill apple sauce on wall
18       Attempt to spill apple sauce on banana

*/


public class BuilderPoints extends Controller {
	static int points;
	static int spill[][] = new int[15][3];


	public int spillAppleSauce(int yLoc, int xLoc){
		if(Subcontrol.MAZE.map[yLoc][xLoc]==3) return 16;
		if(Subcontrol.MAZE.map[yLoc][xLoc]==1) return 17;
		if(Subcontrol.MAZE.map[yLoc][xLoc]==2) return 18;
		Subcontrol.MAZE.map[yLoc][xLoc]=3;
		BuilderPoints.points = BuilderPoints.points-3;
		int i;
		for (i = 0; BuilderPoints.spill[i][0] != 0; i++){
		}
		BuilderPoints.spill[i][0] = 3; // timeout value
		BuilderPoints.spill[i][1] = yLoc; //ylocation value
		BuilderPoints.spill[i][2] = xLoc; //x location value
		return 0;
	}

	public void timeouts(){
	    for(int i = 0; i<15; i++){
	    	if (BuilderPoints.spill[i][0] == 1){
	    		BuilderPoints.spill[i][0] = 0;
	    		Subcontrol.MAZE.map[BuilderPoints.spill[i][1]][BuilderPoints.spill[i][2]] = 0;
	    		BuilderPoints.spill[i][1] = 0;
	    		BuilderPoints.spill[i][2] = 0;
	    	}
	    	else if(BuilderPoints.spill[i][0] > 1) {
	    		BuilderPoints.spill[i][0]--;
	    	}
	    }
	     
	    } 

	public int removeSection(int yLocation, int xLocation){
		if (Subcontrol.MAZE.map[yLocation][xLocation] != 1) return 11;
		Subcontrol.MAZE.map[yLocation][xLocation] = 0;
		BuilderPoints.points++;
		return 0;
	}

	public int buildSection(int yLocation, int xLocation){
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 1) return 12;
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 2 || Subcontrol.MAZE.map[yLocation][xLocation] == 3) return 13;
		Subcontrol.MAZE.map[yLocation][xLocation] = 0;
		BuilderPoints.points = BuilderPoints.points-3;
		return 0;
	}

	public int putBananaPeel(int yLocation, int xLocation){
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 1) return 12;
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 2) return 14;
		if (Subcontrol.MAZE.map[yLocation][xLocation] == 3) return 15;
		Subcontrol.MAZE.map[yLocation][xLocation] = 2;
		BuilderPoints.points--;
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

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPlaying(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}

