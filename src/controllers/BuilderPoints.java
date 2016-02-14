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
	int points;
	int spill[][] = new int[15][3];

	public BuilderPoints(Subcontrol control){
		super(control);
	}

	public int spillAppleSauce(int yLoc, int xLoc){
		if(subController.MAZE.map[yLoc][xLoc]==3) return 16;
		if(subController.MAZE.map[yLoc][xLoc]==1) return 17;
		if(subController.MAZE.map[yLoc][xLoc]==2) return 18;
		subController.MAZE.map[yLoc][xLoc]=3;
		points = points-3;
		int i;
		for (i = 0; spill[i][0] != 0; i++){
		}
		spill[i][0] = 3; // timeout value
		spill[i][1] = yLoc; //ylocation value
		spill[i][2] = xLoc; //x location value
		return 0;
	}

	public void timeouts(){
	    for(int i = 0; i<15; i++){
	    	if (spill[i][0] == 1){
	    		spill[i][0] = 0;
	    		subController.MAZE.map[spill[i][1]][spill[i][2]] = 0;
	    		spill[i][1] = 0;
	    		spill[i][2] = 0;
	    	}
	    	else if(spill[i][0] > 1) {
	    		spill[i][0]--;
	    	}
	    }
	     
	    } 

	public int removeSection(int yLocation, int xLocation){
		if (subController.MAZE.map[yLocation][xLocation] != 1) return 11;
		subController.MAZE.map[yLocation][xLocation] = 0;
		points++;
		return 0;
	}

	public int buildSection(int yLocation, int xLocation){
		if (subController.MAZE.map[yLocation][xLocation] == 1) return 12;
		if (subController.MAZE.map[yLocation][xLocation] == 2 || subController.MAZE.map[yLocation][xLocation] == 3) return 13;
		subController.MAZE.map[yLocation][xLocation] = 0;
		points = points-3;
		return 0;
	}

	public int putBananaPeel(int yLocation, int xLocation){
		if (subController.MAZE.map[yLocation][xLocation] == 1) return 12;
		if (subController.MAZE.map[yLocation][xLocation] == 2) return 14;
		if (subController.MAZE.map[yLocation][xLocation] == 3) return 15;
		subController.MAZE.map[yLocation][xLocation] = 2;
		points--;
		return 0;
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

