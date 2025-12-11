package org.jmitchell238.aoc.aoc2023.utilities.grid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"java:S106", "java:S1940", "java:S2589", "java:S1118"})
public class FloodFill {

    private final Set<Point> visited = new HashSet<>();
    private final List<Point> pipePoints;
    private final int gridWidth;
    private final int gridHeight;

    // Constructor
    public FloodFill(Map<Point, Character> map, List<Point> pipePoints) {
        this.gridWidth = map.keySet().stream().mapToInt(p -> p.x).max().orElse(0) + 1;
        this.gridHeight = map.keySet().stream().mapToInt(p -> p.y).max().orElse(0) + 1;
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
        floodFill(startingPoint);
        return getPointsInsideOriginalMap();
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

    private void floodFill(Point point) {
        List<Point> queue = new ArrayList<>();

        // Append the position of starting point of the map
        queue.add(point);
        visited.add(point);

        // While the queue is not empty i.e. the whole component not counted
        while (!queue.isEmpty()) {
            // Dequeue the last node (using as stack)
            Point currentPoint = queue.removeLast();

            int posX = currentPoint.x;
            int posY = currentPoint.y;

            Point pointAbove = new Point(posX, posY - 1);
            Point pointBelow = new Point(posX, posY + 1);
            Point pointToLeft = new Point(posX - 1, posY);
            Point pointToRight = new Point(posX + 1, posY);

            // Check if adjacent points are valid
            if (isValidPoint(pointAbove)) {
                visited.add(pointAbove);
                queue.add(pointAbove);
            }
            if (isValidPoint(pointBelow)) {
                visited.add(pointBelow);
                queue.add(pointBelow);
            }
            if (isValidPoint(pointToLeft)) {
                visited.add(pointToLeft);
                queue.add(pointToLeft);
            }
            if (isValidPoint(pointToRight)) {
                visited.add(pointToRight);
                queue.add(pointToRight);
            }
        }
    }
}
