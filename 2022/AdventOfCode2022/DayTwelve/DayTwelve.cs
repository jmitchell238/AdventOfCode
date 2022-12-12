using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022.DayTwelve;

public class DayTwelve
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayTwelve/Day12.txt");
    private static readonly Dictionary<char, int> AlphabetDict = CreateAlphabetDict();

    public static void Day12()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static double PartOne(string[]? input = null)
    {
        input ??= Input;

        // Use a Breadth First Search

        return -1;
    }

    public static double PartTwo(string[]? input = null)
    {
        input ??= Input;

        return -1;
    }



    private static Dictionary<char, int> CreateAlphabetDict()
    {
        var dict = new Dictionary<char, int>();
        var value = 1;
        // lowercase : values = 1 - 26
        for (var i = 97; i <= 122; i++, value++) dict.Add((char)i, value);

        return dict;
    }
}
