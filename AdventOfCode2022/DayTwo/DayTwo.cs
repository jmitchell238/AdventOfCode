using System;
using System.IO;

namespace AdventOfCode2022.DayTwo;

public class DayTwo
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayTwo/Day2.txt");

    public static void Day2()
    {
        Console.Write("Part 1: ");
        PartOne();
        Console.Write("Part 2: ");
        PartTwo();
    }

    public static int PartOne(string[]? input = null)
    {
        input ??= Input;

        var score = CalculateGameScore(input);
        
        Console.WriteLine(score);

        return score;
    }

    public static int PartTwo(string[]? input = null)
    {
        input ??= Input;

        var score = CalculateGameScoreCorrected(input);

        Console.WriteLine(score);

        return score;
    }

    private static int CalculateGameScore(string[] input)
    {
        var sum = 0;
        foreach (var line in input)
        {
            // A =   Rock   = X (1 point)
            // B =   Paper  = Y (2 points)
            // C = Scissors = Z (3 points)
            
            // WIN = 6 points
            // DRAW = 3 points
            // LOSS = 0 points
            
            string[] round = line.Split(' ');
            var opponentChoice = round[0];
            var playerChoice = round[1];

            if (opponentChoice == "A") // ROCK
            {
                if (playerChoice == "X") sum += 4; // DRAW
                if (playerChoice == "Y") sum += 8; // WIN
                if (playerChoice == "Z") sum += 3; // LOSS
            }
            else if (opponentChoice == "B") // PAPER
            {
                if (playerChoice == "X") sum += 1; // LOSS 
                if (playerChoice == "Y") sum += 5; // DRAW
                if (playerChoice == "Z") sum += 9; // WIN
            }
            else if (opponentChoice == "C") // SCISSORS
            {
                if (playerChoice == "X") sum += 7; // WIN
                if (playerChoice == "Y") sum += 2; // LOSS
                if (playerChoice == "Z") sum += 6; // DRAW
            }
        }

        return sum;
    }

    private static int CalculateGameScoreCorrected(string[] input)
    {
        
        var sum = 0;
        foreach (var line in input)
        {
            // A =   Rock   = X (1 point)
            // B =   Paper  = Y (2 points)
            // C = Scissors = Z (3 points)
            
        
            // WIN = 6 points
            // DRAW = 3 points
            // LOSS = 0 points
            
            // X = lose
            // Y = draw
            // Z = win
            
            string[] round = line.Split(' ');
            var opponentChoice = round[0];
            var playerChoice = round[1];

            if (opponentChoice == "A") // ROCK
            {
                if (playerChoice == "X") sum += 3; // LOSS
                if (playerChoice == "Y") sum += 4; // DRAW
                if (playerChoice == "Z") sum += 8; // WIN
            }
            else if (opponentChoice == "B") // PAPER
            {
                if (playerChoice == "X") sum += 1; // LOSS 
                if (playerChoice == "Y") sum += 5; // DRAW
                if (playerChoice == "Z") sum += 9; // WIN
            }
            else if (opponentChoice == "C") // SCISSORS
            {
                if (playerChoice == "X") sum += 2; // LOSS
                if (playerChoice == "Y") sum += 6; // DRAW
                if (playerChoice == "Z") sum += 7; // WIN
            }
        }

        return sum;
    }
}
