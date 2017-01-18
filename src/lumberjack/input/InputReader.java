package lumberjack.input;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lumberjack.model.Problem;
import lumberjack.model.Tree;

public class InputReader {

	Problem problem;

	
	public Problem ReadProblemFromString(String input){
		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		return ReadProblemFromInputStream(stream);
	}

	public Problem ReadProblemFromInputStream(InputStream stream) {
		Scanner s = new Scanner(stream);
		int time = s.nextInt();
		int netSize = s.nextInt();
		int nOfTrees = s.nextInt();
		s.nextLine();
		problem = new Problem(time, netSize, nOfTrees);
		
		List<String> input = new ArrayList<String>();
		
		while(s.hasNextLine() && nOfTrees > 0){
			input.add(s.nextLine());
			nOfTrees--;
		}
		
		
		problem = ReadProblemFromInput(input);
		s.close();
		return problem;
	}
	
	

	private Problem ReadProblemFromInput(List<String> input) {
		for (int i = 0; i < input.size(); i++) {
			String nextLine = input.get(i);
			Scanner scanner = new Scanner(nextLine);

			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int heightH = scanner.nextInt();
			int thicknessD = scanner.nextInt();
			int weightC = scanner.nextInt();
			int valueP = scanner.nextInt();

			Tree tree = new Tree(i, heightH, thicknessD, weightC, valueP, x, y);
			scanner.close();
			problem.addTree(tree);
		}
		
		return problem;
	}
}
