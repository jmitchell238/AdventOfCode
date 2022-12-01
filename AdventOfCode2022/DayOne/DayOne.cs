using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayOne;

public static class DayOne
{
    private static readonly string[] Input = File.ReadAllLines("../../../DayOne/Day1.txt");

    public static void Day1()
    {
        PartOne();
        PartTwo();
    }

    private static void PartOne()
    {
        var elves = CalculateCaloriesCarried();

        Console.WriteLine(elves.Max());
    }

    private static void PartTwo()
    {
        var elves = CalculateCaloriesCarried();

        Console.WriteLine(elves.OrderByDescending(i => i).Take(3).Sum());
    }

    private static List<int> CalculateCaloriesCarried()
    {
        List<int> elves = new();

        var sum = 0;
        foreach (var line in Input)
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
