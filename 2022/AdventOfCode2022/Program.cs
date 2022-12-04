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
    }
}
