package lumberjack.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import lumberjack.model.Problem;
import lumberjack.model.Tree;

public class InputReader {

	Problem problem;
	
	
	public Problem ReadProblemFromInput(List<String> input){
		
		String firstLine = input.get(0);
		String[] splitted = firstLine.split("\\s+");
		
		int time = Integer.parseInt(splitted[0]);
		int netSize = Integer.parseInt(splitted[1]);
		int[][] net = new int[netSize][netSize];
		problem = new Problem(netSize, time);
		
		for (int i = 1; i < input.size(); i++) {
			 String nextLine = input.get(i);
			 String[] nextSplitted = nextLine.split("\\s+");
			 int x = Integer.parseInt(nextSplitted[0]);
			 int y = Integer.parseInt(nextSplitted[1]);
			 int heightH = Integer.parseInt(nextSplitted[2]);
			 int thicknessD = Integer.parseInt(nextSplitted[3]);
			 int weightC = Integer.parseInt(nextSplitted[4]);
			 int valueP = Integer.parseInt(nextSplitted[5]);
			 net[x][y] = i;
			 Tree tree = new Tree(i, heightH, thicknessD, weightC, valueP, x, y);
			 problem.addTree(tree);
		}
		return problem;
	}
	
	public Problem ReadProblemFromFile(String filePath){
		try {
			Path path = Paths.get(filePath);
			List<String> lines = Files.readAllLines(path);
			problem = ReadProblemFromInput(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return problem;
	}
}
