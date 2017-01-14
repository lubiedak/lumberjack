package lumberjack.model;

import java.util.ArrayList;
import java.util.Arrays;

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
		while (x != tree.getX()) {
			if (tree.getX() > x) {
				decisions.add("Move right");
				x++;
			} else {
				decisions.add("Move left");
				x--;
			}
		}

		while (y != tree.getY()) {
			if (tree.getY() > y) {
				decisions.add("Move up");
				y++;
			} else {
				decisions.add("Move down");
				y--;
			}
		}
	}

	public void moveUp() {
		decisions.add("Move up");
		y++;
	}

	public void moveDown() {
		decisions.add("Move down");
		y--;
	}

	public void moveRight() {
		decisions.add("Move right");
		x++;
	}

	public void moveLeft() {
		decisions.add("Move left");
		x--;
	}

	public void cutTree(String cutDirection) {
		decisions.add(cutDirection);
	}
}
