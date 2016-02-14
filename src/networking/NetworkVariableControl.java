package networking;

public interface NetworkVariableControl {
	public default void netVarsSetup(GameState state) {}
	public default void netVarsReset(GameState state) {}
	default boolean shouldEndGame(GameState state) {return false;}
}
