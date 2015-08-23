package Futo;

import CSP.*;

import java.io.*;
import java.util.*;

/*
 * This Class is being modified mostly.
 * All the inherited abstract methods of the State class is being defined/implemented here.
 * Assignments is done with tryValues method. 
 * InitialAssignment or makeAssignment is not used for assignment.
 */


public class FutoshikiState extends State{
	
	
static protected int theDimension = 0;

static protected int numberChoiceMade =0;
 
protected FutoshikiVariable[][] theState; 

static protected LinkedList <FutoshikiLessThan> theConstraints = new LinkedList <FutoshikiLessThan>();

static protected LinkedList <FutoshikiAssignment> theAssignments = new LinkedList <FutoshikiAssignment>();

					
		// abstract methods.. defined in refinements of the state
	
public boolean isConsistent(){
	
	boolean repetation = false;
	int current_i = -1,current_j = -1, current_value = -1;
	
	for(int i=0;i<theDimension;i++)
		for(int j=0;j<theDimension;j++)
		{
			
			current_i = i;
			current_j = j;
			
			current_value = theState[i][j].getValue();
			
			if(current_value!=0)
			{
				//search row repetition
				for(int c = 0;c<theDimension;c++)
					if(c!=j)
						if(current_value==theState[i][c].getValue())
							return false;
			
			
				//search column repetition
				for(int r = 0;r<theDimension;r++)
					if(r!=i)
						if(current_value==theState[r][j].getValue())
							return false;
			
				
			}
			
			
			
		}
	
	
	//check less than
	
	for(FutoshikiLessThan fl : theConstraints)
	{
		if(theState[fl.x1][ fl.y1].getValue()!=0&&theState[fl.x2] [fl.y2].getValue()!=0)
		if (theState[fl.x1][ fl.y1].getValue() > theState[fl.x2] [fl.y2].getValue())
			return false;
		
		
	}
	
	
	return true;
}

public boolean isGoal(){
	
	
	boolean isGoalState = true;
	
	for(int i=0;i<theDimension;i++)
		for(int j=0;j<theDimension;j++)
		{
			if(theState[i][j].getValue()==0)
			{
			
				isGoalState = false;
				//System.out.println("Goal?"+isGoalState);
				return isGoalState;
			
			
			}
		}
	
	return true;
	
	
}

public boolean isFailure(){
	
		return false;
}

 public FutoshikiVariable selectVariable(){
	 
	 FutoshikiVariable fv = new FutoshikiVariable(1,2,3);
	 
	 int current_i=-1, current_j=-1;
	 
	 for(int i=0;i<theDimension;i++)
			for(int j=0;j<theDimension;j++)
				if(theState[i][j].getValue()==0)
					{
					current_i=i;current_j=j;
					return theState[i][j];
					}
	 
	 return theState[current_i][current_j];
	 
 }

 //Propagation of row and column repetition constraints checking
 public boolean isPossible(int val, FutoshikiVariable var)
 {
	 
	 
	 
		int  current_value = -1;
		int current_row =-1, current_col =-1;
		
		current_row = var.row;
		current_col = var.column;
		
		//check column
		for(int i=0;i<theDimension;i++)
		{
			
			current_value = this.theState[i][current_col].getValue();
			
			if(current_value!=0)
			{
				if(val==current_value)
					return false;
			}
		}
		
		
		//check row
		for(int j=0;j<theDimension;j++)
		{
			
			current_value = this.theState[current_row][j].getValue();
			
			if(current_value!=0)
			{
				if(val==current_value)
					return false;
			}
		}
		
		//Propagation of less than constraints 		
		for(FutoshikiLessThan fl : theConstraints)
		{
		
			if(fl.x1==current_row && fl.y1==current_col)
				if(this.theState[fl.x2] [fl.y2].getValue()!=0)
					if (val > this.theState[fl.x2] [fl.y2].getValue())
						return false;
					
			
			if(fl.x2==current_row && fl.y2==current_col)
				if(this.theState[fl.x1][ fl.y1].getValue()!=0)
					if (this.theState[fl.x1][ fl.y1].getValue() > val)
						return false;
			
			
		}
		
		
		 
	 return true;
 }
 
 public LinkedList tryValues(FutoshikiVariable var)
 
