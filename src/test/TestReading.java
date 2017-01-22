package test;

import org.junit.Test;

import lumberjack.input.InputReader;
import lumberjack.model.Problem;

public class TestReading {

	@Test
	public void test() {

		String input = 	 "11 10 5\n"
						+"2 3 4 5 2 2\n"
						+"2 6 3 1 1 3\n"
						+"2 7 2 2 2 4\n"
						+"5 5 10 3 1 5\n"
						+"4 3 5 5 2 6\n";

		InputReader ir = new InputReader();
		Problem p = ir.ReadProblemFromString(input);
		p.analyze();
		p.solve();
		System.out.println(p);

	}
	
	@Test
	public void testTreesInRangeLineAndHeavier() {

		//TODO
		// Create small forest 5 trees in shape of +
		// make middle one the heaviest, check if can fall on all surrounding trees after problem solved.

	}

}
