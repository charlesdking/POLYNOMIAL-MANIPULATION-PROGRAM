/* Name:Charles King
   File: Term.java
    
   This class represents a term in an algebraic expression with a term 
   consisting of an integer coefficient and a nonnegative integer exponent.
 */

/**
 * This Term class declares a term object with a coefficient value and an
 * exponent value passed by the constructor parameters. Also accessor methods
 * to get the coefficient value and the exponent value as well as a toString
 * method to get the Term in the form ax^b where a is the coefficient and
 * b is the exponent.
 */
public class Term
{

    //Declares private instance variable of integer type
    private int coefficient ;

    //Declares private instance variable of integer type
    private int exponent ;

    /**
     * Initializes coefficient and exponent values to integer variables
     * 
     * @param coefficient the coefficient of the Term
     * @param exponent the exponent of the Term
     */
    public Term(int coefficient, int exponent)
    {

        //Assigns int variable coefficient with int variable from parameter
        this.coefficient = coefficient ;

        //Assigns int variable exponent with int variable from parameter
        this.exponent = exponent ;

    }

    /**
     * returns the coefficient value of the term
     * 
     * @return the current value of the coefficient
     */
    public int getCoef()
    {
        //returns int value of coeffiecent
        return coefficient ;
    }

    /**
     * returns the exponent value of the term
     *
     * @return the current value of the exponent
     */
    public int getExpon()
    {
        //returns int value of exponent
        return exponent ;
    }
    
    /**
     * Returns a Term as a String in the general form: ax^b where a is the 
     * coefficient and b the exponent with special cases. The special cases are
     * (a = 1, b = 1 returns x), (a = 1 returns x^b), (b = 1 returns ax), and 
     * (b = 0 returns a).
     *
     * @return the Term as a String in the form ax^b 
     */
    public String toString()
    {
        //checks if coefficient and expoent are 1 
        if (coefficient == 1 && exponent == 1)
        {
            //returns string x to represent a variable
            return "x" ;
          
          //if coefficient is 1
        } else if (coefficient == 1)
        {
            //returns string x^ as a variable with an exponent and the exponent
            return "x^" + exponent ;
            
          //checks if exponent is 1
        } else if (exponent == 1)
        {
            //return the value for coefficient and the string x as the variable
            return coefficient + "x" ;
          // if exponent is 0
        } else if (exponent == 0)
        {
            //returns the value of the coefficient by Integer.toString call
            return Integer.toString(coefficient) ;
            
          //checks for any other condition
        } else
        {
            //returns the coefficient value with the string x^ and the exponent
            return coefficient + "x^" + exponent ;
        }
    }

}
