import java.util.HashMap;
import java.util.Iterator;

public class CycleCrossover {

	public int[] operate(int[] parent1, int[] parent2) {
		int[] child = new int[parent1.length];
		// HashMap
		HashMap<Integer, Integer> mMap = new HashMap<Integer, Integer>();
		int time = 1;//loop time number
		int currentPosition = 0;
		while(mMap.size() < parent1.length) {
			int m = parent2[currentPosition];
			mMap.put(currentPosition, m);
			do {
				//to find the position of m in parent1
				int i = returnPositionInP1(m, parent1);
				if(time % 2 != 0) {
					/**
					 * get parent1 genes
					 */
					mMap.put(i, m);
				}else{
					/**
					 * get parent2 genes
					 */
					mMap.put(i, parent2[i]);
				}
				m = parent2[i];
			}while(m != parent2[currentPosition]);

			currentPosition = getNextPosition(mMap);
			time ++;
		}

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
	public int getNextPosition(HashMap<Integer, Integer> mMap) {
		int key = -1;
		Iterator<Integer> it = mMap.keySet().iterator();
		while(it.hasNext()){  // 迭代器遍历
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
	private int returnPositionInP1(int m, int[] parent1) {
		int position = 0;
		for(int i = 0; i < parent1.length; i ++) {
			if(m == parent1[i]) {
				position = i;
			}
		}
		return position;
	}
}
