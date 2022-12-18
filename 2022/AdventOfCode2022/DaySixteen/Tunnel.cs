using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AdventOfCode2022.DaySixteen;

// Define the Tunnel class
public class Tunnel
{
    public string From { get; set; }
    public List<string>? To { get; set; }
    public int Distance { get; set; }
}
