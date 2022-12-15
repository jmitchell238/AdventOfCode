using System;
using static AdventOfCode2022.HelperFunctions;

namespace AdventOfCode2022.DayFifteen;

public class Sensor
{
    // properties for the x and y coordinates of the sensor
    public int X { get; set; }
    public int Y { get; set; }
    
    public int DeltaX { get; set; }

    // property for the closest beacon to this sensor
    public Beacon ClosestBeacon { get; set; }

    // constructor that takes the x and y coordinates of the sensor
    public Sensor(int x, int y, Beacon closestBeacon)
    {
        this.X = x;
        this.Y = y;

        this.ClosestBeacon = closestBeacon;

        DeltaX = ManhattanDistance((X,Y),(ClosestBeacon.X,ClosestBeacon.Y));
    }

    public int MinXAtY(int y) => X - DeltaX + Math.Abs(Y - y);
    public int MaxXAtY(int y) => X + DeltaX - Math.Abs(Y - y);
}
