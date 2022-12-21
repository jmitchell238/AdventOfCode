using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Security.Cryptography;

namespace AdventOfCode2022.DaySixteen
{
    public class DaySixteen
    {
        private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DaySixteen/DaySixteen.txt");

        private static Dictionary<string, Valve>? Valves;
        private static Dictionary<string, Dictionary<string, int>>? ShortestTimes;
        private static List<string>? JammedValves;
        public static int BestPressure { get; set; } = 0;
        public static int MaxFlowRate { get; set; } = 0;

        public static void Day16()
        {
            Console.WriteLine($"Part 1: {PartOne()}");
            Console.WriteLine($"Part 2: {PartTwo()}");
        }

        public static int PartOne(string[]? input = null)
        {
            input ??= Input;

            Valves = ParseValves(input);
            ShortestTimes = GetShortestTimes(Valves);
            JammedValves = GetJammedValves(Valves);

            Solve("AA", 0, 30, new bool[Valves.Count]);

            return BestPressure;
        }

        public static int PartTwo(string[]? input = null)
        {
            // If no input is provided, use the default input
            input ??= Input;

            // Parse the input into a dictionary of valves
            Valves = ParseValves(input);

            // Calculate the shortest times between each pair of valves
            ShortestTimes = GetShortestTimes(Valves);

            // Get the list of jammed valves
            JammedValves = GetJammedValves(Valves);

            // Get an array of valves with a flow rate greater than 0
            Valve[] valvesToOpen = Valves.Values.Where(valve => valve.FlowRate > 0).ToArray();

            // Initialize a cache for storing the results of recursive calls to MaxFlow
            var cache = new Dictionary<string, int>();

            // Determine whether to only consider human valves
            var humanOnly = false;

            // If humanOnly is true, return the maximum flow rate for all human valves
            if (humanOnly)
            {
                return MaxFlow(cache, ShortestTimes, Valves["AA"], valvesToOpen.ToHashSet(), 30);
            }
            // Otherwise, return the maximum flow rate for all possible combinations of human and elephant valves
            else
            {
                // Get all possible combinations of human and elephant valves
                return Combinations(valvesToOpen).Select(combination =>
                     // Calculate the maximum flow rate for the human valves
                     MaxFlow(cache, ShortestTimes, Valves["AA"], combination.human, 26) +
                     // Calculate the maximum flow rate for the elephant valves
                     MaxFlow(cache, ShortestTimes, Valves["AA"], combination.elephant, 26)
                // Return the maximum flow rate of all combinations
                ).Max();
            }
        }

        // Divide the valves between human and elephant in all possible ways
        public static void Solve(string position, int totalPressure, int timeLeft, bool[] openedValves)
        {
            var currentValve = Valves[position];

            // Update totalPressure by adding the flow rate of the current valve multiplied by the time left
            totalPressure += currentValve.FlowRate * timeLeft;

            if (totalPressure > BestPressure)
            {
                BestPressure = totalPressure;
            }

            var numOfJammedValves = JammedValves.Count;
            var numOfOpenedValves = openedValves.Where(v => v == true).Count();

            Valve firstValve = Valves["AA"];
            if (firstValve.FlowRate == 0)
            {
                if (numOfJammedValves + numOfOpenedValves == Valves.Count + 1)
                {
                    return;
                }
            }
            else
            {
                if (numOfJammedValves + numOfOpenedValves == Valves.Count)
                {
                    return;
                }
            }

            if (timeLeft <= 0)
            {
                return;
            }

            // Get connections
            var tunnelConnections = ShortestTimes[currentValve.Name];

            // Open Current Valve
            if (openedValves[currentValve.Id] == false)
            {
                openedValves[currentValve.Id] = true;

                // Decrement timeLeft if the valve is open
                if (openedValves[currentValve.Id])
                {
                    timeLeft--;
                }

                foreach (var connection in tunnelConnections)
                {
                    var connectedValve = connection.Key;
                    var distance = ShortestTimes[currentValve.Name][Valves[connectedValve].Name];
                    var distanceToTimeLeft = timeLeft - distance;
                    var isNotJammed = !JammedValves.Contains(connectedValve);
                    var isNotOpened = openedValves[Valves[connectedValve].Id] == false;
                    if (isNotJammed && isNotOpened)
                    {
                        // Create a copy of openedValves and pass it to the recursive call
                        bool[] openedValvesCopy = (bool[])openedValves.Clone();
                        Solve(connectedValve, totalPressure, distanceToTimeLeft, openedValvesCopy);
                    }
                }

                openedValves[currentValve.Id] = false;
            }
        }

        /* This method calculates the maximum flow that can be obtained from a set of valves, given a certain 
           amount of remaining time. It does this by recursively calling itself with different subsets of the 
           valves, and using a cache to avoid recalculating the same result multiple times. It returns the maximum 
           flow that can be obtained as an integer. */
        public static int MaxFlow(
            Dictionary<string, int> cache,
            Dictionary<string, Dictionary<string, int>> shortestTimes,
            Valve currentValve,
            HashSet<Valve> valves,
            int remainingTime
        )
        {
            // Create a key to use as a cache index
            string key =
                remainingTime + "-" +
                currentValve.Id + "-" +
                string.Join("-", valves.OrderBy(valve => valve.Id).Select(valve => valve.Id));

            // If the cache does not contain this key
            if (!cache.ContainsKey(key))
            {
                // Calculate the flow from the current valve
                var flowFromValve = currentValve.FlowRate * remainingTime;

                // Initialize the flow from the rest of the valves
                var flowFromRest = 0;

                // Convert the valves set to an array
                foreach (var valve in valves.ToArray())
                {
                    // Get the distance between the current valve and the valve in the array
                    var distance = shortestTimes[currentValve.Name][valve.Name];

                    // If there is enough remaining time to reach the valve in the array
                    if (remainingTime >= distance + 1)
                    {
                        // Remove the valve from the set
                        valves.Remove(valve);

                        // Decrement the remaining time by the distance plus 1
                        remainingTime -= distance + 1;

                        // Recursively call MaxFlow to get the maximum flow from the rest of the valves
                        flowFromRest = Math.Max(flowFromRest, MaxFlow(cache, shortestTimes, valve, valves, remainingTime));

                        // Increment the remaining time by the distance plus 1
                        remainingTime += distance + 1;

                        // Add the valve back to the set
                        valves.Add(valve);
                    }
                }

                // Store the result in the cache
                cache[key] = flowFromValve + flowFromRest;
            }

            // Return the result from the cache
            return cache[key];
        }

