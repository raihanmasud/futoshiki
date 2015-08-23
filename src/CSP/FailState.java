
package CSP;
import java.util.*;
import Futo.*;


public class FailState extends State{

	//TO DO - implement the abstract class State.makeAssignment
	
	public FailState(){}
	
//abstract public State makeAssignment(Assignment a);

public  State makeInitialAssignments(State st)

{
return st;
}

	
public boolean isFailure() 
	{
	 return true;
	}	
	
public boolean isGoal() 
	{
	 return false;
	}
	
public boolean isConsistent() 
	{
	 return false;
	}
	      
//returned Variable perviously//changed to FutoshikiVariable  
public FutoshikiVariable selectVariable()
        {
	 return new FutoshikiVariable(5,0,0);
	}
      
public LinkedList tryValues(FutoshikiVariable var)
        {
	 return new LinkedList();
        }

}


