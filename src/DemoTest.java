import org.junit.Test;

import static org.junit.Assert.*;

public class DemoTest {

    @Test
    public void testAddNull() {
        assertNull(Demo.add(null, null, 1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddDifferentSizes() {
        Demo.add(new int[1], new int[2], 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddZeroThreads() {
        Demo.add(new int[5], new int[5], 0);
    }

    @Test
    public void testAddEmpty() {
        assertEquals(Demo.add(new int[0], new int[0], 1).length, 0);
    }

    @Test
    public void testAddOneEntryOneThread() {
        assertArrayEquals(Demo.add(new int[]{1},new int[]{2}, 1), new int[]{3});
    }

    @Test
    public void testAddOneEntryTwoThreads() {
        assertArrayEquals(Demo.add(new int[]{1},new int[]{2}, 2), new int[]{3});
    }

    @Test
    public void testAddTwoEntriesTwoThreads() {
        assertArrayEquals(Demo.add(new int[]{1,2},new int[]{2,-2}, 2), new int[]{3,0});
    }

    @Test
    public void testAddFiveEntriesThreeThreads() {
        assertArrayEquals(Demo.add(new int[]{1,2,3,4,5},new int[]{2,-2,1,2,3}, 3), new int[]{3,0,4,6,8});
    }

    @Test
    public void testAddThousandEntriesOneThread() {
        int[] A = dummyArray(1000);
        int[] B = dummyArray(1000);

        assertArrayEquals(Demo.add(A,B, 1), expectedAdd(A,B));
    }

    @Test
    public void testAddThousandEntriesTwoThreads() {
        int[] A = dummyArray(1000);
        int[] B = dummyArray(1000);

        assertArrayEquals(Demo.add(A,B, 2), expectedAdd(A,B));
    }

    @Test
    public void testAddThousandEntriesFivehundredThreads() {
        int[] A = dummyArray(1000);
        int[] B = dummyArray(1000);

        assertArrayEquals(Demo.add(A,B, 500), expectedAdd(A,B));
    }

    @Test
    public void testAddThousandEntriesThousandAndOneThreads() {
        int[] A = dummyArray(1000);
        int[] B = dummyArray(1000);

        assertArrayEquals(Demo.add(A,B, 1001), expectedAdd(A,B));
    }

    private int[] dummyArray(int l){
        int[] D = new int[l];

        for(int i = 0; i < l; i++) {
            D[i] = i;
        }

        return D;
    }

    private int[] expectedAdd(int[] A, int[] B){
        int[] result = new int[A.length];

        for(int i = 0; i < result.length; i++){
            result[i] = A[i] + B[i];
        }

        return result;
    }
}