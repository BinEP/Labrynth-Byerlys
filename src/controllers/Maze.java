package controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

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
			BSRectangle square = new BSRectangle(xStartCoord + tileSize * j, tileSize * i, tileSize, tileSize);
			square.setColor(Color.WHITE);

			if ((map[i][j] == 0 && runnerVision.intersects(square.getBounds2D())) || !main.role.role) {
				square.setColor(Color.GRAY);
			}
			
			square.autoDraw(g);
		}
	}
		
		
		
		
	}


	@Override
	public boolean ifNewState() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setUpdatedState(NetGameState state) {
		// TODO Auto-generated method stub
		state.runnerVision = runnerVision;
		state.map = map;
	}


	@Override
	public void newUpdateFromServer(NetGameState state) {
		// TODO Auto-generated method stub
		runnerVision = state.runnerVision;
		map = state.map;
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
