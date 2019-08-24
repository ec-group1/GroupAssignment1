import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * author: Deb
 */
public class Exercise4 {

    public static void main(String[] args){
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.print("Mutation array: ");
        for(int i = 0; i < array.length; i ++) {
            System.out.print(array[i] + ",");
        }
        /**
         * 4.1.1 insert
         */
        System.out.print("\nMutation insert");
        int[] child = mutationInsert(array, 2, 5);
        for(int i = 0; i < child.length; i ++) {
            System.out.print(child[i] + ",");
        }
        System.out.println();

        /**
         * 4.1.2 swap
         */
        System.out.print("Mutation swap");
        child = mutationSwap(array, 2, 5);
        for(int i = 0; i < child.length; i ++) {
            System.out.print(child[i] + ",");
        }
        System.out.println();

        /**
         * 4.1.3 inversion
         */
        System.out.print("Mutation inversion");
        child = mutationInversion(array, 2, 5);
        for(int i = 0; i < child.length; i ++) {
            System.out.print(child[i] + ",");
        }
        System.out.println();


        // crossover operated array
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = {9, 3, 7, 8, 2, 6, 5, 1, 4};

        /**
         * 4.2.1 order crossover
         */
        child = orderCrossover(a, b);
        for(int i = 0; i < child.length; i ++) {
            if(i == 0){
                System.out.print("child: ");
            }
            System.out.print(child[i] + ",");
        }
        System.out.println();


        /**
         * 4.2.2 pmx crossover
         */
        child =  pmxCrossover(a, b);
        for(int i = 0; i < child.length; i ++) {
            if(i == 0){
                System.out.print("child: ");
            }
            System.out.print(child[i] + ",");
        }
        System.out.println();

        /**
         * 4.2.3 cycle crossover
         */
        child =  cycleCrossover(a, b);
        for(int i = 0; i < child.length; i ++) {
            if(i == 0){
                System.out.print("child: ");
            }
            System.out.print(child[i] + ",");
        }
        System.out.println();
    }


    /**
     * set arrayList
     * @param arrays
     * @return
     */
    private static ArrayList<Integer> setOriginalArrayList(int[] arrays) {
        ArrayList<Integer> mArrayList = new ArrayList<>();
        for(int i = 0; i < arrays.length; i ++) {
            mArrayList.add(arrays[i]);
        }
        return mArrayList;
    }

    /**
     * 4.1.1 insert
     * @describe move element from pos2 following pos1
     * @param parent
     * @param pos1
     * @param pos2
     * @return
     */
    public static int[] mutationInsert(int[] parent, int pos1, int pos2){
        ArrayList<Integer> mArrayList = setOriginalArrayList(parent);

        System.out.println("("+pos1 + ", " +  pos2 + "): ");
        ArrayList<Integer> permutation = new ArrayList<Integer>();
        int numInPos2 = mArrayList.remove(pos2 -1);
        if(pos1 < pos2) {

            for(int i = 0; i <= pos1 - 1; i ++) {
                permutation.add(mArrayList.get(i));
            }
            permutation.add(numInPos2);
            for(int i = pos1; i < mArrayList.size(); i ++) {
                permutation.add(mArrayList.get(i));
            }
        }else {
            for(int i = 0; i < pos1 - 1; i ++) {
                permutation.add(mArrayList.get(i));
            }
            permutation.add(numInPos2);
            for(int i = pos1 - 1; i < mArrayList.size(); i ++) {
                permutation.add(mArrayList.get(i));
            }

        }

        int[] child = new int[parent.length];
		for(int i = 0; i < permutation.size(); i ++) {
			child[i] = permutation.get(i);
		}
        return child;
    }


    /**
     * 4.1.2 swap
     * @describe exchange the elements of pos1 & pos2
     * @param pos1
     * @param pos2
     * @return
     */
    public static int[] mutationSwap(int[] parent, int pos1, int pos2){
        System.out.println("("+pos1 + ", " +  pos2 + "): ");
        if(pos1 > pos2) {
            int t = pos1;
            pos1 = pos2;
            pos2 = t;
        }

        ArrayList<Integer> mArrayList = setOriginalArrayList(parent);
        ArrayList<Integer> permutation = new ArrayList<Integer>();
        for(int i = 0; i < pos1 - 1; i ++) {
            permutation.add(mArrayList.get(i));
        }
        permutation.add(mArrayList.remove(pos2 - 1));
        for(int i = pos1; i < pos2 - 1; i ++) {
            permutation.add(mArrayList.get(i));
        }
        permutation.add(mArrayList.remove(pos1 - 1));
        for(int i = pos2 - 2; i < mArrayList.size(); i ++) {
            permutation.add(mArrayList.get(i));
        }

        int[] child = new int[parent.length];
        for(int i = 0; i < permutation.size(); i ++) {
            child[i] = permutation.get(i);
        }
        return child;
    }

