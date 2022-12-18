using System;
using System.Collections.Generic;

// Define the Valve class
public class Valve
{

    public string Name { get; set; }
    public int FlowRate { get; set; }
    public List<string> ConnectedValves { get; set; }
    public int TimeToOpen { get; set; }

    public Valve(string name, int flowRate)
    {
        Name = name;
        FlowRate = flowRate;
    }
}
