package controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import listener_control.ObjectListenerManager;
import shapes.BSCircle;
import shapes.BSCompound;
import shapes.BSLine;
import shapes.BSPolygon;
import shapes.BSRectangle;
import extendeds.Controller;
import extendeds.Subcontrol;

public class ScreenButtons extends Controller {
	
	public enum Button {
		BANANA,
		BOMB,
		GHOST
	};
	
	public Button button;
	
	public BSLine buttonDivider = new BSLine (0, 420, 800, 420);
	
	public BSRectangle exitButton = new BSRectangle (0, 420, 60, 60);
	public BSRectangle firstButton = new BSRectangle (560, 420, 60, 60);
	public BSRectangle secondButton = new BSRectangle (620, 420, 60, 60);
	public BSRectangle thirdButton = new BSRectangle (680, 420, 60, 60);
	public BSRectangle fourthButton = new BSRectangle (740, 420, 60, 60);
	
	public BSRectangle bananaStem = new BSRectangle(560 + 27, 433, 5, 7);
	public int[] bananaX = 
		{560 + 12, 560 + 22, 560 + 30, 560 + 38, 560 + 48, 560 + 42, 560 + 32, 560 + 27, 560 + 18, 560 + 12};
	public int[] bananaY = 
		{460, 457, 447, 457, 460, 455, 440, 440, 455, 460};
	public BSPolygon bananaPeel = new BSPolygon(bananaX, bananaY, bananaX.length);
	public BSCompound banana;
	
	public BSCircle bombBody = new BSCircle(650, 465, 20);
	public BSRectangle bombFuse = new BSRectangle(649, 435, 2, 10);
	public int[] bombFireX = {649, 651, 660, 654, 650, 646, 640, 649};
	public int[] bombFireY = {435, 435, 425, 428, 421, 428, 425, 435};
	public BSPolygon bombFire = new BSPolygon(bombFireX, bombFireY, bombFireX.length);
	public BSCompound bomb;
	
	public BSRectangle arrowBody =  new BSRectangle(690, 440, 15, 10);
	public int[] arrowHeadX = {705, 720, 705, 705};
	public int[] arrowHeadY = {435, 445, 455, 435};
	public BSPolygon arrowHead = new BSPolygon(arrowHeadX, arrowHeadY, 4);
	public BSCompound arrow;
	
	public ScreenButtons(Subcontrol control){
		super(control);
		Subcontrol.controllers.add(this);
		bananaStem.setColor(Color.GREEN);
		bananaPeel.setColor(Color.YELLOW);
		banana = new BSCompound (bananaStem, bananaPeel);
		
		bombBody.setColor(Color.BLACK);
		bombFuse.setColor(Color.BLACK);
		bombFire.setColor(Color.RED);
		bomb = new BSCompound(bombBody, bombFuse, bombFire);
		
		arrowBody.setColor(Color.ORANGE);
		arrowHead.setColor(Color.ORANGE);
		arrow = new BSCompound(arrowBody, arrowHead);
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
		
	
		
		ObjectListenerManager.addAction(ObjectListenerManager.MOUSE_CLICKED, exitButton, this, "exit");
		ObjectListenerManager.addAction(ObjectListenerManager.MOUSE_CLICKED, firstButton, this, "first");
		ObjectListenerManager.addAction(ObjectListenerManager.MOUSE_CLICKED, secondButton, this, "second");
		ObjectListenerManager.addAction(ObjectListenerManager.MOUSE_CLICKED, thirdButton, this, "third");
		ObjectListenerManager.addAction(ObjectListenerManager.MOUSE_CLICKED, fourthButton, this, "fourth");

	}
	
	public void exit(){
		
	}
	
	public void first(){
		button = Button.BANANA;
	}
	
	public void second(){
		button = Button.BOMB;
	}
	
	public void third(){
		
	}
	
	public void fourth(){
		button = Button.GHOST;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawPlaying(Graphics2D g) {
		// TODO Auto-generated method stub
		
		exitButton.setColor(Color.GRAY);
		firstButton.setColor(Color.WHITE);
		secondButton.setColor(Color.GRAY);
		thirdButton.setColor(Color.WHITE);
		fourthButton.setColor(Color.GRAY);
		
		exitButton.autoDraw(g);
		firstButton.autoDraw(g);
		secondButton.autoDraw(g);
		thirdButton.autoDraw(g);
		fourthButton.autoDraw(g);
		if(main.role.role){
			banana.autoDraw(g);
			bomb.autoDraw(g);
			arrow.autoDraw(g);
		}
		
	}

}
