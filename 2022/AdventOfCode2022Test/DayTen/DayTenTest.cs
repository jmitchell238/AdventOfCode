namespace AdventOfCode2022Test.DayTen;

[TestClass]
public class DayTenTest
{

    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayTen/Day10Test.txt");


    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        const int expected = 13140;

        var actual = AdventOfCode2022.DayTen.DayTen.PartOne(_input);

        Assert.AreEqual(expected, actual);
    }
}
