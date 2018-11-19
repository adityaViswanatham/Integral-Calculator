/** @author: Aditya Viswanatham
*/

package Paradox;
import java.util.*;
import java.io.*;

//Main Class.
public class Main {

	//Class Variables.
	public static Scanner obj;
	static ArrayList <String> eq_list = new ArrayList<>();

	//openFile to open the file.
	public static void openFile() {
		try {
			obj = new Scanner(new File("filename.txt"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//readFile to read contents of the file.
	public static void readFile() {
		//Exception Handling for writing into the file.
		try {
			File file = new File("answers.txt");
			PrintWriter pw = new PrintWriter(file);
			while(obj.hasNext()) {
				String line = obj.nextLine();
				String equation = "";
				String x_bound = null;
				int upper =0;
				int lower =0;
				String y_bound = null;
				String sp[] = line.split(" ");
				for(int i=0; i < sp.length; i++) {
					if(sp[i].equals("dx"))
						continue;
					else if(sp[i].contains("|")) {
						 x_bound = (sp[i].substring(0, sp[i].indexOf("|")));
						 try {
							 if(x_bound != null)
								 upper = Integer.parseInt(x_bound);
						 }
						 catch(Exception e) {
							 upper = 0;
						 }
						 y_bound = (sp[i].substring(sp[i].indexOf("|") + 1, sp[i].length()));
						 try {
							 if(y_bound != null)
							 	 lower = Integer.parseInt(y_bound);
						 }
						 catch(Exception e) {
							 lower = 0;
						 }
					}
					else if(sp[i].equals("|"))
						continue;
					else
						equation += sp[i];
			}
				//Catching string returned by the eqn_split function.
				String cap_str = eqn_split(equation, upper, lower);
				pw.println(cap_str);
			}
			pw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

//Eqn_split function that splits the equation into terms.
	public static String eqn_split(String equation, int x, int y) {
		//Splitting based on "+" and "-".
		String split_eqn[] = equation.split("(?=[\\+-])");
		ArrayList <String> eqn_list = new ArrayList<String>();
		ArrayList <String> the_list = new ArrayList<String>();
		int expo;
		int coeff;
		String ans;
		for(int i=0; i < split_eqn.length; i++)
			eqn_list.add(split_eqn[i]);
		int i=0;

		//Loop to check for a case where the exponent is split from the term.
		while(i < eqn_list.size()) {
			if(eqn_list.size() > 2 && (i < eqn_list.size() -1)) {
				if((eqn_list.get(i).contains("^")&& eqn_list.get(i).indexOf("^") == eqn_list.get(i).length()-1) && (!eqn_list.get(i+1).contains("x") && eqn_list.get(i+1).contains("-"))) {
					String temp_str = eqn_list.get(i) + eqn_list.get(i+1);
					the_list.add(temp_str);
					i = i+2;
				}
				else {
					the_list.add(eqn_list.get(i));
					i++;
				}
			}
			else if(eqn_list.size() == 2) {
				if(eqn_list.get(i).contains("^") && (!eqn_list.get(i+1).contains("x") && eqn_list.get(i+1).contains("-"))) {
					String temp_str = eqn_list.get(i) + eqn_list.get(i+1);
					the_list.add(temp_str);
					i= i+2;
				}
				else {
					the_list.add(eqn_list.get(i));
					the_list.add(eqn_list.get(i+1));
					break;
				}
			}
			else {
				the_list.add(eqn_list.get(i));
				i++;
			}
		}

		//Loops for inserting items into tree by separating coefficients from exponents.
		BinarySearchTree bst = new BinarySearchTree();
		for(int k=0; k < the_list.size(); k++)	{
			try {
				int number = Integer.parseInt(the_list.get(k));
				coeff = number;
				expo = 0;
				bst.insert(expo, coeff);
			}
			catch (Exception e) {
				if(the_list.get(k).equals("-x")) {
					coeff = -1;
					expo = 1;
					bst.insert(expo, coeff);
				}
				else if(the_list.get(k).equals("+x") || the_list.get(k).equals("x")) {
					coeff = 1;
					expo = 1;
				}
				else if(the_list.get(k).equals("^(\\+?x)")) {
					coeff = 1;
					expo = Integer.parseInt(the_list.get(k).substring(the_list.get(k).indexOf("^") + 1, (the_list.get(k).length())));
					bst.insert(expo, coeff);
				}
				else if(the_list.get(k).matches("^(\\-?\\+?\\d+x)")) {
					coeff = Integer.parseInt(the_list.get(k).substring(0, the_list.get(k).indexOf("x")));
					expo = 1;
					bst.insert(expo, coeff);
				}
				else if(the_list.get(k).indexOf("-x") == 0 && the_list.get(k).contains("^")) {
					coeff = -1;
					expo = Integer.parseInt(the_list.get(k).substring(the_list.get(k).indexOf("^") + 1, the_list.get(k).length()));
					bst.insert(expo, coeff);
				}
				else if((the_list.get(k).indexOf("x") == 0 || the_list.get(k).indexOf("+x") == 0) && the_list.get(k).contains("^")) {
					coeff = 1;
					expo = Integer.parseInt(the_list.get(k).substring(the_list.get(k).indexOf("^") + 1, the_list.get(k).length()));
					bst.insert(expo, coeff);
				}
				else {
					coeff = Integer.parseInt(the_list.get(k).substring(0, the_list.get(k).indexOf("x")));
					expo = Integer.parseInt(the_list.get(k).substring(the_list.get(k).indexOf("^") + 1, (the_list.get(k).length())));
					bst.insert(expo, coeff);
				}
			}
		}
		// ret_val to hold variable that the eq_list function returns.
		String ret_val = "";
		if(x==0 && y==0) {
			eq_list = bst.traverse();
			for(int z=0; z < eq_list.size(); z++)	{
				if(eq_list.get(z).equals("0"))
					continue;
				else {
					if(z == 0) {
						ret_val += eq_list.get(z);
					}
					else {
						if(eq_list.get(z).charAt(0) == '-')
							ret_val+= eq_list.get(z);
						else
							ret_val += "+" + eq_list.get(z);
					}
				}
			}
			ret_val+= " + c";
			bst.delete();
			bst.destroy();
			return ret_val;
		}
		else {
			ans = bst.traverse(x, y);
			ret_val = ans;
			bst.delete();
			bst.destroy();
			return ret_val;
		}
	}
	//Main function.
	public static void main(String [] args)
	{
		//Calling the functions in the main class.
		openFile();
		readFile();
	}
}
