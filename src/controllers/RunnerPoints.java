package controllers;

import java.awt.Color;
import java.awt.Graphics2D;

import extendeds.Controller;
import extendeds.Subcontrol;
import shapes.BSRectangle;

public class RunnerPoints extends Controller {
    /*
    ***ERROR CODES***
    1      Not enough points
	2      Nothing in position
	3      Wall in postion
	4      Apple sauce in postion
	5      Attempting to move out of bounds
	7      Banana in postion 

	*/
    
    public static boolean hidden = false;
    public static boolean suspended = false;
    public static int points = 30;
    public static int hideTimeout = 0;
    public static int suspendTimeout = 0;
    
    public int ticks = 100;
    public int ticksMin = 100;
    
    public enum MovementError {
    	noPoints, nothingThere, outOfBounds, wall, appleSauce, banana, noError, tooFast
    }
    
    public static int OPEN = 0;
    public static int WALL = 1;
    public static int BANANA = 2;
    public static int APPLE_SAUCE = 3;
	
	public BSRectangle runner = new BSRectangle(0, Maze.map.length - 1, Maze.runnerSize, Maze.runnerSize);
	
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
		if (Maze.map[yLocation][xLocation] == OPEN) return MovementError.nothingThere; //if there is nothing in position, returns 2
		if (Maze.map[yLocation][xLocation] == WALL) return MovementError.wall; //if there is a wall in positon, return 3
		if (Maze.map[yLocation][xLocation] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in postion return 4
		Maze.map[yLocation][xLocation] = OPEN; //sets map location with banana to "free"
		points--; //deducts 1 point
		return MovementError.noError;
	}

	public MovementError move(int direction) { // 1 = up, 2 = right, 3 = down, 4 = left
		ticks++;
		if (ticks < ticksMin) return MovementError.tooFast;
		ticks = 0;
		//if (points == 0) return MovementError.noPoints;
		if (runner.x == 9 && direction == 2) return MovementError.outOfBounds; //out of bounds, return 5
		if (runner.x == 0 && direction == 4) return MovementError.outOfBounds; //out of bounds
		if (runner.y == 9 && direction == 3) return MovementError.outOfBounds; // out of bounds
		if (runner.y == 0 && direction == 1) return MovementError.outOfBounds; //out of bounds
		
		System.out.println("Check Directions");
		
		if (direction == 1) {
//			if (Maze.map[runner.y - 1][runner.x] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
//			if (Maze.map[runner.y - 1][runner.x] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
//			if (Maze.map[runner.y - 1][runner.x] == BANANA) {
//				Maze.map[runner.y - 1][runner.x] = OPEN;
//				suspended = true;
//			}
//			
//			if (Maze.map[runner.y - 1][runner.x] == OPEN)
//				runner.y--;
			MovementError err = checkMovement(0, -1);
			if (err != MovementError.noError) return err;
			
		} else if (direction == 2) {
//			if (Maze.map[runner.y][runner.x + 1] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
//			if (Maze.map[runner.y][runner.x + 1] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
//			if (Maze.map[runner.y][runner.x + 1] == BANANA) {
//				Maze.map[runner.y][runner.x + 1] = OPEN;
//				suspended = true;
//			}
//			if (Maze.map[runner.y][runner.x+1] == 0){
//				runner.x++;
//			}
			MovementError err = checkMovement(1, 0);
			if (err != MovementError.noError) return err;
		}
		else if (direction == 3) {
//			if (Maze.map[runner.y+1][runner.x] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
//			if (Maze.map[runner.y+1][runner.x] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
//			if (Maze.map[runner.y+1][runner.x] == BANANA) {
//				Maze.map[runner.y+1][runner.x] = OPEN;
//				suspended = true;
//			}
//			if (Maze.map[runner.y+1][runner.x] == 0)
//				runner.y++;
			MovementError err = checkMovement(0, 1);
			if (err != MovementError.noError) return err;
		}
		else if (direction == 4) {
//			if (Maze.map[runner.y][runner.x - 1] == 1) return 3; //if there is a wall in postion return 3.
//			if (Maze.map[runner.y][runner.x - 1] == 3) return 4; 
//			if (Maze.map[runner.y][runner.x - 1] == 2){
//				Maze.map[runner.y][runner.x - 1] = 0;
//				suspended = true;
//				suspendTimeout = 1;
//			}
//			if (Maze.map[runner.y][runner.x - 1] == 0){
//				runner.x--;
//			}
			MovementError err = checkMovement(-1, 0);
			if (err != MovementError.noError) return err;
		}
		
		//points--;
		return MovementError.noError;
	}

	public MovementError checkMovement(int deltaX, int deltaY) {
		System.out.println("Check Movement");
		if (Maze.map[runner.y + deltaY][runner.x + deltaX] == WALL) return MovementError.wall; //if there is a wall in postion return 3.
		if (Maze.map[runner.y + deltaY][runner.x + deltaX] == APPLE_SAUCE) return MovementError.appleSauce; //if there is apple sauce in position return 4
		if (Maze.map[runner.y + deltaY][runner.x + deltaX] == BANANA) {
			Maze.map[runner.y + deltaY][runner.x + deltaX] = OPEN;
			suspended = true;
		}
		System.out.println("Should Move");
		if (Maze.map[runner.y + deltaY][runner.x + deltaX] == 0) {
			runner.x += deltaX;
			runner.y += deltaY;
		}
		return MovementError.noError;
	}

	public MovementError c4(int yLocation, int xLocation){
		if (Maze.map[yLocation][xLocation] == APPLE_SAUCE) return MovementError.appleSauce;//if there is apple sauce in position return 4
		if (Maze.map[yLocation][xLocation] == BANANA) return MovementError.banana; //if there is banana in position return 7
//		if (Maze.map[yLocation][xLocation] == OPEN) return MovementError.nothingThere; //if there is nothing in postion return 2
		if (Maze.map[yLocation][xLocation] == WALL) {
			Maze.map[yLocation][xLocation] = OPEN;
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
		BSRectangle scaledRunner = new BSRectangle(runner.x * Maze.tileSize + Maze.xStartCoord, runner.y * Maze.tileSize, Maze.runnerSize, Maze.runnerSize);
		scaledRunner.setColor(Color.GREEN);
		scaledRunner.autoDraw(g);
	}
}
