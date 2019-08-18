import java.util.HashMap;
import java.util.Iterator;

public class CycleCrossover {
	
	public int[] operate(int[] parent1, int[] parent2) {
		int[] child = new int[parent1.length];
		
		HashMap<Integer, Integer> mMap = new HashMap<Integer, Integer>();
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
//		System.out.println(mMap.keySet().toString());
//		System.out.println("Cycle crossover: " + mMap.values().toString());
		
		Object[] newchild = mMap.values().toArray();
		for(int t = 0; t < parent1.length; t++) {
			child[t] = Integer.parseInt(newchild[t] + "");
		}
		return child;
	}
	
	/**
	 * 获取下一个即将进行遍历的位置
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
