using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Numerics;
using static AdventOfCode2022.DayFifteen.CalculatePartOne;
using static AdventOfCode2022.DayFifteen.CalculatePartTwo;
using static AdventOfCode2022.DayFifteen.BuildArray;


namespace AdventOfCode2022.DayFifteen;

public static class DayFifteen
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFifteen/DayFifteen.txt");
    // private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFifteen/DayFifteenTest.txt");

    private static List<Sensor> _sensors = new();
    
    public static void Day15()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    private static int PartOne(string[]? input = null)
    {
        input ??= Input;

        if (!_sensors.Any()) _sensors = BuildSensorAndBeaconArray(input);

        return PositionsThatCantContainBeaconInRow(_sensors, 2000000);
    }

    private static BigInteger PartTwo(string[]? input = null)
    {
        input ??= Input;
        
        if (!_sensors.Any()) _sensors = BuildSensorAndBeaconArray(input);

        return GetTuningFrequency(_sensors, 4000000);
    }
}
