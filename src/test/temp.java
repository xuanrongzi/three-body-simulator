package test;

import java.awt.FlowLayout;
import java.util.Dictionary;

public class temp {
	static int[] a=new int[4];
	@SuppressWarnings("null")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary<String, Integer> flowchart = new Dictionary<String, Integer>() {
		};
		flowchart.put("dog", 0);
		flowchart.put("cat", 0);
		System.out.println(flowchart.get("cat"));
	}
}
