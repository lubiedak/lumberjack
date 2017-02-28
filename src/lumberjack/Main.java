package lumberjack;

import java.util.List;

import lumberjack.input.InputReader;
import lumberjack.problem.Problem;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InputReader ir = new InputReader();
		Problem p = ir.readProblemFromInputStream(System.in);
		p.analyze();
		List<String> decisions = p.solve();
		for(String dec : decisions){
			System.out.println(dec);
		}
		
		
	}

}
