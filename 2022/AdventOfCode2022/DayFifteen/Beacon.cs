namespace AdventOfCode2022.DayFifteen;

public class Beacon
{
    // properties for the x and y coordinates of the beacon
    public int X { get; set; }
    public int Y { get; set; }

    // constructor that takes the x and y coordinates of the beacon
    public Beacon(int x, int y)
    {
        this.X = x;
        this.Y = y;
    }
}
