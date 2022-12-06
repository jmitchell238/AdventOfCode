using System;
using System.IO;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using static AdventOfCode2022.HelperFunctions;

namespace AdventOfCode2022.DayFive;


public static class DayFive
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFive/Day5.txt");
    private static readonly string[] StacksInput = File.ReadAllLines("../../../../AdventOfCode2022/DayFive/Day5Stacks.txt");
    private static readonly List<Stack<char>> Stacks1 = CreateStackList();
    private static readonly List<Stack<char>> Stacks2 = CreateStackList();

    private static List<Stack<char>> CreateStackList()
    {
        return StacksInput.Select(CreateStack).ToList();
    }

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

    public static string GetFinalTopCratesCrateMover9000(string[]? input = null, List<Stack<char>>? stack = null)
    {
        input ??= Input;
        stack ??= Stacks1;

        var inp = input.ToList();
        
        for (var i = 0; i < inp.Count; i++)
        {
            var moveCount = MoveCount(inp, i, out var moveFrom, out var moveTo);
            
            for (var j = 0; j < moveCount; j++)
            {
                stack[moveTo].Push(stack[moveFrom].Pop());
            }
        }

        return GetTopCrateLettersFromStacks(stack);
    }

    public static string GetFinalTopCratesCrateMover9001(string[]? input = null, List<Stack<char>>? stacks = null)
    {
        input ??= Input;
        stacks ??= Stacks2;

        var inp = input.ToList();
        
        var lift = new Stack<char>();
        for (var i = 0; i < inp.Count; i++)
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

        return GetTopCrateLettersFromStacks(stacks);
    }
    
    private static int MoveCount(List<string> inp, int i, out int moveFrom, out int moveTo)
    {
        var cmd = inp[i].SplitToStringArray(" ", true);
        var moveCount = int.Parse(cmd[1]);
        moveFrom = int.Parse(cmd[3]) - 1;
        moveTo = int.Parse(cmd[5]) - 1;
        return moveCount;
    }

    private static string GetTopCrateLettersFromStacks(List<Stack<char>> stacks)
    {
        var result = new StringBuilder();
        foreach (var crate in stacks)
        {
            result.Append(crate.Peek());
        }

        return result.ToString();
    }
}

