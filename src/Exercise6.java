import java.io.IOException;
import java.util.LinkedList;

// method3, choose operation based on history. the chance of each operation = average enhanced Distance / total average enhanced Distance

public class Exercise6 {
    static int iterations = 1000;           // optimization iterations
    static int algorithm = 2;               // the parameter choose to execute different algorithms 1: mutation and crossover together, 2: randomly mutation or crossover, 3: choose operation based on history.
    static LinkedList<TSPsolution> newSolutionsSet = new LinkedList<>();
    static LinkedList<TSPsolution> bestSolutionsSet = new LinkedList<>();

    static LinkedList<solutionAdvantageDistance> advanDistance0 = new LinkedList<>();
    static LinkedList<solutionAdvantageDistance> advanDistance1 = new LinkedList<>();
    static LinkedList<solutionAdvantageDistance> advanDistance2 = new LinkedList<>();
    static LinkedList<solutionAdvantageDistance> advanDistance3 = new LinkedList<>();
    static LinkedList<solutionAdvantageDistance> advanDistance4 = new LinkedList<>();
    static LinkedList<solutionAdvantageDistance> advanDistance5 = new LinkedList<>();
    static LinkedList<solutionAdvantageDistance> advanDistance6 = new LinkedList<>();

    static double[][] initial1;
    static double[][] initial2;

    public static void main(String[] args) throws IOException {
        initial1 = Exercise1.getData("eil51.tsp");
        initial2 = Exercise1.getData("eil51.tsp");
        TSPsolution s1 = createSolution(initial1);
        TSPsolution s2 = createSolution(initial2);
        s1 = calculateDistance(s1);
        s2 = calculateDistance(s2);
        bestSolutionsSet.add(s1);
        bestSolutionsSet.add(s2);
        System.out.println("Initial distance result:" + bestSolutionsSet.getFirst().totalDistance);
        switch (algorithm){
            case 0:
                Method1();
                break;
            case 1:
                Method2();
                break;
            case 2:
                Method3();
                break;
        }
        System.out.println("Final distance result:" + bestSolutionsSet.getFirst().totalDistance);

    }

