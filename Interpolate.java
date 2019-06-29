package interpolate;

import java.util.*;

/**
 *
 * This file contains sample use of interpolation, using Gaussian and Newton 
 * interpolations. Note that in order to interpolate a polynomial of n-th degree
 * there must be provided n+1 (different!) nodes (x,y) - pairs from Cartesian 
 * plane, the polynomial is unequivocal. Providing more than n+1 nodes for the 
 * polynomial of n-th degree will not change anything, it will remain unchanged
 * (look sample tests).
 */
public class Interpolate {
      public static void main(String [] args){
          HashMap<Float,Float>XYvalues = new HashMap<>();
          //3 nodes to get f(x)=x^2
          System.out.println("Sample test 1: ");
          System.out.println("---------------");
          XYvalues.put(0.0f, 0.0f);
          XYvalues.put(-1.0f, 1.0f);
          XYvalues.put(1.0f, 1.0f);
          GaussianInterpolation testG1 = new GaussianInterpolation();
          Polynomial polyG1 = testG1.interpolate(XYvalues);
          System.out.println(polyG1);
          NewtonInterpolation testN1 = new NewtonInterpolation();
          Polynomial polyN1 = testN1.interpolate(XYvalues);
          System.out.println(polyN1);
          //adding 4-th node, belonging to f(x)=x^2, polynomial remains the same
          XYvalues.put(2.0f,4.0f);
          System.out.println("Sample test 2: ");
          System.out.println("---------------");
          GaussianInterpolation testG2 = new GaussianInterpolation();
          Polynomial polyG2 = testG2.interpolate(XYvalues);
          System.out.println(polyG2);
          NewtonInterpolation testN2 = new NewtonInterpolation();
          Polynomial polyN2 = testN2.interpolate(XYvalues);
          System.out.println(polyN2);
          //changing 4-th node's value, polynomial changes..
          XYvalues.put(2.0f, 6.0f);
          System.out.println("Sample test 3: ");
          System.out.println("---------------");
          GaussianInterpolation testG3 = new GaussianInterpolation();
          Polynomial polyG3 = testG3.interpolate(XYvalues);
          System.out.println(polyG3);
          NewtonInterpolation testN3 = new NewtonInterpolation();
          Polynomial polyN3 = testN3.interpolate(XYvalues);
          System.out.println(polyN3);
          
      }
}