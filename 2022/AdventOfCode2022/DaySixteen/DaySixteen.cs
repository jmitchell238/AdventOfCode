using AdventOfCode2022.DayFourteen;
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.NetworkInformation;
using System.Security.Cryptography.X509Certificates;
using System.Text.RegularExpressions;

namespace AdventOfCode2022.DaySixteen;


public class DaySixteen
{
    private static readonly string[] Input =
        File.ReadAllLines("../../../../AdventOfCode2022/DaySixteen/DaySixteenTest.txt");

    private static Dictionary<string, Valve> Valves;
    private static Dictionary<string, Dictionary<string, int>> ShortestTimes;
    private static List<string> JammedValves;
    private static decimal BestPressure = 0;
    
    public static void Day16()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        // Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static decimal PartOne(string[]? input = null)
    {
        input ??= Input;
        
        List<bool> openValves = new List<bool>();

        Valves = parseValves(input);
        // Dictionary<string, Valve> valves = parseValves(input);

        ShortestTimes = GetShortestTimes(Valves);

        JammedValves = GetJammedValves(Valves);
        // Dictionary<string, Dictionary<string, int>> shortestTimes = GetShortestTimes(Valves);

        Solve("AA", 0, 31, new bool[Valves.Count]);
        
        // return Solve(valveList, true, 30);
        return BestPressure;
    }

    public static void Solve(string position, decimal totalPressure, int timeLeft,  bool[] openedValves)
    {
        var currentValve = Valves[position];
        timeLeft--;
        totalPressure = totalPressure;

        if (timeLeft == 0)
        {
            if (totalPressure > BestPressure)
            {
                BestPressure = totalPressure;
            }
        }
        
        // Get connections
        var tunnelConnections = ShortestTimes[currentValve.Name];
        
        // Open Current Valve
        if (openedValves[currentValve.Id] == false)
        {
            openedValves[currentValve.Id] = true;
            timeLeft--;
            foreach (var connection in tunnelConnections)
            {
                var connectedValve = connection.Key;
                var testDistance = ShortestTimes[currentValve.Name][Valves[connectedValve].Name];
                if (!JammedValves.Contains(connectedValve) && openedValves[Valves[connectedValve].Id] == false)
                {
                    Solve(connectedValve, totalPressure + currentValve.FlowRate * timeLeft, timeLeft - testDistance, openedValves);
                }
            }

            openedValves[currentValve.Id] = false;
        }
    }

    public static List<string> GetJammedValves(Dictionary<string, Valve> valves)
    {
        List<string> jammedValves = new List<string>();
        foreach (var valve in valves)
        {
            var currentValve = valve.Key;
            if (valves[currentValve].FlowRate == 0)
            {
                jammedValves.Add(valves[currentValve].Name);
            }
        }

        return jammedValves;
    }
    
    // public static void Solve(string position, decimal totalPressure, int timeLeft,  List<bool> openedValves)
    // {
    //     var currentValve = Valves[position];
    //     timeLeft--;
    //
    //     if (timeLeft == 0)
    //     {
    //         if (totalPressure > BestPressure)
    //         {
    //             BestPressure = totalPressure;
    //         }
    //     }
    //     
    //     // Open Valve
    //     if (currentValve.FlowRate > 0 && openedValves[currentValve.Id] == false)
    //     {
    //         openedValves[currentValve.Id] = true;
    //         Solve(position, totalPressure + currentValve.FlowRate * timeLeft, timeLeft, openedValves);
    //         openedValves[currentValve.Id] = false;
    //     }
    //     
    //     // Move
    //     foreach (var tunnel in currentValve.Tunnels)
    //     {
    //         Solve(tunnel, totalPressure, timeLeft, openedValves);
    //     }
    // }

    private static Dictionary<string, Valve> parseValves(string[] input)
    {
        var valves = new Dictionary<string, Valve>();

        var counter = 0;
        foreach (var line in input)
        {
            string[] words = line.Split(' ');

            // Set the Valve name
            string valveName = words[1];

            // Set the flow rate
            string flowRateString = words[4].Split('=').Last().Trim(';');
            int flowRate = Int32.Parse(flowRateString);

            // Set the list of valves
            List<string> connectedValves = new List<string>();

            foreach (var word in words[9..])
            {
                connectedValves.Add(word.TrimEnd(','));
            }

            // Create a new Valve object
            Valve valve = new Valve(counter, valveName, flowRate, connectedValves);

            // Add this Valve to the List<Tunnel>
            valves.Add(valveName, valve);
            
            // Increase counter / id
            counter++;
        }

        return valves;
    }
    
    public static Dictionary<string, Dictionary<string, int>> GetShortestTimes(Dictionary<string, Valve> valves)
    {
        Dictionary<string, Dictionary<string, int>> shortestTimes = new Dictionary<string, Dictionary<string, int>>();
        
        foreach (var valve in valves)
        {
            string startValve = valve.Key;
            Dictionary<string, int> times = new Dictionary<string, int>();

            foreach (var endValve in valves.Keys)
            {
                if (startValve != endValve)
                {
                    int time = GetTimeToTravelBetweenValves(startValve, endValve, valves);
                    times[endValve] = time;
                }
            }

            shortestTimes[startValve] = times;
        }

        return shortestTimes;
    }
    
