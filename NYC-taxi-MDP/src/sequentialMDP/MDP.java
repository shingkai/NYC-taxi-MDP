package sequentialMDP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MDP {

	private Graph graph;
	private HashMap<Graph.Point, Integer> value;
	private HashMap<Graph.Point, Integer> policy;

	public MDP(String file) {
		graph = new Graph();
		value = new HashMap<Graph.Point, Integer>();
		policy = new HashMap<Graph.Point, Integer>();
		reader(file);
	}

	/**
	 * Read taxi data from file and populate graph from data
	 * 
	 * @param file
	 */
	private void reader(String file) {
		BufferedReader br = null;
		try {
			String line;
			String[] parts;
			br = new BufferedReader(new FileReader(file));
			line = br.readLine(); // read and toss out the header line
			while ((line = br.readLine()) != null) {
				parts = line.split(",");
				graph.addEdge(parts[4], parts[5], parts[6], parts[7],
						Integer.parseInt(parts[2]), Float.parseFloat(parts[3]),
						Float.parseFloat(parts[8]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * Initialize V for all states to the number of start trips located
	 * at that state
	 */
	private void initialize() {
		for (Graph.Point p : graph.graph.keySet()) {
			value.put(p, graph.graph.get(p).size());
		}
	}

	public static void main(String[] args) {
		MDP mdp = new MDP(args[0]);
	}
}
