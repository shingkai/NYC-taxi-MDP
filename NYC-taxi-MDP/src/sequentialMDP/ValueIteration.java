package sequentialMDP;

import java.util.HashMap;

public class ValueIteration {
	
	
	private Graph graph;
	private HashMap<Graph.Point, Integer> currentV;
	private HashMap<Graph.Point, Integer> prevV;
	
	/**
	 * Initialize V for all states to the number of start trips located
	 * at that state
	 */
	private void initialize() {
		for (Graph.Point p : graph.graph.keySet()) {
			currentV.put(p, graph.graph.get(p).size());
		}
	}



}
