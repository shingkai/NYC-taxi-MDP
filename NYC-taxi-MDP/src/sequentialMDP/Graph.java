package sequentialMDP;

import java.util.*;

public class Graph {
	Map<String,Point> states;

	public Graph() {
		states = new HashMap<>();
	}

	public void addPoint(String longit, String latit) {
		Point newPoint = new Point(longit, latit);
		String key = longit.concat(latit);
		if (!states.containsKey(key))
			states.put(key, newPoint);
	}

	public void addEdge(String longit1, String latit1, String longit2,
			String latit2, int time, float dist, float fare) {
		Point src;
		Point dst;
		String key1 = longit1.concat(latit1);
		String key2 = longit2.concat(latit2);
		if (states.containsKey(key1)) {
			src = states.get(key1);
		} else {
			src = new Point(longit1, latit1);
		}
		if (states.containsKey(key2)) {
			dst = states.get(key2);
		} else {
			dst = new Point(longit2, latit2);
			states.put(key2, dst);
		}
		
		Edge edge = new Edge(src, dst, time, dist, fare);
		src.addEdge(edge);
		states.put(key1, src);
	}

	public class Point {
		String longit;
		String latit;
		Set<Edge> edges;

		public Point(String longit, String latit) {
			this.longit = longit;
			this.latit = latit;
		}
		
		public void addEdge(Edge edge) {
			edges.add(edge);
		}
		
		public Set<Edge> getEdges() {
			return this.edges;
		}
		
		@Override
		public int hashCode() {
			return this.longit.concat(this.latit).hashCode();
		}
		
		@Override
		public boolean equals(Object that) {
			return this.hashCode() == ((Point)that).hashCode();
		}
	}

	public class Edge {
		int time;
		float dist;
		float fare;
		Point src;
		Point dst;
		double reward;

		public Edge(Point src, Point dst, int time, float dist, float fare) {
			this.time = time;
			this.dist = dist;
			this.fare = fare;
			this.src = src;
			this.dst = dst;
			this.reward = fare / time;
		}
		
		public double getReward() {
			return this.reward;
		}
		public Point getDst() {
			return this.dst;
		}
	}
}
