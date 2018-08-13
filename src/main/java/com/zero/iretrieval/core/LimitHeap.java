package com.zero.iretrieval.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 有限大/小根堆，创建对象的时候设定数量限制，一直添加元素，可以求出总元素集合中前n大/小的元素，但是没有排序，后序加一个排序的方法
 * 
 * @author z
 *
 * @param <T>
 */
public class LimitHeap<T> {
	private List<T> tree;
	private final boolean bigRoot;
	private final int limitCount;
	private Comparator<T> comparator;

	public LimitHeap(int limitCount, Comparator<T> comparator) {
		this(true, limitCount, comparator);
	}

	public LimitHeap(boolean bigRoot, int limitCount, Comparator<T> comparator) {
		this.bigRoot = bigRoot;
		this.limitCount = limitCount;
		this.comparator = comparator;
		tree = new ArrayList<T>(limitCount);
	}
	
	public void add(T node) {
		if (node == null)
			return;
		if (tree.size() >= limitCount) {
			if (comparator.compare(node, tree.get(0)) > 0)// 如果新节点比当前最大的还大就让他滚犊子,哈哈哈
				return;
			tree.set(0, node);
			int i = 1;
			while (2 * i <= limitCount) {// 如果有左子树
				T n = tree.get(i - 1);
				T l = tree.get(2 * i - 1);
				if (2 * i + 1 <= limitCount) {// 如果有右子树
					T r = tree.get(2 * i);
					if (comparator.compare(r, l) > 0 && comparator.compare(r, n) > 0) {// 如果右子树最大
						tree.set(i - 1, r);
						tree.set(2 * i, n);
						i = 2 * i + 1;
						continue;
					}
				}
				if (comparator.compare(l, n) > 0) {// 如果右子树不存在或者右子树不是最大并且左子树较大
					tree.set(i - 1, l);
					tree.set(2 * i - 1, n);
					i = 2 * i;
				} else {// 如果右子树不存在并且左子树较小
					break;
				}
			}
		} else {
			tree.add(tree.size(), node);
			int i = tree.size();
			while (i >= 1) {
				T n = tree.get(i - 1);
				if ((i / 2) >= 1 && comparator.compare(tree.get((i / 2) - 1), n) < 0) {// 存在父亲节点并且比自己小
					tree.set(i - 1, tree.get((i / 2) - 1));
					tree.set((i / 2) - 1, n);
					i = i / 2;
				} else {
					break;
				}
			}
		}
	}

	public List<T> getList() {
		return tree;
	}

	public T getRoot() {
		return tree.get(0);
	}

	public boolean isBigRoot() {
		return bigRoot;
	}

	public int size() {
		return tree.size();
	}

	public int limit() {
		return limitCount;
	}

	public interface Comparator<T> {
		public int compare(T n1, T n2);
	}

//	public static void main(String[] args) {
//		int[] num = { 5, 8, 3, 2, 0, 9, 1, 7, 4, 6 };
//		LimitHeap<Integer> heap = new LimitHeap<>(4, new Comparator<Integer>() {
//			@Override
//			public int compare(Integer n1, Integer n2) {
//				return n1 - n2;
//			}
//		});
//		for (int n : num) {
//			heap.add(n);
//			System.out.println(heap.getList() + " " + n);
//		}
//	}

	public static class Node<K, V> {
		private V value;
		private K key;

		public Node() {
		}

		public Node(K k, V v) {
			this.key = k;
			this.value = v;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}
	}
}
