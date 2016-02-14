package networking;

public interface NetworkVariableControl {
	public default void netVarsSetup(NetGameState state) {}
	public default void netVarsReset(NetGameState state) {}
	default boolean shouldEndGame(NetGameState state) {return false;}
}
