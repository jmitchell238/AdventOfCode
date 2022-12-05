namespace AdventOfCode2022Test.DayFour;

[TestClass]
public class DayFourTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFour/Day4Test.txt");

    [TestMethod]
    public void GetFullyContainedAssignments_Called_ReturnsCorrectAnswer()
    {
        const int expected = 2;

        var actual = AdventOfCode2022.DayFour.DayFour.GetFullyContainedAssignments(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetOverlappingAssignments_Called_ReturnsCorrectAnswer()
    {
        const int expected = 4;

        var actual = AdventOfCode2022.DayFour.DayFour.GetOverlappingAssignments(_input);

        Assert.AreEqual(expected, actual);
    }

}

