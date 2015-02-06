package myProj;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainArray {
	
	private static void fillIn(ArrayList<Integer> arr, String filePath){
		/*arr.add(5);
		arr.add(3);
		arr.add(2);
		arr.add(1);
		arr.add(4);*/
		
		MainArray ma = new MainArray();
		
		//String s = ma.getClass().getClassLoader().getResource("IntegerArray.txt").getFile();
		
		File file = new File(filePath);//s);
		
		try{
			Scanner scanner = new Scanner(file);
			int line = 0;
			while(scanner.hasNextLine()){
				int ss = Integer.parseInt(scanner.nextLine());
				arr.add(ss);
				line++;
				
			}
			scanner.close();
		}
		catch(IOException exc){
			
		}
	}
	
	
	private static void printArray(ArrayList<Integer> arr){
		if(arr == null || arr.size() == 0) return;
		
		for(Integer i : arr){
			System.out.print(i + " ");
		}
		
		System.out.println("");
	}
	
	private static ArrayList<Integer> getHalf(ArrayList<Integer> input, boolean left){
		if(input == null) return null;
		if(input.size() <= 1) return null;
		
		int middle = input.size() / 2;
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int startIndex = (left ? 0 : middle);
		int endIndex = (left ? middle : input.size());
	
		for(int i = startIndex; i < endIndex; i++){
			result.add(input.get(i));
		}
		
		return result;
	}
	
	
	private static Result countInversions(ArrayList<Integer> array){
		//System.out.println("Next Iteration array size: " + array.size());
		if(array.size() == 1) return new Result(0, array);
		
		ArrayList<Integer> left = getHalf(array, true);
		
		ArrayList<Integer> right = getHalf(array, false);
		
		
		Result leftResult = countInversions(left);	
		
		Result rightResult = countInversions(right);
		
		long splitInv = 0;
		int i = 0, j = 0;
		ArrayList<Integer> result = new ArrayList<Integer>(array.size());
		
		while(i < leftResult.getList().size() && j < rightResult.getList().size()){
			if(leftResult.getList().get(i) < rightResult.getList().get(j))
			{
				result.add(leftResult.getList().get(i));
				i++;
			}
			else /*if(right.get(j) < left.get(i))*/{
				result.add(rightResult.getList().get(j));
				splitInv += (leftResult.getList().size() - i);
				// print out:
//				for(int k = i; k < left.size(); k++){
//					System.out.println("(" + left.get(k) + ", " + right.get(j) + ")");
//				}
				
				j++;
			}
		}
		while(i < leftResult.getList().size()){
			result.add(leftResult.getList().get(i));
			i++;
		}
		
		while(j < rightResult.getList().size()){
			result.add(rightResult.getList().get(j));
			j++;
		}
		
		return new Result(leftResult.getInversions() + rightResult.getInversions() + splitInv, result);
	}

	public static void main(String[] args){
		
		if(args == null || args.length == 0){
			System.out.println("No input file! Terminating...");
			return;
		}
		
		ArrayList<Integer> myArr = new ArrayList<Integer>();

		fillIn(myArr, args[0]);
		
		//printArray(myArr);
		
		// test 1: left side
		/*System.out.println("Left:");
		ArrayList<Integer> left = getHalf(myArr, true);
		printArray(left);*/
		
		// test 2: right
		/*System.out.println("Right:");
		ArrayList<Integer> right = getHalf(myArr, false);
		printArray(right);*/
		
		//printArray(myArr);
		
		System.out.println("Let's calc inversions in file: " + args[0]);
		
		Result r = countInversions(myArr);
		System.out.println("result is " + r.getInversions());
	}
}
