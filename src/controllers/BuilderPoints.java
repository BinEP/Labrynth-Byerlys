package controllers;

import extendeds.Controller;
import extendeds.Subcontrol;

public class BuilderPoints extends Controller {
	static int points;
	static int appleSauceTimeout;
	
public int spillAppleSauce(int yLoc, int xLoc){
	
	if(Subcontrol.MAZE.map[yLoc][xLoc]==3){
	return 16;
	}
	
	if(Subcontrol.MAZE.map[yLoc][xLoc]==1) {
	return 17;
	}


	if(Subcontrol.MAZE.map[yLoc][xLoc]==2){
	return 18;
	}
	
	Subcontrol.MAZE.map[yLoc][xLoc]=3;
	BuilderPoints.points=3;
	BuilderPoints.appleSauceTimeout=3;
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
