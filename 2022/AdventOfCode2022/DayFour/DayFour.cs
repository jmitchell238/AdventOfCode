using System;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayFour;

public class DayFour
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFour/Day4.txt");

    public static void Day4()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        return getFullyContainedAssignments(input);
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        return getOverlappingAssignments(input);
    }

    public static int getFullyContainedAssignments(string[]? input = null)
    {
        input ??= Input;

        var totalFullyContainedAssignments = 0;

        foreach (var line in input)
        {
            var assignments = line.Split(',');
            int[] firstElf = getElfAssignments(assignments, 0);
            int[] secondElf = getElfAssignments(assignments, 1);


            if (firstElfAssignmentsFullyContained(firstElf, secondElf))
            {
                totalFullyContainedAssignments++;
            }
            else if (secondElfAssignmentsFullyContained(secondElf, firstElf))
            {
                totalFullyContainedAssignments++;
            }
        }

        return totalFullyContainedAssignments;
    }

    public static int getOverlappingAssignments(string[]? input = null)
    {
        input ??= Input;

        var overlappingAssignments = 0;

        foreach (var line in input)
        {
            var assignments = line.Split(',');
            var firstElf = getElfAssignments(assignments, 0);
            var secondElf = getElfAssignments(assignments, 1);

            if (assignmentInRange(firstElf[0], secondElf[0], secondElf[1])) overlappingAssignments++;
            else if (assignmentInRange(firstElf[1], secondElf[0], secondElf[1])) overlappingAssignments++;
            else if (assignmentInRange(secondElf[0], firstElf[0], firstElf[1])) overlappingAssignments++;
            else if (assignmentInRange(secondElf[1], firstElf[0], firstElf[1])) overlappingAssignments++;
        }


        return overlappingAssignments;
    }

    public static bool assignmentInRange(int numberToCheck, int bottom, int top)
    {
        return (numberToCheck >= bottom && numberToCheck <= top);
    }

    public static int[] getElfAssignments(string[] assignments, int elfIndex)
    {
        return assignments[elfIndex].Split('-').Select(n => Convert.ToInt32(n)).ToArray();

    }

    public static bool firstElfAssignmentsFullyContained(int[] firstElf, int[] secondElf)
    {
        return (firstElf[0] >= secondElf[0]) && (firstElf[1] <= secondElf[1]);
    }

    public static bool secondElfAssignmentsFullyContained(int[] secondElf, int[] firstElf)
    {
        return (secondElf[0] >= firstElf[0]) && (secondElf[1] <= firstElf[1]);
    }

}
