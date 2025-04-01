
public class Ex08Array {

	public static void main(String[] args) {
		int[] starcount = {3,4,4,2,1};
		
		for(int i=0;i<starcount.length;i++) {
			System.out.print(starcount[i]+" : ");
			for(int j=0;j<starcount[i];j++) 
				System.out.print("*");
			System.out.println();
		}
	}
}
