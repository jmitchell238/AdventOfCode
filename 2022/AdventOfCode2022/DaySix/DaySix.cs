using System;
using System.IO;

namespace AdventOfCode2022.DaySix;

public static class DaySix
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DaySix/Day6.txt");

    public static void Day6()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static string PartOne(string[]? input = null)
    {
        input ??= Input;

        // return CreateMethod(input);
        return "-";
    }

    public static string PartTwo(string[]? input = null)
    {
        input ??= Input;

        // return CreateMethod(input);
        return "-";
    }
}