    /**
     * 4.1.3 inversion
     * @describe order elements between pos1 and pos2
     * @param pos1
     * @param pos2
     * @return
     */
    public static int[] mutationInversion(int[] parent, int pos1, int pos2) {
        System.out.println("("+pos1 + ", " +  pos2 + "): ");
        if(pos1 > pos2) {
            int t = pos1;
            pos1 = pos2;
            pos2 = t;
        }
        ArrayList<Integer> mArrayList = setOriginalArrayList(parent);
        ArrayList<Integer> permutation = new ArrayList<Integer>();
        for(int i = 0; i < pos1 - 1; i ++) {
            permutation.add(mArrayList.get(i));
        }
        for(int i = pos2 - 1; i >= pos1 - 1; i --) {
            permutation.add(mArrayList.get(i));
        }
        for(int i = pos2; i < parent.length; i ++) {
            permutation.add(mArrayList.get(i));
        }

        int[] child = new int[parent.length];
        for(int i = 0; i < permutation.size(); i ++) {
            child[i] = permutation.get(i);
        }
        return child;
    }

    /**
     * 4.2.1 order crossover
     * implement order method for permutations
     * @param parent1
     * @param parent2
     * @return
     */
    public static int[] orderCrossover(int[] parent1, int[] parent2) {
        int pos1 = 1 + (int)(Math.random() * parent1.length);
        int pos2 = 1 + (int)(Math.random() * parent1.length);

        if(pos1 > pos2) {
            int t = pos1;
            pos1 = pos2;
            pos2 = t;
        }

        System.out.print("\nOrder crossover:\nparent1: ");
        for(int i = 0; i < parent1.length; i ++) {
            System.out.print(parent1[i] + ",");
        }

        System.out.print("\nparent2: ");
        for(int i = 0; i < parent2.length; i ++) {
            System.out.print(parent2[i] + ",");
        }

        System.out.println("\nselect random position from " + pos1 + " to " + pos2);
        //to make a new array in order crossover
        int[] child = new int[parent1.length];

        //get P1 random part
        String codeGen = "";
        HashMap<Integer, Integer> mMap = new HashMap<Integer, Integer>();
        for(int i = pos1 -1; i < pos2; i ++) {
            mMap.put(parent1[i], parent2[i]);
            codeGen += parent1[i];
        }
        //store order elements
        LinkedList<Integer> mOrderList = new LinkedList<>();
        for(int i = pos2; i < parent2.length; i ++) {
            mOrderList.add(parent2[i]);
        }
        for(int i = 0; i < pos2; i ++) {
            mOrderList.add(parent2[i]);
        }

        int pos = 0;
        for(int i = pos2, j = 0;
            i < parent1.length && j < parent1.length;
            i ++, j ++) {
            if(!codeGen.contains(mOrderList.get(j) + "")) {
                child[i] = mOrderList.get(j);
                codeGen += child[i];
            }else {
                do {
                    j ++;
                }while(codeGen.contains(mOrderList.get(j) + ""));
                child[i] = mOrderList.get(j);
            }
            pos = j;
        }
        for(int i = pos1 - 1; i < pos2; i ++) {
            child[i] = parent1[i];
        }

        for(int i = 0, j = pos + 1;
            i < pos1 - 1 && j < parent1.length;
            i ++, j ++) {
            if(!codeGen.contains(mOrderList.get(j) + "")) {
                child[i] = mOrderList.get(j);
                codeGen += child[i];
            }else {

                double m = 0;
                do {
                    j ++;
                    m = mOrderList.get(j);

                }while(codeGen.contains(m + "") && j < parent1.length -1);
                child[i] = mOrderList.get(j);
            }
        }
        return child;
    }

    private static HashMap<Integer, Integer> mMapForPMX = new HashMap<Integer, Integer>();
    private static String codeGenForPMX = "";

