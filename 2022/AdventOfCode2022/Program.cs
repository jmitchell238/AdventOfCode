using System;

namespace AdventOfCode2022;

public static class Program
{
    public static void Main(string[] args)
    {
        Console.WriteLine("\n" + "Hello Advent of Code 2022!");

        OutputSpacer(1);
        DayOne.DayOne.Day1();
        OutputSpacer(2);
        DayTwo.DayTwo.Day2();
        OutputSpacer(3);
        DayThree.DayThree.Day3();
        OutputSpacer(4);
        DayFour.DayFour.Day4();
        OutputSpacer(5);
        DayFive.DayFive.Day5();
        OutputSpacer(6);
        DaySix.DaySix.Day6();
        OutputSpacer(7);
        DaySeven.DaySeven.Day7();
        OutputSpacer(8);
        DayEight.DayEight.Day8();
        OutputSpacer(9);
        DayNine.DayNine.Day9();
    }

    private static void OutputSpacer(int day)
    {
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine($"Day {day} Results:");
        Console.WriteLine("---------------------------------");
    }
}
