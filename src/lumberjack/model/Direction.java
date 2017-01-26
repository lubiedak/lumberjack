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
	
	public int increaseY(){
		if(this == Direction.UP)
			return 1;
		else if(this == Direction.DOWN)
			return -1;
		else
			return 0;
	}
	public int increaseX(){
		if(this == Direction.RIGHT)
			return 1;
		else if(this == Direction.LEFT)
			return -1;
		else
			return 0;
	}
}