    public static int GetTimeToTravelBetweenValves(string startValve, string endValve, Dictionary<string, Valve> valves)
    {
        // Create a queue to store the valves that are waiting to be processed
        Queue<string> queue = new Queue<string>();

        // Create a dictionary to store the time it takes to reach each valve
        Dictionary<string, int> times = new Dictionary<string, int>();

        // Add the start valve to the queue and set the time to reach it to 0
        queue.Enqueue(startValve);
        times[startValve] = 0;

        // While there are valves in the queue
        while (queue.Count > 0)
        {
            // Get the next valve in the queue
            string valve = queue.Dequeue();

            // If the current valve is the end valve, return the time it takes to reach it
            if (valve == endValve)
            {
                return times[valve];
            }

            // Get the connections for the current valve
            List<string> connections = valves[valve].Tunnels;

            // Iterate over the connections
            foreach (var connection in connections)
            {
                // If the time to reach the connected valve has not been set yet
                if (!times.ContainsKey(connection))
                {
                    // Calculate the time it takes to reach the connected valve
                    int timeToReachConnection = times[valve] + 1;

                    // Set the time to reach the connected valve
                    times[connection] = timeToReachConnection;

                    // Add the connected valve to the queue
                    queue.Enqueue(connection);
                }
            }
        }

        // If the end valve was not reached, return -1
        return -1;
    }

}


//    static int Solve(List<Valve> valveList, bool singlePlayer, int time)
//    {
//        // initialize and run MaxFlow()

//        var map = CreateMap(valveList);

//        var start = map.valves.Single(x => x.name == "AA");

//        var valvesToOpen = new BitArray(map.valves.Length);
//        for (var i = 0; i < map.valves.Length; i++)
//        {
//            if (map.valves[i].flowRate > 0)
//            {
//                valvesToOpen[i] = true;
//            }
//        }

//        //if (singlePlayer)
//        //{
//            // int.MaxValue is used here as a dummy player that doesn't really do anything, it just
//            // walks towards the start node
//            return MaxFlow(map, 0, 0, new Player(start, 0), new Player(start, int.MaxValue), valvesToOpen, time);
//        //}
//        //else
//        //{
//        //    return MaxFlow(map, 0, 0, new Player(start, 0), new Player(start, 0), valvesToOpen, time);
//        //}
//    }

//    record Map(int[,] distances, Valve[] valves);
//    //record Valve(int id, string name, int flowRate, string[] tunnels);
//    record Player(Valve valve, int distance);

//    // Recursively find the maximum available flow in the map by moving the players, opening valves and advancing
//    // time according to the task description
//    static int MaxFlow(
//        Map map,               // this is our map as per task input.
//        int maxFlow,           // is the current maximum we found (call with 0), this is used internally to shortcut
//        int currentFlow,       // the flow produced by the currently investigated steps (on the stack)
//        Player player0,        // this is the 'human' player
//        Player player1,        // this can be a second player, use distance = int.MaxValue to make it inactive
//        BitArray valvesToOpen, // these valves can still be open
//        int remainingTime      // and the remaining time
//    )
//    {

//        // briefly: we advance the simulation and collect what states the players can go -> recurse
//        // use lots of early exits to make this approach practical (Residue is an important concept)

//        // One of the players is standing next to a valve:
//        if (player0.distance != 0 && player1.distance != 0)
//        {
//            throw new ArgumentException();
//        }

//        // Compute the next states for each player:
//        var nextStatesByPlayer = new Player[2][];

//        for (var iplayer = 0; iplayer < 2; iplayer++)
//        {

//            var player = iplayer == 0 ? player0 : player1;

//            if (player.distance > 0)
//            {
//                // this player just steps forward towards the valve
//                nextStatesByPlayer[iplayer] = new[] { player with { distance = player.distance - 1 } };

//            }
//            else if (valvesToOpen[player.valve.id])
//            {
//                // the player is next to the valve, the valve is still closed, let's open:
//                // (this takes 1 time, so multiply with remainingTime -1)
//                currentFlow += player.valve.flowRate * (remainingTime - 1);

//                if (currentFlow > maxFlow)
//                {
//                    maxFlow = currentFlow;
//                }

//                valvesToOpen = new BitArray(valvesToOpen); // copy on write
//                valvesToOpen[player.valve.id] = false;

//                // in the next round this player will take some new target, 
//                // but it already used up it's 1 second this round for opening the valve
//                nextStatesByPlayer[iplayer] = new[] { player };

//            }
//            else
//            {
//                // the valve is already open, let's try each valves that are still closed:
//                // this is where brancing happens

//                var nextStates = new List<Player>();

//                for (var i = 0; i < valvesToOpen.Length; i++)
//                {
//                    if (valvesToOpen[i])
//                    {
//                        var nextValve = map.valves[i];
//                        var distance = map.distances[player.valve.id, nextValve.id];
//                        // the player moves in this time slot towards the valve, so use distance - 1 here
//                        nextStates.Add(new Player(nextValve, distance - 1));
//                    }
//                }

