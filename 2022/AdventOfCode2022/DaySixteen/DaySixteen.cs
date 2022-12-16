using System;
using System.Collections.Generic;
using System.IO;

namespace AdventOfCode2022.DaySixteen;

public class DaySixteen
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DaySixteen/DaySixteen.txt");

    public static void Day16()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;
        
        //input = File.ReadAllLines("../../../../AdventOfCode2022/DaySixteen/DaySixteenTest.txt");
        



        Console.WriteLine("Part  1 not implemented yet");
        return -1;
    }
    
    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;
        
        //input = File.ReadAllLines("../../../../AdventOfCode2022/DaySixteen/DaySixteenTest.txt");

        Console.WriteLine("Part 2 not implemented yet");
        return -1;
    }
}

public class Tunnel
{
    private string tunnel;
    private int flowRate;
    private List<string> leadsToTunnels;

    public void Tunnel(string tunnel, int flowRate, List<string> leadsToTunnels)
    {
        
    }
}

