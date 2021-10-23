/**
 * @author      Enako Matsumoto
 * @version     1.0
 * @since       2021-02-16
 */
public class ComplexNumber {

	private double a;
	private double b;

	/**
	 * Creates a new ComplexNumber with both real and imaginary components
	 * @param a the real component of the complex number
	 * @param b the imaginary component of the complex number
	 */
	public ComplexNumber(double a, double b){
		this.a = a;
		this.b = b;
	}

	/**
	 * The "copy constructor"
	 * Creates a new ComplexNumber from an existing ComplexNumber
	 * @param c a ComplexNumber
	 */
	public ComplexNumber(ComplexNumber c){
		//need a copy constructor because with object variable,
		//the MEMORY PLACE is saved, not the value. Therefore if I do c3 = c1
		//and I change c3, c1 also get affected because the memory place is the same.
		//c3 and c1 become aliases.
		//to avoid this, we need the copy constructor to save the VALUE.
		a = c.getReal();
		b = c.getImaginary();
	}

	/**
    * The default constructor for class ComplexNumber
    * Intitializes both the a and b of the new ComplexNumber to 1.0
    *
    */
  public ComplexNumber() {
    a = 0.0;
    b = 0.0;
  }

	/**
	 * An "accessor" method
	 * Returns the real component of this ComplexNumber
	 * @return a the private real component of this ComplexNumber
	 */
	public double getReal(){
		return a;
	}

	/**
	 * An "accessor" method
	 * Returns the imaginary component of this ComplexNumber
	 * @return b the private imaginary component of this ComplexNumber
	 */
	public double getImaginary(){
		return b;
	}

	/**
	 * Add two ComplexNumbers
	 * instance method: already have a defined ComplexNumber first that is being added to
	 * Returns a new ComplexNumber, the sum of two ComplexNumbers
	 * @param c the ComplexNummber to add to this ComplexNumber
	 * @return the sum of the two ComplexNumbers
	 */
	public ComplexNumber add(ComplexNumber c){
		double addR = this.a + c.getReal();
		double addI = this.b + c.getImaginary();
		return new ComplexNumber(addR,addI);
	}

	/**
	* Add two ComplexNumbers
	* static method: means it doesn't rely on a particular predefined data
	* Returns a new ComplexNumber, the sum of two ComplexNumbers
	* @param c1 the first ComplexNummber
	* @param c2 the second ComplexNumber that is added to c1
	* @return the sum of the two ComplexNumbers
	*/
	public static ComplexNumber add(ComplexNumber c1, ComplexNumber c2){
		double addR = c1.getReal() + c2.getReal();
		double addI = c1.getImaginary() + c2.getImaginary();
		return new ComplexNumber(addR,addI);
	}

	/**
	 * Subtract two ComplexNumbers
	 * instance method: already have a defined ComplexNumber first that is being subtracted
	 * Returns a new ComplexNumber, the difference of two ComplexNumbers
	 * @param c the ComplexNummber to subtract from this ComplexNumber
	 * @return difference of the two ComplexNumbers
	 */
	 public ComplexNumber subtract(ComplexNumber c){
 		double subR = this.a - c.getReal();
 		double subI = this.b - c.getImaginary();
 		return new ComplexNumber(subR,subI);
 	}

	/**
	* Subtract two ComplexNumbers
	* static method: means it doesn't rely on a particular predefined data
	* Returns a new ComplexNumber, the difference of two ComplexNumbers
	* @param c1 the first ComplexNummber
	* @param c2 the second ComplexNumber that is subtracted from c1
	* @return the difference of the two ComplexNumbers
	*/
	public static ComplexNumber subtract(ComplexNumber c1, ComplexNumber c2){
		double subR = c1.getReal() - c2.getReal();
		double subI = c1.getImaginary() - c2.getImaginary();
		return new ComplexNumber(subR,subI);
	}


