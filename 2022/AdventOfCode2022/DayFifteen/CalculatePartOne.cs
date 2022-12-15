using System.Collections.Generic;
using System.Linq;

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
}