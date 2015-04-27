package sequentialMDP;

import java.io.*;

public class MDP {

	private Graph graph;

	public MDP(String file) {
		graph = new Graph();
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

	public static void main(String[] args) {
		MDP mdp = new MDP(args[0]);
	}
}
