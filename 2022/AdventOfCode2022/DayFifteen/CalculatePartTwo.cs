using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Numerics;

namespace AdventOfCode2022.DayFifteen;

public static class CalculatePartTwo
{
    public static BigInteger Solve2(List<Sensor> sensors, int maxBound)
    {
        BigInteger result = 0;
        for (var y = 0; y <= maxBound; y++)
        {
            var bounds = sensors.Select(s => new int[] {Math.Max(s.MinXAtY(y), 0), Math.Min(s.MaxXAtY(y), maxBound)})
                .Where(e => e[0] <= e[1]).ToList();

            bounds.Sort((a, b) => a[0].CompareTo(b[0]));

            var isMerged = true;

            while (isMerged && bounds.Count > 1)
            {
                isMerged = false;

                if (bounds[0][0] > bounds[1][0] || bounds[0][1] < bounds[1][0]) continue;
                bounds[0][1] = Math.Max(bounds[0][1], bounds[1][1]);
                bounds.RemoveAt(1);
                isMerged = true;
            }

            if (isMerged && bounds[0][0] == 0 && bounds[0][1] == maxBound) continue;
            result = ((BigInteger) (bounds[0][1] + 1)) * 4000000 + y;
            break;
        }
        
        return result;
    }
}