        /* Generates all possible combinations of valves that can be opened by the human and the elephant
           This method generates all possible combinations of valves that can be opened by the human and the elephant,
           and returns them as a sequence of tuples.Each tuple contains two sets: one set of valves opened by the human
           and one set of valves opened by the elephant. */
        public static IEnumerable<(HashSet<Valve> human, HashSet<Valve> elephant)> Combinations(Valve[] valves)
        {
            // Generate a binary mask with valves.Length number of 1s
            var maxMask = 1 << (valves.Length);

            // Iterate over all possible combinations of valves that can be opened by the human and the elephant
            for (var mask = 0; mask < maxMask; mask++)
            {
                // Set of valves opened by the elephant
                var elephant = new HashSet<Valve>();
                // Set of valves opened by the human
                var human = new HashSet<Valve>();

                // For each valve, determine if it will be opened by the elephant or the human
                for (var ivalve = 0; ivalve < valves.Length; ivalve++)
                {
                    if ((mask & (1 << ivalve)) == 0)
                    {
                        // If the bit at position ivalve is 0, add the valve to the set of valves opened by the human
                        human.Add(valves[ivalve]);
                    }
                    else
                    {
                        // If the bit at position ivalve is 1, add the valve to the set of valves opened by the elephant
                        elephant.Add(valves[ivalve]);
                    }
                }
                // Return the combination of valves opened by the human and the elephant as a tuple
                yield return (human, elephant);
            }
        }

        // Parses the input strings and creates a dictionary of Valve objects
        private static Dictionary<string, Valve> ParseValves(string[] input)
        {
            // Dictionary to store the Valve objects
            var valves = new Dictionary<string, Valve>();

            // Counter to assign unique IDs to each Valve
            var counter = 0;

            // For each line in the input
            foreach (var line in input)
            {
                // Split the line into an array of words
                string[] words = line.Split(' ');

                // Set the Valve name
                string valveName = words[1];

                // Set the flow rate
                string flowRateString = words[4].Split('=').Last().Trim(';');
                int flowRate = Int32.Parse(flowRateString);

                // Set the list of valves
                List<string> connectedValves = new List<string>();

                // For each word in the words array starting at the 9th index
                foreach (var word in words[9..])
                {
                    // Add the word to the list of connected valves, with the comma trimmed off the end
                    connectedValves.Add(word.TrimEnd(','));
                }

                // Create a new Valve object
                Valve valve = new Valve(counter, valveName, flowRate, connectedValves);

                // Add this Valve to the dictionary
                valves.Add(valveName, valve);

                // Increase counter / id
                counter++;
            }

            // Return the dictionary of Valve objects
            return valves;
        }

        /* Returns a list of jammed valves (valves with a flow rate of 0)
           The GetJammedValves method returns a list of jammed valves, which are defined as valves 
           with a flow rate of 0. It does this by iterating over the dictionary of valves and checking 
           the flow rate of each valve. If the flow rate is 0, the method adds the name of the valve to 
           the list of jammed valves. */
        public static List<string> GetJammedValves(Dictionary<string, Valve> valves)
        {
            // List to store the names of jammed valves
            List<string> jammedValves = new List<string>();

            // For each valve in the dictionary of valves
            foreach (var valve in valves)
            {
                // The current valve being processed
                var currentValve = valve.Key;
                // If the flow rate of the current valve is 0
                if (valves[currentValve].FlowRate == 0)
                {
                    // Add the name of the current valve to the list of jammed valves
                    jammedValves.Add(valves[currentValve].Name);
                }
            }

            // Return the list of jammed valves
            return jammedValves;
        }

        // Calculates the shortest time it takes to travel between all pairs of valves
        public static Dictionary<string, Dictionary<string, int>> GetShortestTimes(Dictionary<string, Valve> valves)
        {
            // Dictionary to store the shortest time it takes to travel between each pair of valves
            Dictionary<string, Dictionary<string, int>> shortestTimes = new Dictionary<string, Dictionary<string, int>>();

            // For each valve in the dictionary of valves
            foreach (var valve in valves)
            {
                // The starting valve for the current iteration
                string startValve = valve.Key;
                // Dictionary to store the shortest times it takes to travel to other valves from the starting valve
                Dictionary<string, int> times = new Dictionary<string, int>();

                // For each end valve in the dictionary of valves
                foreach (var endValve in valves.Keys)
                {
                    // If the starting valve and the end valve are not the same
                    if (startValve != endValve)
                    {
                        // Calculate the time it takes to travel between the starting valve and the end valve
                        int time = GetTimeToTravelBetweenValves(startValve, endValve, valves);
                        // Add the shortest time to the dictionary
                        times[endValve] = time;
                    }
                }

                // Add the dictionary of shortest times to the dictionary of shortest times
                shortestTimes[startValve] = times;
            }

            // Return the dictionary of shortest times
            return shortestTimes;
        }

        // Calculates the shortest time required to travel between two valves
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
}
