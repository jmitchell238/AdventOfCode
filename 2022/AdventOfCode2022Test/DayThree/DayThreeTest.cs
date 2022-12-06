namespace AdventOfCode2022Test.DayThree;

[TestClass]
public class DayThreeTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayThree/Day3Test.txt");
    
    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        const int expected = 157;

        var actual = AdventOfCode2022.DayThree.DayThree.PartOne(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void PartTwo_Called_ReturnsCorrectAnswer()
    {
        const int expected = 70;

        var actual = AdventOfCode2022.DayThree.DayThree.PartTwo(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetCommonItemPerGroupSum_Called_ReturnsCorrectAnswer()
    {
        const int expected = 70;

        var actual = AdventOfCode2022.DayThree.DayThree.GetCommonItemPerGroupSum(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetCommonItem_Called_ReturnsCorrectAnswer()
    {
        var elf1 = new Dictionary<char, int>()
        {
            { 'v', 1 }, { 'J', 1 }, { 'r', 1 }, { 'w', 1 }, { 'p', 1 }, { 'W', 1 }, { 't', 1 }, 
            { 'g', 1 }, { 'h', 1 }, { 'c', 1 }, { 's', 1 }, { 'F', 1 }, { 'M', 1 }, { 'f', 1 }
        };
        var elf2 = new Dictionary<char, int>()
        {
            { 'j', 1 }, { 'q', 1 }, { 'H', 1 }, { 'R', 1 }, { 'N', 1 }, { 'z', 1 }, { 'G', 1 }, { 'D', 1 }, 
            { 'L', 1 }, { 'r', 1 }, { 's', 1 }, { 'F', 1 }, { 'M', 1 }, { 'f', 1 }, { 'Z', 1 }, { 'S', 1 }
        };
        var thirdElfInGroup = _input[3];
        const char expected = 'M';

        var actual = AdventOfCode2022.DayThree.DayThree.GetCommonItem(elf1, elf2, thirdElfInGroup);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetElfItems_Called_returnsCorrectResult()
    {
        var elfItems = _input[0];
        var expected = new Dictionary<char, int>()
        {
            { 'v', 1 }, { 'J', 2 }, { 'r', 2 }, { 'w', 2 }, { 'p', 2 }, { 'W', 2 }, { 't', 1 },
            { 'g', 1 }, { 'h', 2 }, { 'c', 1 }, { 's', 1 }, { 'F', 4 }, { 'M', 2 }, { 'f', 1 }
        };

        var actual = AdventOfCode2022.DayThree.DayThree.GetElfItems(elfItems);

        CollectionAssert.AreEquivalent(expected, actual);
    }

    [TestMethod]
    public void GetPriorityItemsSum_Called_ReturnsCorrectAnswer()
    {
        const int expected = 157;

        var actual = AdventOfCode2022.DayThree.DayThree.GetPriorityItemsSum(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetDuplicateItem_Called_ReturnsCorrectAnswer()
    {
        var elfItems = _input[0];
        const char expected = 'p';

        var actual = AdventOfCode2022.DayThree.DayThree.GetDuplicateItem(elfItems);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetItemValue_Called_ReturnsCorrectAnswer()
    {
        const char ch = 'A';
        const int expected = 27;

        var actual = AdventOfCode2022.DayThree.DayThree.GetItemValue(ch);

        Assert.AreEqual(expected, actual);
    }
}
