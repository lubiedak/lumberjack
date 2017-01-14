package lumberjack.model;

import java.util.ArrayList;

public class Problem {

	int[][] net;
	int[][] profitabilityNet;
	int[][] distancesBetweenTrees;
	
	ArrayList<Tree> trees;
	int startTime;

	public Problem(int startTime, int netSize, int nOfTrees) {
		super();
		initNets(netSize);
		
		this.startTime = startTime;
		trees = new ArrayList<Tree>();
		initGraphs(nOfTrees);
	}
	
	public void addTree(Tree tree){
		trees.add(tree);
		net[tree.getX()][tree.getY()] = tree.getId();
	}
	
	public void analyze(){
		countDistances();
		countProfitability();
	}
	
	public void countProfitability(){
		for(Tree tree : trees){
			profitabilityNet[tree.getX()][tree.getY()] = tree.getTreeValue();
		}
	}
	
	
	public void countDistances(){
		for(int i = 0; i < trees.size(); ++i){
			for(int j = 0; j < trees.size(); ++j){
				distancesBetweenTrees[i][j] = Math.abs(trees.get(i).getX() - trees.get(j).getX())
											+ Math.abs(trees.get(i).getY() - trees.get(j).getY()); 
			}
		}
	}
	
	public void countProfitabilityDividedByCutCost(){
		//TODO
		// z nazwy funkci wynika co trzeba zrobić, przyda sie dwuwymiarowa tablica floatow
	}
	
	public void countOptimalProfitabilityWhenTreeIsCuttedAndFallsOnDifferentTree(){
		//TODO
		//tablica floatow - w ktora strone najlepiej sciac drzewo, zeby obaliło inne i tymczasem mamy 2 drzewa sciete, wiekszy profit
		//Ciut trudniejsze ale dasz rade
		
	}
	

	@Override
	public String toString() {
		String info = "startTime=" + startTime;
		info += "\nnetSize=" + net.length;
		info += "\nNet:\n";
		info += printMatrix(net);

		info += "\nProfitability Net:\n";
		info += printMatrix(profitabilityNet);
		
		info += "\nDistances:\n";
		info += printMatrix(distancesBetweenTrees);
		
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
				info += matrix[i][j] + "\t";
			}
			info += "\n";
		}
		return info;
	}
	
	private void initNets(int netSize){
		net = new int[netSize][];
		profitabilityNet = new int[netSize][];
		for (int i = 0; i < netSize; ++i) {
			net[i] = new int[netSize];
			profitabilityNet[i] = new int[netSize];
		}
	}
	
	private void initGraphs(int nOfTrees){
		distancesBetweenTrees = new int[nOfTrees][];
		for (int i = 0; i < nOfTrees; ++i) {
			distancesBetweenTrees[i] = new int[nOfTrees];
		}
	}

}
