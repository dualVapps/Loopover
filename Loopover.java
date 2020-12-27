import java.util.List;
import java.util.ArrayList;
public class Loopover
{
    char[][] ish, res;
    int[][] pos = new int[2][2];
   public Loopover(char[][] mixedUpBoard, char[][] solvedBoard)
   {
        this.ish = mixedUpBoard;
        this.res = solvedBoard;
   }
   public List<String> solve() {
            char[][] tempish = ish;
        List<String> result = new ArrayList<String>();
        List<String> fullResult = new ArrayList<String>();

        boolean isSolved = true;
        for (int i = 0; i < tempish[0].length; i++) {
            for (int j = 0; j < tempish.length; j++) {
                if (ish[i][j] != res[i][j]) {
                    isSolved = false;
                    break;
                }
            }
        }

        if(!isSolved) {
            for (int i = 0; i < tempish[0].length; i++) {
                for (int j = 0; j < tempish.length; j++) {

                    if (i == 0) {
                        pos = searchPositions(i, i, j, tempish, res);
                        if ((pos[0][0] != pos[1][0]) || (pos[0][1] != pos[1][1])) {
                            if (pos[1][0] == 0) {
                                addMovesDown(1, pos[1][1], result);
                                if ((pos[1][1] - pos[0][1]) > 0) {
                                    addMovesLeft((pos[1][1] - pos[0][1]), pos[1][0]+1, result);
                                } else
                                    addMovesRight((pos[0][1] - pos[1][1]), pos[1][0]+1, result);

                                addMovesUp(1, pos[0][1], result);
                            } else {
                                if ((pos[1][1] - pos[0][1]) > 0) {
                                    addMovesLeft((pos[1][1] - pos[0][1]), pos[1][0], result);
                                } else {
                                    addMovesRight((pos[0][1] - pos[1][1]), pos[1][0], result);
                                }
                                addMovesUp((pos[1][0] - pos[0][0]), pos[0][1], result);
                            }
                        } else {

                        }
                        pos = new int[2][2];
                        tempish = execute(tempish, result);
                        fullResult.addAll(result);
                        result.clear();

                    }
                    else if (i == 1) {
                        if (j == 0) {
                            result.add("D0");
                            result.add("L0");
                            tempish = execute(tempish, result);
                            fullResult.addAll(result);
                            result.clear();
                        }
                        if (j < 3) {
                            pos = searchPositions(i, i, (j + 1), tempish, res);
                            if ((pos[0][0] != pos[1][0]) || (pos[0][1] != pos[1][1])) {
                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                if (pos[1][0] == 0) {
                                    addMovesRight(3 - j, 1, result);
                                    addMovesDown(1, 4, result);
                                    addMovesLeft(3 - j, 1, result);
                                } else if (pos[1][0] == 1) {
                                    addMovesDown(1, 4, result);
                                    addMovesRight(4 - (4 - pos[1][1]) - j - 1, pos[1][0], result); // 4-(4-pos[1][1])-j 4 - required pos, (4-pos) - number of already executed moves, j - already solved chars, +1 required char pos
                                    addMovesUp(1, 4, result);
                                    addMovesLeft(4 - j - 1, pos[1][0], result);
                                } else {
                                    addMovesRight(3 - j, 1, result);
                                    addMovesUp(pos[1][0] - 1, 4, result);
                                    addMovesLeft(3 - j, 1, result);
                                }
                            } else {
                            }
                        }

                        if (j == 3) { //
                            pos = searchPositions(i, i, 0, tempish, res);
                            addMovesRight(4 - pos[1][1], pos[1][0], result); //Maybe first move rifht to 4 column
                            if (pos[1][0] > 2) addMovesUp(pos[1][0] - 2, 4, result);
                            else addMovesDown(2 - pos[1][0], 4, result);
                            addMovesLeft(4, 2, result);

                            tempish = execute(tempish, result);
                            fullResult.addAll(result);
                            result.clear();

                            pos = searchPositions(i, i, 4, tempish, res);
                            if (pos[0][0] != pos[1][0]||(pos[0][1] != pos[1][1])) {
                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                if (pos[1][0] == 0) {
                                    addMovesRight(3 - j, 1, result);
                                    addMovesDown(1, 4, result);
                                    addMovesLeft(3 - j, 1, result);
                                } else if (pos[1][0] == 1) {  //TODO Change to (pos[1][1] == 2)
                                    addMovesDown(1, 4, result);
                                    addMovesRight(4 - (4 - pos[1][1]) - j - 1, pos[1][0], result); // 4-(4-pos[1][1])-j 4 - required pos, (4-pos) - number of already executed moves, j - already solved chars, +1 required char pos
                                    addMovesUp(1, 4, result);
                                    addMovesLeft(1, pos[1][0], result);
                                } else if (pos[1][0] == 2) {  //TODO Change to (pos[1][1] == 2)
                                    addMovesRight(4 - (4 - pos[1][1]) - j - 1, pos[1][0], result); // 4-(4-pos[1][1])-j 4 - required pos, (4-pos) - number of already executed moves, j - already solved chars, +1 required char pos
                                    addMovesUp(1, 4, result);
                                    addMovesLeft((4 - pos[1][1]), pos[1][0], result);
                                } else {
                                    addMovesRight(3 - j, 1, result);
                                    addMovesUp(pos[1][0] - 1, 4, result);
                                    addMovesLeft(3 - j, 1, result);
                                }
                            } else {
                                addMovesUp(1, 4, result);
                                addMovesRight(3 - j, pos[0][0], result);
                                addMovesDown(1, 4, result);
                                addMovesLeft(3 - j, pos[0][0], result);
                            }
                        }
                        if (j == 4) {


                            result.add("R0");
                            result.add("U0");
                        }

                        pos = new int[2][2];
                        tempish = execute(tempish, result);
                        fullResult.addAll(result);
                        result.clear();


                    }
                    else if (i == 2) {
                        if (j == 0) {
                            result.add("D0");
                            result.add("D0");
                            result.add("L0");
                            result.add("L1");
                            tempish = execute(tempish, result);
                            fullResult.addAll(result);
                            result.clear();
                        }
                        if (j < 3) {
                            pos = searchPositions(i, i, (j + 1), tempish, res);
                            if ((pos[0][0] != pos[1][0]) || (pos[0][1] != pos[1][1])) {
                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                if (pos[1][0] == 0) {
                                    addMovesRight(3 - j, 2, result);
                                    addMovesDown(2, 4, result);
                                    addMovesLeft(3 - j, 2, result);
                                } else if (pos[1][0] == 1) {
                                    addMovesRight(3 - j, 2, result); // Moving of distanation raw
                                    addMovesDown(1, 4, result);
                                    addMovesLeft(3 - j, 2, result);
                                } else if (pos[1][0] == 2) {
//                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                    addMovesDown(1, 4, result);
                                    addMovesRight(4 - (4 - pos[1][1]) - j - 1, pos[1][0], result); // 4-(4-pos[1][1])-j 4 - required pos, (4-pos) - number of already executed moves, j - already solved chars, +1 required char pos
                                    addMovesUp(1, 4, result);
                                    addMovesLeft(4 - j - 1, pos[1][0], result);
                                } else {
                                    addMovesRight(3 - j, 2, result);
                                    addMovesUp(pos[1][0] - 2, 4, result);
                                    addMovesLeft(3 - j, 2, result);
                                }


                            } else {
                            }
                        } else if (j == 3) {
                            //Recover of positions of first colomn chars F,K
                            pos = searchPositions(i, i - 1, 0, tempish, res);

                            {
                                addMovesRight(4 - pos[1][1], pos[1][0], result);
                                addMovesDown(3 - pos[1][0], 4, result);
                                addMovesLeft(4, 3, result);
                                tempish = execute(tempish, result);
                                fullResult.addAll(result);
                                result.clear();

                            }
                            pos = searchPositions(i, i, 0, tempish, res);

                            {
                                addMovesRight(4 - pos[1][1], pos[1][0], result);
                                addMovesDown(4 - pos[1][0], 4, result);
                                if (pos[1][0] == 3) {
                                    addMovesLeft(4 - pos[1][1], pos[1][0], result);
                                }
                                addMovesLeft(4, 4, result);
                                tempish = execute(tempish, result);
                                fullResult.addAll(result);
                                result.clear();

                            }

                        }
                        if (j == 4) {
                            pos = searchPositions(i, i, j, tempish, res);

                            if ((pos[0][0] != pos[1][0]) || (pos[0][1] != pos[1][1])) {
                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                if (pos[1][0] == 0) {
                                    addMovesDown(2, 4, result);

                                } else if (pos[1][0] == 1) {
                                    addMovesDown(1, 4, result);
                                } else if (pos[1][0] == 2) {
                                    if (pos[1][0] > pos[0][1]) addMovesDown(pos[1][0] - 2, 4, result);
                                    else addMovesUp(pos[1][0] - 2, 4, result);
                                    addMovesLeft((4 - pos[1][1]), pos[1][0], result);
                                } else {
                                    if (pos[1][0] > pos[0][1]) addMovesDown(pos[1][0] - 2, 4, result);
                                    else addMovesUp(pos[1][0] - 2, 4, result);
                                    addMovesLeft((4 - pos[1][1]), pos[1][0], result);
                                    addMovesLeft(3 - j, 2, result);
                                }
                            }

                            result.add("R0");
                            result.add("R1");
                            result.add("U0");
                            result.add("U0");
                        }


                        pos = new int[2][2];
                        tempish = execute(tempish, result);
                        fullResult.addAll(result);
                        result.clear();
                    } else if (i == 3) {

                        if (j < 4) {
                            pos = searchPositions(i, i, j, tempish, res);
                            if ((pos[0][0] != pos[1][0])) {
                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                addMovesRight(4 - j, i, result);

                                addMovesUp(pos[1][0] - i, 4, result);
                                addMovesLeft(4 - j, i, result);
                                addMovesDown(1, 4, result);

                            } else if ((pos[0][1] != pos[1][1])) {
                                addMovesRight((4 - pos[1][1]), pos[1][0], result);
                                addMovesUp(1, 4, result);
                                addMovesRight(4 - (4 - pos[1][1]) - j, pos[1][0], result);
                                addMovesDown(1, 4, result);
                                addMovesLeft(4 - j, i, result);


                            }
                            tempish = execute(tempish, result);
                            fullResult.addAll(result);
                            result.clear();
                        }


                    } else if (i == 4) {
                        if (j == 0) {
                            pos = searchPositions(i, i, j, tempish, res);
                            if ((pos[0][0] != pos[1][0]) || (pos[0][1] != pos[1][1])) {
                                addMovesDown(4 - pos[1][0], 4, result);
                                addMovesRight((4 - pos[1][1]), pos[0][0], result);
                                addMovesLeft(4 - j, i, result);

                                if (pos[1][0] == 3) addMovesUp(1, 4, result);
                            }
                            tempish = execute(tempish, result);
                            fullResult.addAll(result);
                            result.clear();
                        } else if (j < 4) {
                            pos = searchPositions(i, i, j, tempish, res);
                            if ((pos[0][0] != pos[1][0]) || (pos[0][1] != pos[1][1])) {
                                if (pos[1][0] != 3) addMovesRight((4 - pos[1][1]), pos[0][0], result);
                                else addMovesRight(4 - pos[0][1], pos[0][0], result);

                                if (pos[1][0] != 3) addMovesDown(1, 4, result);
                                if (pos[1][0] != 3) addMovesRight((4 - (4 - pos[1][1]) - j), pos[0][0], result);

                                if (pos[1][0] != 3) addMovesUp(1, 4, result);
                                else addMovesDown(1, 4, result);

                                addMovesLeft(4 - j, i, result);

                                if (pos[1][0] == 3) addMovesUp(1, 4, result);


                            }
                            tempish = execute(tempish, result);
                            fullResult.addAll(result);
                            result.clear();
                        }


                    }
                }
            }
        }

        return fullResult;
    }

