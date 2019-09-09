/* Name:Charles King
   PID:5961386
   Section Number:COP337 U05
   I affirm that this program is entirely my own work 
   and none of it is the work of any other person.
        Signature:Charles David King
   File: Polynomial.java

   Disclaimer:This polynomial class was given to me by the professor from the 
   class website as a skeleton with the intent of having me only writing the 
   bodies of the methods. We are not allowed to declare any new instance 
   variables or methods and can not modify any of the method declarations.
    
   The purpose of this polynomial class is to create polynomials that can be 
   copied, added, mulitplied, simplified by combining like terms and organized
   so the terms are stored in ascending order by exponent.
 */

/**
 * A class to represent a polynomial as a sum of terms where each term has
 * the same variable, an int coefficient, and a nonnegative int exponent.
 * Polynomials may be added and multiplied
 */
public class Polynomial
{

    private Node head;              // points to the first Node of a Polynomial

    /**
     * Default constructor creates a Polynomial with no terms
     */
    public Polynomial() // DO NOT MODIFY THIS CONSTRUCTOR
    {
        head = null;
    }

    /**
     * Creates a "deep copy" of a given Polynomial. I.e. a new Polynomial with
     * identical terms
     *
     * @param p Polynomial to be copied
     */
    public Polynomial(Polynomial p) // a "copy" constructor
    {
        //Creates new pointer to head of polynomial passed by parameter
        Node newP = p.head ;

        //While pointer is not null continue to execute 
        while (newP != null)
        {
            //Addterms for ceofficent and exponent from polynomial passed
            addTerm(newP.info.getCoef(), newP.info.getExpon()) ;

            //Moves to the next node
            newP = newP.next ;
        }
    }

    /**
     * Creates a new Term and Node containing it and inserts it in its proper
     * place in this Polynomial (i.e. in ascending order by exponent)
     *
     * @param coeff the coefficient of the new Term
     * @param expo the exponent of the new Term
     */
    public void addTerm(int coeff, int expo)
    {
        //Creates a term object with values passed 
        Term t1 = new Term(coeff, expo) ;

        //Creates a node object with term object passed
        Node temp = new Node(t1) ;

        //Variable pointer that starts at head of list
        Node temp2 = head ;

        //Checks if statement for when list is empty
        if (head == null)
        {
            //put new Node at head of list.
            head = temp ;
            
          //else for when list is not empty
        } else
        {
            /*
            Checks if exponent of first term in list is greater than exponent 
            of new term being added
             */
            if (temp.info.getExpon() < head.info.getExpon())
            {
                //point new node to first node in list
                temp.next = head ;

                //then point head of list to new node
                head = temp ;

                //Breaks loop since new term was added
                return ;
            }
            //While not at last node
            while (temp2.next != null)
            {
                /*
                Checks if new node exponent is less than the exponent of the 
                node next to the temp2 node
                 */
                if (temp.info.getExpon() < temp2.next.info.getExpon())
                {
                    //point new node to node that temp2 is pointing to 
                    temp.next = temp2.next ;

                    //Then point temp2 to new node
                    temp2.next = temp ;

                    //breaks loop since new term was added
                    return ;
                }
                //Moves to next node
                temp2 = temp2.next ;

            }
            /*
            Append new node to end of list when new node exponent is greater 
            than all other nodes in list
             */
            temp2.next = temp ;

        }

    }

    /**
     * Returns a polynomial as a String in this form: x + 3x^2 + 7x^3 + x^5
     *
     * @return the polynomial as a String
     */
    public String toString()
    {
        //Calls collectTerms method to collect like terms of the polynomial
        collectTerms() ;

        //Initalizes a string variable 
        String polyOut = "" ;

        //Start at head of list 
        Node temp = head ;

        // while more nodes on list
        while (temp != null)
        {

            //append current object info to string variable
            polyOut += temp.info ;

            //checks if next to current node is null
            if (temp.next != null)
            {
                //appends a " + " between every term
                polyOut += " + " ;
            }

            //Moves to next node
            temp = temp.next ;
        }
        //returns string of a polynomial in the form: x + 3x^2 + 7x^3 + x^5
        return polyOut ;

    }

