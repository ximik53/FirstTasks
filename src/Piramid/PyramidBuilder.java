package Piramid;

import java.util.*;


public class PyramidBuilder{

    public int[][] buildPyramid(List<Integer> inputNum) {
        for (Integer elem: inputNum) {
            if (elem == null) {
                throw new CannotBuildPyramidException("");
            }
        }

        int pyramDepth = getPyramDepth(inputNum.size());
        int pyramWidth = 2*pyramDepth - 1;

        int[][] pyram = new int[pyramDepth][pyramWidth];

        Collections.sort(inputNum);

        int x = 0;
        int inputIndx = inputNum.size() - 1;
        for (int row = pyramDepth - 1; row >= 0; row--) {
            int upNum = 0;
            int column = pyramWidth - 1;
            while (upNum < (row + 1)) {
                int elem = inputNum.get(inputIndx);

                pyram[row][column - x] = elem;

                upNum++;
                inputIndx--;
                column-=2;
            }

            x++;

        }
        for (int j = 0; j < pyramDepth; j++) {
                System.out.println(Arrays.toString(pyram[j]));
            }

        return pyram;

    }


    private static int getPyramDepth(int inputSize) {
        int pyramDepth = 0;
        while (inputSize > 0) {
            inputSize -= pyramDepth;
            pyramDepth++;
        }

        if (inputSize < 0) {
            throw new CannotBuildPyramidException("");
        } else {
            return pyramDepth - 1;
        }
    }

}