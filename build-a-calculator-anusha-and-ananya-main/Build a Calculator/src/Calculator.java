/**
 * 
 * Use this class to handle the main features of your application.
 * You should add additional classes as appropriate to support good modularity and reduce redundancy.
 *
 */
//KANKULATOR
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
	public static void main(String[] args) {
		
		//Variables to be used later
		String str = "";
		double buttonL = 50;
		double buttonW = 75;
		double gap = 24;
		Font writing = new Font("Arial", Font.BOLD, 30);
		
		//2D array of all valid keys
		String[][] nums = {{"(", ")", "*","CLEAR"},
			  {"7", "8", "9", "/"},
			  {"4", "5", "6", "*"},
			  {"3", "2", "1", "-"},
			  {"0", ".", "=", "+"}};
		
		//Array of button keys
		double[][] buttons = new double[20][2];
	
		// Base canvas settings
		StdDraw.setCanvasSize(700,450);
		StdDraw.setXscale(-10 ,1300);
		StdDraw.setYscale(-10 ,1260);
		
		//Assign button locations
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 4; c++) {
				
				buttons[r*4+c][0] = (gap+buttonW*2)*c + gap + buttonW;
				buttons[r*4+c][1] = 706 - buttonL -(gap+buttonL*2)*r - buttonW+50;
			}
		}
		
		double [] lEarX = {0,39,111,150};
		double [] lEarY = {1040,1210,1210,1040};
		
		double [] rEarX = {720,720-39,720-111,720-150};
		double [] rEarY = {1040,1210,1210,1040};
		
		//Decorative picture
		StdDraw.picture(1000, 360,
		"Build a Calculator/src/kankulator.png", 650,850);
		
		//Main body of calculator
		StdDraw.setPenColor(255,211,124);
		StdDraw.filledCircle(50,1030,50);
		StdDraw.filledCircle(50,50,50);
		StdDraw.filledRectangle(360,540,310,540);
		StdDraw.filledCircle(670,1030,50);
		StdDraw.filledCircle(670,50,50);
		StdDraw.filledRectangle(360,540,360,490);
		
		//Designs on calculator
		StdDraw.setPenColor(255,120,85);
		StdDraw.filledEllipse(360,1050,60,70);
		StdDraw.filledEllipse(220,1050,60,70);
		StdDraw.filledEllipse(500,1050,60,70);
		StdDraw.setPenColor(255,255,255);
		StdDraw.filledRectangle(360, 1100, 320, 55);
		
		//Cat ear design
		StdDraw.setPenColor(255,211,124);
		StdDraw.filledEllipse(75,1205,35,45);
		StdDraw.filledEllipse(645,1205,35,45);
		StdDraw.filledPolygon(lEarX,lEarY);
		StdDraw.filledPolygon(rEarX,rEarY);
		
		// Window details
		StdDraw.setPenColor(255,245,235);
		StdDraw.filledRectangle(360, 840, 320, 120);
		
		// Kankulator signage!
				Font kankulator = new Font("Arial", Font.BOLD, 25);
				StdDraw.setFont(kankulator);
				StdDraw.setPenColor(255,120,85);
				StdDraw.text(980, 760, "THE KANKULATOR");
		
		// Loop making the buttons
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 4; c++) {
				
				if (c == 3) {
					StdDraw.setPenColor(255,120,85);
					// A special case for clear so that it fits within box
					if(nums[r][c] == "CLEAR") {
						Font clear = new Font("Arial", Font.BOLD, 20);
						StdDraw.setFont(clear);
					}
				}else {
					StdDraw.setPenColor(255,150,85);
					Font font = new Font("Arial", Font.BOLD, 30);
					StdDraw.setFont(font);
				}
				
				StdDraw.filledRectangle(buttons[r*4+c][0],buttons[r*4+c][1],
								buttonW, buttonL);
				StdDraw.setPenColor(255,255,255);
				StdDraw.text(buttons[r*4+c][0], buttons[r*4+c][1],nums[r][c]);
			}
		}
		

		// Mouse pressed + actions
		while (true) {
			
			if (StdDraw.isMousePressed()) {
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 4; c++) {
						
						if ((StdDraw.mouseX() >= buttons[r*4+c][0]-buttonW)&&
					   (StdDraw.mouseX() <= buttons[r*4+c][0]+buttonW)&&
					   (StdDraw.mouseY() >= buttons[r*4+c][1]-buttonL)&&
					   (StdDraw.mouseY() <= buttons[r*4+c][1]+buttonL)){
							
							if (nums[r][c].equals("CLEAR")) {
								str = "";
							}
							else if (nums[r][c].equals("=")) {

								// Preprocess answer, calculate, update string
								String spaces = addSpaces(str);
								Double answer = eval(spaces);
								str = "" + answer;
								
							}
							else {
								str += nums[r][c];
							}
							// Redraw rectangle over updated text
							StdDraw.setPenColor(255,245,235);
							StdDraw.filledRectangle(360, 840, 320, 120);

							StdDraw.setPenColor(255,255,255);
							StdDraw.filledRectangle(buttons[r*4+c][0],buttons[r*4+c][1],
													buttonW-1, buttonL-1);
							StdDraw.setPenColor(255,120,85);
							StdDraw.text(buttons[r*4+c][0], buttons[r*4+c][1],nums[r][c]);
							
							if (c == 3) {
								StdDraw.setPenColor(255,120,85);
							}else {
								StdDraw.setPenColor(255,150,85);
							}
						
							StdDraw.pause(100);
							StdDraw.filledRectangle(buttons[r*4+c][0],buttons[r*4+c][1],
									buttonW, buttonL);
							StdDraw.setPenColor(255,255,255);
							StdDraw.text(buttons[r*4+c][0], buttons[r*4+c][1],nums[r][c]);
						
							StdDraw.pause(500);
							
							//String input
							StdDraw.setPenColor(255,120,85);
							StdDraw.setFont(writing);
							StdDraw.textLeft(50,775, str);

							}
					}
					
				}
					
			}
			
			//If a key is pressed...
					if (StdDraw.hasNextKeyTyped()) {
						
						//Checks if the key is valid
						String [] validKeys = {"0", "1", "2","3", "4", "5",
								"6", "7", "8","9",
								"-", "+","*", "/", "^","=", "(", ")", "ENTER"};
						char key = StdDraw.nextKeyTyped();
						
						for (int i = 0; i < validKeys.length; i++) {
							if ((key+"").equals(validKeys[i])) {
								//Add to stored String
								str += key;
							}
							
							else if ((key+"").equals("=")) {
								// Preprocess answer, calculate, update string
								String spaces = addSpaces(str);
								Double answer = eval(spaces);
								str = "" + answer;
								break;
							}
						}
						// Redraw rectangle over updated text
						StdDraw.setPenColor(255,245,235);
						StdDraw.filledRectangle(360, 840, 320, 120);
						// Text on top of rectangle
						StdDraw.setPenColor(255,120,85);
						StdDraw.setFont(writing);
						StdDraw.textLeft(50,775, str);
					}
				}
		}

	
	// Function doing the complex math + pemdas
	public static double eval(String expression) {
        //Stacks to keep track of unfinished/finished numbers
    	Stack<Double> numStack = new Stack<Double>();
        Stack<String> opStack = new Stack<String>();
        
        // Split array of elements
        String[] expArray = expression.split(" ");

        // Loop through all array elements
        for (int i = 0; i < expArray.length; i++) {
            try 
            {
                Double characterToAdd = Double.parseDouble(expArray[i]);
                numStack.push(characterToAdd);
            }
            catch (Exception e)
            {
            	// If the element is a (, start pushing in calculations
            	if (expArray[i].equals("(")) opStack.push(expArray[i]);
                else if (expArray[i].equals(")")) 
                {
                    while (!opStack.peek().equals("("))
                    {
                        String oper = opStack.pop();
                        Double num2 = numStack.pop();
                        Double num1 = numStack.pop();
                        calculate(num1, num2, numStack, oper);
                    }
                    opStack.pop();
                } 
                else 
                {
                	if (expArray[i].equals("^")) 
                    {
                        while (!opStack.empty() && !opStack.peek().equals("(")) 
                        {
                            String oper = opStack.pop();
                            Double num2 = numStack.pop();
                            Double num1 = numStack.pop();
                            calculate(num1, num2, numStack, oper);
                        }
                        opStack.push(expArray[i]);
                    }
                	
                	else if (expArray[i].equals("+") || expArray[i].equals("-")) 
                    {
                        while (!opStack.empty() && !opStack.peek().equals("(")) 
                        {
                            String oper = opStack.pop();
                            Double num2 = numStack.pop();
                            Double num1 = numStack.pop();
                            calculate(num1, num2, numStack, oper);
                        }
                        opStack.push(expArray[i]);
                    } 
                    else if (expArray[i].equals("*") || expArray[i].equals("/"))
                    {
                        //
                        while (!opStack.empty() && !opStack.peek().equals("(") && !opStack.peek().equals("+") && !opStack.peek().equals("-")) 
                        {
                            String oper = opStack.pop();
                            Double num2 = numStack.pop();
                            Double num1 = numStack.pop();
                            calculate(num1, num2, numStack, oper);
                        }
                        opStack.push(expArray[i]);
                    }
                }
            }
        }

        while (!opStack.empty()) 
        {
            String oper = opStack.pop();
            Double num2 = numStack.pop();
            Double num1 = numStack.pop();
            calculate(num1, num2, numStack, oper);
        }
        if(!numStack.isEmpty()) {
        	return numStack.pop();
        }
        return 0;
    }
    
    public static void calculate(double num1, double num2, Stack<Double> s, String oper) {
    	if(oper.equals("+")) {
        	s.push(num1 + num2);
        }
        else if(oper.equals("-")) {
        	s.push(num1 - num2);
        }
        else if(oper.equals("*")) {
        	s.push(num1 * num2);
        }
        else if(oper.equals("/")) {
        	s.push(num1 / num2);
        }
        else if(oper.equals("^")) {
        	s.push(Math.pow(num1, num2));
        }
        else {
        	throw new UnsupportedOperationException("Unsupported operator :(");
        }
    }
    
    // String input needs to be pre-processed before calc method.
    // This method adds a space between any relevant character
    public static String addSpaces(String s) {
    	Queue<String> q = new LinkedList<String>();
    	String finString = "";
    	
    	// Insert string letters individually into queue
    	for(int i = 0; i < s.length(); i++) {
    		q.add(s.substring(i,i+1));
    	}
    	// Dequeue each element into finString
    	while(!q.isEmpty()) {
    		String next = q.peek();
    		
    		// If the character is a parenthesis, add space before/after
    		if(next.equals("(")) {
    			finString += next + " ";
    		}
    		else if(next.equals(")")) {
    			finString += " " + next;
    		}
    		// If the character is an operator, add spaces around 
    		else if(next.equals("+") || next.equals("-") || next.equals("*") || 
    				next.equals("/") || next.equals("^")) {
    			finString += " " + next + " ";
    		}
    		// If the character is a number, add to finString
    		else {
	    		try {
	    			Double.parseDouble(next);
	    			finString += next;
	    		}
	    		catch(Exception e) {
	    			
	    		}
    		}
    		q.remove(next);
    	}
    	
    	return finString;
    }
}
