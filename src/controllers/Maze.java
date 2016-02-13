package controllers;

import java.awt.Color;

import shapes.BSRectangle;
import extendeds.Controller;

public class Maze extends Controller {
	public int xStartCoord = 200;
	public int yStartCoord = 0;
	public int tileSize = 40;
	public int mapSide = 400;
	
	public BSRectangle mapSize = new BSRectangle(xStartCoord, yStartCoord, mapSide, mapSide);
	public int[][] map = 
		   {{0, 1, 1, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 1, 0, 1, 0, 1, 1}, 
			{0, 1, 0, 1, 1, 0, 1, 0, 0, 1}, 
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 1, 0, 1, 0, 1, 0}, 
			{0, 1, 0, 0, 0, 0, 1, 0, 1, 1}, 
			{1, 1, 0, 1, 0, 1, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 1, 1, 1, 0}, 
			{0, 1, 0, 1, 1, 0, 1, 0, 1, 0}, 
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 0},};

	public static void main(String[] args) {
		
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
		mapSize.setColor(Color.WHITE);
		controller.addShapeToBeDrawn(mapSize);
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				BSRectangle square = new BSRectangle(xStartCoord + tileSize * i, tileSize * i, tileSize, tileSize);
				if (map[i][j] == 1){
					//setColor(Color.WHITE);
					square.setColor(Color.BLACK);
					controller.addShapeToBeDrawn(square);
				} else {
					square.setColor(Color.WHITE);
					controller.addShapeToBeDrawn(square);
				}
			}
		}

	}
}
