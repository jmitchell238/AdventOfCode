using static AdventOfCode2022.HelperFunctions;

namespace AdventOfCode2022Test.DayFive;

[TestClass]
public class DayFiveTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFive/Day5Test.txt");
    private static readonly string[] StacksTestInput = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFive/Day5StacksTest.txt");
    private static List<Stack<char>> Stacks { get; } = CreateStackList();
    private static List<Stack<char>> Stacks2 { get; } = CreateStackList();

    [TestMethod]
    public void GetFinalTopCreatesCrateMover9000_Called_ReturnsCorrectAnswer()
    {
        const string expected = "CMZ";
        
        var actual = AdventOfCode2022.DayFive.DayFive.GetFinalTopCratesCrateMover9000(_input, Stacks);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetFinalTopCreatesCrateMover9001_Called_ReturnsCorrectAnswer()
    {
        const string expected = "MCD";

        var actual = AdventOfCode2022.DayFive.DayFive.GetFinalTopCratesCrateMover9001(_input, Stacks2);

        Assert.AreEqual(expected, actual);
    }
    
    // Helper Methods for These Tests
    private static List<Stack<char>> CreateStackList()
    {
        return StacksTestInput.Select(CreateStack).ToList();
    }
}
