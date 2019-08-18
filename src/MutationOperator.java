import java.util.ArrayList;

/**
 * MutationOperator
 * @author Wenping Du
 *
 */
public class MutationOperator {
	
	private int[] arrays = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	private ArrayList<Integer> mArrayList = new ArrayList<Integer>();
	
	public MutationOperator() {
		setOriginalArrayList();
	}
	
	private void setOriginalArrayList() {
		for(int i = 0; i < arrays.length; i ++) {
			mArrayList.add(arrays[i]);
		}
	}

	/**
	 * @description: implement insert method for permutation
	 * @param pos position
	 * @param n value
	 */
	public ArrayList<Integer> insert(int pos, int num) {
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		int len = mArrayList.size();
		if(pos <= len + 1) {
			if(pos == 1) {
				permutation.add(num);
				permutation.addAll(mArrayList);
			}else if (pos == len + 1) {
				permutation.addAll(mArrayList);
				permutation.add(num);
			}else {
				for(int i = 0; i <= len; i ++) {
					if(i < pos - 1) {
						permutation.add(mArrayList.get(i));
					}else if (i == pos - 1) {
						permutation.add(num);
					}else {
						permutation.add(mArrayList.get(i - 1));
					}
				}
			}	
		}
		return permutation;
		
	}
	
	/**
	 * implement jump method for permutation
	 * @describe move element from pos1 to pos2
	 * @param pos1 position1
	 * @param pos2 position2
	 */
	public int[] jumpMutation(int pos1, int pos2) {
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		if((pos1 >= 1 && pos2 >= 1)
				&& (pos1 <= arrays.length && pos2 <= arrays.length)) {
			permutation = insert(pos2, mArrayList.remove(pos1 - 1));
		}
		int[] returnint = new int[arrays.length];
		for(int i = 0; i < permutation.size(); i ++) {
			returnint[i] = permutation.get(i);
		}
		return returnint;
	}
	
	/**
	 * insert
	 * @describe move element from pos2 following pos1
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public ArrayList<Integer> insertMutation(int pos1, int pos2) {
		
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
		return permutation;
	}
	
	/**
	 * swap
	 * @describe exchange the elements of pos1 & pos2
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public ArrayList<Integer> swapMutation(int pos1, int pos2){
		if(pos1 > pos2) {
			int t = pos1;
			pos1 = pos2;
			pos2 = t;
		}
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
		return permutation;
	}
	
	/**
	 * implement inversion method for permutation
	 * @describe order elements between pos1 and pos2
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public ArrayList<Integer> inversionMutation(int pos1, int pos2) {
		if(pos1 > pos2) {
			int t = pos1;
			pos1 = pos2;
			pos2 = t;
		}
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		for(int i = 0; i < pos1 - 1; i ++) {
			permutation.add(mArrayList.get(i));
		}
		for(int i = pos2 - 1; i >= pos1 - 1; i --) {
			permutation.add(mArrayList.get(i));
		}
		for(int i = pos2; i < arrays.length; i ++) {
			permutation.add(mArrayList.get(i));
		}
		return permutation;
		
	}
	
}
