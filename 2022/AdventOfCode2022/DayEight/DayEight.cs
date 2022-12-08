using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayEight;

public class DayEight
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayEight/Day8.txt");

    public static void Day8()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }
    
    public static int PartOne(string[]? input = null)
    {
        input ??= Input;
        
        var visible = 0;
        // Loop on the Row (top to bottom)
        for(var i = 0; i < input.Length; i++)
        {
            // Loop on the Column (left to right)
            for(var j = 0; j < input[i].Length;j++)
            {
                // j = y(column)
                // i = x(row)
                // 1/-1/0 = xd(value next to startValue on row)
                // 1/-1/0 = yd(value next to startValue on column)
                // input[i][j] = startValue
                
                // Visible from directions:
                var left = CountVisible(input, j, i, -1, 0, input[i][j]);
                var right= CountVisible(input, j, i, 1, 0, input[i][j]);
                var up = CountVisible(input, j, i, 0, -1, input[i][j]);
                var down= CountVisible(input, j, i, 0, 1, input[i][j]);
                
                // If it is visible from any side, add one to the count.
                if (up || down || left || right)
                {
                    visible++;
                }
            }
        }

        return visible;
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;
        
        List<int> scenicScores = new List<int>();
        for (int i = 0; i < input.Length; i++)
        {
            for (int j = 0; j < input[i].Length; j++)
            {
                int left = CountScenicScore(input,j, i, -1, 0, input[i][j]);
                int right = CountScenicScore(input, j, i, 1, 0, input[i][j]);
                int up = CountScenicScore(input, j, i, 0, -1, input[i][j]);
                int down = CountScenicScore(input, j, i, 0, 1, input[i][j]);
                scenicScores.Add(left * right * up * down);
            }
        }
        
        return +scenicScores.Max();
    }

    public static bool CountVisible(string[] input, int x, int y, int xd, int yd, char startValue)
    {
        while (true)
        {
            // If the startValue is on the exterior of grid, return TRUE, yes it is visible.
            if (x == 0 || x == input.Length - 1 || y == 0 || y == input.Length - 1)
            {
                return true;
            }
            
            // If the startValue is less or equal to than the value to left / right, return false.
            if (startValue <= input[y + yd][x + xd])
            {
                return false;
            }

            x = x + xd;
            y = y + yd;
        }
    }

    public static int CountScenicScore(string[] input, int x, int y, int xd, int yd, char startValue)
    {
        if (x == 0 || x == input.Length - 1 || y == 0 || y == input.Length - 1)
            return 0;
        if (startValue <= input[y + yd][x + xd])
            return 1 ;
    
        return 1 + CountScenicScore(input, x + xd,y + yd,xd, yd, startValue);
    }
}
