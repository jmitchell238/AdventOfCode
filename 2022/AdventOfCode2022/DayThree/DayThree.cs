using System;
using System.Collections.Generic;
using System.IO;

namespace AdventOfCode2022.DayThree;

public class DayThree
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayThree/Day3.txt");
    private static readonly string alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void Day3()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        return getPriorityItemsSum(input);
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        return getCommonItemPerGroupSum(input);
    }

    public static int getCommonItemPerGroupSum(string[]? input = null)
    {
        var sum = 0;

        var elf1 = new Dictionary<char, int>();
        var elf2 = new Dictionary<char, int>();

        for (int i = 0; i < input.Length; i += 3)
        {
            if (string.IsNullOrEmpty(input[i])) return sum;

            elf1 = new Dictionary<char, int>();
            elf2 = new Dictionary<char, int>();

            var firstElfInGroup = input[i];
            var secondElfInGroup = input[i + 1];
            var thirdElfInGroup = input[i + 2];

            elf1 = getElfItems(firstElfInGroup);
            elf2 = getElfItems(secondElfInGroup);

            var commonItem = getCommonItem(elf1, elf2, thirdElfInGroup);

            sum += getItemValue(commonItem);
        }

        return sum;
    }

    public static char getCommonItem(Dictionary<char, int> elf1, Dictionary<char, int> elf2, string elf3)
    {
        foreach (char ch in elf3)
        {
            if (elf1.ContainsKey(ch) && elf2.ContainsKey(ch))
            {
                return ch;
            }
        }

        return '-';
    }

    public static Dictionary<char, int> getElfItems(string elfItems)
    {
        var items = new Dictionary<char, int>();

        foreach (char item in elfItems)
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

    public static int getPriorityItemsSum(string[]? input = null)
    {
        var sum = 0;

        foreach (string line in input)
        {
            var priorityItem = getDuplicateItem(line);
            sum += getItemValue(priorityItem);
        }

        return sum;
    }

    public static char getDuplicateItem(string line)
    {
        char result = '-';

        var firstHalf = line.Substring(0, (int)(line.Length / 2));
        var lastHalf = line.Substring((int)(line.Length / 2), (int)(line.Length / 2));

        var mp = new Dictionary<char, int>();

        for (int i = 0; i < firstHalf.Length; i++)
        {
            if (mp.ContainsKey(firstHalf[i]))
            {
                var val = mp[firstHalf[i]];
                mp.Remove(firstHalf[i]);
                mp.Add(firstHalf[i], val + 1);
            }
            else
            {
                mp.Add(firstHalf[i], 1);
            }
        }

        foreach (char ch in lastHalf)
        {
            if (mp.ContainsKey(ch))
            {
                result = ch;
                break;
            }
        }

        return result;
    }

    public static int getItemValue(char ch)
    {
        return alphabet.IndexOf(ch) + 1;
    }
}

