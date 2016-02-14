package main;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JLabel;

import extendeds.Subcontrol;
import game_actions.Game;
import game_actions.Runner;
import game_actions.Scene;
import game_state.SceneManager;
import networking.GameState;
import networking.NetSetup;
import networking.NetworkManager;
import networking.utils.Hub;
import shapes.BSString;

public class Main extends Game {
	
	private Subcontrol subScenes = new Subcontrol();
	
	
	public static NetworkManager networkManager;
	private static JLabel message = new JLabel();
	public static GameState STATE;
	
//	public Main() throws IOException {
//		networkManager = new NetworkManager(HOST, PORT, SERVER, this);
//	}
	
	public static void main(String[] args) {
		
		networkManager = NetSetup.setupNetwork();
		
		Runner runner = new Runner(new Main());
		runner.add(message, BorderLayout.SOUTH);
		runner.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				endGame();
			}
		});
		
		setupWaitScene();
		SceneManager.setScene("Network Waiting");
	}
	
	private static void setupWaitScene() {
		Scene netWait = new Scene("Network Waiting");
		BSString waiting = new BSString("Waiting...", 40);
		netWait.addShapeToBeDrawn(waiting);
		SceneManager.addScene(netWait);
	}

	@Override
	public void moves() {
		// TODO Auto-generated method stub
		subScenes.update();
	}

	@Override
	public boolean checkIfDead() {
		// TODO Auto-generated method stub
		return subScenes.checkIfDead();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		subScenes.reset();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		subScenes.draw(g);
	}

	@Override
	public void drawPlaying(Graphics2D g) {
		// TODO Auto-generated method stub
		subScenes.drawPlaying(g);
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		subScenes.setup();
		networkManager.setMain(this);
	}

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return "Labrynth And Byerly's";
	}
	
	public static void setMessageLabel(String newMessage) {
		message.setText(newMessage);
	}
	
	public static void endGame() {
		networkManager.disconnectClient();
		
	}
}
