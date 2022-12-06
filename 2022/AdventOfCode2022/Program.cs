using System;

namespace AdventOfCode2022;

public static class Program
{
    public static void Main(string[] args)
    {
        Console.WriteLine("\n" + "Hello Advent of Code 2022!");

        Console.WriteLine("\n---------------------------------");
        Console.WriteLine("Day 1 Results:");
        Console.WriteLine("---------------------------------");
        DayOne.DayOne.Day1();
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine("Day 2 Results:");
        Console.WriteLine("---------------------------------");
        DayTwo.DayTwo.Day2();
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine("Day 3 Results:");
        Console.WriteLine("---------------------------------");
        DayThree.DayThree.Day3();
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine("Day 4 Results:");
        Console.WriteLine("---------------------------------");
        DayFour.DayFour.Day4();
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine("Day 5 Results:");
        Console.WriteLine("---------------------------------");
        DayFive.DayFive.Day5();
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine("Day 6 Results:");
        Console.WriteLine("---------------------------------");
        DaySix.DaySix.Day6();
    }
}
