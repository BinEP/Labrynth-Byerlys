package networking;

public interface NetworkListener {
	public boolean ifNewState();
	public void setUpdatedState(GameState state);
	public void newUpdateFromServer(GameState state);
}
