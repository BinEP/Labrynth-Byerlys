package main;

import java.awt.Graphics2D;

import extendeds.Subcontrol;
import game_actions.Game;
import game_actions.Runner;

public class Main extends Game {
	
	private Subcontrol subScenes = new Subcontrol();
	
	public static void main(String[] args) {
		new Runner(new Main());
	}

	@Override
	public void moves() {
		// TODO Auto-generated method stub
		subScenes.update();
	}

	@Override
	public boolean checkIfDead() {
		// TODO Auto-generated method stub
		return subScenes.checkIfDead();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		subScenes.reset();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		subScenes.draw(g);
	}

	@Override
	public void drawPlaying(Graphics2D g) {
		// TODO Auto-generated method stub
		subScenes.drawPlaying(g);
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		subScenes.setup();
	}

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return "Labrynth And Byerly's";
	}
}
