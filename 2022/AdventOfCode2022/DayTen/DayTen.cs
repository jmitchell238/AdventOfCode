using System;
using System.Collections.Generic;
using System.IO;

namespace AdventOfCode2022.DayTen;

public class DayTen
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayTen/Day10.txt");

    public static void Day10()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine("Part 2:");
        PartTwo();
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        var result = ExecuteAssemblyCode(false, input);

        return result;
    }

    public static void PartTwo(string[]? input = null)
    {
        input ??= Input;

        _ = ExecuteAssemblyCode(true, input);
    }

    public static int ExecuteAssemblyCode(bool printToCrt, string[]? input = null)
    {
        // 2 Operations possiible 
        // addx - 2 clock cycles, adds value to x register
        // noop - 1 clock cycle, nothing happens
        // x register starts at 1


        // Signal Strength = Clock Cycle * Register X Value
        // This happens on the 20th cycle, and every 40 cycles after
        // 20, 60, 100, 140, 180, 220.... 

        // Cycle:
        // 20 : Register x = 21 : Signal Strength = 20 * 21 = 420 // Middle of a addx call so x is the value before this operation completes
        // 60 : Register x = 19 : Signal Strength = 60 * 19 = 1,140
        // 100 : Register x = 18 : Signal Strength = 100 * 18 = 1,800
        // 140 : Register x = 21 : Signal Strength = 140 * 21 = 2,940
        // 180 : Register x = 16 : Signal Strength = 180 * 16 = 2,880
        // 220 : Register x = 18 : Signal Strength = 220 * 18 = 3,960

        // Sum of all Signal Strenghts = 13,140
        // Get sum of these 6 signal stengths (20, 60, 100, 140, 180, 220)

        var currentClockCycle = 0;
        var registerX = 1;
        var signalStrengths = new Dictionary<int, int>();
        var totalCycles = 1;
        var j = 0;

        for (var i = 0; j < input.Length; i++, j++)
        {
            string[] lineArray = input[j].Split(' ');

            if (lineArray[0] == "noop")
            {
                totalCycles++;
                currentClockCycle++;

                DrawCrt(currentClockCycle, registerX, printToCrt);
                signalStrengths.Add(currentClockCycle, registerX * currentClockCycle);
            }

            if (lineArray[0] == "addx")
            {
                totalCycles += 2;
                currentClockCycle++;
                DrawCrt(currentClockCycle, registerX, printToCrt);
                signalStrengths.Add(currentClockCycle, registerX * currentClockCycle);
                currentClockCycle++;
                DrawCrt(currentClockCycle, registerX, printToCrt);
                i++;
                signalStrengths.Add(currentClockCycle, registerX * currentClockCycle);
                registerX += int.Parse(lineArray[1]);
            }

        }

        return GetSignalStrengthSum(signalStrengths);
    }

    public static int GetSignalStrengthSum(Dictionary<int, int> signalStrengths) 
    {
        return signalStrengths[20]  + signalStrengths[60] + signalStrengths[100] + signalStrengths[140] + signalStrengths[180] + signalStrengths[220];
    }

    public static void DrawCrt(int currentClockCycle, int middlePixel, bool printToCrt)
    {
        if (!printToCrt) return;

        if (currentClockCycle == 1)
        {
            Console.WriteLine($"Beginning Writing to CRT:");
            Console.WriteLine("----------------------------------------");
        }

        WritePixelOnScreen(currentClockCycle, middlePixel);
    }

    public static void WritePixelOnScreen(int currentClockCycle, int middlePixel)
    {
        var onScreenPixel = currentClockCycle - 1;

        onScreenPixel = onScreenPixel switch
        {
            >= 0 and < 40 => onScreenPixel,
            >= 40 and < 80 => onScreenPixel -= 40,
            >= 80 and < 120 => onScreenPixel -= 80,
            >= 120 and < 160 => onScreenPixel -= 120,
            >= 160 and < 200 => onScreenPixel -= 160,
            >= 200 and < 240 => onScreenPixel - +200,
            >= 240 => -1
        };

        if (onScreenPixel == -1) return;

        var lowPixelValue = onScreenPixel - 1;
        var highPixelValue = onScreenPixel + 1;

        if (currentClockCycle is 41 or 81 or 121 or 161 or 201) 
            Console.WriteLine();
        if (middlePixel >= lowPixelValue && middlePixel <= highPixelValue) 
            Console.Write("#");
        else 
            Console.Write(".");   
    }
    public static int CheckClockCycle(int currentClockCycle)
    {
        if (currentClockCycle == 20)
            return currentClockCycle;
        if (currentClockCycle == 60)
            return currentClockCycle;
        if (currentClockCycle == 100)
            return currentClockCycle;
        if (currentClockCycle == 140)
            return currentClockCycle;
        if (currentClockCycle == 180)
            return currentClockCycle;
        if (currentClockCycle == 220)
            return currentClockCycle;

        return currentClockCycle;
    }

}
