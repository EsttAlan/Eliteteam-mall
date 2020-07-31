package com.simple.util.math.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: simple_tools
 * @description: 阶乘函数
 * @author: ChenWenLong
 * @create: 2019-11-15 11:47
 **/
public class FactorialFunction {

	/**
	 * 功能描述:
	 * 〈递归方式实现阶乘函数〉
	 *
	 * @params : [num]
	 * @return : int
	 * @author : cwl
	 * @date : 2019/11/15 11:47
	 */
	public static int recursion(int num){
		if(num == 1){
			return 1;
		}else if(num == 2){
			return 2;
		}else{
			return num*recursion(num-1);
		}
	}

	/**
	 * 功能描述:
	 * 〈非递归方法实现阶乘函数〉
	 *
	 * @params : [num]
	 * @return : int
	 * @author : cwl
	 * @date : 2019/11/15 11:49
	 */
	public static int unRecursion(int num){
		int jiecheng = 1;
		if(num == 0){
			return 0;
		}
		for (int i = 1; i <= num; i++) {
			jiecheng*=i;
		}
		return jiecheng;
	}



	/**
	 * 把一个数组里的数的组合全部列出(没有重复元素)
	 * @param candidate
	 * @param prefix
	 */
	public static List<Integer[]> permutations(List<Integer> sourceList) {
//		Integer a[] = {1,2,3,4};
		Integer[] a = new Integer[sourceList.size()];
		for(int i=0;i<sourceList.size();i++) {
			a[i]=sourceList.get(i);
		}
		int left = 0;
		int right = a.length - 1;
		List<Integer[]> list=new ArrayList<>(); 
		solve(a,left,right,list);
		
		return list;
		
//		for (Integer[] arr : list) {
//			for (Integer e : arr) {
//				System.out.print(e);
//			}
//			System.out.println();
//		}
		
	}
 
	private static void solve(Integer[] a, int left, int right,List<Integer[]> list) {
	
		if(left==right){
			Integer[] b= {};
			b=a.clone();
			list.add(b);
		}else{
			for(int i = left;i<=right;i++){
				swap(a,left,i);
				solve(a,left+1,right,list);
				swap(a,left,i);
			}
		}
	
	}
	
	private static void swap(Integer[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
	
		List<Integer[]> list = permutations(a);
		
		for (Integer[] arr : list) {
			for (Integer e : arr) {
				System.out.print(e);
			}
			System.out.println();
		}
	}

}


