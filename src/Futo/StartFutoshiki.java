package Futo;
import CSP.*;


public class StartFutoshiki {

	public StartFutoshiki(){}
	
	public static void main(String args[]){
		
	FutoshikiState fs = new FutoshikiState();
	fs.readState("./Futoshiki9by9.in");
	//fs.readState("./Futoshiki105.in");
	//fs.readState("./Futoshiki106.in");
	//fs.readState("./Futoshiki107.in");
	
	
	State goalState = BacktrackSearch.search(fs);
	((FutoshikiState)goalState).printState();
	
	
	System.out.println("Number of States Generated : "+BacktrackSearch.getNumberGenerated());
	
	System.out.println("Number of States Considered: "+BacktrackSearch.getNumberConsidered());
	
	
	System.out.println("Number of Choice Made      : "+FutoshikiState.numberChoiceMade);
		
		
	}
	
}
