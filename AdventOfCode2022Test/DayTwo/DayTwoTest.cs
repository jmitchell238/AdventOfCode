namespace AdventOfCode2022Test.DayTwo;

[TestClass]
public class DayTwoTest
{
    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        var input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayTwo/Day2Test.txt");
        var expect = 15;

        var result = AdventOfCode2022.DayTwo.DayTwo.PartOne(input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void PartTwo_Called_ReturnsCorrectAnswer()
    {
        var input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayTwo/Day2Test.txt");
        var expect = 12;

        var result = AdventOfCode2022.DayTwo.DayTwo.PartTwo(input);

        Assert.AreEqual(expect, result);
    }
}