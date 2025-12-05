package org.jmitchell238.aoc.aoc2023.utilities.grid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class FloodFill {

    private final ArrayList<Character> PIPE_CHARS =
            new ArrayList<Character>(Arrays.asList('S', 'L', 'J', '7', 'F', '-', '|'));

    private final Map<Point, Character> map; // Your Map representing the grid
    private final Map<Point, Character> originalMap;
    private Set<Point> visited = new HashSet<>();
    private ArrayList<Point> pipePoints = new ArrayList<>();
    private int gridWidth; // Correct grid width
    private int gridHeight; // Correct grid height
    int count = 0;

    // Constructor - assuming you provide these values somehow
    public FloodFill(Map<Point, Character> map, ArrayList<Point> pipePoints, Map<Point, Character> originalMap) {
        this.map = map;
        this.originalMap = originalMap;
        this.gridWidth = map.keySet().stream().mapToInt(p -> p.x).max().orElse(0);
        this.gridHeight = map.keySet().stream().mapToInt(p -> p.y).max().orElse(0);
        this.pipePoints = pipePoints;
    }

    private boolean isValidPoint(Point point) {
        return point.x >= 0
                && point.x < gridWidth
                && point.y >= 0
                && point.y < gridHeight
                && !visited.contains(point)
                && !isPipePoint(point);
    }

    private boolean isPipePoint(Point point) {
        return pipePoints.contains(point);
    }

    public int countPointsInsidePipe(Point startingPoint) {
        int floodFillCount = floodFill(startingPoint);
        int pointsInsideOriginalMap = getPointsInsideOriginalMap();

        return pointsInsideOriginalMap;
    }

    private int getPointsInsideOriginalMap() {
        // remove every point that has an odd number x or y
        ArrayList<Point> adjustedVisited = new ArrayList<>();
        for (Point point : visited) {
            if (point.x % 2 == 0 && point.y % 2 == 0) {
                adjustedVisited.add(point);
            }
        }

        return adjustedVisited.size();
    }

    private int floodFill(Point point) {
        Vector<Point> queue = new Vector<Point>();

        // Append the position of starting point of the map
        queue.add(point);

        // Add point to count
        count++;

        // While the queue is not empty i.e. the whole component not counted
        while (queue.size() > 0) {
            // Dequeue the front node
            Point currentPoint = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);

            int posX = currentPoint.x;
            int posY = currentPoint.y;

            // Point above
            Point pointAbove = new Point(posX, posY - 1);
            // Point below
            Point pointBelow = new Point(posX, posY + 1);
            // Point to the left
            Point pointToLeft = new Point(posX - 1, posY);
            // Point to the right
            Point pointToRight = new Point(posX + 1, posY);

            // Check if adjacent points are valid
            if (isValidPoint(pointAbove)) {
                visited.add(pointAbove);
                count++;
                queue.add(pointAbove);
            }
            if (isValidPoint(pointBelow)) {
                visited.add(pointBelow);
                count++;
                queue.add(pointBelow);
            }
            if (isValidPoint(pointToLeft)) {
                visited.add(pointToLeft);
                count++;
                queue.add(pointToLeft);
            }
            if (isValidPoint(pointToRight)) {
                visited.add(pointToRight);
                count++;
                queue.add(pointToRight);
            }
        }
        return count;
    }
}
