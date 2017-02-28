package lumberjack.input;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lumberjack.model.Tree;
import lumberjack.problem.Problem;

public class InputReader {

	private Problem problem;

	
	public Problem readProblemFromString(String input){
		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		return readProblemFromInputStream(stream);
	}

	public Problem readProblemFromInputStream(InputStream stream) {
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
		
		
		problem = readProblemFromInput(input);
		s.close();
		return problem;
	}
	
	

	private Problem readProblemFromInput(List<String> input) {
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
