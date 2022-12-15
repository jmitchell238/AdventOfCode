using BenchmarkDotNet.Characteristics;
using CommandLine;
using Dia2Lib;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace AdventOfCode2022.DayFourteen;

public class DayFourteen
{
    //private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayFourteen/Day14Test.txt");

    public static void Day14(string[] input = null)
    {
        PartOne();
    }

    public static void PartOne()
    {
        string input = File.ReadAllText("../../../../AdventOfCode2022/DayFourteen/Day14.txt");
        //var input = File.ReadAllText(args[0]);
        var parser = new StoneParser();
        var solver = new Solver(parser);
        Console.WriteLine($"PART 1 - {solver.SolveForPartOne(input)}");
        Console.WriteLine($"PART 2 - {solver.SolveForPartTwo(input)}");
    }
}

public class StoneParser
{
    private int _maxX;
    private int _maxY;

    public ParserResult Parse(string input)
    {
        _maxX = 0;
        _maxY = 0;

        var paths = string.IsNullOrEmpty(input)
            ? new List<List<Point>>()
            : input
                .Trim()
                .Split('\n')
                .Select(ParseLine)
                .ToList();

        // "+ 1" because path range in inclusive, so _maxX and _maxY are the biggest valid indexes.
        return new ParserResult(paths, _maxX + 1, _maxY + 1);
    }

    private List<Point> ParseLine(string line) =>
        line
            .Split(" -> ")
            .Select(ParseCoordinate)
            .ToList();

    private Point ParseCoordinate(string coordinateString)
    {
        var segments = coordinateString.Split(',');
        if (segments.Length != 2)
        {
            throw new FormatException($"Expected two numbers separated by a comma. Got: \"{coordinateString}\"");
        }

        var coordinate = new Point(int.Parse(segments[0]), int.Parse(segments[1]));

        _maxX = Math.Max(_maxX, coordinate.X);
        _maxY = Math.Max(_maxY, coordinate.Y);

        return coordinate;
    }
}

public class Solver
{
    private readonly StoneParser _parser;

    public Solver(StoneParser parser) => _parser = parser;

    public int SolveForPartOne(string input)
    {
        var parseResults = _parser.Parse(input);
        var cave = new CaveSlice(parseResults.Width, parseResults.Height, parseResults.Paths);

        var counter = 0;
        var result = SandResult.Stopped;
        while (result is SandResult.Stopped)
        {
            result = cave.SimulateGrainOfSand();
            if (result is SandResult.Stopped)
            {
                ++counter;
            }
        }

        return counter;
    }

    public int SolveForPartTwo(string input)
    {
        const int floorOffset = 2;

        var (paths, width, height) = _parser.Parse(input);

        height += floorOffset;

        var padding = width;

        paths.Add(new List<Point>
        {
            new (0, height - 1),
            new (width + padding - 1, height - 1),
        });

        var cave = new CaveSlice(width, height, paths, padding);

        var counter = 0;
        var result = SandResult.Stopped;
        while (result is SandResult.Stopped)
        {
            result = cave.SimulateGrainOfSand();
            if (result is SandResult.Stopped or SandResult.SourceBlocked)
            {
                ++counter;
            }
            // For debugging Advent of Code Part 2 example output with padding.
            // var visualization = cave.VisualizeChunk(new Point(488 + padding, 0), new Point(512 + padding, 11));
        }


        return counter;
    }
}

public class CaveSlice
{
    public const int SandOriginX = 500;
    public const int SandOriginY = 0;

    public readonly int Width;
    public readonly int Height;
    public readonly Cell[,] Grid;
    public readonly int Padding;

    public CaveSlice(int width, int height, List<List<Point>> stonePaths, int padding = 0)
    {
        Width = width + padding * 2;
        Height = height;
        Grid = new Cell[Height, Width];
        Padding = padding;

        for (var y = 0; y < Height; ++y)
        {
            for (var x = 0; x < Width; ++x)
            {
                Grid[y, x] = Cell.Air;
            }
        }

        foreach (var path in stonePaths)
        {
            for (var i = 0; i < path.Count - 1; ++i)
            {
                var from = path[i];
                var to = path[i + 1];
                DrawLine(Grid, from, to, Cell.Stone, padding);
            }
        }

        Grid[SandOriginY, SandOriginX + Padding] = Cell.SandOrigin;
    }

    public Cell this[int x, int y]
    {
        // 2D arrays are [row,col], so we need to use [y,x] given (x,y).
        get => Grid[y, x];
        set => Grid[y, x] = value;
    }

