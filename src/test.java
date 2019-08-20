import java.io.IOException;
import java.util.LinkedList;

public class test {
    static LinkedList<TSPsolution> newSolutionsSet = new LinkedList<>();
    static LinkedList<TSPsolution> bestSolutionsSet = new LinkedList<>();
    static double[][] initial1;
    static double[][] initial2;
    public static void main(String[] args) throws IOException {
        initial1 = Exercise1.getData("eil51.tsp");
        initial2 = Exercise1.getData("eil51.tsp");
        TSPsolution s1 = Exercise6.createSolution(initial1);
        TSPsolution s2 = Exercise6.createSolution(initial2);
        s1 = Exercise6.calculateDistance(s1);
        s2 = Exercise6.calculateDistance(s2);
        bestSolutionsSet.add(s1);
        bestSolutionsSet.add(s2);
        for (int i = 0; i < 100; i++){
            TSPsolution ts1 = Exercise6.mutationInsert(s1);
            for (int j = 0; j < s1.order.length; j++){
                System.out.print( ts1.order[j][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
            }
            s1 = ts1;
            System.out.println("      ");
        }
    }
}
