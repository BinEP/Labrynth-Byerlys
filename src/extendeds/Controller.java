package extendeds;

import main.Main;
import game_actions.Scene;

public abstract class Controller {
	
	public Scene controller = new Scene();
	public Main main;
	
	public static void main(String[] args) {

	}
	
	public abstract void update();
	public abstract boolean shouldEndGame();
	public abstract void reset();
//	public abstract void draw(Graphics2D g);
//	public abstract void drawPlaying(Graphics2D g);
	public abstract void setup();
}
