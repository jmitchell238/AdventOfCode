using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayEighteen;

public class DayEighteen
{
    static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayEighteen/DayEighteen.txt");
    //public static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayEighteen/DayEighteenTest.txt");
    public static HashSet<(int x, int y, int z)>? ObsidianCubes { get; set; } = ParseInput(Input);
    //public static HashSet<(int x, int y, int z)> XfilteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.z == cube.z).OrderBy(c => c.y);
    //public static HashSet<(int x, int y, int z)> YfilteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.z == cube.z).OrderBy(c => c.y);
    //public static HashSet<(int x, int y, int z)> ZfilteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.z == cube.z).OrderBy(c => c.y);


    public static void Day18()
    {
        var stopwatch = Stopwatch.StartNew();

        Console.WriteLine($"[{stopwatch.Elapsed}] Pre-compute");

        // Part 1: Get all surface area, interior and exterior.
        var partOneResult = PartOne();
        Console.WriteLine($"[{stopwatch.Elapsed}] Part 1: {partOneResult}");

        // Part 2: Get exterior surface area.
        var partTwoResult = PartTwo();
        Console.WriteLine($"[{stopwatch.Elapsed}] Part 1: {partTwoResult}");
    }

    public static int PartOne()
    {
        var result = 0;

        foreach (var cube in ObsidianCubes)
        {
            if (!IsCubeBelow(cube))
            {
                result++;
            }
            if (!IsCubeAbove(cube))
            {
                result++;
            }
            if (!IsCubeBesideLeft(cube))
            {
                result++;
            }
            if (!IsCubeBesideRight(cube))
            {
                result++;
            }
            if (!IsCubeInFront(cube))
            {
                result++;
            }
            if (!IsCubeBehind(cube))
            {
                result++;
            }
        }

        return result;
    }

    public static int PartTwo()
    {
        var result = 0;

        foreach (var cube in ObsidianCubes)
        {
            if (!IsCubeBelow(cube, true))
            {
                result++;
            }
            if (!IsCubeAbove(cube, true))
            {
                result++;
            }
            if (!IsCubeBesideLeft(cube, true))
            {
                result++;
            }
            if (!IsCubeBesideRight(cube, true))
            {
                result++;
            }
            if (!IsCubeInFront(cube, true))
            {
                result++;
            }
            if (!IsCubeBehind(cube, true))
            {
                result++;
            }
        }

        return result;
    }

    private static bool IsCubeBelow((int x, int y, int z) cube, bool partTwo = false)
    {
        if (ObsidianCubes.Contains((cube.x, cube.y, cube.z - 1)))
        {
            return true;
        }

        if (!partTwo)
        {
            return false;
        }

        var filteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.y == cube.y).OrderBy(c => c.z);
        if (!filteredCubes.Any()) return false;

        var minZ = filteredCubes.Min(c => c.z);
        if (cube.z == minZ) return false;

