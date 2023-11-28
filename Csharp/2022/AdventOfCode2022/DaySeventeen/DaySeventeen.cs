using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;

namespace AdventOfCode2022.DaySeventeen
{
    public class DaySeventeen
    {
        //private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DaySeventeen/DaySeventeen.txt");
        static readonly string Input = File.ReadAllText("../../../../AdventOfCode2022/DaySeventeen/DaySeventeenTest.txt");
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
        static readonly int ShapesCount = Shapes.Count;

        public static void Day17()
        {
            Console.WriteLine($"Part 1: {PartOne()}");
            //Console.WriteLine($"Part 2: {PartTwo()}");
            
        }

        public static int PartOne(string? input = null)
        {
            // If no input is provided, use the default input
            input ??= Input;

            var moveList = Input.ToCharArray().Select(x => x == '>' ? 1 : -1).ToArray();
            int lastBlock = 2022;
            int moveIndex = 0;
            int blockIndex = 0;
            int highestIndex = 0;
            (int x, int y) blockPos;
            var grid = new Dictionary<int, HashSet<int>>();
            var floor = new HashSet<int>();
            for (var i = 0; i < AreaWidth; ++i)
            {
                floor.Add(i);
            }
            grid.Add(0, floor);

            do
            {
                var block = Shapes[blockIndex % ShapesCount];
                blockPos = (StartOffset.x, highestIndex + (StartOffset.y - block.height));

            } while (++blockIndex < lastBlock);
            

            return -1;
        }

        public static int PartTwo(string? input = null)
        {
            // If no input is provided, use the default input
            input ??= Input;

            return -1;
        }

    }
}
