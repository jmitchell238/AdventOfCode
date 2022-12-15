using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022.DayFourteen;

public record Sand
{
    public Position Position { get; private set; }
    public Cave Cave { get; }
    //private Cave _cave;

    public Sand(Position start, Cave cave)
    {
        Position = start;
        Cave = cave;
    }

    public bool Fall()
    {
        if (!Cave.IsOccupied(Position.Down))
        {
            this.Position = Position.Down;
            return true;
        }
        if (!Cave.IsOccupied(Position.DownLeft))
        {
            this.Position = Position.DownLeft;
            return true;
        }
        if (!Cave.IsOccupied(Position.DownRight))
        {
            this.Position = Position.DownRight;
            return true;
        }
        return false;
    }
}
