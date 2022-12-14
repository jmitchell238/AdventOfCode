using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;

namespace AdventOfCode2022.DayFourteen;

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
