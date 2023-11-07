package com.test.aj.collections;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    private <T> List<List<T>> batchList(List<T> fullList, Integer batchSize) {
        List<List<T>> chunks = new ArrayList<>();
        int listSize = fullList.size();
        for (int i = 0; listSize > i; i = i + batchSize) {
            List<T> chunk = fullList.subList(i, Math.min(listSize, i + batchSize));
            chunks.add(chunk);
        }
        return chunks;
    }


    private <T> List<List<T>> batchListWithoutSublist(List<T> fullList, final int batchSize) {
        List<List<T>> chunks = new ArrayList<>();
        int listSize = fullList.size();
        for (int i = 0; i < listSize; i = i + batchSize) {
            List<T> chunk = new ArrayList<>();
            for (int j = i; j < Math.min(listSize, i + batchSize); j++) {
                chunk.add(fullList.get(j));
            }
            chunks.add(chunk);
        }
        return chunks;
    }

    @BeforeClass
    public static void setup() {
    }

    @Test
    public void testListBatching() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        List<List<Integer>> chunks = this.batchList(list, 5);
        System.out.println(chunks);
    }

    @Test
    public void testListBatchingWithoutSublist() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        List<List<Integer>> chunks = this.batchListWithoutSublist(list, 5);
        System.out.println(chunks);
    }
}
