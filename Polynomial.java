package interpolate;

import java.lang.*;
import java.util.*;
/**
 *
 * Polynomial class - polynomial is represented in a list of float values - it's
 * coefficients.
 */
public class Polynomial {
    private String name;
    private List<Float> coefficients;
    private int polyDegree;
    //default constructor
    public Polynomial(){
        name="function";
        polyDegree=0;
        coefficients = new ArrayList();
    }
    //constructor using polynomials coefficients with exponents in ascending 
    //order, similar constructor, taking also function name down below
    public Polynomial(final ArrayList<Float> coefficients){
        name="function";
        polyDegree=coefficients.size()-1;
        this.coefficients=(List<Float>) coefficients.clone();
    }
    public Polynomial(String name,final ArrayList<Float> coefficients){
        this.name=name;
        polyDegree=coefficients.size()-1;
        this.coefficients=(List<Float>) coefficients.clone();
    }
    //copying constructor
    public Polynomial(Polynomial copied){
        this.name=copied.name;
        this.coefficients=(List<Float>)copied.getCoef().clone();
        this.polyDegree=copied.getDegree();
    }
    @Override
    public String toString(){
        if(coefficients.size()==0) return "Polynomial is empty";
        int i = 0;
        StringBuilder output = new StringBuilder();
        output.append(name);
        output.append("(x) = ");
        ListIterator<Float> it = coefficients.listIterator(coefficients.size());
        while(it.hasPrevious()){    
            Object obj = it.previous();
            if((float)(obj)!=0)
            output.append((float)(obj)).append("x^"+(this.getDegree()-i)+" + "); i++;
        }
        output.deleteCharAt(output.length()-2);
        return output.toString();
    }
    //method adding second polynomial to this
    public void addPoly(final Polynomial second){
        int i = 0;
        ArrayList<Float>output=new ArrayList<>();
        ArrayList<Float>secondCoef = second.getCoef();
        while(i<=this.getDegree()&&i<=second.getDegree()){
            output.add(this.coefficients.get(i)+secondCoef.get(i)); i++;
        }
        while(i<=this.getDegree()){
            output.add(this.coefficients.get(i)); i++;
        }
        while(i<=second.getDegree()){
            output.add(secondCoef.get(i)); i++;
        }
        this.coefficients=output;
        this.polyDegree=output.size()-1;
    }
    //multiplying polynomial by constant float value mBy, used further in 
    //Newton interpolation
    public void multiplyByConst(final float mBy){
        ListIterator it = coefficients.listIterator();
        while(it.hasNext()){
            Object o = it.next();
            it.set(((float)o)*mBy);
        }
    }
    //multiply polynomial by binomial (x-a)
    public void multiplyByBinomial(float a){
        ArrayList<Float>coefCopy= (ArrayList<Float>)(((ArrayList<Float>)coefficients).clone());
        coefficients.add(coefficients.get(this.getDegree()));
        for(int i = 1; i < this.getDegree()+1;i++){
             coefficients.set(i, coefCopy.get(i-1)-a*coefCopy.get(i));   
        }
        this.polyDegree+=1;
        coefficients.set(0, -coefficients.get(0)*a);
    }
    public String getName(){return name;}
    public ArrayList<Float> getCoef(){return (ArrayList<Float>)(((ArrayList<Float>)(coefficients)).clone());}
    public int getDegree(){return polyDegree;}
    public void setDegree(int deg){this.polyDegree=deg;}
}