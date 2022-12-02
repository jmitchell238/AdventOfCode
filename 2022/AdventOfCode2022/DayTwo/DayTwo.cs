using System;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayTwo;

public static class DayTwo
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayTwo/Day2.txt");

    public static void Day2()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()} ");
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        return GetScore(false, input);
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        return GetScore(true, input);
    }

    private static int GetScore(bool explained, string[]? input = null)
    {
        return (from line in input let round = line.Split(' ') select GetRoundScore(line, explained)).Sum();
    }

    private static int GetRoundScore(string round, bool explained)
    {
        (char opponent, char player) playerChoices = (round[0], round[2]);

        return playerChoices switch
        {
            ('B', 'X') => 1,
            ('B', 'Y') => 5,
            ('B', 'Z') => 9,
            ('A', 'X') => explained ? 3 : 4,
            ('A', 'Y') => explained ? 4 : 8,
            ('A', 'Z') => explained ? 8 : 3,
            ('C', 'X') => explained ? 2 : 7,
            ('C', 'Y') => explained ? 6 : 2,
            ('C', 'Z') => explained ? 7 : 6,
            _ => throw new NotImplementedException()
        };
    }
}
