using System;
using System.Collections.Generic;
using System.Linq;

namespace AdventOfCode2022;

/// <summary>
/// This class contains code that usually are helpful on multiple days.
/// </summary>
public static class HelperFunctions
{
    // Split a string into separate strings, as specified by the delimiter.
    public static string[] SplitToStringArray(this string str, string split, bool removeEmpty)
    {
        return str.Split(new string[] { split }, removeEmpty ? StringSplitOptions.RemoveEmptyEntries : StringSplitOptions.None);
    }

    // Split a string into separate strings, as specified by the delimiter.
    public static string[] SplitToStringArray(this string str, char[] split, bool removeEmpty)
    {
        return str.Split(split, removeEmpty ? StringSplitOptions.RemoveEmptyEntries : StringSplitOptions.None);
    }

    // Split a string into an int array.
    public static int[] SplitToIntArray(this string str, string split)
    {
        return Array.ConvertAll(str.SplitToStringArray(split, true), s => int.Parse(s));
    }

    // Split a string into an int array.
    public static int[] SplitToIntArray(this string str, params char[] split)
    {
        return Array.ConvertAll(str.SplitToStringArray(split, true), s => int.Parse(s));
    }

    // Split a string into a long array.
    public static long[] SplitToLongArray(this string str, string split)
    {
        return Array.ConvertAll(str.SplitToStringArray(split, true), s => long.Parse(s));
    }

    // Split a string into a long array.
    public static long[] SplitToLongArray(this string str, params char[] split)
    {
        return Array.ConvertAll(str.SplitToStringArray(split, true), s => long.Parse(s));
    }

    public static V? Read<K, V>(this Dictionary<K, V?> dict, K key)
    {
        return dict.ContainsKey(key) ? dict[key] : default(V);
    }
    
    // Create a stack from a string.
    public static Stack<char> CreateStack(string str)
    {
        var chars = str.ToCharArray();
        var stack = new Stack<char>();
        foreach (var ch in chars) stack.Push(ch);

        return stack;
    }
    
    // Get from or Add to a Dictionary
    public static TValue GetOrAdd<TKey, TValue>(this Dictionary<TKey, TValue> dict, TKey key, Func<TKey, TValue> func)
        where TKey : notnull
    {
        if (dict.TryGetValue(key, out var value))
            return value;
        return dict[key] = func(key);
    }

    public static string[] ToLines(this string input) => input.Split(new char[] { '\r', '\n' }, StringSplitOptions.RemoveEmptyEntries);
    public static List<int> AsInt32s(this string[] input) => input.Select(x => int.Parse(x)).ToList();
    public static List<int> AsInt32s(this char[] input) => input.Select(x => int.Parse(x.ToString())).ToList();
    public static List<int?> TryAsInt32s(this string[] input) => input.Select(x => int.TryParse(x, out int y) ? (int?)y : null).ToList();
    public static int MisMatchCount(this string input, string other) => input.ToCharArray().Except(other.ToCharArray()).Count() + other.ToCharArray().Except(input.ToCharArray()).Count();
    public static IEnumerable<(int index, T value)> WithIndexes<T>(this IEnumerable<T> values)
    {
        int index = 0;
        foreach (T val in values)
        {
            yield return (index++, val);
        }
    }
    public static List<(int index, Int32 value)> NonNull(this IEnumerable<(int index, Int32? value)> values) => values.Where(x => x.value.HasValue).Select(x => (x.index, x.value.Value)).ToList();
    public static List<long> AsInt64s(this string[] input) => input.Select(x => long.Parse(x)).ToList();
    public static ValueTuple<FirstType, SecondType> As<FirstType, SecondType>(this string[] input, Func<string, FirstType> p1, Func<string, SecondType> p2)
        => (p1(input[0]), p2(input[1]));
    public static ValueTuple<FirstType, SecondType, ThirdType> As<FirstType, SecondType, ThirdType>(this string[] input, Func<string, FirstType> p1, Func<string, SecondType> p2, Func<string, ThirdType> p3)
        => (p1(input[0]), p2(input[1]), p3(input[2]));
    public static ValueTuple<FirstType, SecondType, ThirdType, FourthType> As<FirstType, SecondType, ThirdType, FourthType>(this string[] input, Func<string, FirstType> p1, Func<string, SecondType> p2, Func<string, ThirdType> p3, Func<string, FourthType> p4)
        => (p1(input[0]), p2(input[1]), p3(input[2]), p4(input[3]));
    public static ValueTuple<T, T> AsValueTuple<T>(this IEnumerable<T> input) => (input.First(), input.Skip(1).First());
    public static IEnumerable<string> SplitBy(this string contents, string splitBy)
    {
        int splitLength = splitBy.Length;
        int previousIndex = 0;
        int ix = contents.IndexOf(splitBy);
        while (ix >= 0)
        {
            yield return contents.Substring(previousIndex, ix - previousIndex);
            previousIndex = ix + splitLength;
            ix = contents.IndexOf(splitBy, previousIndex);
        }
        string remain = contents.Substring(previousIndex);
        if (!string.IsNullOrEmpty(remain)) yield return remain;
    }
    public static bool[][] MapAsBool(this string[] lines, char mapAsTrue = '#') => lines.Select(row => row.Select(x => x == mapAsTrue).ToArray()).ToArray();
    public static IEnumerable<(int row, int col)> JustTrue(this bool[][] map)
    {
        bool[] rowData;
        for (int row = 0; row < map.Length; ++row)
        {
            rowData = map[row];
            for (int col = 0; col < rowData.Length; ++col)
            {
                if (rowData[col]) yield return (row, col);
            }
        }
    }
    public static List<T> With<T>(this List<T> list, T value)
    {
        list.Add(value);
        return list;
    }
    public static List<T> With<T>(this List<T> list, params T[] values)
    {
        list.AddRange(values);
        return list;
    }
    public static long GetModWithOffset(this IEnumerable<(int offset, int value)> values)
    {
        long value = values.First().value, step = value;
        foreach (var record in values.Skip(1))
        {
            while ((value + record.offset) % record.value != 0)
            {
                value += step;
            }
            step *= record.value;
        }
        return value;
    }
    public static int gcf(this int a, int b)
    {
        while (b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static int lcm(this int a, int b)
    {
        return (a / gcf(a, b)) * b;
    }
    public static IEnumerable<IEnumerable<T>> GetPermutations<T>(this IEnumerable<T> items, int count)
    {
        int i = 0;
        foreach (var item in items)
        {
            if (count == 1)
                yield return new T[] { item };
            else
            {
                foreach (var result in GetPermutations(items.Skip(i + 1), count - 1))
                {
                    yield return new T[] { item }.Concat(result);
                }
            }

            ++i;
        }
    }
}
