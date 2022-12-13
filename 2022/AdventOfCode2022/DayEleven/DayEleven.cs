using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022.DayEleven;

public class DayEleven
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayEleven/Day11.txt");

    public static void Day11()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static double PartOne(string[]? input = null)
    {
        input ??= Input;

        var rounds = 20;
        var partOne = true;
        var result = GetMonkeyItemInspections(input, rounds, partOne);

        return result;
    }

    public static double PartTwo(string[]? input = null)
    {
        input ??= Input;

        var rounds = 10000;
        var partOne = false;
        var result = GetMonkeyItemInspections(input, rounds, partOne);

        return result;
    }

    private static double GetMonkeyItemInspections(string[]? input, int rounds, bool partOne)
    {
        List<Monkey> monkeys = new();
        GetListOfMonkeys(input, monkeys);

        // Loop through Rounds of Monkey In The Middle
        for (var i = 0; i < rounds; i++)
        {
            MonkeysPlayRound(partOne, monkeys);
        }

        //Console.WriteLine($"After {rounds} rounds:");
        //Console.WriteLine($"Monkey 0 inspections: {monkeys[0].ItemInspections}");
        //Console.WriteLine($"Monkey 1 inspections: {monkeys[1].ItemInspections}");
        //Console.WriteLine($"Monkey 2 inspections: {monkeys[2].ItemInspections}");
        //Console.WriteLine($"Monkey 3 inspections: {monkeys[3].ItemInspections}");

        var inspectedItemsList = new List<double>();
        for (var m = 0; m < monkeys.Count; m++)
        {
            inspectedItemsList.Add(monkeys[m].ItemInspections);
        }

        List<double> result = inspectedItemsList.OrderByDescending(i => i).ToList();

        return result[0] * result[1];
    }

    private static void MonkeysPlayRound(bool partOne, List<Monkey> monkeys)
    {
        // Loop over the list of monkeys each round
        foreach (Monkey monkey in monkeys)
        {
            MonkeyInspectItems(partOne, monkeys, monkey);

            monkey.Items = new List<double>();
        }
    }

    private static void MonkeyInspectItems(bool partOne, List<Monkey> monkeys, Monkey monkey)
    {
        // Loop over the monkeys items
        for (int index = 0; index < monkey?.Items?.Count; index++)
        {
            double item = monkey.Items[index];

            GetWorryMultiplier(monkey, item);

            CalculateWorryNumber(partOne, monkeys, monkey, index, item);
            ThrowItemToDifferentMonkey(monkeys, monkey, index);

            monkey.ItemInspections = monkey.ItemInspections + 1;
        }
    }

    private static void GetWorryMultiplier(Monkey monkey, double item)
    {
        if (monkey.WorryMultiplier == 0)
            monkey.WorryMultiplierOperationNum = item;
        else
            monkey.WorryMultiplierOperationNum = monkey.WorryMultiplier;
    }

    private static void ThrowItemToDifferentMonkey(List<Monkey> monkeys, Monkey monkey, int index)
    {
        if (monkey.Items[index] % monkey.DivisibleNum == 0)
        {
            var itemWorry = monkey.Items[index];
            var monkeyToThrowTo = monkey.Decisions[true];

            monkeys[monkeyToThrowTo].Items.Add(itemWorry);
        }
        else
        {
            var itemWorry = monkey.Items[index];
            var monkeyToThrowTo = monkey.Decisions[false];

            monkeys[monkeyToThrowTo].Items.Add(itemWorry);
        }
    }

    private static void CalculateWorryNumber(bool partOne, List<Monkey> monkeys, Monkey monkey, int index, double item)
    {
        if (monkey.WorryOperation == '*')
        {
            if (partOne)
            {
                monkey.Items[index] = Math.Floor((item * monkey.WorryMultiplierOperationNum) / 3);
            }
            else
            {
                var leastCommonMultiple = monkeys.Select(monkey => monkey.DivisibleNum).Aggregate((a, b) => a * b);
                monkey.Items[index] = Math.Floor((item * monkey.WorryMultiplierOperationNum) % leastCommonMultiple);
            }
        }
        else
        {
            if (partOne)
            {
                monkey.Items[index] = Math.Floor((item + monkey.WorryMultiplierOperationNum) / 3);
            }
            else
            {
                var leastCommonMultiple = monkeys.Select(monkey => monkey.DivisibleNum).Aggregate((a, b) => a * b);

                monkey.Items[index] = Math.Floor((item + monkey.WorryMultiplierOperationNum) % leastCommonMultiple);
            }
        }
    }

    private static void GetListOfMonkeys(string[]? input, List<Monkey> monkeys)
    {
        for (var i = 0; i < input.Length; i += 7)
        {
            Monkey monkey = new();

            var monkeyNum = int.Parse(input[i].Split(' ')[1].Trim(':'));
            var items = input[i + 1].Split(':')[1].Split(',');
            var monkeyItems = new List<double>();
            foreach (var item in items)
            {
                monkeyItems.Add(double.Parse(item));
            }
            monkey.Items = monkeyItems;
            var worryOperation = input[i + 2].Split('=')[1].Split(' ')[2];
            monkey.WorryOperation = char.Parse(worryOperation);
            var worryMultiplierOriginal = input[i + 2].Split('=')[1].Split(' ')[3];
            int worryMultiplier = 0;
            int.TryParse(worryMultiplierOriginal, out worryMultiplier);
            monkey.WorryMultiplier = worryMultiplier;
            monkey.DivisibleNum = int.Parse(input[i + 3].Split(' ').Last());
            var trueDecision = int.Parse(input[i + 4].Split(' ').Last());
            var falseDecision = int.Parse(input[i + 5].Split(' ').Last());
            monkey.Decisions = new Dictionary<bool, int> { { true, trueDecision }, { false, falseDecision } };

            monkeys.Add(monkey);
        }
    }
}

public class Monkey
{
    public List<double> Items { get; set; }
    public double ItemInspections { get; set; } = 0;
    public char WorryOperation { get; set; }
    public double WorryMultiplier { get; set; }
    public double WorryMultiplierOperationNum { get; set; }
    public double DivisibleNum { get; set; }
    public Dictionary<bool, int> Decisions { get; set; }
}
