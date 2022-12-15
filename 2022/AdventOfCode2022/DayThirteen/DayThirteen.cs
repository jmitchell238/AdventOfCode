using System;
using System.IO;
using System.Linq;
using System.Text.Json.Nodes;
using static AdventOfCode2022.DayThirteen.CompareClass;

namespace AdventOfCode2022.DayThirteen;

public static class DayThirteen
{
    // private static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DayThirteen/Day13Test.txt");
    private static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DayThirteen/Day13.txt");

    public static void Day13()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    private static int PartOne(string? input = null)
    {
        input ??= Input;

        var pairs = input.Split("\n\n");
        var pairIndex = 0;
        var correctPairs = 0;

        foreach (var pair in pairs)
        {
            pairIndex++;

            var splitPair = pair.Split("\n");
            var left = splitPair[0];
            var right = splitPair[1];
            var jsonLeft = JsonNode.Parse(left);
            var jsonRight = JsonNode.Parse(right);
            var isCorrect = Compare(jsonLeft, jsonRight);
            if (isCorrect == true) correctPairs += pairIndex;
        }

        return correctPairs;
    }
    
    private static int PartTwo(string? input = null)
    {
        input ??= Input;

        var allPackets = input.Split("\n").Where(l => !string.IsNullOrEmpty(l)).Select(l => JsonNode.Parse(l)).ToList();
        var x = JsonNode.Parse("[[2]]");
        var y = JsonNode.Parse("[[6]]");

        allPackets.Add(x);
        allPackets.Add(y);

        allPackets.Sort((left, right) => Compare(left, right) == true ? -1 : 1);

        return (allPackets.IndexOf(x) + 1) * (allPackets.IndexOf(y) + 1);
    }
}