    /**
     * 4.2.2 PMX crossover
     * implement PMX method for permutations
     * @param parent1
     * @param parent2
     * @return
     */
    public static int[] pmxCrossover(int[] parent1, int[] parent2) {
        int pos1 = 1 + (int) (Math.random() * parent1.length);
        int pos2 = 1 + (int)(Math.random() * parent1.length);

        if(pos1 > pos2) {
            int t = pos1;
            pos1 = pos2;
            pos2 = t;
        }

        System.out.print("\nPMX crossover:\nparent1: ");
        for(int i = 0; i < parent1.length; i ++) {
            System.out.print(parent1[i] + ",");
        }

        System.out.print("\nparent2: ");
        for(int i = 0; i < parent2.length; i ++) {
            System.out.print(parent2[i] + ",");
        }

        System.out.println("\nselect random position from " + pos1 + " to " + pos2);

        codeGenForPMX = "";
        mMapForPMX = new HashMap<>();
        for(int i = pos1 -1; i < pos2; i ++) {
            mMapForPMX.put(parent1[i], parent2[i]);
            codeGenForPMX += parent1[i];
        }

        int[] child = new int[parent1.length];
        for(int i = 0; i < pos1 -1; i ++) {

            if(!codeGenForPMX.contains(parent2[i] + "")) {
                child[i] = parent2[i];
            }else {
                child[i] = getMappingNum(parent2[i]);
            }
        }
        for(int i = pos1 - 1; i < pos2; i ++) {
            child[i] = parent1[i];
        }
        for(int i = pos2; i < parent1.length; i ++) {
            if(!codeGenForPMX.contains(parent2[i] + "")) {
                child[i] = parent2[i];
            }else {
                child[i] = getMappingNum(parent2[i]);
            }
        }
        return child;
    }


    /**
     * get mapping arrays
     * @param num
     * @return
     */
    private static int getMappingNum(int num) {
        try {
            int mappingNum = mMapForPMX.get(num);
            if(!codeGenForPMX.contains(mappingNum + "") || mappingNum == num) {
                return mappingNum;
            }else {

                return getMappingNum(mappingNum);
            }
        }catch (Exception e){
            return num;
        }
    }


    /**
     * 4.2.3 cycle crossover
     * @param parent1
     * @param parent2
     * @return
     */
    public static int[] cycleCrossover(int[] parent1, int[] parent2) {
        int[] child = new int[parent1.length];

        System.out.print("\nCycle crossover:\nparent1: ");
        for(int d = 0; d < parent1.length; d ++) {
            System.out.print(parent1[d] + ",");
        }
        System.out.print("\nparent2: ");
        for(int d = 0; d < parent2.length; d ++) {
            System.out.print(parent2[d] + ",");
        }
        System.out.println();

        HashMap<Integer, Integer> mMap = new HashMap<>();
        int time = 1;//执行循环次数
        int currentPosition = 0;
        while(mMap.size() < parent1.length) {

            if(time % 2 != 0) {
                /**
                 * 获取父亲遗传序列
                 */
                int m = parent2[currentPosition];
                mMap.put(currentPosition, m);
                do {
                    //找到m在parent1中的位置
                    int i = returnPositionInP1(m, parent1);
                    mMap.put(i, m);
                    m = parent2[i];
                }while(m != parent2[currentPosition]);

            }else{
                /**
                 * 获取母亲遗传序列
                 */
                int n = parent2[currentPosition];
                mMap.put(currentPosition, n);
                do {
                    //找到n在parent1中的位置
                    int i = returnPositionInP1(n, parent1);
                    mMap.put(i, parent2[i]);
                    n = parent2[i];
                }while(n != parent2[currentPosition]);
            }
            currentPosition = getNextPosition(mMap);
            time ++;


        }
//        System.out.println("mMap: " + mMap.toString());
        Object[] newchild = mMap.values().toArray();
        for(int t = 0; t < parent1.length; t++) {
            child[t] = Integer.parseInt(newchild[t] + "");
        }
        return child;
    }

    /**
     * get next position which needs to be operated
     * @param mMap
     * @return
     */
    public static int getNextPosition(HashMap<Integer, Integer> mMap) {
        int key = -1;
        Iterator<Integer> it = mMap.keySet().iterator();
        while(it.hasNext()){  // loop
            int next = it.next();
            if(key + 1 != next) {
                return key + 1;
            }
            key = next;
        }
        return key;
    }

    /**
     * find element's position in parent1
     * @param m
     * @return
     */
    private static int returnPositionInP1(int m, int[] parent1) {
        int position = 0;
        for(int i = 0; i < parent1.length; i ++) {
            if(m == parent1[i]) {
                position = i;
            }
        }
        return position;
    }
}
