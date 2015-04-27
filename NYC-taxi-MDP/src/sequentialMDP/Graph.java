package sequentialMDP;

import java.util.*;


public class Graph {
	Map<Point,Set<Edge>> graph;

	public Graph() {
		graph = new HashMap<>();
	}

	public void addPoint(String longit, String latit) {
		Point newPoint = new Point(longit, latit);
		if (!graph.containsKey(newPoint)) {
			Set<Edge> newEdges = new HashSet<Edge>();
			graph.put(newPoint, newEdges);		
		}
	}

	public void addEdge(String longit1, String latit1, String longit2,
			String latit2, int time, float dist, float fare) {
		Point src = new Point(longit1, latit1, PointType.START);
		Point dst = new Point(longit2, latit2, PointType.STOP);
		Edge edge = new Edge(src, dst, time, dist, fare);
		if (!graph.containsKey(src)) {
			Set<Edge> edges = new HashSet<Edge>();
			edges.add(edge);
			graph.put(src, edges);		
		} else {
			Set<Edge> edges = graph.get(src);
			edges.add(edge);
			graph.put(src, edges);
		}
	}

	public class Point {
		String longit;
		String latit;
		Set<Edge> transitions;
		PointType type;

		public Point(String longit, String latit) {
			this.longit = longit;
			this.latit = latit;
		}

		public Point(String longit, String latit, PointType type) {
			this.longit = longit;
			this.latit = latit;
			this.type = type;
		}
	}

	public class Edge {
		int time;
		float dist;
		float fare;
		Point src;
		Point dst;

		public Edge(Point src, Point dst, int time, float dist, float fare) {
			this.time = time;
			this.fare = fare;
			this.src = src;
			this.dst = dst;
		}
	}

	public enum PointType {
		START, STOP;
	}
}
