
public class Catalan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=0;i<10;i++)
			System.out.println(c(i));

	}
	
	public static long c (int n) {
//		if (n==0) {
//			return 1L;
//		}
//		else {
//			return ((4*(n-1))+2)*c(n-1)/(n-1+2);
//		}
		
		long ans= (n==0)? 1L : ((4*(n-1))+2)*c(n-1)/(n-1+2);
		
		return ans;
	}

}
