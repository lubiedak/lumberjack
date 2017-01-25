package test;

import java.util.concurrent.ThreadLocalRandom;

public class TestCasesGenerator {

	String problem;
	int maxDim; //covers x, y, height
	int maxD; //thickness
	int maxC; //weight
	int maxP; //profit
	
	public TestCasesGenerator(int t, int n, int nOfTrees){
		problem = "" + t + " " + n + " "  + nOfTrees + "\n";
		maxDim = n-1;
		maxD = 10;
		maxC = 10;
		maxP = 20;
		generate(nOfTrees);
	}
	
	public String getProblem(){
		return problem;
	}
	
	private void generate(int nOfTrees){
		for(int i = 0; i < nOfTrees; ++i){
			problem += rand(0, maxDim) + " " + rand(0, maxDim) + " " + rand(1, maxDim) + " "
					 + rand(1, maxD) + " " + rand(1, maxC) + " " + rand(1, maxP) + "\n"; 
		}
	}
	
	private int rand(int min, int max){
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
