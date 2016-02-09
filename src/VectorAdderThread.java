public class VectorAdderThread extends Thread{

    private int i, j;
    private final int[] A, B, C;

    public VectorAdderThread(int i, int j, int[] A, int[] B, int[] C) {
        this.i = i;
        this.j = j;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public void run() {
        for(;i<j;i++){
            C[i] = A[i] + B[i];
        }
    }
}
