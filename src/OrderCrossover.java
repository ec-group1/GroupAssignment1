

import java.util.HashMap;
import java.util.LinkedList;

public class OrderCrossover {
	
	/**
	 * implement order method for permutations
	 */
	//
	public static double[] operate(double[] parent1, double[] parent2) {
		int pos1 = 1 + (int) (Math.random() * parent1.length);
		int pos2 = 1 + (int)(Math.random() * parent1.length);
		
		if(pos1 > pos2) {
			int t = pos1;
			pos1 = pos2;
			pos2 = t;
		}

		for(int i =0; i < parent1.length; i ++){
			System.out.println(parent1[i] + "---" + parent2[i] + ", ");
		}
		System.out.println("Order crossover: select random position from " + pos1 + " to " + pos2);
		//to make a new array in order crossover
		double[] child = new double[parent1.length];
		
		//get P1 random part
		String codeGen = "";
		HashMap<Double, Double> mMap = new HashMap<Double, Double>();
		for(int i = pos1 -1; i < pos2; i ++) {
			mMap.put(parent1[i], parent2[i]);
			codeGen += parent1[i];
		}
		//store order elements
		LinkedList<Double> mOrderList = new LinkedList<>();
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
					System.out.print(j + " j ");
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
					System.out.print(j + " ");
				}while(codeGen.contains(m + "") && j < parent1.length -1);
				child[i] = mOrderList.get(j);
			}
		}
		System.out.println("order crossover====== over");
		return child;
	}

}
