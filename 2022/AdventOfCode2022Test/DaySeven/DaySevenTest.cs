namespace AdventOfCode2022Test.DaySeven;

[TestClass]
public class DaySevenTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DaySeven/Day7Test.txt");

    [TestMethod]
    public void PartOne_Called_ThrowsNotImplementedException()
    {
        Assert.ThrowsException<NotImplementedException>(() => AdventOfCode2022.DaySeven.DaySeven.PartOne(_input));
    }
    
    [TestMethod]
    public void PartTwo_Called_ThrowsNotImplementedException()
    {
        Assert.ThrowsException<NotImplementedException>(() => AdventOfCode2022.DaySeven.DaySeven.PartTwo(_input));
    }
}