 {
	 /************************************************/
	 //This method is assigning values to the variables
	 //Also generating states
	 //Generated States are added into a linkedList
	 //Finally created linked list with states is returned to the backtrack search method
	 /*************************************************/
	 
	 //copying the current theState (FutoshikiVariable grid) to childState	  
	 FutoshikiVariable[][] childState = theState;  
	
	 
	 LinkedList  cList = new LinkedList() ;
	
	 int choice_count =0;
	 
	 
	
	 for(int i=1;i<theDimension+1;i++){
		 
		//checking row or column repetition and less than constraints and eliminating states
		 if(isPossible(i,var))
		 {
			 ++choice_count;
			 
			 childState[var.row][var.column].assignValue(i);
	 
			 FutoshikiState cfs = new FutoshikiState();
	
			 cfs.theState = new FutoshikiVariable[theDimension][theDimension];
	 
			 for(int j=0;j<theDimension;j++)
				 for(int k=0;k<theDimension;k++)
					 cfs.theState[j][k] = childState[j][k].clone();
	 
	 
			 cList.add(cfs);
	 
			 numberChoiceMade++;
			 
			 
		 }
	 }
	 
	//checking whether number is picked up based on row column repetition constraints
	 //if only one number was possible for a variable then that number is picked up based
	 //on row column repetition constraints
	 
	 if(choice_count==1)
		 numberChoiceMade--;
	 
	 return cList;
	 
 }
  
// abstract public State makeAssignment(Assignment a);
 
 
 
  
 public State makeInitialAssignments(State st)  // assign theAssignments, returning the resultant State
 {
	 int i=0,j=0;
	 boolean valueAssigned = false;
	 for(i=0;i<theDimension;i++)
	 {
		 for(j=0;j<theDimension;j++)
		 {
	 	 
			 if(theState[i][j].getValue()==0)
			 {
				 theState[i][j].assignValue(1);
				 valueAssigned = true;
				 break;
			 }
			 
	 
	 
		 }
	 if(valueAssigned)
		 break;
	 }
	 return st;
 } 
  ////
  

 
 ////////
 
public void printState()
 {
	for (int i = 0; i<theDimension; i++)
    {
     System.out.println();
     System.out.print("| ");
     for (int j=0; j<theDimension; j++)
	    {
    	 int val = theState[i][j].getValue();           ////
	     if (val == 0) {System.out.print("  | ");}      // unbound then empty space
	     else {System.out.print(val + " | ");}
		}
    }
   System.out.println();
 }
 
 	// a problem/state is represented in a file
	// first line is an integer: the dimension
	// next lines are triples:   x y  value
	// next lines are 4-tuples: x1 y1 x2 y2 representing (x1,y1) < (x2, y2)
	
	
public void readState(String filename)
 {
	try
   {
	FileInputStream fis = new FileInputStream(filename);
    BufferedReader bR = new BufferedReader(new InputStreamReader(fis));        	  
    String line = bR.readLine();   // use String.split(" ");
    String [] tokens = line.split(" ");
    theDimension = Integer.parseInt(tokens[0]);
	theState = new FutoshikiVariable[theDimension][theDimension]; 	
	
	for(int i= 0; i<theDimension; i++)
		for(int j = 0; j<theDimension; j++)
			{
			theState[i][j] = new FutoshikiVariable(theDimension, i, j);
			
			}
	
	line = bR.readLine();
	while (line != null)
	  {
	   tokens = line.split(" ");
       if (tokens.length == 3)
	    {
    	 int x = Integer.parseInt(tokens[0]);
         int y = Integer.parseInt(tokens[1]);
		 int v = Integer.parseInt(tokens[2]);   //// 
		 theAssignments.add(new FutoshikiAssignment(x, y, v));
		 }
	   else
        {
		 int x1 = Integer.parseInt(tokens[0]);
         int y1 = Integer.parseInt(tokens[1]);
		 int x2 = Integer.parseInt(tokens[2]);   //// 
		 int y2 = Integer.parseInt(tokens[3]);
		 theConstraints.add(new FutoshikiLessThan(x1, y1, x2, y2));
		 }
        line = bR.readLine();
      }
	
	for(FutoshikiAssignment fa : theAssignments)
	{
		
		theState[fa.x][fa.y].assignValue(fa.value) ; 
	}
	
    }
  catch(Exception e) {System.out.println(e.toString());}
}
 
 
 
  
   
 	  
  
  
}
  
  
  
	  
