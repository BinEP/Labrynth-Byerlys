package extendeds;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game_actions.Scene;
import shapes.interfaces.BSShape;

public class Subcontrol {
	
	public ArrayList<Controller> controllers = new ArrayList<Controller>();
	public static Scene ALWAYS_DRAW_SCENE = new Scene("AlwaysDraw");
	
	public static void addShapeToAlwaysBeDrawn(BSShape shape) {
		ALWAYS_DRAW_SCENE.addShapeToBeDrawn(shape);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		for (Controller c : controllers) {
			c.update();
		}
	}

	public boolean checkIfDead() {
		for (Controller c : controllers) {
			if (c.shouldEndGame()) return true;
		}
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		for (Controller c : controllers) {
			c.reset();
		}
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		ALWAYS_DRAW_SCENE.drawShapes(g);
	}

	public void drawPlaying(Graphics2D g) {
		for (Controller c : controllers) {
			c.controller.drawShapes(g);
		}
	}

	public void setup() {
		// TODO Auto-generated method stub
		for (Controller c : controllers) {
			c.setup();
		}
	}
	
	
	
}
