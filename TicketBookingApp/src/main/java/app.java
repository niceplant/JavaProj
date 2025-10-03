import java.util.*;
public class app
{
	public static void main(String args[])
	{
		System.out.print("enter a number");
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		if(n%2==0) {
			System.out.println("the entered number is even");
		}
	}
}