    // collect terms of a Polynomial object. I.e. replace all terms having the 
    // same exponent with a single term which is their sum
    private void collectTerms()
    {
        //creates a dummy node to start the collected polynomial from
        Node head2 = new Node(new Term(0, 0));

        //Variable pointer that starts at dummy node
        Node temp = head2 ;

        //Creates a int variable for combined coefficents
        int combinedCoef ;

        //Start at head of list of the Polynomial being collected
        Node temp2 = head ;

        // while more nodes on list
        while (temp2 != null)
        {
            //Assign int variable the value of current object exponent
            int currentExpo = temp2.info.getExpon();

            //Assigns combinedCoef to 0, reinitalize everytime like terms added
            combinedCoef = 0 ;

            /*
            while loop that checks list has more nodes and that current 
            object exponent is equal to a int variable that was initialized to 
            the first object exponent as to continue to exicute until exponent
            changes
             */
            while (temp2 != null && temp2.info.getExpon() == currentExpo)
            {
                //adds value of each object coefficent that have 
                combinedCoef += temp2.info.getCoef() ;
                
                //Moves to next node
                temp2 = temp2.next ;
            }
            /*
            checks if combine coefficent variable is not equal to zero to not 
            add it as a new node if above condition is not meant
             */
            if (combinedCoef != 0)
            {
                /*
            Creates a new node with combined coefficent of the objects from the
            list that had the same exponent, creating it with said exponent too
                 */
                Node current = new Node(new Term(combinedCoef, currentExpo)) ;

                //appends the current node to list
                temp.next = current ;
                
                //Moves to next node
                temp = temp.next ;

            }

        }
        /*
        Makes head of list point to what is next to what head is pointing to
        as to put dummy node into garbage collection
         */
        head2 = head2.next ;

        /*
        Makes head of uncombined polynomial list point to head of combined 
        polynomial list
         */
        head = head2 ;

    }

    /**
     * Multiply this Polynomial by another Polynomial
     *
     * @param p the other Polynomial
     * @return the Polynomial product
     */
    public Polynomial polyMultiply(Polynomial p)
    {
        //creates a polynomial copy of the polynomial passed as p
        Polynomial pMultiple = new Polynomial(p) ;

        //creates a new polynomial for the the mulitpled polynomial 
        Polynomial multipliedP = new Polynomial() ;

        //Start at head of list of the Polynomial being mulitpled
        Node dotP = head ;

        //Start at head of list of the copied Polynomial being mulitpled
        Node copyP = pMultiple.head ;

        // while more nodes on list of copied polynomial list
        while (copyP != null)
        {
            //while more nodes on list of polynomial list accessed by the dot 
            while (dotP != null)
            {
                //int for mulitpled coefficent of copied poly and other poly
                int multipliedCoef = (dotP.info.getCoef()
                        * copyP.info.getCoef()) ;
                
                //int for added exponent of copied poly and other poly
                int addedExponent = (dotP.info.getExpon()
                        + copyP.info.getExpon()) ;
                
                //Add terms for ceofficent and exponent for mulitped polynomial
                multipliedP.addTerm(multipliedCoef, addedExponent) ;
                
                //Moves to next node of dot accessed polynomial list
                dotP = dotP.next ;
            }
            //Moves to next node of copied polynomial list
            copyP = copyP.next ;
            
            /*
            resets dotP node pointer to head to multipy by next copied list 
            node
            */
            dotP = head ;
        }
        //returns the new polynomial for the the mulitpled polynomial 
        return multipliedP ;
    }

    /**
     * Add this Polynomial and another Polynomial
     *
     * @param p the other Polynomial
     * @return the Polynomial sum
     */
    public Polynomial polyAdd(Polynomial p)
    {
        //creates a polynomial copy of the polynomial passed as p
        Polynomial p3 = new Polynomial(p) ;
        
        //Start at head of list of the Polynomial being added
        Node temp = this.head ;
        
        //while not at last node
        while (temp != null) 
        {
            /*
            Add terms for ceofficent and exponent of the polynomial accessed by 
            dot to the copied polynomial list
            */
            p3.addTerm(temp.info.getCoef(), temp.info.getExpon()) ;
            
            //Move to next node
            temp = temp.next ;	
        }

        /*
        returns the copied polynomial added with the polynomial accessed by 
        dot as one polynomial
        */
        return p3 ;
    }

    // Node class definition - DO NOT MODIFY!
    class Node<E extends Term>
    {

        private E info;     // each node stores an object of the 
        // type-parameter class...
        private Node next;  // ...and a pointer to another node

        // Node Constructor 
        // parameter x is an object of the type-parameter class
        Node(E x)
        {
            info = x;	    // set info portion to parameter passed
            next = null;
        }
    } // end of Node class definition ============================
} // end of Polynomial class definition =========================
