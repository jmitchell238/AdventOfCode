using System;
using System.Collections.Generic;

// Define the Valve class
public class Valve
{
    public int Id { get; }
    public string Name { get; set; }
    public int FlowRate { get; set; }
    public List<string> Tunnels { get; set; }
    public bool IsOpen { get; set; }

    public Valve(int id, string name, int flowRate, List<string> tunnels)
    {
        Id = id;
        Name = name;
        FlowRate = flowRate;
        Tunnels = tunnels;
        IsOpen = false;
    }

    //public int CompareTo(Valve other)
    //{
    //    // Check if the object is a Valve
    //    if (other is Valve valve)
    //    {
    //        // Compare the FlowRate of the current Valve with the FlowRate of the passed in Valve
    //        return this.FlowRate.CompareTo(valve.FlowRate);
    //    }
    //    else
    //    {
    //        throw new ArgumentException("other is not a Valve");
    //    }
    //}
}
