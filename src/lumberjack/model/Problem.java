package lumberjack.model;

import java.util.ArrayList;

public class Problem {

	int[][] net;
	ArrayList<Tree> trees;
	int startTime;

	public Problem(int netSize, int startTime) {
		super();
		net = new int[netSize][];
		for (int i = 0; i < netSize; ++i) {
			net[i] = new int[netSize];
		}
		this.startTime = startTime;
		trees = new ArrayList<Tree>();
	}
	
	public void addTree(Tree tree){
		trees.add(tree);
		net[tree.getX()][tree.getY()] = tree.getId();
	}

	@Override
	public String toString() {
		String info = "startTime=" + startTime;
		info += "\nnetSize=" + net.length;
		info += "\nNet:\n";
		for (int i = 0; i < net.length; ++i) {

			for (int j = 0; j < net[i].length; ++j) {
				info += net[i][j] + "\t";
			}
			info += "\n";
		}

		info += "\nTrees:\n";
		for (Tree tree : trees) {
			info += "\n" + tree;
		}

		return info;
	}

}
