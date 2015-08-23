package CSP;
import java.util.*;
import Futo.*;


public class BacktrackSearch
{  static private int numberConsidered;
   static private int numberGenerated;
   
   
   static public int getNumberGenerated()
    {
	   return numberGenerated;
	}
 
   static public int getNumberConsidered()
    {
	   return numberConsidered;
	}
    
    
                                                           //was Assignments
   static public State search(State theState /*, LinkedList <Assignment> aLst */)  // search for a solution, given a starting set of assignments
      {
	   numberConsidered = 0;
       numberGenerated = 0;
	   //theState = theState.makeInitialAssignments(theState);
       return considerState(theState);
      }
      
 
   static private State considerState(State theState)
      {
	   numberConsidered++;
	   boolean b = true;
			// if theState violates a constraint of the problem the fail
       if (!(b = theState.isConsistent())) {
    	
    	   
    	   return new FailState();
    	   
       }
		
       //System.out.println(b);
       
       
       // if theState is a complete state that meets all constraints of the goal, then succeed
       if (theState.isGoal()) {
    	
    	   
    	   return theState;
    	   
       }
       
       
			// otherwise create new states by considering all possible values for a selected variable
       else {
    	     FutoshikiVariable var = theState.selectVariable();
    	     //var.assignValue(2);
    	     //System.out.println(var.getValue());
             LinkedList children = theState.tryValues(var);
             numberGenerated += children.size();
	     return considerChildren(children);
            }
      }

   static  private State considerChildren(LinkedList theStates)
      {
	   
	   
	   
	   if (theStates.isEmpty()) 
	   {
		   return (new FailState());
	   }
       else 
       {
    	   FutoshikiState childState =  (FutoshikiState)theStates.removeFirst();
    	   
    	   //childState.printState();
    	   
    	   State result = considerState(childState);
	     
    	   //boolean iF = result.isFailure();
    	   
    	   //System.out.println(result+" "+iF);
    	   
    	   if (result.isFailure()) 
	     {
	    	 return considerChildren(theStates);
	     }
             else {return result;}
            }
      }
      
}
