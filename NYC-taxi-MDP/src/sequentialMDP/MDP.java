package sequentialMDP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
			float pickupLongit;
			float pickupLatit;
			float dropoffLongit;
			float dropoffLatit;
			int tripTime;
			float tripDist;
			float tripFare;
			
			String pLongit = "";
			String pLatit = "";
			String dLongit = "";
			String dLatit = "";
			
			while ((line = br.readLine()) != null) {
				parts = line.split(",");
				tripTime = Integer.parseInt(parts[2]);
				tripDist = Float.parseFloat(parts[3]);
				tripFare = Float.parseFloat(parts[8]);
				
				pickupLongit = Float.parseFloat(parts[4]);
				pickupLatit = Float.parseFloat(parts[5]);
				dropoffLongit = Float.parseFloat(parts[6]);
				dropoffLatit = Float.parseFloat(parts[7]);
				
				// skip lines with faulty data
				if (pickupLongit > -72 || pickupLongit < -76)
					continue;
				if (pickupLatit > 42 || pickupLatit < 38)
					continue;
				if (dropoffLongit > -72 || dropoffLongit < -76)
					continue;
				if (dropoffLatit > 42 || dropoffLatit < 38)
					continue;
				if (tripTime == 0)
					continue;
				
				pLongit = String.format("%.3f", pickupLongit).substring(0, 7);
				pLatit = String.format("%.3f", pickupLatit).substring(0,6);
				dLongit = String.format("%.3f", dropoffLongit).substring(0,7);
				dLatit = String.format("%.3f", dropoffLatit).substring(0,6);
				
				graph.addEdge(pLongit, pLatit, dLongit,
						dLatit, tripTime, tripDist, tripFare);
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
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public static void main(String[] args) {
		double gamma = 0.9;
		int k = 25;
		MDP mdp = new MDP(args[0]);
		ValueIteration vi = new ValueIteration(mdp.getGraph(), k, gamma);
	}
}
