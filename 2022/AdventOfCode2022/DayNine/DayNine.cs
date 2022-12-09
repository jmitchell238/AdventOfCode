using System;
using System.Collections.Generic;
using System.IO;

namespace AdventOfCode2022.DayNine
{
    public class DayNine
    {
        private static readonly string[] Input = File.ReadAllLines("../../../../AdventOfCode2022/DayNine/Day9.txt");
        public static void Day9()
        {
            Console.WriteLine($"Part 1: {PartOne()}");
            Console.WriteLine($"Part 2: {PartTwo()}");
        }
        public static int PartOne(string[]? input = null)
        {
            input ??= Input;

            var head = (x: 0, y: 0);
            var tail = (x: 0, y: 0);
            var tailVisited = new HashSet<(int, int)>() { (0, 0), };

            // Go through each line in the Input file
            foreach (var line in input)
            {
                var count = int.Parse(line.AsSpan()[2..]);
                for (int i = 0; i < count; i++)
                {
                    // Move Head
                    if (line[0] == 'U') 
                        head = (head.x, head.y - 1);
                    if (line[0] == 'D') 
                        head = (head.x, head.y + 1);
                    if (line[0] == 'L') 
                        head = (head.x - 1, head.y);
                    if (line[0] == 'R') 
                        head = (head.x + 1, head.y);

                    // Move Tail
                    if (head.x - tail.x > 1) 
                        tail = (head.x - 1, head.y);
                    if (head.x - tail.x < -1) 
                        tail = (head.x + 1, head.y);
                    if (head.y - tail.y > 1) 
                        tail = (head.x, head.y - 1);
                    if (head.y - tail.y < -1) 
                        tail = (head.x, head.y + 1);
                    tail = (tail.x, tail.y);

                    // Add Tail Position to HashSet
                    tailVisited.Add(tail);
                }
            }

            // Count total amount of places the tail had been.
            return tailVisited.Count;
        }

        public static int PartTwo(string[]? input = null)
        {
            input ??= Input;

            var head = (x: 0, y: 0);
            var tail = (x: 0, y: 0);
            var tailVisited = new HashSet<(int, int)>() { (0, 0), };

            // Lets create a snake this time since it's H, 1, 2, .... 9 (10 character long)
            var snake = new (int x, int y)[10];

            foreach (var line in input)
            {
                var count = int.Parse(line.AsSpan()[2..]);
                for (int i = 0; i < count; i++)
                {
                    // Move Head
                    if (line[0] == 'U')
                        snake[0] = (snake[0].x, snake[0].y - 1);
                    if (line[0] == 'D')
                        snake[0] = (snake[0].x, snake[0].y + 1);
                    if (line[0] == 'L')
                        snake[0] = (snake[0].x - 1, snake[0].y);
                    if (line[0] == 'R')
                        snake[0] = (snake[0].x + 1, snake[0].y);

                    // Move body of snake - for loop to move other 9 pieces of snake.
                    for (int j = 1; j < 10; j++)
                    {
                        // get head position
                        head = snake[j - 1];

                        // get tail position
                        tail = snake[j];

                        // Move tail
                        (int, int) newTail;
                        if ((head.x - tail.x, head.y - tail.y) is (> 1, > 1))
                            newTail = (head.x - 1, head.y - 1);
                        else if ((head.x - tail.x, head.y - tail.y) is (> 1, < -1))
                            newTail = (head.x - 1, head.y + 1);
                        else if ((head.x - tail.x, head.y - tail.y) is (< -1, > 1))
                            newTail = (head.x + 1, head.y - 1);
                        else if ((head.x - tail.x, head.y - tail.y) is (< -1, < -1))
                            newTail = (head.x + 1, head.y + 1);
                        else if ((head.x - tail.x, head.y - tail.y) is (> 1, _))
                            newTail = (head.x - 1, head.y);
                        else if ((head.x - tail.x, head.y - tail.y) is (< -1, _))
                            newTail = (head.x + 1, head.y);
                        else if ((head.x - tail.x, head.y - tail.y) is (_, > 1))
                            newTail = (head.x, head.y - 1);
                        else if ((head.x - tail.x, head.y - tail.y) is (_, < -1))
                            newTail = (head.x, head.y + 1);
                        else
                            newTail = (tail.x, tail.y);

                        // If new position of tail is same as previous, break out of loop
                        if (newTail == tail)
                            break;

                        // else move the tail
                        snake[j] = newTail;
                    }

                    // Add the new tail position to Hashset
                    tailVisited.Add(snake[^1]);
                }
            }

            // Return the count of how many places the tail visited.
            return tailVisited.Count;
        }
    }
}
