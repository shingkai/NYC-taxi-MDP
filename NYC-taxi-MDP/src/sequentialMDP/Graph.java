package sequentialMDP;

import java.util.*;

public class Graph {
	Set<Point> states;

	public Graph() {
		states = new HashSet<>();
	}

	public void addPoint(String longit, String latit) {
		Point newPoint = new Point(longit, latit);
		states.add(newPoint);
	}

	public void addEdge(String longit1, String latit1, String longit2,
			String latit2, int time, float dist, float fare) {
		Point src = new Point(longit1, latit1);
		Point dst = new Point(longit2, latit2);
		Edge edge = new Edge(src, dst, time, dist, fare);
		src.addEdge(edge);
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
