using System;
using System.IO;
using AdventOfCode2022.DayFifteen;

namespace AdventOfCode2022.DayFourteen;

public static class DayFourteen
{
    private static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DayFourteen/Day14.txt");

    public static void Day14()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
        PartOne();
        PartTwo();
    }

    private static int PartOne(string? input = null)
    {
        input ??= Input;
        
        var parser = new StoneParser();
        var solver = new Solver(parser);
        return solver.SolveForPartOne(input);
    }
    
    private static int PartTwo(string? input = null)
    {
        input ??= Input;
        
        var parser = new StoneParser();
        var solver = new Solver(parser);
        return solver.SolveForPartTwo(input);
    }
}
