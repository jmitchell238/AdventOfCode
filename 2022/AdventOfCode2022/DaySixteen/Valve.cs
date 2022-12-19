using System;
using System.Collections.Generic;

// Define the Valve class
public class Valve
{
    public int Id { get; }
    public string Name { get; set; }
    public int FlowRate { get; set; }
    public List<string> Tunnels { get; set; }

    public Valve(int id, string name, int flowRate, List<string> tunnels)
    {
        Id = Id;
        Name = name;
        FlowRate = flowRate;
        Tunnels = tunnels;
    }
}
