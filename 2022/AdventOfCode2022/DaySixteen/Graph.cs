//using Microsoft.CodeAnalysis.CSharp.Syntax;
//using System.Collections.Generic;

//namespace AdventOfCode2022.DaySixteen;
//// Define the Graph class
//public class Graph
//{
//    public List<Vertex> vertices;
//    public List<Edge> edges;

//    public Graph()
//    {
//        vertices = new List<Vertex>();
//        edges = new List<Edge>();
//    }

//    public void AddVertices(List<Valve> valves)
//    {
//        // Add the valves to the graph as vertices
//        foreach (Valve valve in valves)
//        {
//            Vertex vertex = new Vertex(valve);
//            vertices.Add(vertex);
//        }
//    }

//    public void AddEdges(List<Valve> valves)
//    {
//        // Add the edges to the graph
//        foreach (Vertex vertex in vertices)
//        {
//            // Find the corresponding valve
//            Valve valve = vertex.valve;

//            // Iterate through the list of connected valves
//            foreach (string connectedValveName in valve.connectedValves)
//            {
//                // Find the Valve corresponding to the connected Valve string
//                var connectedValve = valves.Find(x => x.name == connectedValveName);

//                // Find the vertex corresponding to the connected valve
//                Vertex connectedVertex = vertices.Find(v => v.valve.id == connectedValve.id);

//                // Create an edge between the current vertex and the connected vertex, with the time to traverse equal to the time to open the connected valve
//                Edge edge = new Edge(vertex, connectedVertex, connectedValve.flowRate, connectedValve.timeToOpen);
//                edges.Add(edge);
//            }
//        }
//    }

//    // Define the Vertex class
//    public class Vertex
//    {
//        public Valve valve;
//        public int distance;
//        public int timeToReach;

//        public Vertex(Valve valve)
//        {
//            this.valve = valve;
//        }
//    }

//    // Define the Edge class
//    public class Edge
//    {
//        public Vertex source;
//        public Vertex destination;
//        public int flowRate;
//        public int timeToTraverse;

//        public Edge(Vertex source, Vertex destination, int flowRate, int timeToTraverse)
//        {
//            this.source = source;
//            this.destination = destination;
//            this.flowRate = flowRate;
//            this.timeToTraverse = timeToTraverse;
//        }
//    }
//}
