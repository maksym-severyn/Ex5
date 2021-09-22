package pl.infoshare;

public class Sorting {
    public static void sortDesc(int[] tableToSort) {
        int lengthOfTable = tableToSort.length;
        int bigger;

        for (int i = 0; i < lengthOfTable; i++) {
            for (int j = 1; j < (lengthOfTable - i); j++) {
                if (tableToSort[j] > tableToSort[j - 1]) {
                    bigger = tableToSort[j];
                    tableToSort[j] = tableToSort[j - 1];
                    tableToSort[j - 1] = bigger;
                }
            }
        }
    }
}
