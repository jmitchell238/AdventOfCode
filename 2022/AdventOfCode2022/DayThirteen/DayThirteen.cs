using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022.DayThirteen;

public class DayThirteen
{
    // private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayThirteen/Day13.txt");
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayThirteen/Day13Test.txt");

    public static void Day13()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static double PartOne(string[]? input = null)
    {
        input ??= Input;

        // Create variable ( int[] ) to hold indices of pairs not in the right order
        // Sum those indeces together and that's the return value
        
        // Test data should be 13
        
        // Take first line, place in a temp variable (left)
        // Take second line, place in a temp variable(right)

        return -1;
    }

    public static double PartTwo(string[]? input = null)
    {
        input ??= Input;

        return -1;
    }
    
    
    
    
}