	/**
	 * Multiply two ComplexNumbers
	 * instance method: already have a defined ComplexNumber first that is being multiplied
	 * Returns a new ComplexNumber, the product of two ComplexNumbers
	 * @param c the ComplexNummber to multiply to this ComplexNumber
	 * @return product of the two ComplexNumbers
	 */
	 public ComplexNumber multiply(ComplexNumber c){
		double mulR = (this.a*c.getReal())-(this.b*c.getImaginary());
		double mulI = (this.a*c.getImaginary())+(this.b*c.getReal());
		return new ComplexNumber(mulR,mulI);
	}

	/**
	* Multiply two ComplexNumbers
	* static method: means it doesn't rely on a particular predefined data
	* Returns a new ComplexNumber, the product of two ComplexNumbers
	* @param c1 the first ComplexNummber
	* @param c2 the second ComplexNumber that is multiplied to c1
	* @return the product of the two ComplexNumbers
	*/
	public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2){
		double mulR = (c1.getReal()*c2.getReal())-(c1.getImaginary()*c2.getImaginary());
		double mulI = (c1.getReal()*c2.getImaginary())+(c1.getImaginary()*c2.getReal());
		return new ComplexNumber(mulR,mulI);
	}

	/**
	 * Square of this ComplexNumber
	 * instance method: already have a defined ComplexNumber, so no parameter
	 * Returns a new ComplexNumber, the square of this ComplexNumber
	 * @return the square of this ComplexNumber
	 */
	 public ComplexNumber square(){
		return this.multiply(this);
	}

	/**
	* Square of ComplexNumber
	* static method: means it doesn't rely on a particular predefined data
	* Returns a new ComplexNumber, the square of ComplexNumber
	* @param c the ComplexNummber that's being squared
	* @return the square of the ComplexNumber c
	*/
	public static ComplexNumber square(ComplexNumber c){
		return c.multiply(c);
	}

	/**
	 * Conjugate of this ComplexNumber
	 * instance method: already have a defined ComplexNumber, so no parameter
	 * Returns a new ComplexNumber, the conjugate of this ComplexNumber
	 * @return the conjugate of this ComplexNumber
	 */
	 public ComplexNumber conjugate(){
		double conjR = this.getReal();
 		double conjI = -1*this.getImaginary();
 		return new ComplexNumber(conjR,conjI);
	}

	/**
	 * Division of two ComplexNumbers
	 * instance method: already have a defined ComplexNumber first that is being divided
	 * Returns a new ComplexNumber, the quotient of two ComplexNumbers
	 * @param c the ComplexNummber to divide this ComplexNumber
	 * @return quotient of the two ComplexNumbers
	 */
	 public ComplexNumber divide(ComplexNumber c){
		if(c.getReal() == 0 && c.getImaginary() == 0) throw new ArithmeticException("Cannot divide by 0");
		ComplexNumber c1 = this.multiply(c.conjugate());//numerator multiplication
		double factor = c.multiply(c.conjugate()).getReal();//denominator multiplication
		double divR = (c1.getReal())/factor;
		double divI = (c1.getImaginary())/factor;
		return new ComplexNumber(divR,divI);
	}

	/**
	* Divide two ComplexNumbers
	* static method: means it doesn't rely on a particular predefined data
	* Returns a new ComplexNumber, the quotient of two ComplexNumbers
	* @param c1 the first ComplexNummber
	* @param c2 the second ComplexNumber that is divides c1
	* @return the quotient of the two ComplexNumbers
	*/
	public static ComplexNumber divide(ComplexNumber c1, ComplexNumber c2){
	 ComplexNumber c3 = c1.multiply(c2.conjugate());
	 double factor = c2.multiply(c2.conjugate()).getReal();
	 double divR = (c3.getReal())/factor;
	 double divI = (c3.getImaginary())/factor;
	 return new ComplexNumber(divR,divI);
 }

 /**
	* Get the magnitude of this ComplexNumber
	* Returns a double, the magnitude of this ComplexNumber
	* @return the magnitude of this ComplexNumber
	*/
 public double magnitude(){
	 double r_squared = this.getReal();
	 r_squared*=r_squared;
	 double i_squared = this.getImaginary();
	 i_squared*=i_squared;
	 double m_squared = r_squared + i_squared;
	 return Math.sqrt(m_squared);

 }

 /**
	 * Compares two ComplexNumber objects by comparing their magnitudes
	 * <p>
	 * Returns:
	 * <ul>
	 * <li> 0 if the magnitude of this ComplexNumber is equal to magnitude of the parameter ComplexNumber </li>
	 * <li> negative integer if the magnitude of this ComplexNumber is less than than the magnitude of the parameter ComplexNumber</li>
	 * <li> positive integer if the magnitude of this ComplexNumber is greater than the magnitude of the parameter ComplexNumber. </li>
	 * </ul>
	 *
	 * @param c a ComplexNumber to be compared with this ComplexNumber
	 * @return a negative integer, zero, or a positive integer as this ComplexNumber is less than, equal to, or greater than the specified ComplexNumber
	 *
	 */
 public int compareTo(ComplexNumber c){
	 return (int)(this.magnitude() - c.magnitude());

 }

	/**
    * Returns a String version of the ComplexNumber
    * <p>Indicates the current real and imaginary
    * @return the String version of the ComplexNumber
    *
    */
	public String toString(){
		//convert to 3 decimal places
		String aStr = ""+a;
		String bStr = ""+b;
		int n=0, m=0;
		for(int i=0; i<aStr.length(); i++){
			if(aStr.substring(i,i+1)=="."){
				n=i;
				break;
			}
		}

		if((n+5)<(aStr.length())){
		 	aStr = aStr.substring(0,n+6);

			a = Double.parseDouble(aStr);
		}
		for(int j=0; j<bStr.length(); j++){
			if(bStr.substring(j,j+1)=="."){
				m=j;
				break;
			}
		}
		if((m+5)<(bStr.length())){
		 bStr = bStr.substring(0,m+6);
		 b = Double.parseDouble(bStr);
	 }



		if(b==1){
			if(a==0)
				return "i";
			else
			 	return a + "+" + "i";
		}
		else if(b==-1){
			if(a==0)
				return "-i";
			else
				return a + "-i";
		}
		else if(b==0){
			return "" + a;
		}
		else if(a==0){
			return b + "i";
		}
		else if(b<0)
			return a + "" + b + "i";
		else
	 		return a + "+" + b + "i";
	}

	/**
	 * A tester method
	 * @param args
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(1.00000021, -2.123456789);
		ComplexNumber c2 = new ComplexNumber(c1);
		ComplexNumber c3 = new ComplexNumber();
		ComplexNumber c4 = new ComplexNumber(-4,-9);
		ComplexNumber c5 = new ComplexNumber(2,-3);
		ComplexNumber c6 = new ComplexNumber(4,5);


		System.out.print("Specific Constructor, Accessor. (c1:1-2i r:1 i:-2):");
		System.out.println("c1:"+c1+" r:"+c1.getReal()+" i"+c1.getImaginary());// only shows the location of where the complex number is stored in the computer as memory in hexadecimal if there is no toString method.
		System.out.print("Copy Constructor, Accessor. (c2:1-2i r:1 i:-2):");
		System.out.println("c2:"+c2+" r:"+c2.getReal()+" i:"+c2.getImaginary());
		System.out.print("Default Constructor, Accessor. (c3:0+0i r:0 i:0):");
		System.out.println("c3:"+c3+" r:"+c3.getReal()+" i:"+c3.getImaginary());
		System.out.println(c1.add(c2));//instance add method
		System.out.println(ComplexNumber.add(c4,c2));//static add method
		System.out.println(c1.subtract(c4));//instance subtract method
		System.out.println(ComplexNumber.subtract(c4,c2));//static subtract method
		System.out.println(c5.multiply(c6));//instance multiply method
		System.out.println(ComplexNumber.multiply(c5,c6));//static multiply method
		System.out.println(c5.square());//instance square method
		System.out.println(ComplexNumber.square(c5));//static square method
		System.out.println(c6.conjugate());//instance conjugate method
		System.out.println(c5.divide(c6));//instance divide method
		System.out.println(ComplexNumber.divide(c5,c6));
		System.out.println(c3.magnitude());
		System.out.println(c4.compareTo(c5));




	}
}
