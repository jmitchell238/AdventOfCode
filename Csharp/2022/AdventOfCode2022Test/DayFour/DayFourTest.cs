namespace AdventOfCode2022Test.DayFour;

[TestClass]
public class DayFourTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayFour/Day4Test.txt");

    [TestMethod]
    public void GetFullyContainedAssignments_Called_ReturnsCorrectAnswer()
    {
        const int expected = 2;

        var actual = AdventOfCode2022.DayFour.DayFour.GetFullyContainedAssignments(_input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetOverlappingAssignments_Called_ReturnsCorrectAnswer()
    {
        const int expected = 4;

        var actual = AdventOfCode2022.DayFour.DayFour.GetOverlappingAssignments(_input);

        Assert.AreEqual(expected, actual);
    }
    
    [TestMethod]
    public void AssignmentInRange_CalledWithoutOverlappingValues_ReturnsFalse()
    {
        var firstElf = new List<int> { 2, 4 };
        var secondElf = new List<int> { 6, 8 };
        const bool expected = false;

        var actual = AdventOfCode2022.DayFour.DayFour.AssignmentInRange(firstElf[0], secondElf[0], secondElf[1]);

        Assert.AreEqual(expected, actual);
    }
    
    [TestMethod]
    public void AssignmentInRange_CalledWithOverlappingValues_ReturnsTrue()
    {
        var firstElf = new List<int> { 2, 7 };
        var secondElf = new List<int> { 6, 8 };
        const bool expected = true;

        var actual = AdventOfCode2022.DayFour.DayFour.AssignmentInRange(firstElf[1], secondElf[0], secondElf[1]);

        Assert.AreEqual(expected, actual);
    }
    
    [TestMethod]
    public void GetElfAssignments_Called_ReturnsCorrectAnswer()
    {
        var assignments = new[] { "2-4", "6-8" };
        const int elfIndex = 0;
        var expected = new[] { 2, 4 };

        var actual = AdventOfCode2022.DayFour.DayFour.GetElfAssignments(assignments, elfIndex);

        CollectionAssert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void FirstElfAssignmentsFullyContained_CalledWithoutFullyContained_ReturnsFalse()
    {
        var firstElf = new [] { 2, 7 };
        var secondElf = new [] { 6, 8 };
        const bool expected = false;

        var actual = AdventOfCode2022.DayFour.DayFour.FirstElfAssignmentsFullyContained(firstElf, secondElf);

        Assert.AreEqual(expected, actual);
    }
    
    [TestMethod]
    public void FirstElfAssignmentsFullyContained_CalledWithFullyContained_ReturnsTrue()
    {
        var firstElf = new [] { 2, 7 };
        var secondElf = new [] { 1, 8 };
        const bool expected = true;

        var actual = AdventOfCode2022.DayFour.DayFour.FirstElfAssignmentsFullyContained(firstElf, secondElf);

        Assert.AreEqual(expected, actual);
    }
    
    [TestMethod]
    public void SecondElfAssignmentsFullyContained_CalledWithoutFullyContained_ReturnsFalse()
    {
        var firstElf = new [] { 2, 7 };
        var secondElf = new [] { 6, 8 };
        const bool expected = false;

        var actual = AdventOfCode2022.DayFour.DayFour.SecondElfAssignmentsFullyContained(secondElf, firstElf);

        Assert.AreEqual(expected, actual);
    }
    
    [TestMethod]
    public void SecondElfAssignmentsFullyContained_CalledWithFullyContained_ReturnsTrue()
    {
        var firstElf = new [] { 2, 9 };
        var secondElf = new [] { 6, 8 };
        const bool expected = true;

        var actual = AdventOfCode2022.DayFour.DayFour.SecondElfAssignmentsFullyContained(secondElf, firstElf);

        Assert.AreEqual(expected, actual);
    }
}
