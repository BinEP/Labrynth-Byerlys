package controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import shapes.BSCircle;
import shapes.BSCompound;
import shapes.BSPolygon;
import shapes.BSRectangle;
import shapes.interfaces.BSShape;
import extendeds.Controller;
import extendeds.Subcontrol;
import networking.NetGameState;
import networking.NetworkListener;
import networking.NetworkManager;
import networking.NetworkVariableControl;

public class Maze extends Controller implements NetworkListener, NetworkVariableControl {
	
	public int xStartCoord = 200;
	public int yStartCoord = 40;
	public int tileSize = 40;
	public int mapSide = 400;
	public int runnerSize = 30;
	
	public boolean moved = false;
	
	ArrayList<BSShape> shapes = new ArrayList<BSShape>();
	
//	public BSRectangle mapSize = new BSRectangle(xStartCoord, yStartCoord, mapSide, mapSide);
	public int[][] origMap = {{0, 1, 1, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 1, 0, 1, 0, 1, 1}, 
			{0, 1, 0, 1, 1, 0, 1, 0, 1, 0}, 
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 1, 0, 1, 0, 1, 0}, 
			{0, 1, 0, 0, 0, 0, 1, 0, 1, 1}, 
			{1, 1, 0, 1, 0, 1, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 1, 1, 1, 0}, 
			{0, 1, 0, 1, 1, 0, 1, 0, 1, 0}, 
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 0}};
	
	public int[][] map = origMap.clone();
	
	
	public BSRectangle runnerVision = new BSRectangle(xStartCoord - 25, 335, tileSize * 3, tileSize * 3);
	
	public Maze(Subcontrol control){
		super(control);
		NetworkManager.addNetworkListener(this);
		NetworkManager.addNetworkVariableController(this);
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
		
//		controller.addShapeToBeDrawn(mapSize);
		
		

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void drawPlaying(Graphics2D g) {
		// TODO Auto-generated method stub
//		mapSize.setColor(Color.WHITE);
//		mapSize.autoDraw(g);
		
	BSRectangle background = new BSRectangle(0, 0, 800, 480);
	background.setColor(Color.DARK_GRAY);
	background.autoDraw(g);
		
	for (int i = 0; i < map.length; i++){
		for (int j = 0; j < map[i].length; j++){
			
			BSRectangle bananaStem = new BSRectangle(200 + 40 * j + 28, 40 * i + 14, 5, 7);
			int[] bananaX = 
				{200 + 40 * j + 12, 200 + 40 * j + 22, 200 + 40 * j + 30, 200 + 40 * j + 38, 200 + 40 * j + 48, 200 + 40 * j + 42,
					200 + 40 * j + 32, 200 + 40 * j + 27, 200 + 40 * j + 18, 560 + 12};
			int[] bananaY = 
				{40 * i  + 40, 40 * i + 37, 40 * i + 27, 40 * i + 37, 40 * i + 40, 40 * i + 35, 40 * i + 20, 40 * i + 20, 40 * i  + 53,
					40 * i + 40};
			BSPolygon bananaPeel = new BSPolygon(bananaX, bananaY, bananaX.length);
			BSCompound banana;
			bananaStem.setColor(Color.GREEN);
			bananaPeel.setColor(Color.YELLOW);
			
			BSCircle puddleA = new BSCircle(200 + 40 * j + 10, 40 * i + 10, 20);
			BSCircle puddleB = new BSCircle(200 + 40 * j + 20, 40 * i + 18, 30);
			BSCircle puddleC = new BSCircle(200 + 40 * j + 25, 40 * i + 7, 20);
			puddleA.setColor(Color.BLUE);
			puddleB.setColor(Color.BLUE);
			puddleC.setColor(Color.BLUE);
			
			BSRectangle square = new BSRectangle(xStartCoord + tileSize * j, tileSize * i, tileSize, tileSize);
			square.setColor(Color.WHITE);

			if (state.map[i][j] == 0 && (state.runnerVision.intersects(square.getBounds2D()) || !main.role.role)) {
				square.setColor(Color.GRAY);
				square.autoDraw(g);
			} else if (state.map[i][j] == 2 || !main.role.role) {
				bananaStem.autoDraw(g);
				bananaPeel.autoDraw(g);
			} else if (state.map[i][j] == 2 || !main.role.role) {
				puddleA.autoDraw(g);
				puddleB.autoDraw(g);
				puddleC.autoDraw(g);
			} else {
				square.setColor(Color.WHITE);
				square.autoDraw(g);
			}
		}
	}
		
		
		
		
	}


	public void buttonPress(String button, int y, int x) {
		if (button.equals("Banana")) map[y][x] = RunnerPoints.BANANA;
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
		state.runnerVision = this.state.runnerVision;
		state.map = this.state.map;
	}


	@Override
	public void newUpdateFromServer(NetGameState state) {
		// TODO Auto-generated method stub
//		runnerVision = state.runnerVision;
//		map = state.map;
//		this.state = state;
	}
	
	@Override
	public void netVarsReset(NetGameState state) {
		// TODO Auto-generated method stub
		state.runnerVision = new BSRectangle(xStartCoord - 25, 335, tileSize * 3, tileSize * 3);
		state.map = origMap;

	}
	
	@Override
	public void netVarsSetup(NetGameState state) {
		// TODO Auto-generated method stub
		state.runnerVision = runnerVision;
		state.map = origMap;
	}
}
