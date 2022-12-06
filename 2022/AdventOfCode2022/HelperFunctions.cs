using System;
using System.Collections.Generic;

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
}
