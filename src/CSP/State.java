
package CSP;
import java.util.*;
import Futo.*;


abstract public class State{

 
	//Modified
 public State(){
	 
 }	

 //modify
 //following methods were abstract
 
 abstract public boolean isConsistent();
	 
	 

abstract  public boolean isGoal();
  

 abstract public boolean isFailure();

//returned Variable perviously//changed to FutoshikiVariable  
abstract public FutoshikiVariable selectVariable();
  

abstract public LinkedList tryValues(FutoshikiVariable var); 

  

//abstract public State makeAssignment(Assignment a);
 
  
abstract public State makeInitialAssignments(State st);

{
	//tryValues()
//return new State();	
}


 
 
}
