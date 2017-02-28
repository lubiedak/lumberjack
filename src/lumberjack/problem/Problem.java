package lumberjack.problem;

import java.util.ArrayList;
import java.util.Arrays;

import lumberjack.model.Direction;
import lumberjack.model.LumberJack;
import lumberjack.model.Tree;

public class Problem {
	// public in package - this is my intention
	int[][] net;
	int[][] profitabilityNet;
	int[][] distancesBetweenTrees;
	int[][] profitabilityNetByCutCost;

	ArrayList<Tree> trees;

	private LumberJack lumberjack;

	private ProblemAnalyzer analyzer;

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
		ProblemAnalyzer analyzer = new ProblemAnalyzer(this);
		analyzer.analyze();
	}

	public ArrayList<String> solve() {
		while (lumberjack.getTimeToWalk() > 0) {
			Tree t = findClosestTree2();
			if (t.getId() == -1)
				t = findClosestTree();

			if (t.getId() != -1) {
				lumberjack.goToTree(t);
				Direction dir = t.getBestDirectionToFall();
				boolean treeCut = lumberjack.cutTree(dir, t);

				if (dir != Direction.NOT_IN_LINE && treeCut)
					runDominoEffect(t);
			} else {
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

	/*
	 * Couldnt find better name for 2nd naive approach
	 */
	private Tree findClosestTree2() {
		int x = lumberjack.getX();
		int y = lumberjack.getY();

		int minValuePerCost = 0;
		Tree closestTree = new Tree();
		for (Tree tree : trees) {
			if (!tree.isCut()) {
				int cost = Math.abs(x - tree.getX()) + Math.abs(y - tree.getY()) + tree.getThicknessD();
				if (cost <= lumberjack.getTimeToWalk()) {
					int valuePerCost = tree.getMaxProfit() / cost;
					if (valuePerCost > minValuePerCost) {
						closestTree = tree;
						minValuePerCost = valuePerCost;
					}
				}
			}
		}
		return closestTree;
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