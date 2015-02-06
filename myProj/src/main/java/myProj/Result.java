package myProj;

import java.util.ArrayList;

public class Result{
	private long inversions;
	private ArrayList<Integer> list;
	
	public long getInversions() {
		return inversions;
	}
	public void setInversions(long inversions) {
		this.inversions = inversions;
	}
	public ArrayList<Integer> getList() {
		return list;
	}
	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}
	
	public Result(long inversions, ArrayList<Integer> list) {
		this.inversions = inversions;
		this.list = list;
	}
}
