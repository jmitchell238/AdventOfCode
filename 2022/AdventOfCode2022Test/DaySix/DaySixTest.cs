namespace AdventOfCode2022Test.DaySix;

[TestClass]
public class DaySixTest
{
    [TestMethod]
    public void GetMarker_Called_Returns7()
    {
        const int expected = 7;
        const string input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMarker(input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetMarker_Called_Returns5()
    {
        const int expected = 5;
        const string input = "bvwbjplbgvbhsrlpgdmjqwftvncz";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMarker(input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetMarker_Called_Returns6()
    {
        const int expected = 6;
        const string input = "nppdvjthqldpwncqszvftbrmjlhg";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMarker(input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetMarker_Called_Returns10()
    {
        const int expected = 10;
        const string input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMarker(input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetMarker_Called_Returns11()
    {
        const int expected = 11;
        const string input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMarker(input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetMessage_Called_Returns19()
    {
        const int expected = 19;
        const string input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMessage(input);

        Assert.AreEqual(expected, actual);
    }

    [TestMethod]
    public void GetMessage_Called_Returns23first()
    {
        const int expected = 23;
        const string input = "bvwbjplbgvbhsrlpgdmjqwftvncz";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMessage(input);

        Assert.AreEqual(expected, actual);
    }
    [TestMethod]
    public void GetMessage_Called_Returns23second()
    {
        const int expected = 23;
        const string input = "nppdvjthqldpwncqszvftbrmjlhg";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMessage(input);

        Assert.AreEqual(expected, actual);
    }
    [TestMethod]
    public void GetMessage_Called_Returns29()
    {
        const int expected = 29;
        const string input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMessage(input);

        Assert.AreEqual(expected, actual);
    }
    [TestMethod]
    public void GetMessage_Called_Returns26()
    {
        const int expected = 26;
        const string input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

        var actual = AdventOfCode2022.DaySix.DaySix.GetMessage(input);

        Assert.AreEqual(expected, actual);
    }
}
