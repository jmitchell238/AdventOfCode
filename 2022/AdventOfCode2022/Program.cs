using System;
using System.Linq.Expressions;

namespace AdventOfCode2022;

public static class Program
{
    public static void Main(string[] args)
    {
        Console.WriteLine("\n" + "Hello Advent of Code 2022!");

        RunDaySolution(18);
        
    }

    private static void OutputSpacer(int day)
    {
        Console.WriteLine("\n---------------------------------");
        Console.WriteLine($"Day {day} Results:");
        Console.WriteLine("---------------------------------");
    }

    private static void RunDaySolution(int day)
    {
        OutputSpacer(day);


        if (day == 1)
            DayOne.DayOne.Day1();
        else if (day == 2)
            DayTwo.DayTwo.Day2();
        else if (day == 3)
            DayThree.DayThree.Day3();
        else if (day == 4)
            DayFour.DayFour.Day4();
        else if (day == 5)
            DayFive.DayFive.Day5();
        else if (day == 6)
            DaySix.DaySix.Day6();
        else if (day == 7)
            DaySeven.DaySeven.Day7();
        else if (day == 8)
            DayEight.DayEight.Day8();
        else if (day == 9)
            DayNine.DayNine.Day9();
        else if (day == 10)
            DayTen.DayTen.Day10();
        else if (day == 11)
            DayEleven.DayEleven.Day11();
        else if (day == 12)
            DayTwelve.DayTwelve.Day12();
        else if (day == 13)
            DayThirteen.DayThirteen.Day13();
        else if (day == 14)
            DayFourteen.DayFourteen.Day14();
        else if (day == 15)
            DayFifteen.DayFifteen.Day15();
        else if (day == 16)
            DaySixteen.DaySixteen.Day16();
        else if (day == 17)
            DaySeventeen.DaySeventeen.Day17();
        else if (day == 18)
            DayEighteen.DayEighteen.Day18();
        // else if (day == 99)
        //     DaySixteen.TestingClass.Day16();
        else
            Console.WriteLine("Day Not Implemented");
    }
    
}
