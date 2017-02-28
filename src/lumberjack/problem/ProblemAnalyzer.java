package lumberjack.problem;

import java.util.Arrays;

import lumberjack.model.Direction;
import lumberjack.model.Tree;

public class ProblemAnalyzer {

	private Problem p;

	public ProblemAnalyzer(Problem p) {
		this.p = p;
	}

	public void analyze() {
		/*
		 * This methods are fine but not needed for current solution. Commented
		 * out due to get some performance improvements
		 * countProfitabilityDividedByCutCost(); countProfitability();
		 * countDistances();
		 */
		countIfCanFallATree();
		countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree();
	}

	private void countProfitabilityDividedByCutCost() {
		for (Tree tree : p.trees) {
			p.profitabilityNetByCutCost[tree.getY()][tree.getX()] = (tree.getTreeValue()) / tree.getTimeNeededToCut();
		}
	}

	private void countProfitability() {
		for (Tree tree : p.trees) {
			p.profitabilityNet[tree.getY()][tree.getX()] = tree.getTreeValue();
		}
	}

	private void countDistances() {
		for (int i = 0; i < p.trees.size(); ++i) {
			for (int j = 0; j < p.trees.size(); ++j) {
				p.distancesBetweenTrees[i][j] = Math.abs(p.trees.get(i).getX() - p.trees.get(j).getX())
						+ Math.abs(p.trees.get(i).getY() - p.trees.get(j).getY());
			}
		}
	}

	private void countIfCanFallATree() {
		for (Tree i : p.trees) {
			for (Tree j : p.trees) {
				if (!i.equals(j) && checkIfICanFallOnJ(i, j)) {
					i.addTreeAbleToFall(j);
				}
			}
		}
	}

	private boolean checkIfICanFallOnJ(Tree i, Tree j) {
		return areNeighbors(i, j) && i.IsInLineAndRangeAndHeavier(j) != Direction.NOT_IN_LINE;
	}

	private boolean areNeighbors(Tree a, Tree b) {
		Direction dir = a.isInLine(b);
		if (dir != Direction.NOT_IN_LINE) {
			int x = a.getX();
			int y = a.getY();
			x += dir.increaseX();
			y += dir.increaseY();
			for (int i = 0; i < p.distancesBetweenTrees[a.getId()][b.getId()] - 1; ++i) {

				if (p.net[y][x] != -1)
					return false;
				x += dir.increaseX();
				y += dir.increaseY();
			}
			if (p.net[y][x] == b.getId())
				return true;
		} else {
			return false;
		}
		return false;
	}

	private void countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree() {
		// TODO - could be separated into smaller chunks
		for (Tree tree : p.trees) {
			int[] maxProfit = new int[4];
			Arrays.fill(maxProfit, tree.getTreeValue());

			int[] treesAbleToFall = tree.getTreesAbleToFall();

			for (int i = 0; i < treesAbleToFall.length; ++i) {
				int currentTreeId = treesAbleToFall[i];

				while (currentTreeId >= 0) {
					Tree currentTree = p.getTree(currentTreeId);
					maxProfit[i] += p.getTree(currentTreeId).getTreeValue();
					currentTreeId = currentTree.getTreesAbleToFall()[i];
				}
			}

			int biggestProfit = tree.getTreeValue();
			int direction = Direction.NOT_IN_LINE.ordinal();
			for (int i = 0; i < maxProfit.length; ++i) {
				if (maxProfit[i] > biggestProfit) {
					direction = i;
					biggestProfit = maxProfit[i];
				}
			}
			tree.setDirectionAndProfit(Direction.values()[direction], biggestProfit);
		}

	}
}
