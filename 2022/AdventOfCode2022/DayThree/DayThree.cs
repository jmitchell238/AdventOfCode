using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayThree;

public static class DayThree
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayThree/Day3.txt");
    private const string Alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void Day3()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        return GetPriorityItemsSum(input);
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        return GetCommonItemPerGroupSum(input);
    }

    public static int GetCommonItemPerGroupSum(string[]? input = null)
    {
        var sum = 0;

        for (var i = 0; i < input!.Length; i += 3)
        {
            if (string.IsNullOrEmpty(input[i])) return sum;

            var firstElfInGroup = input[i];
            var secondElfInGroup = input[i + 1];
            var thirdElfInGroup = input[i + 2];
            var elf1 = GetElfItems(firstElfInGroup);
            var elf2 = GetElfItems(secondElfInGroup);
            var commonItem = GetCommonItem(elf1, elf2, thirdElfInGroup);

            sum += GetItemValue(commonItem);
        }

        return sum;
    }

    public static char GetCommonItem(Dictionary<char, int> elf1, Dictionary<char, int> elf2, string elf3)
    {
        foreach (var ch in elf3.Where(ch => elf1.ContainsKey(ch) && elf2.ContainsKey(ch))) return ch;
        return '-';
    }

    public static Dictionary<char, int> GetElfItems(string elfItems)
    {
        var items = new Dictionary<char, int>();

        foreach (var item in elfItems)
        {
            if (items.ContainsKey(item))
            {
                var val = items[item];
                items.Remove(item);
                items.Add(item, val + 1);
            }
            else
            {
                items.Add(item, 1);
            }
        }

        return items;
    }

    public static int GetPriorityItemsSum(IEnumerable<string>? input = null)
    {
        return input!.Select(GetDuplicateItem).Select(GetItemValue).Sum();
    }

    public static char GetDuplicateItem(string line)
    {
        var result = '-';

        var firstHalf = line[..(line.Length / 2)];
        var lastHalf = line.Substring(line.Length / 2, line.Length / 2);

        var mp = new Dictionary<char, int>();

        foreach (var item in firstHalf)
        {
            if (mp.ContainsKey(item))
            {
                var val = mp[item];
                mp.Remove(item);
                mp.Add(item, val + 1);
            }
            else
            {
                mp.Add(item, 1);
            }
        }

        foreach (var ch in lastHalf.Where(ch => mp.ContainsKey(ch)))
        {
            result = ch;
            break;
        }

        return result;
    }

    public static int GetItemValue(char ch)
    {
        return Alphabet.IndexOf(ch) + 1;
    }
}

