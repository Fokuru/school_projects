//  Class author:  Raley Wilkin
//  Date created:  11/20/2025
//  General description: Takes in a map of elevations and tells you if water can flow from one specific spot.

package WaterFall;

public class Waterfall {
    public static void main (String[] args) {
    int[][] map = {
			{100, 99, 200, 200, 200, 200, 200, 200, 200, 200}, 
			{200, 98, 200, 200, 200, 200, 200, 200, 200, 200},
			{200, 97, 96, 200, 200, 200, 200, 200, 200, 200},
			{200, 200, 95, 200, 200, 200, 85, 84, 83, 200},
			{200, 200, 94, 93, 92, 200, 86, 200, 82, 200},
			{200, 150, 200, 200, 91, 200, 87, 200, 81, 200},
			{200, 200, 200, 200, 90, 89, 88, 200, 80, 200},
			{200, 150, 100, 200, 200, 200, 200, 200, 79, 200},
			{200, 200, 200, 200, 200, 200, 200, 200, 78, 77},
			{200, 98, 200, 200, 200, 200, 200, 200, 200, 76}		
	};

    int[][] map2 = {
        {100, 200, 200, 200, 200, 200, 200, 200, 200, 200}, 
        {200, 200, 200, 200, 200, 200, 200, 200, 200, 200},
        {200, 10, 200, 200, 200, 200, 200, 200, 200, 200},
        {200, 11, 10, 200, 200, 6, 85, 84, 83, 200},
        {200, 200, 14, 15, 59, 200, 86, 200, 82, 200},
        {200, 11, 12, 200, 200, 200, 87, 200, 81, 200},
        {200, 10, 200, 200, 90, 89, 88, 200, 200, 200},
        {200, 9, 8, 200, 200, 200, 200, 200, 200, 200},
        {200, 200, 7, 200, 200, 200, 200, 200, 200, 200},
        {200, 98, 6, 200, 200, 200, 200, 200, 200, 200}
    };

    int[][] map3 = {
        {200, 200, 200, 200, 200}, 
        {200, 200, 200, 200, 200},
        {200, 200, 30, 200, 200},
        {200, 200, 200, 200, 200},
        {200, 200, 200, 200, 200},
    };

    System.out.println(canFlowOff(map, 0, 0)); // Should be true
    System.out.println(canFlowOff(map, map[0].length-1, map.length-1)); // Should be true
    System.out.println(canFlowOff(map, 1, 1)); // Should be true
    System.out.println(canFlowOff(map2, 4, 5)); // Should be true
    System.out.println(canFlowOff(map3, 2, 2)); // Should be false
}
    public static boolean canFlowOff(int[][] map, int row, int col) {
        boolean canFlow = false;
        int currentNumber = map[row][col];
        if (row == 0 || row == map[0].length-1 || col == 0 || col == map.length-1) {
            return true;
        } else {
            if (canFlow == false && map[row][col+1] < currentNumber) {
                canFlow = canFlowOff(map, row, col+1);
            }
            if (canFlow == false && map[row][col-1] < currentNumber) {
                canFlow = canFlowOff(map, row, col-1);
            }
            if (canFlow == false && map[row+1][col] < currentNumber) {
                canFlow = canFlowOff(map, row+1, col);
            }
            if (canFlow == false && map[row-1][col] < currentNumber) {
                canFlow = canFlowOff(map, row-1, col);
            }
        }

        return canFlow;
    }
    
    

}

