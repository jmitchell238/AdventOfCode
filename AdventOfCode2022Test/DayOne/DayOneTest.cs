namespace AdventOfCode2022Test.DayOne;

[TestClass]
public class DayOneTest
{
    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        var input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayOne/Day1Test.txt");
        var expect = 24000;

        var result = AdventOfCode2022.DayOne.DayOne.PartOne(input);

        Assert.AreEqual(expect, result);
    }

    [TestMethod]
    public void PartTwo_Called_ReturnsCorrectAnswer()
    {
        var input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayOne/Day1Test.txt");
        var expect = 45000;

        var result = AdventOfCode2022.DayOne.DayOne.PartTwo(input);

        Assert.AreEqual(expect, result);
    }
}
