using System;
using System.IO;
using System.Linq;
using System.Text.Json.Nodes;

namespace AdventOfCode2022.DayThirteen;

public class DayThirteen
{
    // private static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DayThirteen/Day13Test.txt");
    private static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DayThirteen/Day13.txt");

    public static void Day13()
    {
        PartOneAndTwo();
    }

    public static (int, int) PartOneAndTwo(string? input = null)
    {
        input ??= Input;

        var pairs = input.Split("\n\n");
        var pairIndex = 0;
        var correctPairs = 0;

        foreach (var pair in pairs)
        {
            pairIndex++;

            var splitPair = pair.Split("\n");
            // if (splitPair != null && splitPair.Length > 1)
            // {
                var left = splitPair[0];
                var right = splitPair[1];
                var jsonLeft = JsonNode.Parse(left);
                var jsonRight = JsonNode.Parse(right);
                var isCorrect = Compare(jsonLeft, jsonRight);
                if (isCorrect == true) correctPairs += pairIndex;
            // }
        }

        Console.WriteLine($"Part 1: {correctPairs}");

        var allPackets = input.Split("\n").Where(l => !string.IsNullOrEmpty(l)).Select(l => JsonNode.Parse(l)).ToList();
        var x = JsonNode.Parse("[[2]]");
        var y = JsonNode.Parse("[[6]]");

        allPackets.Add(x);
        allPackets.Add(y);

        allPackets.Sort((left, right) => Compare(left, right) == true ? -1 : 1);

        Console.WriteLine($"Part 2: {(allPackets.IndexOf(x) + 1) * (allPackets.IndexOf(y) + 1)}");

        return (correctPairs, (allPackets.IndexOf(x) + 1) * (allPackets.IndexOf(y) + 1));
    }

    private static bool? Compare(JsonNode left, JsonNode right)
    {
        if (left is JsonValue leftVal && right is JsonValue rightVal)
        {
            return CompareValues(leftVal, rightVal);
        }

        if (left is not JsonArray leftArray) leftArray = new JsonArray(left.GetValue<int>());
        if (right is not JsonArray rightArray) rightArray = new JsonArray(right.GetValue<int>());

        return CompareArrays(leftArray, rightArray);
    }

    private static bool? CompareValues(JsonValue leftVal, JsonValue rightVal)
    {
        var leftInt = leftVal.GetValue<int>();
        var rightInt = rightVal.GetValue<int>();
        return leftInt == rightInt ? null : leftInt < rightInt;
    }

    private static bool? CompareArrays(JsonArray leftArray, JsonArray rightArray)
    {
        for (var i = 0; i < Math.Min(leftArray.Count, rightArray.Count); i++)
        {
            var res = Compare(leftArray[i], rightArray[i]);
            if (res.HasValue) { return res.Value; }
        }

        if (leftArray.Count < rightArray.Count) return true;
        if (leftArray.Count > rightArray.Count) return false;
        return null;
    }
}
