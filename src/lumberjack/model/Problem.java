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
	
	public void addTree(Tree tree){
		trees.add(tree);
		net[tree.getY()][tree.getX()] = tree.getId();
	}
	
	public Tree getTree(int id){
		return trees.stream().filter(t -> t.getId() == id).findFirst().get();
	}
	
	public void analyze(){
		countDistances();
		countProfitability();
		countProfitabilityDividedByCutCost();
		countIfCanFallATree();
		countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree();
	}
	
	public void solve(){
		lumberjack.goToTree(trees.get(0));
		lumberjack.cutTree("Cut up", trees.get(0));
		lumberjack.moveRight();
	}
	
	private void countProfitability(){
		for(Tree tree : trees){
			profitabilityNet[tree.getY()][tree.getX()] = tree.getTreeValue();
		}
	}
	
	
	private void countDistances(){
		for(int i = 0; i < trees.size(); ++i){
			for(int j = 0; j < trees.size(); ++j){
				distancesBetweenTrees[i][j] = Math.abs(trees.get(i).getX() - trees.get(j).getX())
											+ Math.abs(trees.get(i).getY() - trees.get(j).getY()); 
			}
		}
	}
	
	private void countProfitabilityDividedByCutCost(){
		for(Tree tree : trees){
			profitabilityNetByCutCost[tree.getY()][tree.getX()] = (tree.getTreeValue()) / tree.getTimeNeededToCut();
		}
	}
	
	private void countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree(){
		//TODO - could be separated into smaller chunks
		for(Tree tree : trees){
			int[] maxProfit = new int[4];
			Arrays.fill(maxProfit, tree.getTreeValue());
			
			int[] treesAbleToFall = tree.getTreesAbleToFall();
			
			for(int i = 0; i < treesAbleToFall.length ; ++i){
				int currentTreeId = treesAbleToFall[i];
				
				while(currentTreeId >= 0){
					Tree currentTree = getTree(currentTreeId);
					maxProfit[i] += getTree(currentTreeId).getTreeValue();
					currentTreeId = currentTree.getTreesAbleToFall()[i];
				}
			}
			
			int biggestProfit = tree.getTreeValue();
			int direction = Direction.NOT_IN_LINE.ordinal();
			for(int i = 0; i < maxProfit.length; ++i){
				if(maxProfit[i] > biggestProfit){
					direction = i;
					biggestProfit = maxProfit[i];
				}
			}
			tree.setDirectionAndProfit(direction, biggestProfit);
		}
		
	}
	
	private void countIfCanFallATree(){
		for(Tree i : trees){
			for(Tree j : trees){
				if(!i.equals(j)){
					Direction dir = i.IsInLineAndRangeAndHeavier(j);
					if(dir != Direction.NOT_IN_LINE)
						i.addTreeAbleToFall(dir, j);
				}
			}
		}
	}
	

	@Override
	public String toString() {
		String info = "Lumberjack=" + lumberjack;
		info += "\nnetSize=" + net.length;
		info += "\nNet:\n";
		info += printMatrix(net);

		info += "\nProfitability Net:\n";
		info += printMatrix(profitabilityNet);
		
		info += "\nDistances:\n";
		info += printMatrix(distancesBetweenTrees);
		
		info += "\nProfitabilityNetByCutCost:\n";
		info += printMatrix(profitabilityNetByCutCost);
				
		info += "\nTrees:\n";
		for (Tree tree : trees) {
			info += "\n" + tree;
		}

		return info;
	}
	
	private String printMatrix(int[][] matrix){
		String info = "";
		for (int i = 0; i < matrix.length; ++i) {

			for (int j = 0; j < matrix[i].length; ++j) {
				
				info += (matrix[i][j] >= 0 ? matrix[i][j] : ".") + "\t";
			}
			info += "\n";
		}
		return info;
	}

	
	private void initNets(int netSize){
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
	
	private void initGraphs(int nOfTrees){
		distancesBetweenTrees = new int[nOfTrees][];
		for (int i = 0; i < nOfTrees; ++i) {
			distancesBetweenTrees[i] = new int[nOfTrees];
		}
	}

}
