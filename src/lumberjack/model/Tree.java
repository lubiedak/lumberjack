package lumberjack.model;

import java.util.Arrays;

public class Tree {

	private final int id;

	private final int heightH;
	private final int thicknessD;
	private final int unitWeightC;
	private final int unitValueP;
	private final int x;
	private final int y;

	private boolean cut;
	private int[] treesAbleToFall = { -1, -1, -1, -1 };
	private Direction bestDirectionToFall;
	private int maxProfit;

	
	
	
	public Tree(int id, int heightH, int thicknessD, int weightC, int valueP, int x, int y) {
		super();
		this.id = id;
		this.heightH = heightH;
		this.thicknessD = thicknessD;
		this.unitWeightC = weightC;
		this.unitValueP = valueP;
		this.x = x;
		this.y = y;
		this.cut = false; // up, right, down, left
	}

	public Tree() {
		super();
		this.id = -1;
		this.heightH = -1;
		this.thicknessD = -1;
		this.unitWeightC = -1;
		this.unitValueP = -1;
		this.x = -1;
		this.y = -1;
		this.cut = false; // up, right, down, left
	}
	
	@Override
	public String toString() {
		return "Tree [id=" + id + ", heightH=" + heightH + ", thicknessD=" + thicknessD + ", unitWeightC=" + unitWeightC
				+ ", unitValueP=" + unitValueP + ", x=" + x + ", y=" + y + ", cut=" + cut + ", bestDirectionToFall="
				+ bestDirectionToFall + ", maxProfit=" + maxProfit + ", Value=" + getTreeValue() + ", NeighborsInRange = "
				+ Arrays.toString(treesAbleToFall) + "]";

	}

	public int getId() {
		return id;
	}

	public int getHeightH() {
		return heightH;
	}

	public int getThicknessD() {
		return thicknessD;
	}

	public int getUnitWeightC() {
		return unitWeightC;
	}

	public int getUnitValueP() {
		return unitValueP;
	}

	public int getTreeValue() {
		return unitValueP * heightH * thicknessD;
	}

	public int getTreeWeight() {
		return unitWeightC * heightH * thicknessD;
	}

	public int getTimeNeededToCut() {
		return thicknessD;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int cutTree() {
		cut = true;
		return getTreeValue();
	}

	public boolean isCut() {
		return cut;
	}

	public void addTreeAbleToFall(Direction dir, Tree tree) {
		treesAbleToFall[dir.ordinal()] = tree.getId();
	}

	public void setDirectionAndProfit(Direction direction, int profit) {
		bestDirectionToFall = direction;
		maxProfit = profit;
	}

	public Direction getBestDirectionToFall() {
		return bestDirectionToFall;
	}

	public Direction IsInLineAndRangeAndHeavier(Tree tree) {
		Direction dir = isInLine(tree);
		if(getTreeWeight() > tree.getTreeWeight()){
			return dir;
		}
		return Direction.NOT_IN_LINE;
	}

	public Direction isInLine(Tree tree){
		if (x == tree.getX()) {
			int distance = y - tree.getY();
			if (Math.abs(distance) <= heightH)
				return (distance > 0) ? Direction.DOWN : Direction.UP;
		}
		if (y == tree.getY()) {
			int distance = x - tree.getX();
			if (Math.abs(distance) <= heightH)
				return (distance > 0) ? Direction.LEFT : Direction.RIGHT;
		}
		return Direction.NOT_IN_LINE;
	}
	
	public int[] getTreesAbleToFall() {
		return treesAbleToFall;
	}
	
	

}
