package controllers;

import extendeds.Controller;

public class BuilderPoints extends Controller {

	static int points;

	public void timeouts(){

	}

	public int removeSection(int yLocation, int xLocation){
		if (Subcontrol.MAZE[yLocation][xLocation] != 1) return 11;
		Subcontrol.MAZE[yLocation][xLocation] = 0;
		BuilderPoints.points++;
	}

	public int buildSection(int yLocation, int xLocation){
		if (Subcontrol.MAZE[yLocation][xLocation] == 1) return 12;
		if (Subcontrol.MAZE[yLocation][xLocation] == 2 || Subcontrol.MAZE[yLocation][xLocation] == 3) return 13;
		Subcontrol.MAZE[yLocation][xLocation] = 0;
		BuilderPoints.points = BuilderPoints.points-3;
		return 0;
	}

	public int putBananaPeel(int yLocation, int xLocation){
		if (Subcontrol.MAZE[yLocation][xLocation] == 1) return 12;
		if (Subcontrol.MAZE[yLocation][xLocation] == 2) return 14;
		if (Subcontrol.MAZE[yLocation][xLocation] == 3) return 15;
		Subcontrol.MAZE[yLocation][xLocation] = 2;
		BuilderPoints.points--;
		return 0;
	}

	public int spillAppleSauce(int yLocation, int xLocation){
		
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