    public string Visualize(char air = '.', char stone = '#', char sand = 'O', char sandOrigin = '+') =>
        VisualizeChunk(new Point(0, 0), new Point(Width - 1, Height - 1));

    /// Inclusive bounds for start and end points.
    public string VisualizeChunk(Point start, Point end, char air = '.', char stone = '#', char sand = 'O', char sandOrigin = '+')
    {
        if (start.X < 0 || start.X + 1 > Width || start.Y < 0 || start.Y + 1 > Height)
        {
            throw new ArgumentException("Invalid start position", nameof(start));
        }

        if (end.X < 0 || end.X + 1 > Width || end.Y < 0 || end.Y + 1 > Height)
        {
            throw new ArgumentException("Invalid end position", nameof(end));
        }

        var visualization = string.Empty;

        for (var y = start.Y; y <= end.Y; ++y)
        {
            for (var x = start.X; x <= end.X; ++x)
            {
                var cell = Grid[y, x];
                var character = cell switch
                {
                    Cell.Air => air,
                    Cell.Stone => stone,
                    Cell.Sand => sand,
                    Cell.SandOrigin => sandOrigin,
                    _ => throw new ArgumentOutOfRangeException(nameof(cell), cell, "Unsupported Cell value")
                };
                visualization += character;
            }

            visualization += '\n';
        }

        return visualization.Trim();
    }

    public SandResult SimulateGrainOfSand()
    {
        var origin = new Point(SandOriginX + Padding, SandOriginY);
        var position = origin;

        if (position.Y >= Height)
        {
            return SandResult.FellInAbyss;
        }

        while (position.Y < Height)
        {
            var (left, middle, right) = GetBelow(position);

            if (CanMove(middle))
            {
                position = middle;
                if (position.Y >= Height)
                {
                    return SandResult.FellInAbyss;
                }
            }
            else if (CanMove(left))
            {
                position = left;
                if (position.Y >= Height)
                {
                    return SandResult.FellInAbyss;
                }
            }
            else if (CanMove(right))
            {
                position = right;
                if (position.Y >= Height)
                {
                    return SandResult.FellInAbyss;
                }
            }
            else
            {
                if (left.Y >= Height && middle.Y >= Height && right.Y >= Height)
                {
                    return SandResult.FellInAbyss;
                }

                Grid[position.Y, position.X] = Cell.Sand;
                return position == origin
                    ? SandResult.SourceBlocked
                    : SandResult.Stopped;
            }
        }

        return SandResult.FellInAbyss;
    }

    private (Point Left, Point Middle, Point Right) GetBelow(Point coordinate) =>
    (
        Left: coordinate with { X = coordinate.X - 1, Y = coordinate.Y + 1 },
        Middle: coordinate with { Y = coordinate.Y + 1 },
        Right: coordinate with { X = coordinate.X + 1, Y = coordinate.Y + 1 }
    );

    private bool CanMove(Point destination)
    {
        if (destination.X < 0 || destination.X >= Width || destination.Y >= Height || destination.Y < 0)
        {
            return false;
        }

        return Grid[destination.Y, destination.X] is Cell.Air;
    }

    private static void DrawLine(Cell[,] grid, Point from, Point to, Cell stroke, int padding)
    {
        if (from == to)
        {
            grid[from.Y, from.X + padding] = stroke;
        }
        else if (from.X != to.X)
        {
            DrawHorizontalLine(grid, from, to, stroke, padding);
        }
        else if (from.Y != to.Y)
        {
            DrawVerticalLine(grid, from, to, stroke, padding);
        }
    }

    private static void DrawHorizontalLine(Cell[,] grid, Point from, Point to, Cell strokeCharacter, int padding)
    {
        var start = Math.Min(from.X, to.X) + padding;
        var end = Math.Max(from.X, to.X) + padding;

        // NOTE: "<=" because coordinates have inclusive bounds.
        for (var x = start; x <= end; ++x)
        {
            grid[from.Y, x] = strokeCharacter;
        }
    }

    private static void DrawVerticalLine(Cell[,] grid, Point from, Point to, Cell strokeCharacter, int padding)
    {
        var start = Math.Min(from.Y, to.Y);
        var end = Math.Max(from.Y, to.Y);

        // NOTE: "<=" because coordinates have inclusive bounds.
        for (var y = start; y <= end; ++y)
        {
            grid[y, from.X + padding] = strokeCharacter;
        }
    }
}

public enum Cell
{
    Air,
    Stone,
    Sand,
    SandOrigin,
}

public record ParserResult(List<List<Point>> Paths, int Width, int Height);

public enum SandResult
{
    Stopped,
    FellInAbyss,
    SourceBlocked,
}



