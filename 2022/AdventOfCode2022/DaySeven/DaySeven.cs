using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Runtime.ExceptionServices;

namespace AdventOfCode2022.DaySeven;

public static class DaySeven
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DaySeven/Day7.txt");

    public static void Day7()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }
    
    public static string PartOne(string[]? input = null)
    {
        input ??= Input;

        var cwd = string.Empty;
        var dirs = new Dictionary<string, Dictionary<string, int>>();

        foreach (var l in input)
        {
            if (l.Equals("$ cd .."))
            {
                var previousSlash = cwd.LastIndexOf('/', cwd.Length - 2) + 1;
                var name = cwd[previousSlash..^1];
                var parent = cwd[..previousSlash];

                dirs[parent][name] = dirs[cwd].Values.Sum();

                cwd = parent;
            }
            else if (l.StartsWith("$ cd "))
            {
                var path = l[5..];
                cwd = path.StartsWith("/") ? path : $"{cwd}{path}/";
            }
            else if (l.StartsWith("dir"))
            {
                dirs.GetOrAdd(cwd, _ => new())[l[4..]] = 0;
            }
            else if (!l.StartsWith("$ ls"))
            {
                var size = int.Parse(l.Split()[0]);
                var name = l.Split()[1];

                dirs.GetOrAdd(cwd, _ => new())[name] = size;
            }
        }

        while (cwd != "/")
        {
            var previousSlash = cwd.LastIndexOf('/', cwd.Length - 2) + 1;
            var name = cwd[previousSlash..^1];
            var parent = cwd[..previousSlash];

            dirs[parent][name] = dirs[cwd].Values.Sum();

            cwd = parent;
        }

        var part1 = dirs.Values.Select(v => v.Values.Sum())
            .Where(x => x <= 100_000)
            .Sum()
            .ToString();

        var unused = 70_000_000 - dirs["/"].Values.Sum();
        var needed = 30_000_000 - unused;

        var part2 = dirs.Values.Select(v => v.Values.Sum())
            .Where(x => x >= needed)
            .Min()
            .ToString();

        //return (part1, part2);
        return part1;
    }

    public static string PartTwo(string[]? input = null)
    {
        input ??= Input;

        var cwd = string.Empty;
        var dirs = new Dictionary<string, Dictionary<string, int>>();

        foreach (var l in input)
        {
            if (l.Equals("$ cd .."))
            {
                var previousSlash = cwd.LastIndexOf('/', cwd.Length - 2) + 1;
                var name = cwd[previousSlash..^1];
                var parent = cwd[..previousSlash];

                dirs[parent][name] = dirs[cwd].Values.Sum();

                cwd = parent;
            }
            else if (l.StartsWith("$ cd "))
            {
                var path = l[5..];
                cwd = path.StartsWith("/") ? path : $"{cwd}{path}/";
            }
            else if (l.StartsWith("dir"))
            {
                dirs.GetOrAdd(cwd, _ => new())[l[4..]] = 0;
            }
            else if (!l.StartsWith("$ ls"))
            {
                var size = int.Parse(l.Split()[0]);
                var name = l.Split()[1];

                dirs.GetOrAdd(cwd, _ => new())[name] = size;
            }
        }

        while (cwd != "/")
        {
            var previousSlash = cwd.LastIndexOf('/', cwd.Length - 2) + 1;
            var name = cwd[previousSlash..^1];
            var parent = cwd[..previousSlash];

            dirs[parent][name] = dirs[cwd].Values.Sum();

            cwd = parent;
        }

        var unused = 70_000_000 - dirs["/"].Values.Sum();
        var needed = 30_000_000 - unused;

        var part2 = dirs.Values.Select(v => v.Values.Sum())
            .Where(x => x >= needed)
            .Min()
            .ToString();

        return part2;
    }

    public static TValue GetOrAdd<TKey, TValue>(this Dictionary<TKey, TValue> dict, TKey key, Func<TKey, TValue> func)
        where TKey : notnull
    {
        if (dict.TryGetValue(key, out var value))
            return value;
        return dict[key] = func(key);
    }
}
