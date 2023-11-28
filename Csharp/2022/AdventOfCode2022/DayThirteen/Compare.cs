using System.Text.Json.Nodes;
using static AdventOfCode2022.DayThirteen.CompareValuesClass;

namespace AdventOfCode2022.DayThirteen;

public static class CompareClass
{
    public static bool? Compare(JsonNode left, JsonNode right)
    {
        if (left is JsonValue leftVal && right is JsonValue rightVal)
        {
            return CompareValues(leftVal, rightVal);
        }

        if (left is not JsonArray leftArray) leftArray = new JsonArray(left.GetValue<int>());
        if (right is not JsonArray rightArray) rightArray = new JsonArray(right.GetValue<int>());

        return CompareArrays(leftArray, rightArray);
    }
    
}
