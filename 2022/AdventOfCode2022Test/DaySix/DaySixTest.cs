namespace AdventOfCode2022Test.DaySix;

[TestClass]
public class DaySixTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DaySix/Day6Test.txt");
    
    [TestMethod]
    public void CreatedMethod_Called_ReturnsCorrectAnswer()
    {
        const string expected = "Fail";
        
        var actual = AdventOfCode2022.DaySix.DaySix.PartOne(_input);

        Assert.AreEqual(expected, actual);
    }
}
