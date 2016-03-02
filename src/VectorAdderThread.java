public class VectorAdderThread extends Thread {

    private int i, j; //start and end index in the array
    private final int[] A, B, C; //input arrays A,B and output array C

    public VectorAdderThread(int i, int j, int[] A, int[] B, int[] C) {
        this.i = i;
        this.j = j;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public void run() {
        //Perform vector addition for i-j section
        for(;i<j;i++){
            C[i] = A[i] + B[i];
        }
    }
}
