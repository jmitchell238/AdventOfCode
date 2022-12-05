using AdventOfCode2022.DayThree;

namespace AdventOfCode2022Test.DayThreeTest;

[TestClass]
public class DayThreeTest
{
    readonly string[] input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayThree/Day3Test.txt");
    
    [TestMethod]
    public void GetCommonItemPerGroupSum_Called_ReturnsCorrectAnswer()
    {
        var expected = 70;

        var actual = DayThree.getCommonItemPerGroupSum(this.input);

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
        var thirdElfInGroup = this.input[3];
        var expected = 'M';

        var actual = DayThree.getCommonItem(elf1, elf2, thirdElfInGroup);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetElfItems_Called_returnsCorrectResult()
    {
        var elfItems = this.input[0];
        var expected = new Dictionary<char, int>()
        {
            { 'v', 1 }, { 'J', 2 }, { 'r', 2 }, { 'w', 2 }, { 'p', 2 }, { 'W', 2 }, { 't', 1 },
            { 'g', 1 }, { 'h', 2 }, { 'c', 1 }, { 's', 1 }, { 'F', 4 }, { 'M', 2 }, { 'f', 1 }
        };

        var actual = DayThree.getElfItems(elfItems);

        CollectionAssert.AreEquivalent(expected, actual);
        //Assert.AreSame(expected, actual);

    }

    [TestMethod]
    public void GetPriorityItemsSum_Called_ReturnsCorrectAnswer()
    {
        var expected = 157;

        var actual = DayThree.getPriorityItemsSum(this.input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetDupplicateItem_Called_ReturnsCorrectAnswer()
    {
        var elfItems = this.input[0];
        var expected = 'p';

        var actual = DayThree.getDuplicateItem(elfItems);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetItemValue_Called_ReturnsCorrectAnswer()
    {
        char ch = 'A';
        var expected = 27;

        var actual = DayThree.getItemValue(ch);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        var expected = 157;

        var actual = DayThree.PartOne(this.input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void PartTwo_Called_ReturnsCorrectAnswer()
    {
        var expected = 70;

        var actual = DayThree.PartTwo(this.input);

        Assert.AreEqual(expected, actual);
    }
}
