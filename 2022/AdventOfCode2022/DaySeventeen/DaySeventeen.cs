using Microsoft.CodeAnalysis.CSharp.Syntax;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;

namespace AdventOfCode2022.DaySeventeen;

public class DaySeventeen
{
    //static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DaySeventeen/DaySeventeenTest.txt");
    static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DaySeventeen/DaySeventeen.txt");
    static readonly List<(int width, int height, List<(int x, int y)> points)> Shapes = new()
    { 
            // ####
            (4, 1, new List<(int x, int y)>{ (0,0), (1,0), (2,0), (3,0) }),
            /*
                 .#.
                 ###
                 .#.
            */
            (3, 3, new List<(int x, int y)>
            {
                        (1, 0),
                (0, 1), (1, 1), (2, 1),
                        (1, 2)
            }),
            /*
               ..#
               ..#
               ###
            */
            (3, 3, new List<(int x, int y)>
            {
                                (2, 0),
                                (2, 1),
                (0, 2), (1, 2), (2, 2)
            }),
            /* 
               #
               #
               #
               #
            */
            (1, 4, new List<(int x, int y)>
            {
                (0, 0),
                (0, 1),
                (0, 2),
                (0, 3)
            }),
            /*
                ##
                ##
            */
            (2, 2, new List<(int x, int y)>
            {
                (0, 0), (1, 0),
                (0, 1), (1, 1)
            })
        };
    static readonly (int x, int y) StartOffset = (2, -3);
    static readonly int AreaWidth = 7;
    static readonly ulong ShapesCount = (ulong)Shapes.Count;

    public static void Day17()
    {
        Console.WriteLine($"Part 1: {PartOne()}");
        //Console.WriteLine($"Part 2: {PartTwo()}");
    }

    static ulong moveIndex, blockIndex;
    static int highestIndex, moveDownCount, x, y;

