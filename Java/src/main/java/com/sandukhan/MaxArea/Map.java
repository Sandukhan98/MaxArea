package com.sandukhan.MaxArea;

import java.util.Stack;

public class Map {

    int map[][];

    public Map(int width, int height){
        map = new int[height][width];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }

        }
    }

    public void select(int x, int y){
        map[y][x] = (map[y][x] + 1) % 2;
    }

    public int[] getMaxAreaHist(int hist[])
    {
        int n = hist.length;
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp; // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        int L = 0;
        while (i < n)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || hist[s.peek()] <= hist[i])
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek(); // store the top index
                s.pop(); // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top) {
                    //store the height of largest rectangle found until now
                    L = tp;
                    max_area = area_with_top;
                }

            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
            {
                //store the height of largest rectangle found until now
                L = tp;
                max_area = area_with_top;
            }
        }

        int end_index = L, start_index = L;

        // get the right born of the rectangle
        for (int j = L; j < (L + (max_area / hist[L])) && j < n; j++) {
            if(hist[j] >= hist[L]) end_index = j;
            else break;
        }

        // get the left born of the rectangle
        if(hist[L] * (end_index - start_index) != max_area){
            for (int j = L; j >= 0; j--) {
                if(hist[j] >= hist[L]) start_index = j;
                else break;
            }
        }
        int[] res = {start_index, end_index, max_area};
        return res;

    }

    int[] nextHistogram(int[] previous, int[] current){
        int[] res = new int[previous.length];

        for(int i = 0; i < previous.length; i++){
            if(current[i] == 0) res[i] = 0;
            else res[i] = current[i] + previous[i];
        }
        return res;
    }

    int[] getMaxArea(){
        int final_res[] = new int[5];
        int max_area = 0;
        int cur_hist[] = new int[map[0].length];
        int start_column = 0, end_column = 0, start_row = 0, end_row = 0;
        for (int i = 0; i < map.length; i++) {
            cur_hist = nextHistogram(cur_hist, map[i]);
            int res[] = getMaxAreaHist(cur_hist);
            if(res[2] > max_area) {
                max_area = res[2];
                start_column = res[0];
                end_column = res[1];
                end_row = i;
                start_row = i - (max_area / (end_column - start_column + 1)) + 1;
            }
        }
        final_res[0] = max_area;
        final_res[1] = start_column;
        final_res[2] = end_column;
        final_res[3] = start_row;
        final_res[4] = end_row;
        return  final_res;
    }

    public void printMatrix(){
        for (int i = 0; i < map.length; i++) {
            String line = "";
            for (int j = 0; j < map[0].length; j++) {
                line += map[i][j] + ", ";
            }
            System.out.println(line);
        }
        System.out.println("======================");
    }
}
