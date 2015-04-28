package tst;
import org.junit.Test;

import sequentialMDP.MDP;
import sequentialMDP.ValueIteration;

public class MDPTest {
	
	
//	@Test
//	public void readerTest() {
//		String file = "../taxi_10k.csv";
//		MDP mdp = new MDP(file);
//		Graph graph = mdp.getGraph();
//		graph.printStates();
//		graph.printEdges();
//	}
	
	@Test
	public void valueIterationTest() {
		int k = 25;
		double gamma = 0.9;
		String file = "../taxi_10k.csv";
		MDP mdp = new MDP(file);
		ValueIteration vi = new ValueIteration(mdp.getGraph(), k, gamma);
		vi.runAll();
		vi.printCurrentV();
	}
	
}
