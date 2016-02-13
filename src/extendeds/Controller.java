package extendeds;

import java.awt.Graphics2D;

import game_actions.Scene;

public abstract class Controller {
	
	public Scene controller = new Scene();
	
	public static void main(String[] args) {

	}
	
	public abstract void update();
	public abstract boolean shouldEndGame();
	public abstract void reset();
//	public abstract void draw(Graphics2D g);
//	public abstract void drawPlaying(Graphics2D g);
	public abstract void setup();
}
