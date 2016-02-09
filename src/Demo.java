import java.util.Random;

public class Demo {

    public static final Random random = new Random();

    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("Exactly two arguments are needed.");
            return;
        }

        final int NUMBER_OF_THREADS = Integer.parseInt(args[0]);
        final int VECTOR_LENGTH = Integer.parseInt(args[1]);

        int[] A = randomVector(VECTOR_LENGTH);
        int[] B = randomVector(VECTOR_LENGTH);

        add(A,B, NUMBER_OF_THREADS);
    }

    public static int[] add(int[] A, int[] B, int NUMBER_OF_THREADS){
        if(A == null || B == null){
            return null;
        }

        if(A.length != B.length){
            throw new IllegalArgumentException("Sizes of both arrays must be the same.");
        }

        if(NUMBER_OF_THREADS <= 0){
            throw new IllegalArgumentException("Must have at least 1 thread.");
        }

        int VECTOR_LENGTH = A.length;
        int[] result = new int[VECTOR_LENGTH];

        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        int threadLoad = VECTOR_LENGTH / NUMBER_OF_THREADS;
        int leftOvers = VECTOR_LENGTH - (threadLoad * NUMBER_OF_THREADS);

        int i = 0;
        int j = threadLoad;
        for(int t = 0; t < NUMBER_OF_THREADS; t++){
            if(leftOvers > 0){
                leftOvers--;
                j++;
            }

            threads[t] = new VectorAdderThread(i, j, A,B, result);
            i = j;
            j += threadLoad;
        }

        long startTime = System.nanoTime();
        for(Thread t : threads){
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            }
            catch (InterruptedException e){
                System.err.println("Oh well...");
            }
        }

        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

        return result;
    }

    public static int[] randomVector(int l){
        if(l < 0){
            return null;
        }

        int[] V = new int[l];

        for(int i=0; i < l; i++){
            V[i] = random.nextInt();
        }

        return V;
    }


}
