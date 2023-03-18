

public class CheckWinner {
    private String cell = null;
    private int index;
    private int sum = 0;
    private String[] gameInfo;
    private int sideSize ;
    private int wonCndition =3;

    public CheckWinner(String[] gameInfo, int sideSize ) {
        this.gameInfo = gameInfo;
        this.sideSize = sideSize;
    }

    /**
     * call the functions to check every possible situation
      * @param gameInfo an array include amounts of cells like : X,O,block,free
     * @return return free or X or O mean that any player win or not
     */
    public String checkWinnerStatus(String[] gameInfo ){
        this.gameInfo = gameInfo;
        String result = "free";
        if (!(result =checkRow()).equals("free")){
            return result;
        }else if(!(result =checkColumn()).equals("free")){
            return result;
        } else if (!(result =checkMainDiagonal()).equals("free")) {
            return result;
        }else if(!(result=checkSubDiagonal()).equals("free")){
            return result;
        }else {return "free";}
    }

    /**
     * check the row of board to find suitable number of x or o
     * @return x or o or free as type string
     */
    public String checkRow() {
        for (int i = 0; i < sideSize; i++) {
            cell = null;
            sum = 0;

            for (int j = 0; j < sideSize; j++) {

                index = sideSize * i + j;

                if (gameInfo[index].equals(cell)) {
                    sum++;
                } else {
                    cell = gameInfo[index];
                    sum = 1;
                }

                if (!cell.equals("free") && sum == wonCndition) {
                    return cell;
                }
            }
        }
        return "free";
    }

    /**
     *  check the column of board to find suitable number of x or o
      * @return x or o or free as type string
     */
    public String checkColumn() {
        for (int i = 0; i < sideSize; i++) {
            cell = null;
            sum = 0;

            for (int j = 0; j < sideSize; j++) {
                index = sideSize * j + i;

                if (gameInfo[index].equals(cell)) {
                    sum++;
                } else {
                    cell = gameInfo[index];
                    sum = 1;
                }

                if (!cell.equals("free") && sum == wonCndition) {
                    return cell;
                }
            }
        }
        return "free";
    }

    /**
     * check the main diagonal of board to find suitable number of x or o
      * @return x or o or free as type string
     */
    public String checkMainDiagonal() {
        for (int i = 2-sideSize; i <= sideSize-2; i++) {
            cell = null;
            sum =0;
            for (int j = 0; j < sideSize; j++) {
                for (int k = 0; k < sideSize; k++) {
                    if (j - k != i) {
                        continue;
                    }
                    index = sideSize * j + k;
                    if (gameInfo[index].equals(cell)) {
                        sum++;
                    } else {
                        cell = gameInfo[index];
                        sum = 1;
                    }
                    if (!cell.equals("free") && sum == wonCndition) {
                        return cell;
                    }
                }
            }
        }
        return "free";
    }

    /**
     * check the sub diagonal of board to find suitable number of x or o
      * @return x or o or free as type string
     */
    public String checkSubDiagonal() {


        for (int i = 1; i <= (sideSize *2 -3); i++) {
            sum = 0;
            cell = null;
            for (int j = 0; j < sideSize; j++) {
                for (int k = 0; k < sideSize; k++) {
                    if (j + k != i) {
                        continue;
                    }
                    index = sideSize * j + k;
                    if (gameInfo[index].equals(cell)) {
                        sum++;
                    } else {
                        cell = gameInfo[index];
                        sum = 1;
                    }
                    if (!cell.equals("free") && sum == wonCndition) {
                        return cell;
                    }

                }
            }
        }
        return "free";
    }
}
