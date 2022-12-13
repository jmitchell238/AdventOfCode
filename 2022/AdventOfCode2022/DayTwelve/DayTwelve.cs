using Priority_Queue;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DayTwelve;

public class DayTwelve
{
    private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayTwelve/Day12.txt");
    const char StartMarker = 'S';
    const char EndMarker = 'E';
    const char StartMarkerMapping = 'a';
    const char EndMarkerMapping = 'z';

    public static void Day12()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        Console.WriteLine($"Part 2: {PartTwo()}");
    }

    public static double PartOne(string[]? input = null)
    {
        input ??= Input;
        //input = File.ReadAllLines("../../../../AdventOfCode2022/DayTwelve/Day12Test.txt");

        var map = Map(input);

        int rows = map.Length, cols = map[0].Length;
        //Console.WriteLine($"Map Size: {rows}h - {cols}w");
        var startEnd = FindStartAndEnd(map);
        map[startEnd.startPoint.y][startEnd.startPoint.x] = StartMarkerMapping;
        map[startEnd.endPoint.y][startEnd.endPoint.x] = EndMarkerMapping;
        //Console.WriteLine($"Find path from {startEnd.startPoint.y},{startEnd.startPoint.x} to {startEnd.endPoint.y},{startEnd.endPoint.x}");
        (int x, int y)[] moves = HelperFunctions.Directions.HorizontalAndVertical;
        //int bestScore = int.MaxValue;
        SimplePriorityQueue<((int y, int x) pos, int stepsTaken)> queue = new SimplePriorityQueue<((int y, int x) pos, int stepsTaken)>();
        queue.Enqueue((startEnd.startPoint, 0), 0);

        var result = FindPath(
            map: map, 
            visited: new Dictionary<(int y, int x), int>(), 
            queue: queue, 
            isEnd: (targetPos, targetVal) => targetPos ==  startEnd.endPoint,
            isValidMove: (targetPos, currentVal, targetVal) => targetVal - currentVal <= 1,
            maxY: rows - 1, 
            maxX: cols - 1, 
            moves: moves);

        return result;
    }

    public static double PartTwo(string[]? input = null)
    {
        input ??= Input;
        //input = File.ReadAllLines("../../../../AdventOfCode2022/DayTwelve/Day12Test.txt");

        var map = Map(input);

        int rows = map.Length, cols = map[0].Length;
        var startEnd = FindStartAndEnd(map);
        map[startEnd.startPoint.y][startEnd.startPoint.x] = StartMarkerMapping;
        map[startEnd.endPoint.y][startEnd.endPoint.x] = EndMarkerMapping;
        (int x, int y)[] moves = HelperFunctions.Directions.HorizontalAndVertical;
        //int bestScore = int.MaxValue;
        SimplePriorityQueue<((int y, int x) pos, int stepsTaken)> queue = new SimplePriorityQueue<((int y, int x) pos, int stepsTaken)>();
        queue.Enqueue((startEnd.endPoint, 0), 0);

        var result = FindPath(
            map: map,
            visited: new Dictionary<(int y, int x), int>(),
            queue: queue,
            isEnd: (targetPos, targetVal) => targetVal == StartMarkerMapping,
            isValidMove: (targetPos, currentVal, targetVal) => currentVal - targetVal <= 1,
            maxY: rows - 1,
            maxX: cols - 1,
            moves: moves);

        return result;
    }

    public static int FindPath(
        char[][] map,
        Dictionary<(int y, int x), int> visited,
        SimplePriorityQueue<((int y, int x) pos, int stepsTaken)> queue,
        Func<(int y, int x), char, bool> isEnd,
        Func<(int y, int x), char, char, bool> isValidMove,
        int maxY,
        int maxX,
        (int y, int x)[] moves
        )
    {
        char targetVal;
        while (queue.Count > 0)
        {
            (int y, int x) targetPos;
            var target = queue.Dequeue();
            char currentVal = map[target.pos.y][target.pos.x];
            if (visited.ContainsKey(target.pos)) continue;
            visited.Add(target.pos, target.stepsTaken);
            
            foreach(var move in moves)
            {
                int x = target.pos.x + move.x;
                int y = target.pos.y + move.y;
                targetPos = (y, x);
                if (
                    x >= 0
                    &&
                    y >= 0
                    &&
                    x <= maxX
                    &&
                    y <= maxY
                    &&
                    !visited.ContainsKey(targetPos)
                    )
                {

                    targetVal = map[y][x];
                    // Difference upwards at max 1
                    if (isValidMove((y,x), currentVal, targetVal))
                    {
                        int stepsTaken = target.stepsTaken + 1;
                        if (isEnd((y, x), targetVal)) return stepsTaken;
                        else queue.Enqueue((targetPos, stepsTaken), stepsTaken);
                    }
                }
            }
        }
        return -1;
    }

    public static char[][] Map(string[]? input = null) => input.Select(x => x.ToCharArray()).ToArray();

    public static ((int y, int x) startPoint, (int y, int x) endPoint) FindStartAndEnd(char[][] map)
    {
        bool startFound = false, endFound = false;
        (int y, int x) startPoint = (-1, -1), endPoint = (-1, -1);
        int rows = map.Length, cols = map[0].Length;
        char val;

        for (int y = 0; y < rows; ++y)
        {
            for (int x = 0; x < cols; ++x)
            {
                val = map[y][x];

                if (val == StartMarker)
                {
                    if (startFound) throw new ApplicationException($"More than 1 startPoint found! {startPoint.y},{startPoint.x} {y},{x}");
                    startPoint = (y, x);
                    startFound = true;
                    if (endFound) break;
                }
                else if (val == EndMarker)
                {
                    if (endFound) throw new ApplicationException($"More than 1 endPoint found! {endPoint.y},{endPoint.x} {y}, {x}");
                    endPoint = (y, x);
                    endFound = true;
                    if (startFound) break;
                }
            }
        }

        if (!startFound || !endFound) throw new ApplicationException("Unable to find start & end points!");
        return (startPoint, endPoint);
    }

}
