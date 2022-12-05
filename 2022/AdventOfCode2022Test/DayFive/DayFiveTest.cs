namespace AdventOfCode2022Test.DayFive;

[TestClass]
public class DayFiveTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFive/Day5Test.txt");
    
    [TestMethod]
    public void GetFinalTopCreatesCrateMover9000_Called_ReturnsCorrectAnswer()
    {
        const string expected = "CMZ";
        
        var actual = AdventOfCode2022.DayFive.DayFive.GetFinalTopCratesCrateMover9000(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetFinalTopCreatesCrateMover9001_Called_ReturnsCorrectAnswer()
    {
        const string expected = "MCD";

        var actual = AdventOfCode2022.DayFive.DayFive.GetFinalTopCratesCrateMover9001(_input);

        Assert.AreEqual(expected, actual);
    }
}
