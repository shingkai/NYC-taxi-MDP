package sequentialMDP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
			String pickupLongit;
			String pickupLatit;
			String dropoffLongit;
			String dropoffLatit;
			int tripTime;
			float tripDist;
			float tripFare;
			while ((line = br.readLine()) != null) {
				parts = line.split(",");
				tripTime = Integer.parseInt(parts[2]);
				tripDist = Float.parseFloat(parts[3]);
				pickupLongit = parts[4].substring(0,7);
				pickupLatit = parts[5].substring(0,6);
				dropoffLongit = parts[6].substring(0,7);
				dropoffLatit = parts[7].substring(0,6);
				tripFare = Float.parseFloat(parts[8]);
				graph.addEdge(pickupLongit, pickupLatit, dropoffLongit,
						dropoffLatit, tripTime, tripDist, tripFare);
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
		double gamma = 0.9;
		int k = 25;
		MDP mdp = new MDP(args[0]);
	}
}
