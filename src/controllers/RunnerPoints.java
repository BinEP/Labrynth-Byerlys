package controllers;

import java.awt.Color;
import java.awt.Graphics2D;

import extendeds.Controller;
import extendeds.Subcontrol;
import networking.NetGameState;
import networking.NetworkListener;
import networking.NetworkManager;
import networking.NetworkVariableControl;
import shapes.BSRectangle;

public class RunnerPoints extends Controller implements NetworkListener, NetworkVariableControl {
    /*
    ***ERROR CODES***
    1      Not enough points
	2      Nothing in position
	3      Wall in postion
	4      Apple sauce in postion
	5      Attempting to move out of bounds
	7      Banana in postion 

	*/
    
    public boolean hidden = false;
    public boolean suspended = false;
    public int points = 30;
    public int hideTimeout = 0;
    public int suspendTimeout = 0;
    
    public int ticksMin = 10;
    public int ticks = ticksMin;

    public boolean moved = false;
    
    public enum MovementError {
    	noPoints, nothingThere, outOfBounds, wall, appleSauce, banana, noError, tooFast
    }
    
    public int OPEN = 0;
    public int WALL = 1;
    public int BANANA = 2;
    public int APPLE_SAUCE = 3;
	
	public BSRectangle runner = new BSRectangle(0, subController.MAZE.map.length - 1, subController.MAZE.runnerSize, subController.MAZE.runnerSize);
	
	public RunnerPoints(Subcontrol control) {
		super(control);
		NetworkManager.addNetworkListener(this);
		NetworkManager.addNetworkVariableController(this);
	}
	
	public void timeouts(){
	    if (hidden || suspended){
	        hideTimeout--;
	        if (hideTimeout < 0){
	            hidden = false;
	        }
	    }
	}
	
