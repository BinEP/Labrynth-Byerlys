package extendeds;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Main;
import networking.NetGameState;
import controllers.BuilderPoints;
import controllers.Maze;
import controllers.NetworkController;
import controllers.RunnerPoints;
import controllers.ScreenButtons;
import game_actions.Scene;
import shapes.interfaces.BSShape;

public class Subcontrol {
	
	public static ArrayList<Controller> controllers = new ArrayList<Controller>();
	@SuppressWarnings("unused")
	private Main main;
	public static Scene ALWAYS_DRAW_SCENE = new Scene("AlwaysDraw");
	
	public final BuilderPoints BUILDER_POINTS = new BuilderPoints(this);
	public final Maze MAZE = new Maze(this);
	public final NetworkController NETWORK_CONTROLLER = new NetworkController(this);
	public final RunnerPoints RUNNER_POINTS = new RunnerPoints(this);
	public final ScreenButtons SCREEN_BUTTONS = new ScreenButtons(this);
	public NetGameState state;
	
	public Subcontrol() {
		controllers.add(BUILDER_POINTS);
		controllers.add(MAZE);
		controllers.add(NETWORK_CONTROLLER);
		controllers.add(RUNNER_POINTS);
		controllers.add(SCREEN_BUTTONS);
	}
	
	public static void addShapeToAlwaysBeDrawn(BSShape shape) {
		ALWAYS_DRAW_SCENE.addShapeToBeDrawn(shape);
	}
	
	public void updateState(NetGameState state, boolean fromWho) {
		if (fromWho) {
			//Runner things
			this.state = state;
			for (Controller c : controllers) {
				c.state = state;
			}
			switch (state.button)  {
			case "Banana" :
				MAZE.buttonPress(state.button, state.x, state.y);
				break;
			case "Bomb" :
				RUNNER_POINTS.c4(state.y, state.x);
				break;
			case "Ghost" :
				RUNNER_POINTS.hide();
				break;
			
			}
		} else {
			//Builder Things
			
			
		}
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
			c.drawPlaying(g);
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
