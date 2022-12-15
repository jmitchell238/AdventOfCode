using System.Collections.Generic;
using System.Linq;

namespace AdventOfCode2022.DayFifteen;

public static class BuildArray
{
    public static List<Sensor> BuildSensorAndBeaconArray(string[] input)
    {
        return (from line in input select line.SplitToStringArray(":", false) into parts 
            let coordinates1 = parts[0].SplitToStringArray("=", false) 
            let xCoords1 = coordinates1[1].SplitToStringArray(",", false) 
            let x = int.Parse(xCoords1[0]) 
            let y = int.Parse(coordinates1[2]) 
            let coordinates2 = parts[1].SplitToStringArray("=", false) 
            let xCoords2 = coordinates2[1].SplitToStringArray(",", false) 
            let beaconX = int.Parse(xCoords2[0]) 
            let beaconY = int.Parse(coordinates2[2]) 
            select new Sensor(x, y, new Beacon(beaconX, beaconY))).ToList();
    }
}
