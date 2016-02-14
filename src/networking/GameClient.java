package networking;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.Main;
import networking.utils.Client;

/**
 * This class defines the client object that handles communication with the Hub.
 */
public class GameClient extends Client {

	private Main main;
	/**
	* Connect to the hub at a specified host name and port number.
	 * @throws IOException 
	*/
	public GameClient(String hubHostName, int hubPort) throws IOException {
		super(hubHostName, hubPort);
	}

	/**
	 * Responds to a message received from the Hub. The only messages that are
	 * supported are TicTacToeGameState objects. When one is received, the
	 * newState() method in the TicTacToeWindow class is called. To avoid
	 * problems with synchronization, that method is called using
	 * SwingUtilities.invokeLater() so that it will run in the GUI event thread.
	 */
	protected void messageReceived(final Object message) {
		if (message instanceof GameState) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() { // calls a method at the end of the
									// TicTacToeWindow class
					NetworkManager.messageReceived((GameState) message);
				}
			});
		}
	}

	/**
	 * If a shutdown message is received from the Hub, the user is notified and
	 * the program ends.
	 */
	protected void serverShutdown(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JOptionPane.showMessageDialog(main.getParent(), "Your opponent has disconnected.\nThe game is ended.");
				System.exit(0);
			}
		});
	}

}