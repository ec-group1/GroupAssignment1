public class MainClass {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		/**
		 * insert
		 */
		System.out.println("MutationOperator insert: " + new MutationOperator().insertMutation(2, 5).toString());
		
		/**
		 * jump
		 */
		System.out.println("MutationOperator jump: " + new MutationOperator().jumpMutation(2, 5).toString());
		
		/**
		 * swap
		 */
		System.out.println("MutationOperator swap: " + new MutationOperator().swapMutation(2, 5).toString());
		
		/**
		 * inversion
		 */
		System.out.println("MutationOperator inversion: " + new MutationOperator().inversionMutation(2, 5).toString());
		
		//Order crossover
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] b = {9, 3, 7, 8, 2, 6, 5, 1, 4};
		
		int[] child =  new OrderCrossover().operate(a, b);
		for(int i = 0; i < child.length; i ++) {
			System.out.print(child[i] + ",");
		}
		System.out.println();
		
		//PMX crossover
		child =  new PMXCrossover().operate(a, b);
		for(int i = 0; i < child.length; i ++) {
			System.out.print(child[i] + ",");
		}
		System.out.println();
		
		//Cycle crossover
		int[] parent2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] parent1 = {9, 3, 7, 8, 2, 6, 5, 1, 4};
		child = new CycleCrossover().operate(parent1, parent2);
		for(int i = 0; i < child.length; i ++) {
			System.out.print(child[i] + ",");
		}
		System.out.println();
		
		int[] parent3 = {1, 2, 3, 4, 5, 6, 7};
		int[] parent4 = {5, 4, 6, 7, 2, 3, 1};
		child = new CycleCrossover().operate(parent3, parent4);
		for(int i = 0; i < child.length; i ++) {
			System.out.print(child[i] + ",");
		}
		System.out.println();
		
	}

}
