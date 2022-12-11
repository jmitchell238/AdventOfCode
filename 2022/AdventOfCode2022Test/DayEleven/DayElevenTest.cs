using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022Test.DayEleven;

public class DayElevenTest
{
    private readonly string[] _input = File.ReadAllLines("../../../../AdventOfCode2022Test/DayEleven/Day11Test.txt");


    [TestMethod]
    public void PartOne_Called_ReturnsCorrectAnswer()
    {
        const int expected = 0;

        var actual = AdventOfCode2022.DayEleven.DayEleven.PartOne(_input);

        Assert.AreEqual(expected, actual);
    }
}
