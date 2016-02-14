package extendeds;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Main;
import controllers.BuilderPoints;
import controllers.Maze;
import controllers.NetworkController;
import controllers.RunnerPoints;
import game_actions.Scene;
import shapes.interfaces.BSShape;

public class Subcontrol {
	
	public ArrayList<Controller> controllers = new ArrayList<Controller>();
	private Main main;
	public static Scene ALWAYS_DRAW_SCENE = new Scene("AlwaysDraw");
	
	public static final BuilderPoints BUILDER_POINTS = new BuilderPoints();
	public static final Maze MAZE = new Maze();
	public static final NetworkController NETWORK_CONTROLLER = new NetworkController();
	public static final RunnerPoints RUNNER_POINTS = new RunnerPoints();
	
	public Subcontrol() {
		controllers.add(BUILDER_POINTS);
		controllers.add(MAZE);
		controllers.add(NETWORK_CONTROLLER);
		controllers.add(RUNNER_POINTS);
	}
	
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

	public void setup(Main main) {
		this.main = main;
		// TODO Auto-generated method stub
		for (Controller c : controllers) {
			c.main = main;
			c.setup();
		}
	}
	
	
	
}
