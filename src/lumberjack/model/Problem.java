package lumberjack.model;

import java.util.ArrayList;

public class Problem {

	int[][] net;
	int[][] profitabilityNet;
	ArrayList<Tree> trees;
	int startTime;

	public Problem(int netSize, int startTime) {
		super();
		net = new int[netSize][];
		profitabilityNet = new int[netSize][];
		for (int i = 0; i < netSize; ++i) {
			net[i] = new int[netSize];
			profitabilityNet[i] = new int[netSize];
		}
		this.startTime = startTime;
		trees = new ArrayList<Tree>();
	}
	
	public void addTree(Tree tree){
		trees.add(tree);
		net[tree.getX()][tree.getY()] = tree.getId();
	}
	
	public void countProfitability(){
		for(Tree tree : trees){
			profitabilityNet[tree.getX()][tree.getY()] = tree.getTreeValue();
		}
	}

	@Override
	public String toString() {
		String info = "startTime=" + startTime;
		info += "\nnetSize=" + net.length;
		info += "\nNet:\n";
		info += printTrees(net);

		info += "\nProfitability Net:\n";
		info += printTrees(profitabilityNet);
		
		info += "\nTrees:\n";
		for (Tree tree : trees) {
			info += "\n" + tree;
		}

		return info;
	}
	
	private String printTrees(int[][] netOfTrees){
		String info = "";
		for (int i = 0; i < netOfTrees.length; ++i) {

			for (int j = 0; j < netOfTrees[i].length; ++j) {
				info += netOfTrees[i][j] + "\t";
			}
			info += "\n";
		}
		return info;
	}

}
