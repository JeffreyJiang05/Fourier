package org.jiang;

import org.jiang.matrix.EMatrix;
import org.jiang.matrix.IntegerMatrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerMatrix m1 = new IntegerMatrix(new int[]{1, 2}, new int[]{3, 4});
        IntegerMatrix m2 = new IntegerMatrix(m1);

        System.out.println(m1.equals(m2));
    }
}
