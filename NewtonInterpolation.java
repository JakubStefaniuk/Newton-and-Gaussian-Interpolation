/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * note that to get a polynomial of degree n, you have to...
 */
public class NewtonInterpolation implements Interpolation{
    @Override
    public Polynomial interpolate(HashMap<Float, Float> XYvalues) {
        int i = 0,k;
        ArrayList<Float>newtonBaseCoefs=new ArrayList<>();
        int size = XYvalues.size();
        assert(size>1);
        //array used in calculating Newton's base coefficients
        Float [][] computeArray = new Float[size][size+1];
        //initializating first two columns
        Set<Entry<Float, Float>> entrySet = XYvalues.entrySet();
        for(Entry<Float,Float>entry:entrySet){
            computeArray[i][0]=entry.getKey();
            computeArray[i][1]=entry.getValue();
            i++;
        }
        newtonBaseCoefs.add(computeArray[0][1]);
        for(int j = 2; j < size+1;j++){
            k=0;
            for(i = j-1; i < size; i++){
                computeArray[i][j]=(computeArray[i][j-1]-computeArray[i-1][j-1])/(computeArray[i][0]-computeArray[k][0]);
                k++;
            }
            newtonBaseCoefs.add(computeArray[j-1][j]);
        }
        //transforming Newton's base coefficients to polynomial
        //natural representation
        int deg = 0;
        ArrayList<Float>tmp = new ArrayList<>();
        tmp.add(0.0f);
        Polynomial output = new Polynomial(tmp);
        tmp.set(0, 1.0f);
        Polynomial nextComponent = new Polynomial(tmp);
        Polynomial nextComponentCopy = new Polynomial(nextComponent);
        nextComponentCopy.setDegree(deg);
        nextComponentCopy.multiplyByConst(newtonBaseCoefs.get(0));
        output.addPoly(nextComponentCopy);
        for(i = 1; i < newtonBaseCoefs.size();i++){
            nextComponent.multiplyByBinomial(computeArray[i-1][0]);
            nextComponentCopy = new Polynomial(nextComponent);
            nextComponentCopy.multiplyByConst(newtonBaseCoefs.get(i));
            output.addPoly(nextComponentCopy);
        }
        output.setDegree(newtonBaseCoefs.size()-1);
        return output;
    }
    
}
