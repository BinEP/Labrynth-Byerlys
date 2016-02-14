package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import extendeds.Subcontrol;
import game_actions.Game;
import game_actions.Runner;
import game_actions.Scene;
import game_state.GameStateManager;
import game_state.GameStateManager.GameState;
import game_state.SceneManager;
import networking.NetGameState;
import networking.NetSetup;
import networking.NetworkListener;
import networking.NetworkManager;
import shapes.BSString;

public class Main extends Game implements NetworkListener {
	
	private static final long serialVersionUID = 1L;

	public Subcontrol subScenes = new Subcontrol();
	
	public class Role {
		public boolean role = true;
	}
	public Role role = new Role();
	
	public Main main;
	
	public NetworkManager networkManager = NetSetup.setupNetwork(role);
	
	private JLabel message = new JLabel();
		
	public Main()  {
//	networkManager = new NetworkManager(HOST, PORT, SERVER, this);
	
//		subScenes = new Subcontrol();
//		networkManager = nm;
		this.main = this;

	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		NetworkManager.addNetworkListener(main);
		Runner runner = new Runner(main);
		runner.add(main.message, BorderLayout.SOUTH);
		runner.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				main.endGame();
			}
		});
		
//		setupWaitScene();
//		SceneManager.setScene("Network Waiting");
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
		networkManager.ifNewState();
	}

	@Override
	public boolean checkIfDead() {
		// TODO Auto-generated method stub
		System.out.println("Winner!");
		System.out.println(subScenes.MAZE.map[subScenes.RUNNER_POINTS.runner.y][subScenes.RUNNER_POINTS.runner.x]);
		return subScenes.MAZE.map[subScenes.RUNNER_POINTS.runner.y][subScenes.RUNNER_POINTS.runner.x] == 5;
//		return subScenes.checkIfDead();
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
		this.setBackground(Color.DARK_GRAY);
		subScenes.setup(this);
		networkManager.setMain(this);
	}

	@Override
	public String getGameName() {
		// TODO Auto-generated method stub
		return "Labrynth And Byerly's";
	}
	
	public void setMessageLabel(String newMessage) {
		main.message.setText(newMessage);
	}
	
	public void endGame() {
		networkManager.disconnectClient();
		
	}
	
//	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		super.keyPressed(e);
//		if (e.getKeyCode() == KeyEvent.VK_ENTER && GameStateManager.isStartGame()) {
//			
//		}
//	}

	@Override
	public void setUpdatedState(NetGameState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newUpdateFromServer(NetGameState state) {
		// TODO Auto-generated method stub
		if (state.messageFromServer[0].equals("newgame"))  {
			GameStateManager.toPlayingBooleans();
//			setup();
		}
		if (role.role != state.fromWho || state.messageFromServer[0].equals("newgame"))
			subScenes.updateState(state, state.fromWho);
	}

	@Override
	public boolean ifNewState() {
		// TODO Auto-generated method stub
		return false;
	}
}
