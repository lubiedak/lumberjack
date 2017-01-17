package lumberjack.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import lumberjack.model.Problem;
import lumberjack.model.Tree;

public class InputReader {

	Problem problem;

	public Problem ReadProblemFromInput(List<String> input) {

		String firstLine = input.get(0);
		Scanner scanner = new Scanner(firstLine);
		int time = scanner.nextInt();
		int netSize = scanner.nextInt();
		int nOfTrees = scanner.nextInt();
		problem = new Problem(time, netSize, nOfTrees);
		
		

		for (int i = 1; i < input.size(); i++) {
			String nextLine = input.get(i);
			scanner = new Scanner(nextLine);

			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int heightH = scanner.nextInt();
			int thicknessD = scanner.nextInt();
			int weightC = scanner.nextInt();
			int valueP = scanner.nextInt();

			Tree tree = new Tree(i-1, heightH, thicknessD, weightC, valueP, x, y);
			problem.addTree(tree);
		}
		scanner.close();
		return problem;
	}

	public Problem ReadProblemFromFile(String filePath) {
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