    public static ulong PartOne(string? input = null)
    {
        // If no input is provided, use the default input
        input ??= Input;

        int printCount = 0;
        bool debugPrint = false;

        var moveList = Input.ToCharArray().Select(x => x == '>' ? true : false).ToArray();

        ulong moveListLength = (ulong)moveList.Length;
        ulong lastBlock = 2022;
        ulong lastBlock2 = 1_000_000_000_000;
        Console.WriteLine(moveList.Length);
        ulong repeatAfter = (ulong)moveList.Length * ShapesCount;
        //lastBlock = (int)repeatAfter * 3;

        moveIndex = 0UL;
        blockIndex = 0UL;
        highestIndex = 0;
        (int x, int y) blockPos;
        int blockBottomY, blockMaxX;
        var grid = new Dictionary<int, HashSet<int>>();
        var floor = new HashSet<int>();

        for (var i = 0; i < AreaWidth; ++i)
        {
            floor.Add(i);
        }

        grid.Add(0, floor);

        moveDownCount = 0;

        bool xMoveRight, hit;

        ulong prev = 0UL, prevIx = 0UL;
        string previousGrid = null;
        ulong skippedHeight = 0UL;
        List<int> moveIndexes = new List<int>();
        (int start, int length) pattern = (0, 0);


        do
        {

            if (blockIndex % ShapesCount == 0)
            {
                moveIndexes.Add((int)(moveIndex % moveListLength));
                if (pattern.start == 0 && moveIndexes.Count >= (int)moveListLength / 2)
                {
                    pattern = moveIndexes.ToArray().FindPattern();
                    Console.WriteLine($"Pattern starts at index {pattern.start} and is {pattern.length} long, repeating these move indeces {string.Join(", ", moveIndexes.Skip(pattern.start).Take(pattern.length))}");
                }

                if (skippedHeight == 0 && pattern.length > 0 && (int)(moveIndex % moveListLength) == moveIndexes[pattern.start])
                {
                    ulong newCount = (ulong)(grid.Keys.Min() * -1), gained = newCount - prev;
                    if (debugPrint) Console.WriteLine($"ix: {blockIndex}, blockIndex: {(int)(blockIndex % ShapesCount)}, moveIndex: {(int)(moveIndex % moveListLength)}, height: {newCount} ({prev} + {gained})");
                    int gridTop = (int)newCount * -1;
                    string itterationGrid = GetGrid(grid, 0, AreaWidth - 1, gridTop, gridTop + (int)gained - 1);
                    // PrintGrid(grid, 0, AreaWidth - 1, gridTop, gridTop + (int)gained - 1, $"{blockIndex}.txt");
                    if (itterationGrid.Equals(previousGrid))
                    {
                        // Bingo!
                        if (debugPrint) Console.WriteLine($"Boom! Repeating pattern found!");
                        // so every N we get {gained} height.
                        ulong startHeight = prev;
                        ulong perCycle = gained;
                        ulong ixJump = blockIndex - prevIx;
                        if (debugPrint) Console.WriteLine($"We can gain {perCycle} height every {ixJump} blocks we process. We needed {prevIx} blocks processed before we got to this stable state.");
                        ulong skippedCycles = (lastBlock2 - blockIndex + 1UL) / ixJump;
                        ulong resumeAt = prevIx + (skippedCycles * ixJump);
                        skippedHeight = (skippedCycles - 1) * perCycle;
                        if (debugPrint) Console.WriteLine($"We can resume @ {resumeAt} to process the last {lastBlock2 - resumeAt} cycles...(adding {skippedCycles * (ulong)perCycle:N0} to our current total)");
                        blockIndex = resumeAt;
                    }
                    previousGrid = itterationGrid;
                    //if (++printCount >= 5) break;
                    prev = newCount;
                    prevIx = blockIndex;
                }
            }

            // Test for collision
            hit = false;
            moveDownCount = 0;
            var block = Shapes[(int)(blockIndex % ShapesCount)];
            blockMaxX = AreaWidth - (block.width);
            blockBottomY = highestIndex + (StartOffset.y);
            blockPos = (StartOffset.x, highestIndex + (StartOffset.y - block.height));

            while (!hit)
            {
                xMoveRight = moveList[moveIndex++ % moveListLength];

                if (xMoveRight)
                {
                    // Check right bounds

                    if (blockPos.x < blockMaxX)
                    {
                        if (moveDownCount <= 3 || !HasCollision((blockPos.x + 1, blockPos.y), block.points, grid))
                        {
                            ++blockPos.x;
                            if (debugPrint) Console.WriteLine($"Block moved one to the right: x={blockPos.x}, y={blockPos.y}");
                        }
                        else
                        {
                            if (debugPrint) Console.WriteLine($"Block can't move to the right due to collision: x={blockPos.x}, y={blockPos.y}");
                        }
                    }
                    else
                    {
                        if (debugPrint) Console.WriteLine($"Block can't move to the right: x={blockPos.x}, y={blockPos.y}");
                    }
                }
                else
                {
                    // make sure > 0
                    if (blockPos.x > 0)
                    {
                        if (moveDownCount <= 3 || !HasCollision((blockPos.x -1, blockPos.y), block.points, grid))
                        {
                            --blockPos.x;
                            if (debugPrint) Console.WriteLine($"Block moved one to the left: x={blockPos.x}, y={blockPos.y}");
                        }
                        else
                        {
                            if (debugPrint) Console.WriteLine($"Block can't move to the left due to collision: x={blockPos.x}, y={blockPos.y}");
                        }
                    }
                    else
                    {
                        if (debugPrint) Console.WriteLine($"Block can't move to the left: x={blockPos.x}, y={blockPos.y}");
                    }
                }

                if (++moveDownCount > 3)
                {
                    // Test for Hit
                    y = blockPos.y + 1;
                    hit = HasCollision((blockPos.x, y), block.points, grid);

                    if (hit)
                    {
                        if (debugPrint) Console.WriteLine($"Collision detected @ x={blockPos.x}, y={y}");

                        AddBlockPointsToGrid(blockPos, grid, ref x, ref y, block);
                        //Console.WriteLine($"Max y: {grid.Keys.Min() * -1}");
                    }
                    else
                    {
                        ++blockPos.y;
                        if (debugPrint) Console.WriteLine($"Moving one down to: x={blockPos.x}, y={blockPos.y}");
                    }
                }
                else
                {
                    ++blockPos.y;
                    if (debugPrint) Console.WriteLine($"Moving one down to: x={blockPos.x}, y={blockPos.y}");
                }
            }

            //PrintGrid(grid);
            //if (blockIndex == 10) break;
        //} while (++blockIndex < lastBlock);
        } while (++blockIndex < lastBlock2);

        if (debugPrint) PrintGrid(grid);

        Console.WriteLine($"Max y: {(ulong)(grid.Keys.Min() * -1) + skippedHeight}");

        var result = (ulong)(grid.Keys.Min() * -1) + skippedHeight;
        return result;
    }

