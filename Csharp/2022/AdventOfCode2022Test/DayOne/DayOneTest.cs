namespace AdventOfCode2022Test.DayOne;

[TestClass]
public class DayOneTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayOne/Day1Test.txt");

    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        const int expect = 24000;

        var result = AdventOfCode2022.DayOne.DayOne.PartOne(_input);

        Assert.AreEqual(expect, result);
    }

    [TestMethod]
    public void PartTwo_Called_ReturnsCorrectAnswer()
    {
        const int expect = 45000;

        var result = AdventOfCode2022.DayOne.DayOne.PartTwo(_input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void GetMaxCaloriesFromElf_Called_ReturnsCorrectAnswer()
    {
        const int expect = 24000;

        var result = AdventOfCode2022.DayOne.DayOne.GetCaloriesFromTopElf(_input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void GetCaloriesFromTopThreeElf_Called_ReturnsCorrectAnswer()
    {
        const int expect = 45000;

        var result = AdventOfCode2022.DayOne.DayOne.GetCaloriesFromTopThreeElves(_input);

        Assert.AreEqual(expect, result);
    }
    
    [TestMethod]
    public void CalculateCaloriesCarried_Called_ReturnsCorrectAnswer()
    {
        var expect = new List<int>{ 6000, 4000, 11000, 24000, 10000 };

        var result = AdventOfCode2022.DayOne.DayOne.CalculateCaloriesCarried(_input);

        CollectionAssert.AreEqual(expect, result);
    }
}
