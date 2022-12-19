using AdventOfCode2022.DaySixteen;
using Microsoft.Diagnostics.Tracing.Parsers.Kernel;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022.DaySixteen;

public class TestingClass
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DaySixteen/DaySixteenTest.txt");

    // public static void Day16()
    // {
    //     //Console.WriteLine($"Part 1: {PartOne()}");
    //     PartOne();
    // }

    // public static void PartOne(string[]? input = null)
    // {
    //     input ??= Input;
    //
    //     // Parse the input and create the valve network
    //     ValveNetwork network = new ValveNetwork();
    //     network.Valves["AA"] = new Valve("AA", 0, new List<string> { "DD", "II", "BB" });
    //     network.Valves["BB"] = new Valve("BB", 13, new List<string> { "CC", "AA" });
    //     network.Valves["CC"] = new Valve("CC", 2, new List<string> { "DD", "BB" });
    //     network.Valves["DD"] = new Valve("DD", 20, new List<string> { "CC", "AA", "EE" });
    //     network.Valves["EE"] = new Valve("EE", 3, new List<string> { "FF", "DD" });
    //     network.Valves["FF"] = new Valve("FF", 0, new List<string> { "EE", "GG" });
    //     network.Valves["GG"] = new Valve("GG", 0, new List<string> { "FF", "HH" });
    //     network.Valves["HH"] = new Valve("HH", 22, new List<string> { "GG" });
    //     network.Valves["II"] = new Valve("II", 0, new List<string> { "AA", "JJ" });
    //     network.Valves["JJ"] = new Valve("JJ", 21, new List<string> { "II" });
    //
    //     // Find the maximum pressure release
    //     int maxPressureRelease = MaxPressureRelease(network);
    //     Console.WriteLine(maxPressureRelease);
    // }

    //public static int MaxPressureRelease(ValveNetwork network)
    //{
    //    // Implement a search algorithm here to find the optimal path
    //    // through the valves that leads to the highest pressure release

    //    // Use a queue to store the valves that need to be visited
    //    Queue<Valve> queue = new Queue<Valve>();

    //    // Start at valve AA
    //    Valve startValve = network.Valves["AA"];
    //    queue.Enqueue(startValve);

    //    // Use a dictionary to store the pressure release at each valve
    //    Dictionary<string, int> pressureReleases = new Dictionary<string, int>();
    //    pressureReleases[startValve.Name] = 0;

    //    // Use a set to store the valves that have been visited
    //    HashSet<string> visited = new HashSet<string>();

    //    // BFS loop
    //    while (queue.Count > 0)
    //    {
    //        Valve valve = queue.Dequeue();
    //        visited.Add(valve.Name);

    //        // Check the connections of the current valve
    //        foreach (string connection in valve.Connections)
    //        {
    //            // Skip valves that have already been visited
    //            if (visited.Contains(connection))
    //            {
    //                continue;
    //            }

    //            Valve connectedValve = network.Valves[connection];
    //            int pressureRelease = pressureReleases[valve.Name] + connectedValve.FlowRate;

    //            // Update the pressure release if it is higher than the current value
    //            if (!pressureReleases.ContainsKey(connection) || pressureRelease > pressureReleases[connection])
    //            {
    //                pressureReleases[connection] = pressureRelease;
    //            }

    //            queue.Enqueue(connectedValve);
    //        }
    //    }

    //    // Return the maximum pressure release
    //    return pressureReleases.Values.Max();
    //}

    //public static int MaxPressureRelease(ValveNetwork network)
    //{
    //    // Use a stack to store the valves that need to be visited
    //    Stack<Valve> stack = new Stack<Valve>();

    //    // Start at valve AA
    //    Valve startValve = network.Valves["AA"];
    //    stack.Push(startValve);

    //    // Use a dictionary to store the pressure release at each valve
    //    Dictionary<string, int> pressureReleases = new Dictionary<string, int>();
    //    pressureReleases[startValve.Name] = 0;

    //    // Use a set to store the valves that have been visited
    //    HashSet<string> visited = new HashSet<string>();

    //    // DFS loop
    //    while (stack.Count > 0)
    //    {
    //        Valve valve = stack.Pop();
    //        visited.Add(valve.Name);

    //        // Check the connections of the current valve
    //        foreach (string connection in valve.Connections)
    //        {
    //            // Skip valves that have already been visited
    //            if (visited.Contains(connection))
    //            {
    //                continue;
    //            }

    //            Valve connectedValve = network.Valves[connection];
    //            int pressureRelease = pressureReleases[valve.Name] + connectedValve.FlowRate;

    //            // Update the pressure release if it is higher than the current value
    //            if (!pressureReleases.ContainsKey(connection) || pressureRelease > pressureReleases[connection])
    //            {
    //                pressureReleases[connection] = pressureRelease;
    //            }

    //            stack.Push(connectedValve);
    //        }
    //    }

    //    // Return the maximum pressure release
    //    return pressureReleases.Values.Max();
    //}



    //public static int MaxPressureRelease(ValveNetwork network)
    //{
    //    // Create a dictionary to store the capacities of the edges
    //    Dictionary<Tuple<string, string>, int> capacities = new Dictionary<Tuple<string, string>, int>();

    //    // Add the capacities of the edges to the dictionary
    //    foreach (Valve valve in network.Valves.Values)
    //    {
    //        foreach (string connection in valve.Connections)
    //        {
    //            capacities[Tuple.Create(valve.Name, connection)] = valve.FlowRate;
    //        }
    //    }

    //    // Set the source and sink nodes
    //    string source = "AA";
    //    string sink = "ZZ";

    //    // Initialize the maximum flow to 0
    //    int maxFlow = 0;

    //    // Use a dictionary to store the flow through each edge
    //    Dictionary<Tuple<string, string>, int> flow = new Dictionary<Tuple<string, string>, int>();

    //    // Use a dictionary to store the residual capacities of the edges
    //    Dictionary<Tuple<string, string>, int> residualCapacities = new Dictionary<Tuple<string, string>, int>();

    //    // Use a dictionary to store the edges in the residual graph
    //    Dictionary<string, List<string>> adjacencyList = new Dictionary<string, List<string>>();

    //    // Initialize the flow, residual capacities, and adjacency list
    //    foreach (var key in capacities.Keys)
    //    {
    //        flow[key] = 0;
    //        residualCapacities[key] = capacities[key];
    //        if (!adjacencyList.ContainsKey(key.Item1))
    //        {
    //            adjacencyList[key.Item1] = new List<string>();
    //        }
    //        if (!adjacencyList.ContainsKey(key.Item2))
    //        {
    //            adjacencyList[key.Item2] = new List<string>();
    //        }
    //        adjacencyList[key.Item1].Add(key.Item2);
    //        adjacencyList[key.Item2].Add(key.Item1);
    //    }

    //    // Use a dictionary to store the parent nodes in the BFS tree
    //    Dictionary<string, string> parents = new Dictionary<string, string>();

    //    // Use a queue for the BFS loop
    //    Queue<string> queue = new Queue<string>();

    //    // Loop until there are no more augmenting paths
    //    while (true)
    //    {
    //        // Clear the parents and enqueue the source node
    //        parents.Clear();
    //        queue.Clear();
    //        queue.Enqueue(source);
    //        parents[source] = source;

    //        // BFS loop
    //        while (queue.Count > 0 && !parents.ContainsKey(sink))
    //        {
    //            string node = queue.Dequeue();
    //            foreach (string neighbor in adjacencyList[node])
    //            {
    //                if (!parents.ContainsKey(neighbor) && residualCapacities[Tuple.Create(node, neighbor)] > 0)
    //                {
    //                    parents[neighbor] = node;
    //                    queue.Enqueue(neighbor);
    //                }
    //            }
    //        }

    //        // Break if we have reached the sink or there are no more augmenting paths
    //        if (!parents.ContainsKey(sink))
    //        {
    //            break;
    //        }

    //        // Find the minimum residual capacity along the augmenting path
    //        string node1 = sink;
    //        int minCapacity = int.MaxValue;
    //        while (node1 != source)
    //        {
    //            string node2 = parents[node1];
    //            minCapacity = Math.Min(minCapacity, residualCapacities[Tuple.Create(node2, node1)]);
    //            node1 = node2;
    //        }

    //        // Update the flow and residual capacities along the augmenting path
    //        node1 = sink;
    //        while (node1 != source)
    //        {
    //            string node2 = parents[node1];
    //            flow[Tuple.Create(node2, node1)] += minCapacity;
    //            flow[Tuple.Create(node1, node2)] -= minCapacity;
    //            residualCapacities[Tuple.Create(node2, node1)] -= minCapacity;
    //            residualCapacities[Tuple.Create(node1, node2)] += minCapacity;
    //            node1 = node2;
    //        }

    //        // Update the maximum flow
    //        maxFlow += minCapacity;
    //    }

    //    // Return the maximum flow, which is also the maximum pressure release
    //    return maxFlow;
    //}

    // public static int MaxPressureRelease(ValveNetwork network)
    // {
    //     // Initialize the flow, residual capacities, and adjacency list
    //     Dictionary<string, int> flow = new Dictionary<string, int>();
    //     Dictionary<string, int> residualCapacities = new Dictionary<string, int>();
    //     Dictionary<string, List<string>> adjacencyList = new Dictionary<string, List<string>>();
    //
    //     // Initialize the flow and residual capacities for each valve
    //     foreach (Valve valve in network.Valves.Values)
    //     {
    //         flow[valve.Name] = 0;
    //         residualCapacities[valve.Name] = valve.FlowRate;
    //     }
    //
    //     // Initialize the adjacency list
    //     foreach (Valve valve in network.Valves.Values)
    //     {
    //         adjacencyList[valve.Name] = new List<string>();
    //         foreach (string connection in valve.Connections)
    //         {
    //             adjacencyList[valve.Name].Add(connection);
    //         }
    //     }
    //
    //     // While there is an augmenting path from the source to the sink
    //     while (true)
    //     {
    //         // Use a queue to store the valves that need to be visited
    //         Queue<string> queue = new Queue<string>();
    //         Dictionary<string, string> predecessor = new Dictionary<string, string>();
    //
    //         // Start at the source valve
    //         queue.Enqueue("AA");
    //         predecessor["AA"] = null;
    //
    //         // BFS loop
    //         while (queue.Count > 0)
    //         {
    //             string current = queue.Dequeue();
    //
    //             // Check the connections of the current valve
    //             foreach (string connection in adjacencyList[current])
    //             {
    //                 // Skip valves that have no residual capacity
    //                 if (residualCapacities[connection] == 0 || predecessor.ContainsKey(connection))
    //                 {
    //                     continue;
    //                 }
    //
    //                 predecessor[connection] = current;
    //                 queue.Enqueue(connection);
    //
    //                 // Break if we have reached the sink
    //                 if (connection == "HH")
    //                 {
    //                     break;
    //                 }
    //             }
    //         }
    //
    //         // Break if there are no more augmenting paths
    //         if (!predecessor.ContainsKey("HH"))
    //         {
    //             break;
    //         }
    //
    //         // Find the minimum residual capacity along the augmenting path
    //         int minResidualCapacity = int.MaxValue;
    //         string currentValve = "HH";
    //         while (predecessor[currentValve] != null)
    //         {
    //             minResidualCapacity = Math.Min(minResidualCapacity, residualCapacities[currentValve]);
    //             currentValve = predecessor[currentValve];
    //         }
    //
    //         // Update the flow and residual capacities along the augmenting path
    //         currentValve = "HH";
    //         while (predecessor[currentValve] != null)
    //         {
    //             flow[currentValve] += minResidualCapacity;
    //             residualCapacities[currentValve] -= minResidualCapacity;
    //             residualCapacities[predecessor[currentValve]] += minResidualCapacity;
    //             currentValve = predecessor[currentValve];
    //         }
    //
    //         // Update the adjacency list to reflect the changes in the flow and residual capacities
    //         foreach (string valveName in adjacencyList.Keys)
    //         {
    //             adjacencyList[valveName].Clear();
    //             foreach (string connection in network.Valves[valveName].Connections)
    //             {
    //                 if (residualCapacities[connection] > 0)
    //                 {
    //                     adjacencyList[valveName].Add(connection);
    //                 }
    //             }
    //         }
    //     }
    //
    //     // Return the sum of the flow through all the valves
    //     return flow.Values.Sum();
    // }
}

// public class Valve
// {
//     public string Name { get; set; }
//     public int FlowRate { get; set; }
//     public List<string> Connections { get; set; }
//
//     public Valve(string name, int flowRate, List<string> connections)
//     {
//         Name = name;
//         FlowRate = flowRate;
//         Connections = connections;
//     }
// }
//
// public class ValveNetwork
// {
//     public Dictionary<string, Valve> Valves { get; set; }
//
//     public ValveNetwork()
//     {
//         Valves = new Dictionary<string, Valve>();
//     }
// }