    public char[][] execute(char[][] ishMethod, List<String> moves) {

        char[][] result = new char[ishMethod.length][ishMethod[0].length];
        char[][] ish = new char[ishMethod.length][ishMethod[0].length];

        for (int i = 0; i < ishMethod.length; i++) {
            for (int j = 0; j < ishMethod[0].length; j++) {
                ish[i][j] = ishMethod[i][j];
            }
        }
        if (moves.size() > 0) {
            for (String str : moves) {

                int second = Integer.parseInt(str.substring(1));
                char tempChar;
                if (str.substring(0, 1).equals("U")) {
                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            result[i][j] = ish[i][j];
                        }
                    }

                    for (int i = 0; i < ish.length - 1; i++) {
                        result[i][second] = ish[i + 1][second];
                    }
                    result[result.length - 1][second] = ish[0][second];


                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            ish[i][j] = result[i][j];
                        }
                    }
                } else if (str.substring(0, 1).equals("D")) {
                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            result[i][j] = ish[i][j];
                        }
                    }

                    for (int i = 1; i < ish.length; i++) {
                        result[i][second] = ish[i - 1][second];
                    }
                    result[0][second] = ish[ish.length - 1][second];


                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            ish[i][j] = result[i][j];
                        }
                    }
                } else if (str.substring(0, 1).equals("R")) {
                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            result[i][j] = ish[i][j];
                        }
                    }

                    for (int i = 1; i < ish.length; i++) {
                        result[second][i] = ish[second][i - 1];
                    }
                    result[second][0] = ish[second][ish[second].length - 1];


                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            ish[i][j] = result[i][j];
                        }
                    }
                } else { //Left
                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            result[i][j] = ish[i][j];
                        }
                    }

                    for (int i = 0; i < ish.length - 1; i++) {
                        result[second][i] = ish[second][i + 1];
                    }
                    result[second][ish[second].length - 1] = ish[second][0];


                    for (int i = 0; i < ish.length; i++) {
                        for (int j = 0; j < ish[0].length; j++) {
                            ish[i][j] = result[i][j];
                        }
                    }
                }
            }
        } else {


            for (int i = 0; i < result.length; i++) {
                result[i] = ish[i];
            }


        }
        return result;

    }

    public void addMovesLeft(int count, int row, List<String> movesList) {
        for (int i = 0; i < count; i++) {
            movesList.add("L" + row);
        }
    }

    public void addMovesRight(int count, int row, List<String> movesList) {
        for (int i = 0; i < count; i++) {
            movesList.add("R" + row);
        }
    }

    public void addMovesUp(int count, int column, List<String> movesList) {
        for (int i = 0; i < count; i++) {
            movesList.add("U" + column);
        }
    }

    public void addMovesDown(int count, int column, List<String> movesList) {
        for (int i = 0; i < count; i++) {
            movesList.add("D" + column);
        }
    }



                    /*Solve for grid 5x5
                    -first solve first line
                    - move d1,l1
                    - solve 13, 22,32,42,
                    - move r1, u1
                    - move  l1,l2 2*d1,
                    - solve 15, 23,33,43,43 ( as 13,23,33,43,53 )
                    - move r1,r2,3*u1
                    - remain 2 lines
                    -  4 line solve move only u5, d5, l5,r5
                    - 5 line solve using 4x5,5x5 cells
                    * */


    int[][] searchPositions(int index, int raw, int column, char[][] ish, char[][] res) {
        int[][] result = new int[2][2];

        result[0][0] = raw;
        result[0][1] = column;
        result[1][0] = -1;
        result[1][1] = -1;
        for (int i = 0; i < ish.length; i++) {
            for (int j = 0; j < ish[0].length; j++) {
                if (ish[i][j] == res[raw][column]) {

                    result[1][0] = i;
                    result[1][1] = j;
                }
            }
        }


        return result;
    }

}
