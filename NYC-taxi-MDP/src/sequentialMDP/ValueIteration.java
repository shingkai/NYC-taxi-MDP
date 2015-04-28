package sequentialMDP;

import java.util.HashMap;

public class ValueIteration {
	
	
	private Graph graph;
	private HashMap<Graph.Point, Double> currentV;
	private HashMap<Graph.Point, Double> prevV;
	private int numIter;
	private double gamma;
	private int k;
	
	public ValueIteration(Graph graph, int numIter, double gamma) {
		this.graph = graph;
		this.numIter = numIter;
		this.gamma = gamma;
		initialize();	
		k = 2;
	}
	
	
	/**
	 * Initialize V for all states to the number of start trips located
	 * at that state
	 */
	private void initialize() {
		currentV = new HashMap<Graph.Point, Double>();
		for (String coordKey : graph.states.keySet()) {
			Graph.Point p = graph.states.get(coordKey);
			currentV.put(p, 1.0*p.edges.size());
		}
	}
	
	/**
	 * Run one iteration of the Value Iteration algorithm
	 */
	public void singleIteration() {
		prevV.clear();
		prevV = currentV;
		currentV = new HashMap<>();
		for (String coordKey : graph.states.keySet()) {
			Graph.Point p = graph.states.get(coordKey);
			double sum = 0.0;
			for (Graph.Edge e : p.edges) {
				double v = 1/p.edges.size();
				v = v*(e.reward + gamma*prevV.get(e.dst));
				sum += v;
			}
			currentV.put(p, sum);
		}
		k++;
	}
	
	/**
	 * Run iterations of the algorithm until the horizon is reached
	 */
	public void runAll() {
		while (k < numIter) {
			singleIteration();
		}
	}
	
	public HashMap<Graph.Point, Double> getCurrentV() {
		return currentV;
	}
	
	public void printCurrentV() {
		for (Graph.Point point : this.currentV.keySet()) {
			double value = this.currentV.get(point);
			System.out.print(value + ",");
			point.print();
		}
	}
}
