using System;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DaySix;

public static class DaySix
{
    private static readonly string Input = File.ReadLines("../../../../AdventOfCode2022/DaySix/Day6.txt").First();

    public static void Day6()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static int PartOne(string? input = null)
    {
        input ??= Input;

        return GetMarker(input);
    }

    public static int PartTwo(string? input = null)
    {
        input ??= Input;

        return GetMessage(input);
    }

    public static int GetMarker(string input)
    {
        for (var i = 0; i < input.Length; i++)
        {
            var substr = input.Substring(i, 4).ToCharArray();
            if (substr.Distinct().Count() == substr.Length)
            {
                return i + 4;
            }
        }

        return -1;
    }

    public static int GetMessage(string input)
    {
        for (var i = 0; i < input.Length; i++)
        {
            var substr = input.Substring(i, 14).ToCharArray();
            if (substr.Distinct().Count() == substr.Length)
            {
                return i + 14;
            }
        }

        return -1;
    }
}
