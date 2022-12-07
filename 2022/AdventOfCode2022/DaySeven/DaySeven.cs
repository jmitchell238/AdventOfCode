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

        var dirs = PrepareDirsDictionary(input);

        var totalSizes = dirs.Values.Select(v => v.Values.Sum())
            .Where(x => x <= 100_000)
            .Sum()
            .ToString();

        return totalSizes;
    }

    public static string PartTwo(string[]? input = null)
    {
        input ??= Input;

        var dirs = PrepareDirsDictionary(input);

        var unused = 70_000_000 - dirs["/"].Values.Sum();
        var needed = 30_000_000 - unused;

        var smallestDirDeleteSize = dirs.Values.Select(v => v.Values.Sum())
            .Where(x => x >= needed)
            .Min()
            .ToString();

        return smallestDirDeleteSize;
    }
    
    private static Dictionary<string, Dictionary<string, int>> PrepareDirsDictionary(string[] input)
    {
        var currentWorkingDir = string.Empty;
        var dirs = new Dictionary<string, Dictionary<string, int>>();

        foreach (var line in input)
        {
            if (line.Equals("$ cd .."))
            {
                var previousSlash = currentWorkingDir.LastIndexOf('/', currentWorkingDir.Length - 2) + 1;
                var name = currentWorkingDir[previousSlash..^1];
                var parent = currentWorkingDir[..previousSlash];

                dirs[parent][name] = dirs[currentWorkingDir].Values.Sum();

                currentWorkingDir = parent;
            }
            else if (line.StartsWith("$ cd "))
            {
                var path = line[5..];
                currentWorkingDir = path.StartsWith("/") ? path : $"{currentWorkingDir}{path}/";
            }
            else if (line.StartsWith("dir"))
            {
                dirs.GetOrAdd(currentWorkingDir, _ => new())[line[4..]] = 0;
            }
            else if (!line.StartsWith("$ ls"))
            {
                var size = int.Parse(line.Split()[0]);
                var name = line.Split()[1];

                dirs.GetOrAdd(currentWorkingDir, _ => new())[name] = size;
            }
        }

        while (currentWorkingDir != "/")
        {
            var previousSlash = currentWorkingDir.LastIndexOf('/', currentWorkingDir.Length - 2) + 1;
            var name = currentWorkingDir[previousSlash..^1];
            var parent = currentWorkingDir[..previousSlash];

            dirs[parent][name] = dirs[currentWorkingDir].Values.Sum();

            currentWorkingDir = parent;
        }

        return dirs;
    }
}
