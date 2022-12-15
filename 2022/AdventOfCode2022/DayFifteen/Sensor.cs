using System;

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
        
        DeltaX = Math.Abs(x - ClosestBeacon.X) + Math.Abs(y - ClosestBeacon.Y);
    }
    
    public int MinXAtY(int y) => X - DeltaX + Math.Abs(Y - y);
    public int MaxXAtY(int y) => X + DeltaX - Math.Abs(Y - y);

    // method to calculate the distance to the closest beacon
    public int DistanceToClosestBeacon()
    {
        // calculate the Manhattan distance using the formula
        // distance = |sensor.x - beacon.x| + |sensor.y - beacon.y|
        return Math.Abs(this.X - this.ClosestBeacon.X) + Math.Abs(this.Y - this.ClosestBeacon.Y);
    }
}
