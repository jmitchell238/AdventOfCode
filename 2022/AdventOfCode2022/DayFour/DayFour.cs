using System;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayFour;

public static class DayFour
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFour/Day4.txt");

    public static void Day4()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    private static int PartOne(string[]? input = null)
    {
        input ??= Input;

        return GetFullyContainedAssignments(input);
    }

    private static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        return GetOverlappingAssignments(input);
    }

    public static int GetFullyContainedAssignments(string[]? input = null)
    {
        input ??= Input;

        var totalFullyContainedAssignments = 0;

        foreach (var line in input)
        {
            var assignments = line.Split(',');
            var firstElf = GetElfAssignments(assignments, 0);
            var secondElf = GetElfAssignments(assignments, 1);

            if (FirstElfAssignmentsFullyContained(firstElf, secondElf))
            {
                totalFullyContainedAssignments++;
            }
            else if (SecondElfAssignmentsFullyContained(secondElf, firstElf))
            {
                totalFullyContainedAssignments++;
            }
        }

        return totalFullyContainedAssignments;
    }

    public static int GetOverlappingAssignments(string[]? input = null)
    {
        input ??= Input;

        var overlappingAssignments = 0;

        foreach (var line in input)
        {
            var assignments = line.Split(',');
            var firstElf = GetElfAssignments(assignments, 0);
            var secondElf = GetElfAssignments(assignments, 1);

            if (AssignmentInRange(firstElf[0], secondElf[0], secondElf[1])) overlappingAssignments++;
            else if (AssignmentInRange(firstElf[1], secondElf[0], secondElf[1])) overlappingAssignments++;
            else if (AssignmentInRange(secondElf[0], firstElf[0], firstElf[1])) overlappingAssignments++;
            else if (AssignmentInRange(secondElf[1], firstElf[0], firstElf[1])) overlappingAssignments++;
        }


        return overlappingAssignments;
    }

    private static bool AssignmentInRange(int numberToCheck, int bottom, int top)
    {
        return (numberToCheck >= bottom && numberToCheck <= top);
    }

    private static int[] GetElfAssignments(string[] assignments, int elfIndex)
    {
        return assignments[elfIndex].Split('-').Select(n => Convert.ToInt32(n)).ToArray();

    }

    private static bool FirstElfAssignmentsFullyContained(int[] firstElf, int[] secondElf)
    {
        return (firstElf[0] >= secondElf[0]) && (firstElf[1] <= secondElf[1]);
    }

    private static bool SecondElfAssignmentsFullyContained(int[] secondElf, int[] firstElf)
    {
        return (secondElf[0] >= firstElf[0]) && (secondElf[1] <= firstElf[1]);
    }
}
