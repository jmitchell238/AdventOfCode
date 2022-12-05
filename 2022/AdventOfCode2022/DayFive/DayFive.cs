using System;
using System.IO;
using System.Text;
using System.Collections.Generic;
using System.Linq;

namespace AdventOfCode2022.DayFive;


public static class DayFive
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFive/Day5.txt");

    public static void Day5()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    private static string PartOne(string[]? input = null)
    {
        input ??= Input;

        return GetFinalTopCratesCrateMover9000(input);
    }

    private static string PartTwo(string[]? input = null)
    {
        input ??= Input;

        return GetFinalTopCratesCrateMover9001(input);
    }

    public static string GetFinalTopCratesCrateMover9000(string[]? input = null)
    {
        input ??= Input;

        var inp = CreateStack(input, out var stacks, out var i);

        for (; i < inp.Count; i++)
        {
            var moveCount = MoveCount(inp, i, out var moveFrom, out var moveTo);
            for (var j = 0; j < moveCount; j++)
            {
                stacks[moveTo].Push(stacks[moveFrom].Pop());
            }
        }

        return GetTopCrateLettersFromStacks(stacks).ToString();
    }

    public static string GetFinalTopCratesCrateMover9001(string[]? input = null)
    {
        input ??= Input;

        var inp = CreateStack(input, out var stacks, out var i);

        var lift = new Stack<char>();
        for (; i < inp.Count; i++)
        {
            var moveCount = MoveCount(inp, i, out var moveFrom, out var moveTo);
            for (var j = 0; j < moveCount; j++)
            {
                lift.Push(stacks[moveFrom].Pop());
            }
            for (var j = 0; j < moveCount; j++)
            {
                stacks[moveTo].Push(lift.Pop());
            }

        }

        return GetTopCrateLettersFromStacks(stacks).ToString();
    }
    
    private static int MoveCount(List<string> inp, int i, out int moveFrom, out int moveTo)
    {
        var cmd = inp[i].SplitToStringArray(" ", true);
        var moveCount = int.Parse(cmd[1]);
        moveFrom = int.Parse(cmd[3]) - 1;
        moveTo = int.Parse(cmd[5]) - 1;
        return moveCount;
    }

    private static List<string> CreateStack(string[] input, out List<Stack<char>> stacks, out int i)
    {
        var inp = input.ToList();

        var init = new List<List<char>>();
        stacks = new List<Stack<char>>();

        for (i = 1; i < inp[0].Length; i += 4)
        {
            init.Add(new List<char>());
            stacks.Add(new Stack<char>());
        }

        for (i = 0; inp[i].IndexOf("[") != -1; i++)
        {
            for (var j = 1; j < inp[i].Length; j += 4)
            {
                if (inp[i][j] != ' ')
                    init[j / 4].Add(inp[i][j]);
            }
        }

        i += 2;

        for (var j = 0; j < init.Count; j++)
        {
            for (var k = init[j].Count - 1; k >= 0; k--)
            {
                stacks[j].Push(init[j][k]);
            }
        }
        
        return inp;
    }

    private static StringBuilder GetTopCrateLettersFromStacks(List<Stack<char>> stacks)
    {
        var result = new StringBuilder();
        foreach (var crate in stacks)
        {
            result.Append(crate.Peek());
        }

        return result;
    }
}

