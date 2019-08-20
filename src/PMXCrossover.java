
import java.util.HashMap;

/**
 * CrossOverOperator
 * @author Wenping Du
 *
 */
public class PMXCrossover {
	
	
	private static HashMap<Double, Double> mMap = new HashMap<Double, Double>();
	private static String codeGen = "";
	
	/**
	 * implement PMX method for permutations
	 */
	public static double[] operate(double[] parent1, double[] parent2) {
		int pos1 = 1 + (int) (Math.random() * parent1.length);
		int pos2 = 1 + (int)(Math.random() * parent1.length);
		
		if(pos1 > pos2) {
			int t = pos1;
			pos1 = pos2;
			pos2 = t;
		}
		System.out.println("PMX crossover: select random position from " + pos1 + " to " + pos2);
		
		codeGen = "";
		for(int i = pos1 -1; i < pos2; i ++) {
			mMap.put(parent1[i], parent2[i]);
			codeGen += parent1[i];
		}
		double[] child = new double[parent1.length];
		for(int i = 0; i < pos1 -1; i ++) {
			if(!codeGen.contains(parent2[i] + "")) {
				child[i] = parent2[i];
 			}else {
 				child[i] = getMappingNum(parent2[i]);
 			}
		}
		for(int i = pos1 - 1; i < pos2; i ++) {
			child[i] = parent1[i];
		}
		for(int i = pos2; i < parent1.length; i ++) {
			if(!codeGen.contains(parent2[i] + "")) {
				child[i] = parent2[i];
 			}else {
 				child[i] = getMappingNum(parent2[i]);
 			}
		}
		return child;
	}
	
	
	private static double getMappingNum(double num) {
		double mappingNum = mMap.get(num);
		if(!codeGen.contains(mappingNum + "")) {
			return mappingNum;
		}else {
			return getMappingNum(mappingNum);
		}
	}
	
}
