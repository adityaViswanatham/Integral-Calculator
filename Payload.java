/** @author: Aditya Viswanatham
*/

package Paradox;

//Payload Class.
public class Payload {
	
	//Class Attributes.
	public int coeff;
	public int expo;
	
	//Class Constructor.
	public Payload() {
		coeff = 0;
		expo = 0;
	}
	//Overloaded constructor.
	public Payload(int coeff, int expo) {
		this.coeff = coeff;
		this.expo = expo;
	}
	
	//Setter
	public void setcoeff(int coeff) {
		this.coeff = coeff;
	}
	
	//Getter
	public int getcoeff() {
		return coeff;
	}
	
	//Setter
	public void setexpo(int expo) {
		this.expo = expo;
	}
	
	//Getter
	public int getexpo() {
		return expo;
	}
	
	//toString that returns a string to be written into a file.
	public String toString() {
		if(expo == -1) {
			if(coeff!=0) {
				if(coeff < 0)
					return ((coeff) + "lnx");
				else
					return ((coeff) + "lnx");
			}
			else
				return "0";
		}
		else if(expo == 0) {
			if(coeff!=0) {
				if(coeff < 0)
					return "-(" + Integer.toString(coeff * -1) + ")" + "x^" + (expo +1);
				else
					return "(" + Integer.toString(coeff * 1) + ")" + "x^" + (expo +1);
			}
			else
				return "0";
		}
		else if(expo < 0) {
			if(expo == -2 && !(coeff < 0)) {
				if(coeff!=0) {
					if(coeff < 0)
						return "(" + Integer.toString(coeff * -1) + ")" + "x^" + (expo +1);
					
					else
						return "-(" + Integer.toString(coeff) + ")" + "x^" + (expo +1);
				}
				else
					return "0";
			}
			else {	
				if(coeff!=0) {
					if(coeff < 0)
						return "(" + Integer.toString(coeff * -1) + "/" + Integer.toString((expo + 1) * -1) + ")" + "x^" + (expo +1);
					else
						return "-(" + Integer.toString(coeff) + "/" + Integer.toString((expo + 1) * -1) + ")" + "x^" + (expo +1);
				}
				else
					return "0";
			}
		}
		else {
			if(coeff!=0) {
				if(coeff < 0)
					return "-(" + Integer.toString(coeff * -1) + "/" + Integer.toString(expo + 1) + ")"+ "x^" + (expo + 1);
				else
					return "(" + Integer.toString(coeff * 1) + "/" + Integer.toString(expo + 1) + ")"+ "x^" + (expo + 1);
			}
			else
				return "0";
		}
	}
	
	//Evaluate function that calculates the definite integral.
	public double evaluate(int bound) {
		if(expo == -1)
			return (coeff) * Math.log(bound);
		else
			return ((double)(coeff)/(expo + 1)) * Math.pow(bound, (expo+1));
	}
}
