import java.util.ArrayList;
import java.util.Arrays;

public class NQueens {
    ArrayList<ArrayList<String>> result = new ArrayList<>();

    public ArrayList<ArrayList<String>> solveNQueens(int n) {
        int[][] visited = new int[n][n];
        String[][] res = new String[n][n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = ".";
            }
        }
        populate(res,n,n,visited,0,0);
        return result;
    }

    private void populate(String[][] res, int rem, int n, int[][] visited, int indX, int indY) {
        if(rem<=0){
            ArrayList<String> tmp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append((res[i][j]));
                }
                tmp.add(sb.toString());
            }
            result.add(tmp);
        }
        for (int i = indX; i < n; i++) {
            for (int j = indY; j < n ; j++) {
                if(isValid(visited,i,j)) {
                    fill(visited,i,j,1);
                    res[i][j] = "Q";
                    populate(res, rem - 1, n , visited,i+1,0);

                    res[i][j] = ".";
                    fill(visited,i,j,-1);
                }
            }
        }
    }

    private void fill(int[][] visited, int x, int y,int salt) {
        int n = visited.length;
        for (int i = 0; i <n ; i++) {

            if(isOnBoard(x,i,n))
                visited[x][i] +=salt;

            if(isOnBoard(i,y,n))
                visited[i][y] +=salt;

            if(isOnBoard(x+i,y+i,n))
                visited[x+i][y+i] +=salt;

            if(isOnBoard(x-i,y-i,n))
                visited[x-i][y-i] +=salt;

            if(isOnBoard(x+i,y-i,n))
                visited[x+i][y-i] +=salt;

            if(isOnBoard(x-i,y+i,n))
                visited[x-i][y+i] +=salt;
        }

    }

    private boolean isValid(int[][] visited, int x, int y) {
        return isOnBoard(x,y,visited.length) && visited[x][y]<=0;
    }

    private boolean isOnBoard(int x,int y,int n){
        return x>=0 && y>=0 && x<n && y<n;
    }
}
