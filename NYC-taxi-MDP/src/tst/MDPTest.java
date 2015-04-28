package tst;
import sequentialMDP.*;

import org.junit.*;
import org.junit.rules.*;
import org.apache.commons.cli.*;

public class MDPTest {
	
	
	@Test
	public void readerTest() {
		String file = "../taxi_10.csv";
		MDP mdp = new MDP(file);
		Graph graph = mdp.getGraph();
		graph.printStates();
	}
	
}
