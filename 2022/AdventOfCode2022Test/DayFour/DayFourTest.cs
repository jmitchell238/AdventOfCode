using AdventOfCode2022.DayFour;

namespace AdventOfCode2022Test.DayFourTest;

[TestClass]
public class DayFourTest
{
    readonly string[] input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFour/Day4Test.txt");

    [TestMethod]
    public void GetFullyContainedAssignments_Called_ReturnsCorrectAnswer()
    {
        var expected = 2;

        var actual = DayFour.getFullyContainedAssignments(this.input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetOverlappingAssignments_Called_ReturnsCorrectAnswer()
    {
        var expected = 4;

        var actual = DayFour.getOverlappingAssignments(this.input);

        Assert.AreEqual(expected, actual);
    }

}

