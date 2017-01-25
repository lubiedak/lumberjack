package lumberjack.model;

import java.util.ArrayList;

public class LumberJack {

	int x; // position
	int y; // position
	ArrayList<String> decisions;
	int timeToWalk;

	public LumberJack(int time) {
		x = 0;
		y = 0;
		decisions = new ArrayList<String>();
		timeToWalk = time;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<String> getDecisions() {
		return decisions;
	}

	public int getTimeToWalk() {
		return timeToWalk;
	}

	@Override
	public String toString() {
		return "LumberJack [x=" + x + ", y=" + y + ", decisions=" + decisions
				+ ", timeToWalk=" + timeToWalk + "]";
	}

	public void goToTree(Tree tree) {
		while (x != tree.getX() && timeToWalk > 0) {
			if (tree.getX() > x) {
				moveRight();
			} else {
				moveLeft();
			}
		}

		while (y != tree.getY() && timeToWalk > 0) {
			if (tree.getY() > y) {
				moveUp();
			} else {
				moveDown();
			}
		}
	}

	public void moveUp() {
		decisions.add("move up");
		y++;
		timeToWalk--;
	}

	public void moveDown() {
		decisions.add("move down");
		y--;
		timeToWalk--;
	}

	public void moveRight() {
		decisions.add("move right");
		x++;
		timeToWalk--;
	}

	public void moveLeft() {
		decisions.add("move left");
		x--;
		timeToWalk--;
	}

	public void cutTree(Direction cutDirection, Tree tree) {
		if(tree.getTimeNeededToCut() < timeToWalk){
			Direction direction = cutDirection == Direction.NOT_IN_LINE ? Direction.UP : cutDirection; 
			decisions.add("cut " + direction.toString().toLowerCase());
			timeToWalk -= tree.getTimeNeededToCut();
			tree.cutTree();
		}
	}
	
	
}
