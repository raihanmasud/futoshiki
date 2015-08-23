package Futo;

import CSP.*;
import java.io.*;
import java.util.*;


 public class FutoshikiVariable extends Variable{
	
    //private int range;
	//private int row;
	//private int column;
	//private boolean [] possible;
	//private int value;

	public int range;
	public int row;
	public int column;
	public boolean [] possible;
	public int value;
	
	public FutoshikiVariable clone()
	{
		
	FutoshikiVariable fv =	new FutoshikiVariable(possible.length-1,row,column);
	
	fv.possible = possible;
	fv.value = value;

	return fv;
	
	}
	
	public void assignValue(int v) 
	   {
		value = v;                    // bind variable to v
		for(int i=1; i<range+1; i++)   // make all values other than v impossible
	       {
			if (i != v) 
			{
				possible[i] = false;
			}
		   }
       }
	
	public int getValue() {
		return value;
		}  // if returns 0, then not bound
	
	
	
	
	public FutoshikiVariable(int dim, int r, int c)
	{
	 range = dim;
	 row = r;
	 column = c;
	 possible = new boolean[dim+1];
	 for(int i=1; i<range+1; i++)   // make all values 1 .. dim possible
	  {
	   possible[i] = true;
	  }
	   value = 0;
	 }
	  
	
  
}
  
  
  
	  
