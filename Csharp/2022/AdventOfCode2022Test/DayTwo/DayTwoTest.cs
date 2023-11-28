namespace AdventOfCode2022Test.DayTwo;

[TestClass]
public class DayTwoTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayTwo/Day2Test.txt");
    
    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        const int expect = 15;

        var result = AdventOfCode2022.DayTwo.DayTwo.PartOne(_input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void PartTwo_Called_ReturnsCorrectAnswer()
    {
        const int expect = 12;

        var result = AdventOfCode2022.DayTwo.DayTwo.PartTwo(_input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void GetScoreNotExplained_Called_ReturnsCorrectAnswer()
    {
        const int expect = 15;

        var result = AdventOfCode2022.DayTwo.DayTwo.GetScore(false, _input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void GetScoreExplained_Called_ReturnsCorrectAnswer()
    {
        const int expect = 12;

        var result = AdventOfCode2022.DayTwo.DayTwo.GetScore(true, _input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void GetRoundScoreNotExplained_Called_ReturnsCorrectAnswer()
    {
        var line = _input[0];
        const int expect = 8;

        var result = AdventOfCode2022.DayTwo.DayTwo.GetRoundScore(line, false);

        Assert.AreEqual(expect, result);
    }

    [TestMethod]
    public void GetRoundScoreExplained_Called_ReturnsCorrectAnswer()
    {
        var line = _input[0];
        const int expect = 4;

        var result = AdventOfCode2022.DayTwo.DayTwo.GetRoundScore(line, true);

        Assert.AreEqual(expect, result);
    }
}