//                nextStatesByPlayer[iplayer] = nextStates.ToArray();
//            }
//        }

//        // ran out of time, cannot improve maxFlow
//        remainingTime--;
//        if (remainingTime < 1)
//        {
//            return maxFlow;
//        }

//        // the is not enough juice left for the remaining time to improve on maxFlow
//        // we can shortcut here
//        if (currentFlow + Residue(valvesToOpen, map, remainingTime) <= maxFlow)
//        {
//            return maxFlow;
//        }

//        // all is left is going over every possible step combinations for each players:
//        for (var i0 = 0; i0 < nextStatesByPlayer[0].Length; i0++)
//        {
//            for (var i1 = 0; i1 < nextStatesByPlayer[1].Length; i1++)
//            {

//                player0 = nextStatesByPlayer[0][i0];
//                player1 = nextStatesByPlayer[1][i1];

//                // there is no point in walking to the same valve
//                // if one of the players has other thing to do:
//                if ((nextStatesByPlayer[0].Length > 1 || nextStatesByPlayer[1].Length > 1) && player0.valve == player1.valve)
//                {
//                    continue;
//                }

//                // this is an other optimization, if both players are walking
//                // we can advance time until one of them reaches target:
//                var advance = 0;
//                if (player0.distance > 0 && player1.distance > 0)
//                {
//                    advance = Math.Min(player0.distance, player1.distance);
//                    player0 = player0 with { distance = player0.distance - advance };
//                    player1 = player1 with { distance = player1.distance - advance };
//                }

//                maxFlow = MaxFlow(
//                    map,
//                    maxFlow,
//                    currentFlow,
//                    player0,
//                    player1,
//                    valvesToOpen,
//                    remainingTime - advance
//                );
//            }
//        }

//        return maxFlow;
//    }

//    static int Residue(BitArray valvesToOpen, Map map, int remainingTime)
//    {
//        // Some upper bound for the possible amount of flow that we can
//        // still produce in the remaining time. 

//        // IT'S JUST AN UPPER BOUND. HEURISTICAL

//        // The valves are in decreasing order of flowRate. We open the 
//        // first two (we have two players), this takes 1 time then we 
//        // step to the next two valves supposing that each valve is 
//        // just one step away. Open these as well. Continue until we run 
//        // out of time.

//        var flow = 0;
//        for (var i = 0; i < valvesToOpen.Length; i++)
//        {
//            if (valvesToOpen[i])
//            {
//                if (remainingTime <= 0)
//                {
//                    break;
//                }

//                flow += map.valves[i].flowRate * (remainingTime - 1);

//                if ((i & 1) == 0)
//                {
//                    remainingTime--;
//                }
//            }
//        }
//        return flow;
//    }

//    static Map CreateMap(List<Valve> valveList)
//    {
//        // Valve BB has flow rate=0; tunnels lead to valve CC
//        // Valve CC has flow rate=10; tunnels lead to valves DD, EE
        
//        var valves = valveList
//            .OrderByDescending(valve => valve.flowRate)
//            .Select((v, i) => v with { id = i })
//            .ToArray();

//        return new Map(ComputeDistances(valves), valves);
//    }

//    static int[,] ComputeDistances(Valve[] valves)
//    {
//        // Bellman-Ford style distance calculation for every pair of valves
//        var distances = new int[valves.Length, valves.Length];
//        for (var i = 0; i < valves.Length; i++)
//        {
//            for (var j = 0; j < valves.Length; j++)
//            {
//                distances[i, j] = int.MaxValue;
//            }
//        }
//        foreach (var valve in valves)
//        {
//            foreach (var target in valve.connectedValves)
//            {
//                var targetNode = valves.Single(x => x.name == target);
//                distances[valve.id, targetNode.id] = 1;
//                distances[targetNode.id, valve.id] = 1;
//            }
//        }

//        var n = distances.GetLength(0);
//        var done = false;
//        while (!done)
//        {
//            done = true;
//            for (var source = 0; source < n; source++)
//            {
//                for (var target = 0; target < n; target++)
//                {
//                    if (source != target)
//                    {
//                        for (var through = 0; through < n; through++)
//                        {
//                            if (distances[source, through] == int.MaxValue || distances[through, target] == int.MaxValue)
//                            {
//                                continue;
//                            }
//                            var cost = distances[source, through] + distances[through, target];
//                            if (cost < distances[source, target])
//                            {
//                                done = false;
//                                distances[source, target] = cost;
//                                distances[target, source] = cost;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return distances;
//    }
//}

//public record class Valve
//{
//    public int id { get; set; }
//    public string name { get; private set; }
//    public int flowRate { get; private set; }
//    public List<string> connectedValves { get; private set; }

//    public Valve(int id, string name, int flowRate, List<string> connectedValves)
//    {
//        this.id = id;
//        this.name = name;
//        this.flowRate = flowRate;
//        this.connectedValves = connectedValves;
//    }
//}

