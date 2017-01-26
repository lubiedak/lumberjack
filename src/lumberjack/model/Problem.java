package lumberjack.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem {

	int[][] net;
	int[][] profitabilityNet;
	int[][] distancesBetweenTrees;
	int[][] profitabilityNetByCutCost;

	ArrayList<Tree> trees;

	LumberJack lumberjack;

	public Problem(int startTime, int netSize, int nOfTrees) {
		super();
		initNets(netSize);

		trees = new ArrayList<Tree>();
		initGraphs(nOfTrees);
		lumberjack = new LumberJack(startTime);
	}

	public void addTree(Tree tree) {
		trees.add(tree);
		net[tree.getY()][tree.getX()] = tree.getId();
	}

	public Tree getTree(int id) {
		return trees.stream().filter(t -> t.getId() == id).findFirst().get();
	}

	public void analyze() {
		//countDistances();
		//countProfitability();
		//countProfitabilityDividedByCutCost();
		countIfCanFallATree();
		countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree();
	}

	public ArrayList<String> solve() {
		while (lumberjack.getTimeToWalk() > 0) {
			Tree t = findClosestTree();
			//Tree t = findClosestTree2();
			if (t.getId() != -1) {
				lumberjack.goToTree(t);
				Direction dir = t.getBestDirectionToFall();
				boolean treeCut = lumberjack.cutTree(dir, t);
				if (dir != Direction.NOT_IN_LINE && treeCut)
					runDominoEffect(t);
				countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree();
			}else{
				lumberjack.finishTrip();
			}
		}
		return lumberjack.getDecisions();
	}

	private void runDominoEffect(Tree tree) {
		Direction dir = tree.getBestDirectionToFall();
		int id = tree.getIdOfNeighbourInThisDirection(dir);
		while (id != -1) {
			Tree t = trees.get(id);
			t.cutTree();
			id = t.getIdOfNeighbourInThisDirection(dir);
		}
	}

	private Tree findClosestTree() {
		int x = lumberjack.getX();
		int y = lumberjack.getY();

		int minDistance = 9999;
		Tree closestTree = new Tree();
		for (Tree tree : trees) {
			int distance = Math.abs(x - tree.getX()) + Math.abs(y - tree.getY());
			if (distance < minDistance && !tree.isCut()) {
				closestTree = tree;
				minDistance = distance;
			}
		}
		return closestTree;
	}
	
	private Tree findClosestTree2() {
		int x = lumberjack.getX();
		int y = lumberjack.getY();

		int minValuePerCost = 0;
		Tree closestTree = new Tree();
		for (Tree tree : trees) {
			int cost = Math.abs(x - tree.getX()) + Math.abs(y - tree.getY()) + tree.getThicknessD();
			if (cost < lumberjack.getTimeToWalk()) {
				int valuePerCost = tree.getMaxProfit() / cost;
				if (valuePerCost > minValuePerCost && !tree.isCut()) {
					closestTree = tree;
					minValuePerCost = valuePerCost;
				}
			}
		}
		return closestTree;
	}

	private void countProfitability() {
		for (Tree tree : trees) {
			profitabilityNet[tree.getY()][tree.getX()] = tree.getTreeValue();
		}
	}

	private void countDistances() {
		for (int i = 0; i < trees.size(); ++i) {
			for (int j = 0; j < trees.size(); ++j) {
				distancesBetweenTrees[i][j] = Math.abs(trees.get(i).getX() - trees.get(j).getX())
						+ Math.abs(trees.get(i).getY() - trees.get(j).getY());
			}
		}
	}

	private void countProfitabilityDividedByCutCost() {
		for (Tree tree : trees) {
			profitabilityNetByCutCost[tree.getY()][tree.getX()] = (tree.getTreeValue()) / tree.getTimeNeededToCut();
		}
	}

	private void countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree() {
		// TODO - could be separated into smaller chunks
		for (Tree tree : trees) {
			if(!tree.isCut()){
			int[] maxProfit = new int[4];
			Arrays.fill(maxProfit, tree.getTreeValue());

			int[] treesAbleToFall = tree.getTreesAbleToFall();

			for (int i = 0; i < treesAbleToFall.length; ++i) {
				int currentTreeId = treesAbleToFall[i];

				while (currentTreeId >= 0) {
					Tree currentTree = getTree(currentTreeId);
					maxProfit[i] += getTree(currentTreeId).getTreeValue();
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
		}}
	}

	private void countIfCanFallATree() {
		for (Tree i : trees) {
			for (Tree j : trees) {
				if (!i.equals(j)) {
					if (checkIfICanFallOnJ(i, j))
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
			for (int i = 0; i < distancesBetweenTrees[a.getId()][b.getId()] - 1; ++i) {

				if (net[y][x] != -1)
					return false;
				x += dir.increaseX();
				y += dir.increaseY();
			}
			if (net[y][x] == b.getId())
				return true;
		} else {
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		String info = "Lumberjack=" + lumberjack;
		info += "\nnetSize=" + net.length;
		info += "\nNet:\n";
		info += printMatrixReversed(net);

		info += "\nProfitability Net:\n";
		info += printMatrixReversed(profitabilityNet);

		info += "\nDistances:\n";
		info += printMatrix(distancesBetweenTrees);

		info += "\nProfitabilityNetByCutCost:\n";
		info += printMatrixReversed(profitabilityNetByCutCost);

		info += "\nTrees:\n";
		for (Tree tree : trees) {
			info += "\n" + tree;
		}

		return info;
	}

	private String printMatrix(int[][] matrix) {
		String info = "";
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[i].length; ++j) {

				info += (matrix[i][j] >= 0 ? matrix[i][j] : ".") + "\t";
			}
			info += "\n";
		}
		return info;
	}

	private String printMatrixReversed(int[][] matrix) {
		String info = printMatrix(matrix);
		String[] reversedInfo = info.split("\\r?\\n");
		info = "";
		for (int i = reversedInfo.length - 1; i >= 0; --i) {
			info += reversedInfo[i] + "\n";
		}
		return info;
	}

	private void initNets(int netSize) {
		net = new int[netSize][];
		profitabilityNet = new int[netSize][];
		profitabilityNetByCutCost = new int[netSize][];
		for (int i = 0; i < netSize; ++i) {

			net[i] = new int[netSize];
			Arrays.fill(net[i], -1);
			profitabilityNet[i] = new int[netSize];
			profitabilityNetByCutCost[i] = new int[netSize];
		}
	}

	private void initGraphs(int nOfTrees) {
		distancesBetweenTrees = new int[nOfTrees][];
		for (int i = 0; i < nOfTrees; ++i) {
			distancesBetweenTrees[i] = new int[nOfTrees];
		}
	}

}