    public static void Method1(){
        for(int i = 0; i < iterations; i++){
            newSolutionsSet.clear();
            for (int j = 0; j < 3; j++){                // averagely create 9 new solutions based 3 mutations
                TSPsolution ts1 = mutationInsert(bestSolutionsSet.getFirst());
                //System.out.println(ts1.totalDistance);
                //for (int k = 0; k < ts1.order.length; k++){
                //    System.out.print( ts1.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                newSolutionsSet.add(ts1);
                TSPsolution ts2 = mutationSwap(bestSolutionsSet.getFirst());
                //System.out.println(ts2.totalDistance);
                //for (int k = 0; k < ts2.order.length; k++){
                //    System.out.print( ts2.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                newSolutionsSet.add(ts2);
                TSPsolution ts3 = mutationInversion(bestSolutionsSet.getFirst());
                //for (int k = 0; k < ts3.order.length; k++){
                //    System.out.print( ts3.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                //System.out.println(ts3.totalDistance);
                newSolutionsSet.add(ts3);
            }
            chooseBestTwo();
            //System.out.println(" mid Iteration " + i + " distance result:" + bestSolutionsSet.getFirst().totalDistance);
            //System.out.println(" mid Iteration " + i + " distance result:" + bestSolutionsSet.get(1).totalDistance);
            for (int j = 0; j < 2; j++){
                TSPsolution ts1 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));
                //for (int k = 0; k < ts1.order.length; k++){
                //    System.out.print( ts1.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                newSolutionsSet.add(ts1);
                TSPsolution ts2 = changeToTspObject(PMXCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                //for (int k = 0; k < ts2.order.length; k++){
                //   System.out.print( ts2.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                newSolutionsSet.add(ts2);
                TSPsolution ts3 = changeToTspObject(OrderCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                //for (int k = 0; k < ts3.order.length; k++){
                //    System.out.print( ts3.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                newSolutionsSet.add(ts3);
                TSPsolution ts4 = changeToTspObject(CycleCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                //for (int k = 0; k < ts4.order.length; k++){
                //    System.out.print( ts4.order[k][0] + " "); //+ "  " + ts1.order[j][1] + "  " + ts1.order[j][2] + "  ///// ");
                //}
                //System.out.println(" ");
                newSolutionsSet.add(ts4);
            }
            chooseBestOne();
            System.out.println("Iteration " + i + " distance result:" + bestSolutionsSet.getFirst().totalDistance);
        }
    }

    public static void Method2(){
        int action;
        int chooseParent;
        for (int i = 0; i < iterations; i++){
            newSolutionsSet.clear();
            for (int j = 0; j < 17; j ++){
                action = (int)(Math.random() * 7);
                chooseParent = (int)(Math.random());
                switch (action){
                    case 0:
                        TSPsolution ts0 = mutationInsert(bestSolutionsSet.get(chooseParent));
                        newSolutionsSet.add(ts0);
                    case 1:
                        TSPsolution ts1 = mutationSwap(bestSolutionsSet.get(chooseParent));
                        newSolutionsSet.add(ts1);
                    case 2:
                        TSPsolution ts2 = mutationInversion(bestSolutionsSet.get(chooseParent));
                        newSolutionsSet.add(ts2);
                    case 3:
                        TSPsolution ts3 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));
                        newSolutionsSet.add(ts3);
                    case 4:
                        TSPsolution ts4 = changeToTspObject(PMXCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                        newSolutionsSet.add(ts4);
                    case 5:
                        TSPsolution ts5 = changeToTspObject(OrderCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                        newSolutionsSet.add(ts5);
                    case 6:
                        TSPsolution ts6 = changeToTspObject(CycleCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                        newSolutionsSet.add(ts6);
                }
            }
            chooseBestTwo();
            System.out.println("Iteration " + i + " distance result:" + bestSolutionsSet.getFirst().totalDistance + "      " + bestSolutionsSet.getLast().totalDistance);
        }
        chooseBestOne();
    }

    public static void Method3(){
        int action;
        int chooseParent;
        int i = 0;

        for (; i < iterations; i++){
            newSolutionsSet.clear();
            if (i < 5){
                chooseParent = (int)(Math.random());
                TSPsolution ts0 = mutationInsert(bestSolutionsSet.get(chooseParent));
                newSolutionsSet.add(ts0);
                advanDistance0.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance0.size() > 5) {
                    advanDistance0.remove(0);
                }
                TSPsolution ts1 = mutationSwap(bestSolutionsSet.get(chooseParent));
                newSolutionsSet.add(ts1);
                advanDistance1.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance1.size() > 5) {
                    advanDistance1.remove(0);
                }
                TSPsolution ts2 = mutationInversion(bestSolutionsSet.get(chooseParent));
                newSolutionsSet.add(ts2);
                advanDistance2.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance2.size() > 5) {
                    advanDistance2.remove(0);
                }
                TSPsolution ts3 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));
                newSolutionsSet.add(ts3);
                advanDistance3.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance3.size() > 5) {
                    advanDistance3.remove(0);
                }
                TSPsolution ts4 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));//changeToTspObject(PMXCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                newSolutionsSet.add(ts4);
                advanDistance4.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance4.size() > 5) {
                    advanDistance4.remove(0);
                }
                TSPsolution ts5 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));//changeToTspObject(CycleCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                newSolutionsSet.add(ts5);
                advanDistance5.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance5.size() > 5) {
                    advanDistance5.remove(0);
                }

                TSPsolution ts6 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));//changeToTspObject(OrderCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                newSolutionsSet.add(ts6);
                advanDistance6.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                if (advanDistance6.size() > 5) {
                    advanDistance6.remove(0);
                }
            }else {
                for (int j = 0; j < 17; j ++){
                    action = softmaxChooseAction();
                    //System.out.println("choose the action:" + action);
                    chooseParent = (int)(Math.random());
                    switch (action){
                        case 0:
                            TSPsolution ts0 = mutationInsert(bestSolutionsSet.get(chooseParent));
                            newSolutionsSet.add(ts0);
                            advanDistance0.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance0.size() > 5) {
                                advanDistance0.remove(0);
                            }
                            break;
                        case 1:
                            TSPsolution ts1 = mutationSwap(bestSolutionsSet.get(chooseParent));
                            newSolutionsSet.add(ts1);
                            advanDistance1.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance1.size() > 5) {
                                advanDistance1.remove(0);
                            }
                            break;
                        case 2:
                            TSPsolution ts2 = mutationInversion(bestSolutionsSet.get(chooseParent));
                            newSolutionsSet.add(ts2);
                            advanDistance2.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance2.size() > 5) {
                                advanDistance2.remove(0);
                            }
                            break;
                        case 3:
                            TSPsolution ts3 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));
                            newSolutionsSet.add(ts3);
                            advanDistance3.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance3.size() > 5) {
                                advanDistance3.remove(0);
                            }
                            break;
                        case 4:
                            TSPsolution ts4 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));//changeToTspObject(PMXCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                            newSolutionsSet.add(ts4);
                            advanDistance4.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance4.size() > 5) {
                                advanDistance4.remove(0);
                            }
                            break;
                        case 5:
                            TSPsolution ts5 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));//changeToTspObject(CycleCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                            newSolutionsSet.add(ts5);
                            advanDistance5.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance5.size() > 5) {
                                advanDistance5.remove(0);
                            }
                            break;
                        case 6:
                            TSPsolution ts6 = CrossOver.crossoverEdgeRecombination(bestSolutionsSet.getFirst(), bestSolutionsSet.get(1));//changeToTspObject(OrderCrossover.operate(changeToSingleColume(bestSolutionsSet.getFirst().order), changeToSingleColume(bestSolutionsSet.get(1).order)));
                            newSolutionsSet.add(ts6);
                            advanDistance6.add(new solutionAdvantageDistance(bestSolutionsSet.get(chooseParent).totalDistance - newSolutionsSet.getLast().totalDistance));
                            if (advanDistance6.size() > 5) {
                                advanDistance6.remove(0);
                            }
                            break;
                    }
                }
            }
            chooseBestTwo();
            System.out.println("Iteration " + i + " distance result:" + bestSolutionsSet.getFirst().totalDistance + "      " + bestSolutionsSet.getLast().totalDistance);
        }
        chooseBestOne();
    }

    public static int softmaxChooseAction(){
        double point;
        double totalExp = 0;
        double[] ExpChance = new double[7];
        ExpChance[0] = Math.exp(advanDistance0.get(0).advatageDistance + advanDistance0.get(1).advatageDistance + advanDistance0.get(2).advatageDistance + advanDistance0.get(3).advatageDistance + advanDistance0.get(4).advatageDistance);
        ExpChance[1] = Math.exp(advanDistance1.get(0).advatageDistance + advanDistance1.get(1).advatageDistance + advanDistance1.get(2).advatageDistance + advanDistance1.get(3).advatageDistance + advanDistance1.get(4).advatageDistance);
        ExpChance[2] = Math.exp(advanDistance2.get(0).advatageDistance + advanDistance2.get(1).advatageDistance + advanDistance2.get(2).advatageDistance + advanDistance2.get(3).advatageDistance + advanDistance2.get(4).advatageDistance);
        ExpChance[3] = Math.exp(advanDistance3.get(0).advatageDistance + advanDistance3.get(1).advatageDistance + advanDistance3.get(2).advatageDistance + advanDistance3.get(3).advatageDistance + advanDistance3.get(4).advatageDistance);
        ExpChance[4] = Math.exp(advanDistance4.get(0).advatageDistance + advanDistance4.get(1).advatageDistance + advanDistance4.get(2).advatageDistance + advanDistance4.get(3).advatageDistance + advanDistance4.get(4).advatageDistance);
        ExpChance[5] = Math.exp(advanDistance5.get(0).advatageDistance + advanDistance5.get(1).advatageDistance + advanDistance5.get(2).advatageDistance + advanDistance5.get(3).advatageDistance + advanDistance5.get(4).advatageDistance);
        ExpChance[6] = Math.exp(advanDistance6.get(0).advatageDistance + advanDistance6.get(1).advatageDistance + advanDistance6.get(2).advatageDistance + advanDistance6.get(3).advatageDistance + advanDistance6.get(4).advatageDistance);
        for (int i = 0; i < 7; i++){
            totalExp += ExpChance[i];
        }
        point = Math.random();
        if (point < ((ExpChance[0]) / totalExp)){
            return 0;
        }else if (point < ((ExpChance[0] + ExpChance[1]) / totalExp)){
            return 1;
        }else if (point < ((ExpChance[0] + ExpChance[1] + ExpChance[2]) / totalExp)){
            return 2;
        }else if (point < ((ExpChance[0] + ExpChance[1] + ExpChance[2] + ExpChance[3]) / totalExp)){
            return 3;
        }else if (point < ((ExpChance[0] + ExpChance[1] + ExpChance[2] + ExpChance[3] + ExpChance[4]) / totalExp)){
            return 4;
        }else if (point < ((ExpChance[0] + ExpChance[1] + ExpChance[2] + ExpChance[3] + ExpChance[4] + ExpChance[5]) / totalExp)){
            return 5;
        }else if (point < 1){
            return 6;
        }
        return 7;
    }

    public static void chooseBestTwo(){
        bestSolutionsSet.add(newSolutionsSet.get(0));
        for (int j = 1; j < newSolutionsSet.size(); j++) {      // replace the worst solution in best solution set if possible
            if (bestSolutionsSet.get(0).totalDistance > bestSolutionsSet.get(1).totalDistance){
                if (newSolutionsSet.get(j).totalDistance < bestSolutionsSet.get(0).totalDistance){
                    bestSolutionsSet.remove(0);
                    bestSolutionsSet.add(newSolutionsSet.get(j));
                }
            }else {
                if (newSolutionsSet.get(j).totalDistance < bestSolutionsSet.get(1).totalDistance){
                    bestSolutionsSet.remove(1);
                    bestSolutionsSet.add(newSolutionsSet.get(j));
                }
            }
        }
        while(bestSolutionsSet.size() > 2){
            if (bestSolutionsSet.get(bestSolutionsSet.size()-1).totalDistance > bestSolutionsSet.get(bestSolutionsSet.size()-2).totalDistance){
                bestSolutionsSet.removeLast();
            }else {
                bestSolutionsSet.remove(bestSolutionsSet.size()-1);
            }
        }
        newSolutionsSet.clear();
    }

    public static void chooseBestOne(){
        /*
        while (newSolutionsSet.size() != 0){
            if (newSolutionsSet.get(0).totalDistance < bestSolutionsSet.get(0).totalDistance){
                bestSolutionsSet.remove(0);
                bestSolutionsSet.add(newSolutionsSet.get(0));
                newSolutionsSet.remove(0);
            }else {
                newSolutionsSet.remove(0);
            }
        }
        */
        for (int i = 0; i < newSolutionsSet.size(); i ++){
            //System.out.println("  " + newSolutionsSet.get(i).totalDistance + "    " + bestSolutionsSet.get(0).totalDistance);
            if (newSolutionsSet.get(i).totalDistance < bestSolutionsSet.get(0).totalDistance) {
                TSPsolution t1 = new TSPsolution(newSolutionsSet.get(i));
                bestSolutionsSet.add(t1);
                //System.out.println(t1.totalDistance + "  -----------  " + newSolutionsSet.get(i).totalDistance);
            }
        }
        //System.out.println("00000000000000   " + bestSolutionsSet.size());
        TSPsolution t = new TSPsolution(bestSolutionsSet.get(0));
        double bestDistance = 2 * bestSolutionsSet.get(0).totalDistance;
        for (int i = 0; i < bestSolutionsSet.size(); i ++){
            //System.out.println("----" + bestSolutionsSet.get(i) + "    " + bestSolutionsSet.size());
            if(bestSolutionsSet.get(i).totalDistance < bestDistance){
                t.order = bestSolutionsSet.get(i).order;
                t.totalDistance = bestSolutionsSet.get(i).totalDistance;
                bestDistance = t.totalDistance;
                //System.out.println(i);
            }
        }
        newSolutionsSet.clear();
        bestSolutionsSet.clear();
        bestSolutionsSet.add(t);
        /*
        while (bestSolutionsSet.size() != 1){
            if (bestSolutionsSet.get(0).totalDistance < bestSolutionsSet.get(1).totalDistance){
                bestSolutionsSet.remove(1);
            }else {
                bestSolutionsSet.remove(0);
            }
        }
        */
    }

    public static TSPsolution createSolution(double[][] s){
        TSPsolution t = new TSPsolution(s);
        return t;
    }

    public static TSPsolution calculateDistance(TSPsolution t){
        t.totalDistance = 0;
        for(int i = 0; i < t.order.length - 1; i++){
            t.totalDistance += Math.sqrt(Math.pow(t.order[i][1] - t.order[i+1][1],2) + Math.pow(t.order[i][2] - t.order[i+1][2],2));
        }
        return t;
    }

    public static TSPsolution mutationInsert(TSPsolution s){
        TSPsolution p = new TSPsolution(s);
        int index1 = (int)(Math.random()*p.order.length);
        int index2;
        while((index2 = (int)(Math.random()*p.order.length)) == index1){
        }
        double[][] newOrder = new double[p.order.length][3];
        int j = 0;
        if (index1 > index2){
            int a = index1;
            index1 = index2;
            index2 = a;
        }
        for (int i = 0; i < p.order.length; i++){
            if (i == index1){
                //System.out.println(j);
                newOrder[i][0] = p.order[j][0];
                newOrder[i][1] = p.order[j][1];
                newOrder[i][2] = p.order[j][2];
                i++;
                newOrder[i][0] = p.order[index2][0];
                newOrder[i][1] = p.order[index2][1];
                newOrder[i][2] = p.order[index2][2];
            }else if (j == index2){
                j++;
                newOrder[i][0] = p.order[j][0];
                newOrder[i][1] = p.order[j][1];
                newOrder[i][2] = p.order[j][2];
            }else {
                newOrder[i][0] = p.order[j][0];
                newOrder[i][1] = p.order[j][1];
                newOrder[i][2] = p.order[j][2];
            }
            j++;
        }
        p.order = newOrder;
        p = calculateDistance(p);
        return p;
    }

    public static TSPsolution mutationSwap(TSPsolution s){
        TSPsolution p = new TSPsolution(s);
        int index1 = (int)(Math.random()*p.order.length);
        int index2;
        while((index2 = (int)(Math.random()*p.order.length)) == index1){
        }
        double a = p.order[index1][0];
        p.order[index1][0] = p.order[index2][0];
        p.order[index2][0] = a;

        a = p.order[index1][1];
        p.order[index1][1] = p.order[index2][1];
        p.order[index2][1] = a;

        a = p.order[index1][2];
        p.order[index1][2] = p.order[index2][2];
        p.order[index2][2] = a;
        p = calculateDistance(p);
        return p;
    }

    public static TSPsolution mutationInversion(TSPsolution s){
        TSPsolution p = new TSPsolution(s);
        int index1 = (int)(Math.random()*p.order.length);
        int index2;
        double[][] newOrder = new double[p.order.length][3];
        while((index2 = (int)(Math.random()*p.order.length)) == index1){
        }
        //System.out.println(index1 + "  " + index2);
        if (index1 > index2){
            int a = index1;
            index1 = index2;
            index2 = a;
        }
        for (int i = 0; i < p.order.length; i++){
            if (i < index1 || i > index2){
                newOrder[i][0] = p.order[i][0];
                newOrder[i][1] = p.order[i][1];
                newOrder[i][2] = p.order[i][2];
            }else {
                newOrder[i][0] = p.order[index2 - (i - index1)][0];
                newOrder[i][1] = p.order[index2 - (i - index1)][1];
                newOrder[i][2] = p.order[index2 - (i - index1)][2];
            }
        }
        p.order = newOrder;
        p = calculateDistance(p);
        return p;
    }

    public static TSPsolution crossover(TSPsolution p1, TSPsolution p2){

        return p1;
    }

    public static double[] changeToSingleColume(double[][] d){
        double[] r = new double[d.length];
        for(int i = 0; i < d.length; i++){
            r[i] = d[i][0];
        }
        return r;
    }

    public static TSPsolution changeToTspObject(double[] d){
        double[][] r1 = new double[d.length][3];
        for (int i = 0; i < d.length; i++){
            r1[i][0] = d[i];
            r1[i][1] = initial1[(int)d[i]][1];
            r1[i][2] = initial1[(int)d[i]][2];
        }
        TSPsolution ts1 = new TSPsolution(r1);
        ts1 = calculateDistance(ts1);
        return ts1;
    }
}

class TSPsolution{
    double[][] order;
    double totalDistance;
    public TSPsolution(double[][] a) {
        this.order = new double[a.length][3];
        for ( int i = 0; i < a.length; i ++){
            this.order[i][0] = i;
            this.order[i][1] = a[i][0];
            this.order[i][2] = a[i][1];
        }
        this.totalDistance = 0;
    }

    public TSPsolution(TSPsolution t){
        this.totalDistance = t.totalDistance;
        this.order = new double[t.order.length][3];
        for (int i = 0; i < t.order.length; i++){
            this.order[i][0] = t.order[i][0];
            this.order[i][1] = t.order[i][1];
            this.order[i][2] = t.order[i][2];
        }
    }
}
class solutionAdvantageDistance{
    double advatageDistance;
    public solutionAdvantageDistance(double advatageDistance){
        this.advatageDistance = advatageDistance;
    }
}
