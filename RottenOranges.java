//The approach here is to use BFS to traverse through the graph.
//We first add the rotten oranges to the queue and increase the count of fresh oranges.
//We then start BFS, and for each rotten tomato, we check if there are any neighboring fresh oranges, if yes, we mark them rotten and add it to queue and decrease the fresh oranges' count.
//After every level, we increase the time and after the traversal, we check if the fresh oranges count is 0, if yes, we return time -1, and if not, then we return -1.
//Time Complexity: O(m*n), where m and n are the lengths of rows and colums of the given matrix
//Space Complexity: O(m*n)
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0 || grid == null){
            return 0;
        }
        //calc grid length
        int m = grid.length;
        int n = grid[0].length;
        //Initialize fresh count
        int fresh = 0;
        //Initialize time variable
        int time = 0;
        //Queue for storing the indices of rotten oranges
        Queue<int[]> q = new LinkedList<>();
        //dirs array for calc indices of 4 adjacent neighbors
        int[][] dirs = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i,j});
                }
                if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }
        //BFS
        if(fresh == 0) return time;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                int[] cur = q.poll();
                for(int[] dir: dirs){
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    //bounds check
                    if(nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc] == 1){
                        q.add(new int[]{nr, nc}); //add fresh one's indices to queue
                        grid[nr][nc] = 2; // mark it rotten
                        fresh--;
                    }
                }
            }
            time++; //we increase time after every level
        }
        if(fresh != 0) return -1;
        return time - 1;//because time increases even after processing the last level.
    }
}
