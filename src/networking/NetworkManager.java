package networking;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

import game_state.SceneManager;
import main.Main;
import networking.utils.Hub;


//import networking.GoFishWindow.GoFishClient;

public class NetworkManager {
	
	public static ArrayList<NetworkListener> netListeners = new ArrayList<NetworkListener>();
	public static ArrayList<NetworkVariableControl> netVariableControllers = new ArrayList<NetworkVariableControl>();
//	public static String HOST;
//	public static int PORT;
//	public static Hub SERVER;
	public Main main;
	
	/**
	 * The state of the game. This state is a copy of the official state, which
	 * is stored on the server. When the state changes, the state is sent as a
	 * message to this window. (It is actually sent to the TicTacToeClient
	 * object that represents the connection to the server.) When that happens,
	 * the state that was received in the message replaces the value of this
	 * variable, and the board and UI is updated to reflect the changed state.
	 * This is done in the newState() method, which is called by the
	 * TicTacToeClient object.
	 */
	private GameState state;

	private int myID; // The ID number that identifies the player using this
//	public boolean cheat = true; // window.

//	private String customFontName;
//	private Font customFont;
//	private static final String Font_File_Name = "joystix";

	private GameClient connection; // The Client object for sending and
										// receiving
										// network messages.
	
	
	
	/**
	 * Creates and configures the window, opens a connection to the server, and
	 * makes the widow visible on the screen. This constructor can block until
	 * the connection is established.
	 * 
	 * @param hostName
	 *            the name or IP address of the host where the server is
	 *            running.
	 * @param serverPortNumber
	 *            the port number on the server computer when the Hub is
	 *            listening for connections.
	 * @throws IOException
	 *             if some I/O error occurs while trying to open the connection.
	 * @throws Client.DuplicatePlayerNameException
	 *             it playerName is already in use by another player in the
	 *             game.
	 */
	public NetworkManager(String host, int port) throws IOException {
			connection = new GameClient(host, port);
			myID = connection.getID();
			SceneManager.setScene("Start");
	}
		
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void disconnectClient() {
		connection.disconnect(); // Send a disconnect message to the
		// hub.
		try {
			Thread.sleep(333); // Wait one-half second to allow the
			// message to be sent.
		} catch (InterruptedException e) {}
		System.exit(0);
	}
	
	
	public static void addNetworkListener(NetworkListener nl) {
		synchronized(netListeners) {
			netListeners.add(nl);
		}
	}
	
	public static void addNetworkVariableController(NetworkVariableControl nl) {
		synchronized(netVariableControllers) {
			netVariableControllers.add(nl);
		}
	}
	
	public static void messageReceived(GameState state) {
		synchronized(netListeners) {
			for (NetworkListener nl : netListeners) {
				nl.newUpdateFromServer(state);
			}
		}
	}
	
	public boolean ifNewState() {
		boolean ifNew = false;
		synchronized(netListeners) {
			for (NetworkListener nl : netListeners) {
				if (nl.ifNewState()) {
					ifNew = true;
					nl.setUpdatedState(state);
				};
			}
		}
		connection.send(state);
		return ifNew;
	}
	
	public static void networkSetupVariables(GameState state) {
		synchronized(netVariableControllers) {
			for (NetworkVariableControl nl : netVariableControllers) {
				nl.netVarsSetup(state);
			}
		}
	}
	
	public static void networkResetVariables(GameState state) {
		synchronized(netVariableControllers) {
			for (NetworkVariableControl nl : netVariableControllers) {
				nl.netVarsReset(state);
			}
		}
	}
	
	public static boolean networkShouldEndGame(GameState state) {
		synchronized(netVariableControllers) {
			for (NetworkVariableControl nl : netVariableControllers) {
				if (nl.shouldEndGame(state)) return true;
			}
		}
		return false;
	}
	
	
	
}