        // Find the z value of the next cube below the passed-in cube
        int zValueBelow = filteredCubes.Where(c => c.x == cube.x && c.y == cube.y && c.z < cube.z)
                                        .Select(c => c.z)
                                        .FirstOrDefault();
        if (zValueBelow == 0) return false;
        if (zValueBelow < cube.z)
        {
            var airPocket = true;
            // check each empty space between current cube and next cube
            for (int i = cube.z - 1; i > zValueBelow; i--)
            {
                // If previous cube shown there wasn't an airpocket then return false that there isn't a cube below because there is an opening.
                if (airPocket == false) return false;
                // temp cube to pass into methods
                var tempCube = (x: cube.x, y: cube.y, z: i);
                // if that cube doesn't have a cube to it's left > :  Return false
                if (IsCubeBesideLeft(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube to the right < :  Return false
                if (IsCubeBesideRight(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube behind v :  Return false
                if (IsCubeBehind(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube in front ^,  Return false
                if (IsCubeInFront(tempCube, true) == false) airPocket = false;
            }
            // If any of these cubes return false then the main cube isn't an inner cube.
            return airPocket;
        }
        else
        {
            return false;
        }
    }

    private static bool IsCubeAbove((int x, int y, int z) cube, bool partTwo = false)
    {
        if (ObsidianCubes.Contains((cube.x, cube.y, cube.z + 1)))
        {
            return true;
        }

        if (!partTwo)
        {
            return false;
        }

        var filteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.y == cube.y).OrderBy(c => c.z);
        if (!filteredCubes.Any()) return false;
        var maxZ = filteredCubes.Max(c => c.z);
        if (cube.z == maxZ) return false;

        // Find the z value of the next cube above the passed-in cube
        int zValueAbove = filteredCubes.Where(c => c.x == cube.x && c.y == cube.y && c.z > cube.z)
                                        .Select(c => c.z)
                                        .FirstOrDefault();
        if (zValueAbove == 0) return false;
        if (zValueAbove > cube.z)
        {
            var airPocket = true;
            // check each empty space between current cube and next cube
            for (int i = cube.z + 1; i < zValueAbove; i++)
            {
                // If previous cube shown there wasn't an airpocket then return false that there isn't a cube below because there is an opening.
                if (airPocket == false) return false;
                // temp cube to pass into methods
                var tempCube = (x: cube.x, y: cube.y, z: i);
                // if that cube doesn't have a cube to it's left > :  Return false
                if (IsCubeBesideLeft(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube to the right < :  Return false
                if (IsCubeBesideRight(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube behind v :  Return false
                if (IsCubeBehind(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube in front ^,  Return false
                if (IsCubeInFront(tempCube, true) == false) airPocket = false;
            }
            // If any of these cubes return false then the main cube isn't an inner cube.
            return airPocket;
        }
        else
        {
            return false;
        }
    }

    private static bool IsCubeBesideLeft((int x, int y, int z) cube, bool partTwo = false)
    {
        if (ObsidianCubes.Contains((cube.x + 1, cube.y, cube.z)))
        {
            return true;
        }

        if (!partTwo)
        {
            return false;
        }

        var filteredCubes = ObsidianCubes.Where(c => c.y == cube.y && c.z == cube.z).OrderBy(c => c.x);
        if (!filteredCubes.Any()) return false;
        var maxX = filteredCubes.Max(c => c.x);
        if (cube.x == maxX) return false;

        // Find the x value of the next cube to the left the passed-in cube
        int xValueToLeft = filteredCubes.Where(c => c.y == cube.y && c.z == cube.z && c.x > cube.x)
                                        .Select(c => c.x)
                                        .FirstOrDefault();
        if (xValueToLeft == 0) return false;
        if (xValueToLeft > cube.x)
        {
            var airPocket = true;
            // check each empty space between current cube and next cube
            for (int i = cube.x + 1; i < xValueToLeft; i++)
            {
                // If previous cube shown there wasn't an airpocket then return false that there isn't a cube below because there is an opening.
                if (airPocket == false) return false;
                // temp cube to pass into methods
                var tempCube = (x: i, y: cube.y, z: cube.z);
                // if the cube doesn't have a cube above it ^ : airPocket is false
                if (IsCubeAbove(tempCube, true) == false) airPocket = false;
                // if the cube doesn't have a cube below it v : airPocket is false
                if (IsCubeBelow(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube behind v :  Return false
                if (IsCubeBehind(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube in front ^,  Return false
                if (IsCubeInFront(tempCube, true) == false) airPocket = false;
            }
            // If any of these cubes return false then the main cube isn't an inner cube.
            return airPocket;
        }
        else
        {
            return false;
        }
    }

    private static bool IsCubeBesideRight((int x, int y, int z) cube, bool partTwo = false)
    {
        if (ObsidianCubes.Contains((cube.x - 1, cube.y, cube.z)))
        {
            return true;
        }

        if (!partTwo)
        {
            return false;
        }

        var filteredCubes = ObsidianCubes.Where(c => c.y == cube.y && c.z == cube.z).OrderBy(c => c.x);
        if (!filteredCubes.Any()) return false;
        var minX = filteredCubes.Min(c => c.x);
        if (cube.x == minX) return false;

        // Find the x value of the next cube to the right the passed-in cube
        int xValueToRight = filteredCubes.Where(c => c.y == cube.y && c.z == cube.z && c.x < cube.x)
                                        .Select(c => c.x)
                                        .FirstOrDefault();
        if (xValueToRight == 0) return false;
        if (xValueToRight < cube.x)
        {
            var airPocket = true;
            // check each empty space between current cube and next cube
            for (int i = cube.x - 1; i > xValueToRight; i--)
            {
                // If previous cube shown there wasn't an airpocket then return false that there isn't a cube below because there is an opening.
                if (airPocket == false) return false;
                // temp cube to pass into methods
                var tempCube = (x: i, y: cube.y, z: cube.z);
                // if the cube doesn't have a cube above it ^ : airPocket is false
                if (IsCubeAbove(tempCube, true) == false) airPocket = false;
                // if the cube doesn't have a cube below it v : airPocket is false
                if (IsCubeBelow(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube behind v :  Return false
                if (IsCubeBehind(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube in front ^,  Return false
                if (IsCubeInFront(tempCube, true) == false) airPocket = false;
            }
            // If any of these cubes return false then the main cube isn't an inner cube.
            return airPocket;
        }
        else
        {
            return false;
        }
    }

    private static bool IsCubeInFront((int x, int y, int z) cube, bool partTwo = false)
    {
        if (ObsidianCubes.Contains((cube.x, cube.y + 1, cube.z)))
        {
            return true;
        }

        if (!partTwo)
        {
            return false;
        }

        var filteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.z == cube.z).OrderBy(c => c.y);
        if (!filteredCubes.Any()) return false;
        var maxY = filteredCubes.Max(c => c.y);
        if (cube.y == maxY) return false;

        // Find the x value of the next cube in front of the passed-in cube
        int yValueInFront = filteredCubes.Where(c => c.x == cube.x && c.z == cube.z && c.y > cube.y)
                                        .Select(c => c.y)
                                        .FirstOrDefault();
        if (yValueInFront == 0) return false;
        if (yValueInFront > cube.y)
        {
            var airPocket = true;
            // check each empty space between current cube and next cube
            for (int i = cube.y + 1; i < yValueInFront; i++)
            {
                // If previous cube shown there wasn't an airpocket then return false that there isn't a cube below because there is an opening.
                if (airPocket == false) return false;
                // temp cube to pass into methods
                var tempCube = (x: cube.x, y: i, z: cube.z);
                // if the cube doesn't have a cube above it ^ : airPocket is false
                if (IsCubeAbove(tempCube, true) == false) airPocket = false;
                // if the cube doesn't have a cube below it v : airPocket is false
                if (IsCubeBelow(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube to it's left > :  Return false
                if (IsCubeBesideLeft(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube to the right < :  Return false
                if (IsCubeBesideRight(tempCube, true) == false) airPocket = false;
            }
            // If any of these cubes return false then the main cube isn't an inner cube.
            return airPocket;
        }
        else
        {
            return false;
        }
    }

    private static bool IsCubeBehind((int x, int y, int z) cube, bool partTwo = false)
    {
        if (ObsidianCubes.Contains((cube.x, cube.y - 1, cube.z)))
        {
            return true;
        }

        if (!partTwo)
        {
            return false;
        }

        var filteredCubes = ObsidianCubes.Where(c => c.x == cube.x && c.z == cube.z).OrderBy(c => c.y);
        if (!filteredCubes.Any()) return false;
        var minY = filteredCubes.Min(c => c.y);
        if (cube.y == minY) return false;

        // Find the x value of the next cube in front of the passed-in cube
        int yValueBehind = filteredCubes.Where(c => c.x == cube.x && c.z == cube.z && c.y < cube.y)
                                        .Select(c => c.y)
                                        .FirstOrDefault();
        if (yValueBehind == 0) return false;
        if (yValueBehind < cube.y)
        {
            var airPocket = true;
            // check each empty space between current cube and next cube
            for (int i = cube.y - 1; i > yValueBehind; i--)
            {
                // If previous cube shown there wasn't an airpocket then return false that there isn't a cube below because there is an opening.
                if (airPocket == false) return false;
                // temp cube to pass into methods
                var tempCube = (x: cube.x, y: i, z: cube.z);
                // if the cube doesn't have a cube above it ^ : airPocket is false
                if (IsCubeAbove(tempCube, true) == false) airPocket = false;
                // if the cube doesn't have a cube below it v : airPocket is false
                if (IsCubeBelow(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube to it's left > :  Return false
                if (IsCubeBesideLeft(tempCube, true) == false) airPocket = false;
                // if that cube doesn't have a cube to the right < :  Return false
                if (IsCubeBesideRight(tempCube, true) == false) airPocket = false;
            }
            // If any of these cubes return false then the main cube isn't an inner cube.
            return airPocket;
        }
        else
        {
            return false;
        }
    }

    private static HashSet<(int x, int y, int z)> ParseInput(string[] input)
    {
        HashSet<(int x, int y, int z)> data = new HashSet<(int x, int y, int z)>();

        foreach (var line in input)
        {
            int[] lineArray = line.Split(",").Select(int.Parse).ToArray();
            data.Add((lineArray[0], lineArray[1], lineArray[2]));
        }

        return data;
    }
}
