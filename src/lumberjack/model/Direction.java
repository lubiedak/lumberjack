package lumberjack.model;

public enum Direction {
	UP(0),
	RIGHT(1),
	DOWN(2),
	LEFT(3),
	NOT_IN_LINE(-1);

	private final int dir;
	Direction(int dir) { this.dir = dir; }
	public int getDir() { return dir; }
	public Direction asDir(int intDir){ return Direction.values()[intDir];}
}
