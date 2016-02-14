package networking;

public interface NetworkListener {
	public boolean ifNewState();
	public void setUpdatedState(NetGameState state);
	public void newUpdateFromServer(NetGameState state);

}
