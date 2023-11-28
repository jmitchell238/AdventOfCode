using System;
using System.Collections.Generic;
using System.Linq;
using BenchmarkDotNet.Diagnosers;

namespace AdventOfCode2022.DayFifteen;

public static class CalculatePartOne
{
    public static int PositionsThatCantContainBeaconInRow(List<Sensor> sensors, int row)
    {
        var minX = sensors.Min(s => s.X - s.DeltaX);
        var maxX = sensors.Max(s => s.X + s.DeltaX);

        var score = 0;

        for (var i = minX; i <= maxX; i++)
        {
            var isBeacon = sensors.Any(sensor => sensor.ClosestBeacon.X == i && sensor.ClosestBeacon.Y == row);

            if (isBeacon) continue;

            if (sensors.Any(s => i >= s.MinXAtY(row) && i <= s.MaxXAtY(row)))
            {
                score++;
            }
        }

        return score;
    }
    
    public static int BuildDiamond((int x, int y) sensor, (int x, int y) beacon) =>
        Math.Abs(sensor.x - beacon.x) + Math.Abs(sensor.y - beacon.y);
}

// public class DeadZone
// {
//     public int Radius { get; private set; }
//     public (int x, int y) Top { get; private set; }
//     public (int x, int y) Right { get; private set; }
//     public (int x, int y) Bottom { get; private set; }
//     public (int x, int y) Left { get; private set; }
//     
//     // public DeadZone((int x, int y) sensor, (int x, int y) beacon)
//     // {
//     //     Radius = sensor.ManhattanDistance(beacon) - 1;
//     //     Top = (beacon.x, beacon.y - Radius);
//     // }
// }
