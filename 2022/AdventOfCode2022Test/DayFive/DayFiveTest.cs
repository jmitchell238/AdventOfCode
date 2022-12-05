using AdventOfCode2022.DayFive;

namespace AdventOfCode2022Test.DayFiveTest;

[TestClass]
public class DayFiveTest
{
    readonly string[] input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFive/Day5Test.txt");
    
    [TestMethod]
    public void GetFinalTopCreatesCrateMover9000_Called_ReturnsCorrectAnswer()
    {
        var expected = "CMZ";
        
        var actual = DayFive.getFinalTopCratesCrateMover9000(this.input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetFinalTopCreatesCrateMover9001_Called_ReturnsCorrectAnswer()
    {
        var expected = "MCD";

        var actual = DayFive.getFinalTopCratesCrateMover9001(this.input);

        Assert.AreEqual(expected, actual);
    }
}
