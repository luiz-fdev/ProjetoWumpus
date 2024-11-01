
package main.strategies;

import java.util.List;

import main.game.map.Map;
import main.game.map.Point;


public class FewerObstacles implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
		int minObstacleCount = Integer.MAX_VALUE;
		Point bestPoint = null;

		for (Point nextPoint : possibleNextStep) {
			int obstacleCount = countAdjacentObstacles(nextPoint, map);
			if (obstacleCount < minObstacleCount) {
				minObstacleCount = obstacleCount;
				bestPoint = nextPoint;
			}
		}
		return bestPoint;
	}

	private int countAdjacentObstacles(Point point, Map map) {
		int count = 0;
		int x = point.getPositionX();
		int y = point.getPositionY();

		// Check all 8 possible adjacent squares
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				if (dx == 0 && dy == 0) continue; // Skip the center point
				Point adjacent = new Point(x + dx, y + dy);
				String content = map.get(adjacent);
				if (content != null && !content.equals("*")) {
					count++;
				}
			}
		}
		return count;
	}
}
