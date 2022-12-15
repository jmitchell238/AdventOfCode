using System;
using System.Text.Json.Nodes;
using static AdventOfCode2022.DayThirteen.CompareClass;

namespace AdventOfCode2022.DayThirteen;

public static class CompareValuesClass
{
    public static bool? CompareValues(JsonValue leftVal, JsonValue rightVal)
    {
        var leftInt = leftVal.GetValue<int>();
        var rightInt = rightVal.GetValue<int>();
        return leftInt == rightInt ? null : leftInt < rightInt;
    }

    public static bool? CompareArrays(JsonArray leftArray, JsonArray rightArray)
    {
        for (var i = 0; i < Math.Min(leftArray.Count, rightArray.Count); i++)
        {
            var res = Compare(leftArray[i], rightArray[i]);
            if (res.HasValue) { return res.Value; }
        }

        if (leftArray.Count < rightArray.Count) return true;
        if (leftArray.Count > rightArray.Count) return false;
        return null;
    }
}