    public static int PartTwo(string? input = null)
    {
        // If no input is provided, use the default input
        input ??= Input;

        return -1;
    }

    public static void AddBlockPointsToGrid((int x, int y) blockPos, Dictionary<int, HashSet<int>> grid, ref int x, ref int y, (int width, int height, List<(int x, int y)> points) block)
    {
        // Add block points to grid
        foreach (var point in block.points)
        {
            y = blockPos.y + point.y;
            if (y < highestIndex)
            {
                highestIndex = y;
            }
            x = blockPos.x + point.x;
            if (!grid.TryGetValue(y, out var xPoints))
            {
                xPoints = new HashSet<int>();
                grid.Add(y, xPoints);
            }
            xPoints.Add(x);
        }
    }

    public static bool HasCollision((int x, int y) startPos, List<(int x, int y)> points, Dictionary<int, HashSet<int>> grid)
    {
        bool result = false;
        int x, y;

        foreach(var point in points)
        {
            y = startPos.y + point.y;
            if (grid.TryGetValue(y, out var xPoints))
            {
                x = startPos.x + point.x;
                if (xPoints.Contains(x))
                {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    const char Filled = '█';
    const char Empty = ' ';
    const char On = '#';
    const char Off = '.';

    public static void PrintGrid(Dictionary<int, HashSet<int>> grid)
    {
        int minY = grid.Min(x => x.Key),
            maxY = grid.Max(x => x.Key),
            minX = 0,
            maxX = AreaWidth;

        PrintGrid(grid, minX, maxX, minY, maxY);

        char on = On;
        char off = Off;

        for (int y = minY; y <= maxY; ++y)
        {
            if (grid.TryGetValue(y, out var cols))
            {
                for (int x = minX; x < maxX; ++x)
                {
                    Console.Write(cols.Contains(x) ? On : Off);
                }

                Console.WriteLine();
            }
            else
            {
                Console.WriteLine(new string(Off, AreaWidth));
            }
        }
        Console.WriteLine();
    }

    public static void PrintGrid(Dictionary<int, HashSet<int>> grid, int minX, int maxX, int minY, int maxY)
    {
        char on = On;
        char off = Off;

        for (int y = minY; y <= maxY; ++y)
        {
            if (grid.TryGetValue(y, out var cols))
            {
                for (int x = minX; x < maxX; ++x)
                {
                    Console.Write(cols.Contains(x) ? On : Off);
                }

                Console.WriteLine();
            }
            else
            {
                Console.WriteLine(new string(Off, AreaWidth));
            }
        }
        Console.WriteLine();
    }
    public static string GetGrid(Dictionary<int, HashSet<int>> grid, int minX, int maxX, int minY, int maxY)
    {
        char on = On;
        char off = Off;
        var sb = new StringBuilder();
        for (int y = minY; y <= maxY; ++y)
        {
            if (grid.TryGetValue(y, out var cols))
            {
                for (int x = minX; x <= maxX; ++x)
                {
                    sb.Append(cols.Contains(x) ? on : off);
                }
                sb.AppendLine();
            }
            else
            {
                sb.AppendLine(new string(off, AreaWidth));
            }
        }
        return sb.ToString();
    }
}
