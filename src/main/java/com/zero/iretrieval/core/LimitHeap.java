package com.zero.iretrieval.core;

import java.util.ArrayList;
import java.util.List;

/**
 * ���޴�/С���ѣ����������ʱ���趨�������ƣ�һֱ���Ԫ�أ����������Ԫ�ؼ�����ǰn��/С��Ԫ�أ�����û�����򣬺����һ������ķ���
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
			if (comparator.compare(node, tree.get(0)) > 0)// ����½ڵ�ȵ�ǰ���Ļ��������������,������
				return;
			tree.set(0, node);
			int i = 1;
			while (2 * i <= limitCount) {// �����������
				T n = tree.get(i - 1);
				T l = tree.get(2 * i - 1);
				if (2 * i + 1 <= limitCount) {// �����������
					T r = tree.get(2 * i);
					if (comparator.compare(r, l) > 0 && comparator.compare(r, n) > 0) {// ������������
						tree.set(i - 1, r);
						tree.set(2 * i, n);
						i = 2 * i + 1;
						continue;
					}
				}
				if (comparator.compare(l, n) > 0) {// ��������������ڻ�������������������������ϴ�
					tree.set(i - 1, l);
					tree.set(2 * i - 1, n);
					i = 2 * i;
				} else {// ��������������ڲ�����������С
					break;
				}
			}
		} else {
			tree.add(tree.size(), node);
			int i = tree.size();
			while (i >= 1) {
				T n = tree.get(i - 1);
				if ((i / 2) >= 1 && comparator.compare(tree.get((i / 2) - 1), n) < 0) {// ���ڸ��׽ڵ㲢�ұ��Լ�С
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
