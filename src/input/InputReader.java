package input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import lumberjack.Problem;
import lumberjack.model.Tree;

public class InputReader {
	List<String> lines;
	String firstLine;
	String[] splitted;
	int time;
	ArrayList<ArrayList<Integer>> net;
	List<Tree> trees;

	public Problem ReadProblemFromFile(String filePath){
		try {
			Path path = Paths.get(filePath);
			lines = Files.readAllLines(path);
			firstLine = lines.get(0);
			splitted = firstLine.split("\\s+");
			time = Integer.parseInt(splitted[0]);
			
			for (int i = 1; i < lines.size(); i++) {
				 String nextLine = lines.get(i);
				 String[] nextSplitted = nextLine.split("\\s+");
				 int x = Integer.parseInt(nextSplitted[0]);
				 int y = Integer.parseInt(nextSplitted[1]);
				 int heightH = Integer.parseInt(nextSplitted[2]);
				 int thicknessD = Integer.parseInt(nextSplitted[3]);
				 int weightC = Integer.parseInt(nextSplitted[4]);
				 int valueP = Integer.parseInt(nextSplitted[5]);
				 // tutaj do net trzeba wrzucic i-1 na pozycje x,y
				 Tree treeToAdd = new Tree(heightH, thicknessD, weightC, valueP);
				 trees.add(treeToAdd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Problem();
	}
}
