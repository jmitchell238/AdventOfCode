using System;
using System.IO;

namespace AdventOfCode2022.DayFourteen;

public class DayFourteen
{
    //private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFourteen/Day14Test.txt");

    public static void Day14(string[] input = null)
    {
        PartOne();
    }

    public static void PartOne()
    {
        string input = File.ReadAllText("../../../../AdventOfCode2022/DayFourteen/Day14.txt");
        //var input = File.ReadAllText(args[0]);
        var parser = new StoneParser();
        var solver = new Solver(parser);
        Console.WriteLine($"PART 1 - {solver.SolveForPartOne(input)}");
        Console.WriteLine($"PART 2 - {solver.SolveForPartTwo(input)}");
    }
}
