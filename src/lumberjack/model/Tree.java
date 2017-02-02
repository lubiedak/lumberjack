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
	private int bestDirectionToFall;
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

	public void cutTree() {
		cut = true;
	}

	public boolean isCut() {
		return cut;
	}

	public void addTreeAbleToFall(Direction dir, Tree tree) {
		treesAbleToFall[dir.ordinal()] = tree.getId();
	}

	public void setDirectionAndProfit(int direction, int profit) {
		bestDirectionToFall = direction;
		maxProfit = profit;
	}

	public Direction isInLineAndRangeAndHeavier(Tree tree) {
		if (x == tree.getX()) {
			int distance = y - tree.getY();
			if (Math.abs(distance) <= heightH && getTreeWeight() > tree.getTreeWeight())
				return (distance > 0) ? Direction.DOWN : Direction.UP;
		}
		if (y == tree.getY()) {
			int distance = x - tree.getX();
			if (Math.abs(distance) <= heightH && getTreeWeight() > tree.getTreeWeight())
				return (distance > 0) ? Direction.LEFT : Direction.RIGHT;
		}

		return Direction.NOT_IN_LINE;
	}

	public int[] getTreesAbleToFall() {
		return treesAbleToFall;
	}

}
