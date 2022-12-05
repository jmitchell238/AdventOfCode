using System;
using System.IO;
using System.Text;
using System.Collections.Generic;

namespace AdventOfCode2022.DayFive;


public class DayFive
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFive/Day5.txt");

    public static void Day5()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static string PartOne(string[]? input = null)
    {
        input ??= Input;

        return getFinalTopCratesCrateMover9000(input);
    }

    public static string PartTwo(string[]? input = null)
    {
        input ??= Input;

        return getFinalTopCratesCrateMover9001(input);
    }

    public static string getFinalTopCratesCrateMover9000(string[]? input = null)
    {
        input ??= Input;

        List<string> inp = new List<string>();

        foreach (string line in input)
        {
            inp.Add(line);
        }

        var init = new List<List<char>>();
        var stacks = new List<Stack<char>>();

        int i;
        for (i = 1; i < inp[0].Length; i += 4)
        {
            init.Add(new List<char>());
            stacks.Add(new Stack<char>());
        }
        for (i = 0; inp[i].IndexOf("[") != -1; i++)
        {
            for (int j = 1; j < inp[i].Length; j += 4)
            {
                if (inp[i][j] != ' ')
                    init[j / 4].Add(inp[i][j]);
            }
        }
        i += 2;

        for (int j = 0; j < init.Count; j++)
        {
            for (int k = init[j].Count - 1; k >= 0; k--)
            {
                stacks[j].Push(init[j][k]);
            }
        }

        var lift = new Stack<char>();
        for (; i < inp.Count; i++)
        {
            var cmd = inp[i].SplitToStringArray(" ", true);
            int moveCount = int.Parse(cmd[1]);
            int moveFrom = int.Parse(cmd[3]) - 1;
            int moveTo = int.Parse(cmd[5]) - 1;
            for (int j = 0; j < moveCount; j++)
            {
                stacks[moveTo].Push(stacks[moveFrom].Pop());
            }
        }

        StringBuilder result = new StringBuilder();
        for (int j = 0; j < stacks.Count; j++)
        {
            result.Append(stacks[j].Peek());
        }

        return result.ToString();
    }

    public static string getFinalTopCratesCrateMover9001(string[]? input = null)
    {
        input ??= Input;

        List<string> inp = new List<string>();

        foreach (string line in input)
        {
            inp.Add(line);
        }

        var init = new List<List<char>>();
        var stacks2 = new List<Stack<char>>();

        int i;
        for (i = 1; i < inp[0].Length; i += 4)
        {
            init.Add(new List<char>());
            stacks2.Add(new Stack<char>());
        }
        for (i = 0; inp[i].IndexOf("[") != -1; i++)
        {
            for (int j = 1; j < inp[i].Length; j += 4)
            {
                if (inp[i][j] != ' ')
                    init[j / 4].Add(inp[i][j]);
            }
        }
        i += 2;

        for (int j = 0; j < init.Count; j++)
        {
            for (int k = init[j].Count - 1; k >= 0; k--)
            {
                stacks2[j].Push(init[j][k]);
            }
        }

        var lift = new Stack<char>();
        for (; i < inp.Count; i++)
        {
            var cmd = inp[i].SplitToStringArray(" ", true);
            int moveCount = int.Parse(cmd[1]);
            int moveFrom = int.Parse(cmd[3]) - 1;
            int moveTo = int.Parse(cmd[5]) - 1;
            for (int j = 0; j < moveCount; j++)
            {
                lift.Push(stacks2[moveFrom].Pop());
            }
            for (int j = 0; j < moveCount; j++)
            {
                stacks2[moveTo].Push(lift.Pop());
            }

        }

        StringBuilder result = new StringBuilder();
        for (int j = 0; j < stacks2.Count; j++)
        {
            result.Append(stacks2[j].Peek());
        }

        return result.ToString();
    }
}

