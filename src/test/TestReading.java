package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import lumberjack.input.InputReader;
import lumberjack.model.Problem;

public class TestReading {

	@Test
	public void test() {

		List<String> input = new ArrayList<String>();

		input.add("11 10 5");
		input.add("2 3 4 5 2 2");
		input.add("2 6 3 1 1 3");
		input.add("2 7 2 2 2 4");
		input.add("5 5 10 3 1 5");
		input.add("4 3 5 5 2 6");

		InputReader ir = new InputReader();
		Problem p = ir.ReadProblemFromInput(input);
		p.analyze();
		p.solve();
		System.out.println(p);

		// add asserts

	}

}
