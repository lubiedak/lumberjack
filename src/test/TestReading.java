package test;

import org.junit.Assert;
import org.junit.Test;

import lumberjack.input.InputReader;
import lumberjack.model.Problem;
import lumberjack.model.Tree;

public class TestReading {

	//@Test
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
	
		String input = 	 "11 3 5\n"
				+"1 1 2 2 2 2\n"
				+"0 1 1 1 1 1\n"
				+"1 0 1 1 1 1\n"
				+"2 1 1 1 1 1\n"
				+"1 2 1 1 1 1\n";
		
		InputReader ir = new InputReader();
		Problem p = ir.ReadProblemFromString(input);
		p.analyze();
				
		p.solve();
		System.out.println(p);
		
		int[] treesAbleToFall = p.getTree(0).getTreesAbleToFall();
		int[] expectedTreesAbleToFall = {4, 3, 2, 1};
		
		Assert.assertArrayEquals(expectedTreesAbleToFall, treesAbleToFall);
	}

}
