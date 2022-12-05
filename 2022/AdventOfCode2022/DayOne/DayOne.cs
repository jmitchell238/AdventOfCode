using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayOne;

public static class DayOne
{
     private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayOne/Day1.txt");

    public static void Day1()
    {
        Console.Write($"Part 1: {PartOne()}");
        Console.Write($"Part 2: {PartTwo()}");
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        var elves = CalculateCaloriesCarried(input);

        Console.WriteLine(elves.Max());

        return elves.Max();
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        var elves = CalculateCaloriesCarried(input);

        var result = elves.OrderByDescending(i => i).Take(3).Sum();

        Console.WriteLine(elves.OrderByDescending(i => i).Take(3).Sum());

        return result;
    }

    private static List<int> CalculateCaloriesCarried(IEnumerable<string> input)
    {
        List<int> elves = new();

        var sum = 0;
        foreach (var line in input)
        {
            if (string.IsNullOrWhiteSpace(line))
            {
                elves.Add(sum);
                sum = 0;

                continue;
            }

            sum += int.Parse(line);
        }

        return elves;
    }
}