	public MovementError pickUpBanana(int yLocation, int xLocation) {
		if (points == 0) return MovementError.noPoints; //if not enough points, returns 1
		if (state.map[yLocation][xLocation] == OPEN) return MovementError.nothingThere; //if there is nothing in position, returns 2
		if (state.map[yLocation][xLocation] == WALL) return MovementError.wall; //if there is a wall in positon, return 3
		if (state.map[yLocation][xLocation] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in postion return 4
		state.map[yLocation][xLocation] = OPEN; //sets map location with banana to "free"
		points--; //deducts 1 point
		return MovementError.noError;
	}

	public MovementError move(int direction) { // 1 = up, 2 = right, 3 = down, 4 = left
		
		if (!(main.networkManager.sent && main.networkManager.received) || !(!main.networkManager.sent && !main.networkManager.received)) {
			main.networkManager.sent = false;
			main.networkManager.received = false;
			if (ticks < ticksMin) {
				return MovementError.tooFast;
			}
		}
		
		ticks = 0;
		
		//if (points == 0) return MovementError.noPoints;
		if (state.runner.x == 9 && direction == 2) return MovementError.outOfBounds; //out of bounds, return 5
		if (state.runner.x == 0 && direction == 4) return MovementError.outOfBounds; //out of bounds
		if (state.runner.y == 9 && direction == 3) return MovementError.outOfBounds; // out of bounds
		if (state.runner.y == 0 && direction == 1) return MovementError.outOfBounds; //out of bounds
		
		System.out.println("Check Directions");
		
		if (direction == 1) {
//			if (subController.MAZE.map[state.runner.y - 1][state.runner.x] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
//			if (subController.MAZE.map[state.runner.y - 1][state.runner.x] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
//			if (subController.MAZE.map[state.runner.y - 1][state.runner.x] == BANANA) {
//				subController.MAZE.map[state.runner.y - 1][state.runner.x] = OPEN;
//				suspended = true;
//			}
//			
//			if (subController.MAZE.map[state.runner.y - 1][state.runner.x] == OPEN)
//				state.runner.y--;
			MovementError err = checkMovement(0, -1);
			if (err != MovementError.noError) return err;
			
		} else if (direction == 2) {
//			if (subController.MAZE.map[state.runner.y][state.runner.x + 1] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
//			if (subController.MAZE.map[state.runner.y][state.runner.x + 1] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
//			if (subController.MAZE.map[state.runner.y][state.runner.x + 1] == BANANA) {
//				subController.MAZE.map[state.runner.y][state.runner.x + 1] = OPEN;
//				suspended = true;
//			}
//			if (subController.MAZE.map[state.runner.y][state.runner.x+1] == 0){
//				state.runner.x++;
//			}
			MovementError err = checkMovement(1, 0);
			if (err != MovementError.noError) return err;
		}
		else if (direction == 3) {
//			if (subController.MAZE.map[state.runner.y+1][state.runner.x] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
//			if (subController.MAZE.map[state.runner.y+1][state.runner.x] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
//			if (subController.MAZE.map[state.runner.y+1][state.runner.x] == BANANA) {
//				subController.MAZE.map[state.runner.y+1][state.runner.x] = OPEN;
//				suspended = true;
//			}
//			if (subController.MAZE.map[state.runner.y+1][state.runner.x] == 0)
//				state.runner.y++;
			MovementError err = checkMovement(0, 1);
			if (err != MovementError.noError) return err;
		}
		else if (direction == 4) {
//			if (subController.MAZE.map[state.runner.y][state.runner.x - 1] == 1) return 3; //if there is a wall in postion return 3.
//			if (subController.MAZE.map[state.runner.y][state.runner.x - 1] == 3) return 4; 
//			if (subController.MAZE.map[state.runner.y][state.runner.x - 1] == 2){
//				subController.MAZE.map[state.runner.y][state.runner.x - 1] = 0;
//				suspended = true;
//				suspendTimeout = 1;
//			}
//			if (subController.MAZE.map[state.runner.y][state.runner.x - 1] == 0){
//				state.runner.x--;
//			}
			MovementError err = checkMovement(-1, 0);
			if (err != MovementError.noError) return err;
		}
		
		//points--;
		if (main.role.role) {
			moved = true;
			subController.MAZE.moved = true;
		}
		return MovementError.noError;
	}

	public MovementError checkMovement(int deltaX, int deltaY) {
		System.out.println("Check Movement");
		if (state.map[state.runner.y + deltaY][state.runner.x + deltaX] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
		if (state.map[state.runner.y + deltaY][state.runner.x + deltaX] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
		if (state.map[state.runner.y + deltaY][state.runner.x + deltaX] == BANANA) {
			state.map[state.runner.y + deltaY][state.runner.x + deltaX] = OPEN;
			suspended = true;
		}
		System.out.println("Should Move");
		if (state.map[state.runner.y + deltaY][state.runner.x + deltaX] == OPEN) {
			state.runner.x += deltaX;
			state.runner.y += deltaY;
			state.runnerVision.x += deltaX * subController.MAZE.tileSize;
			state.runnerVision.y += deltaY * subController.MAZE.tileSize;
		}
		return MovementError.noError;
	}

	public MovementError c4(int yLocation, int xLocation){
		if (state.map[yLocation][xLocation] == APPLE_SAUCE) return MovementError.appleSauce;//if there is apple sauce in position return 4
		if (state.map[yLocation][xLocation] == BANANA) return MovementError.banana; //if there is banana in position return 7
//		if (subController.MAZE.map[yLocation][xLocation] == OPEN) return MovementError.nothingThere; //if there is nothing in postion return 2
		if (state.map[yLocation][xLocation] == WALL) {
			state.map[yLocation][xLocation] = OPEN;
			points -= 3;
			
		}
		return MovementError.noError;
	}

	public MovementError hide(){
		if (points < 10) return MovementError.noPoints;
		points -= 10;
		hidden = true;
		hideTimeout = 2;
		return MovementError.noError;
	}

	@Override
	public void update() {
		// TODO Auto - generated method stub
		ticks++;
		if (main.upPressed) {
			System.out.println("Up Pressed");
			move(1);
			
		} else if (main.rightPressed) {
			System.out.println("Right Pressed");
			move(2);
			
		} else if (main.downPressed) {
			System.out.println("Down Pressed");
			move(3);
			
		} else if (main.leftPressed) {
			System.out.println("Left Pressed");
			move(4);
		}
		
	}

	@Override
	public boolean shouldEndGame() {
		// TODO Auto - generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto - generated method stub

	}

	@Override
	public void setup() {
		// TODO Auto - generated method stub
		runner.setColor(Color.GREEN);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto - generated method stub
		
	}

	@Override
	public void drawPlaying(Graphics2D g) {
		// TODO Auto - generated method stub
		BSRectangle scaledRunner = new BSRectangle(state.runner.x * subController.MAZE.tileSize + subController.MAZE.xStartCoord + 5, 5 + state.runner.y * subController.MAZE.tileSize, subController.MAZE.runnerSize, subController.MAZE.runnerSize);
		scaledRunner.setColor(Color.GREEN);
		scaledRunner.autoDraw(g);
	}

	@Override
	public boolean ifNewState() {
		// TODO Auto-generated method stub
		if (moved) {
			moved = false;
			return true;
		}
		return false;
	}

	@Override
	public void setUpdatedState(NetGameState state) {
		// TODO Auto-generated method stub
		state.runner = this.state.runner;
	}

	@Override
	public void newUpdateFromServer(NetGameState state) {
		// TODO Auto-generated method stub
//		runner = state.runner;
//		this.state = state;
	}
	
	@Override
	public void netVarsReset(NetGameState state) {
		// TODO Auto-generated method stub
		state.runner = new BSRectangle(0, state.map.length - 1, subController.MAZE.runnerSize, subController.MAZE.runnerSize);
	}
	
	@Override
	public void netVarsSetup(NetGameState state) {
		// TODO Auto-generated method stub
		state.runner = runner;
	}
}
