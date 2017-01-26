package lumberjack.model;

import java.util.ArrayList;

public class LumberJack {

	int x; // position
	int y; // position
	ArrayList<String> decisions;
	ArrayList<Integer> decisionsCost;
	ArrayList<String> additionalInfo;
	int timeToWalk;

	public LumberJack(int time) {
		x = 0;
		y = 0;
		decisions = new ArrayList<String>();
		decisionsCost = new ArrayList<Integer>();
		additionalInfo = new ArrayList<String>();
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
				+ ", TimeLeft=" + decisionsCost + ", additionalInfo=" + additionalInfo + "]";
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
		decisionsCost.add(timeToWalk);
	}

	public void moveDown() {
		decisions.add("move down");
		y--;
		timeToWalk--;
		decisionsCost.add(timeToWalk);
	}

	public void moveRight() {
		decisions.add("move right");
		x++;
		timeToWalk--;
		decisionsCost.add(timeToWalk);
	}

	public void moveLeft() {
		decisions.add("move left");
		x--;
		timeToWalk--;
		decisionsCost.add(timeToWalk);
	}

	public void cutTree(Direction cutDirection, Tree tree) {
		if(tree.getTimeNeededToCut() < timeToWalk){
			Direction direction = cutDirection == Direction.NOT_IN_LINE ? Direction.UP : cutDirection; 
			
			timeToWalk -= tree.getTimeNeededToCut();
				
			decisions.add("cut " + direction.toString().toLowerCase());
			tree.cutTree();
			decisionsCost.add(timeToWalk);
			additionalInfo.add("---x=" + x + ", y=" + y + ", tree cut=" + tree.getId() + ", Direction=" + direction + "---");
		}else{
			//Tried to cut but didn't make it
			timeToWalk = 0;
		}
	}
	
	
}
