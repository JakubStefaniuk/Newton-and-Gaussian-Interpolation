/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author 99xku
 */
public class GaussianInterpolation implements Interpolation {
    @Override
    public Polynomial interpolate(HashMap<Float, Float> pairs) {
           //will be used later in returning polynomial
           ArrayList<Float>naturalCoefs=new ArrayList<>();
           //making linear system of equations matrix 
           ArrayList<Float>GaussianCoefs=new ArrayList<>();
           float [][] linearMatrix = new float [pairs.size()][pairs.size()+1];
           Set<Entry<Float,Float>>map=pairs.entrySet();
           //writing function values into last matrix column and polynominal's 
           //part independent from x value (free)
           int i = 0;
           for(Entry<Float,Float>ent:map){
               linearMatrix[i][pairs.size()]=ent.getValue();
               linearMatrix[i][pairs.size()-1]=1;
               i++;
           }
           float [] interpolationNodes = new float [pairs.size()];
           i=0; //wrting interpolation nodes into helper float array
           for(Entry<Float,Float>ent:map){
               interpolationNodes[i++]=ent.getKey();
           }
           float [] nextPowers = new float[pairs.size()];
           //copying interpolation nodes to helper matrix, 
           for(i = 0; i < pairs.size();i++){
               nextPowers[i]=interpolationNodes[i];
           }
           //creating whole linearMatrix
           for(int j = pairs.size()-2; j >=0;j--){
               for(i = 0; i < pairs.size() ;i++){
                   linearMatrix[i][j]=nextPowers[i];
                   nextPowers[i]*=interpolationNodes[i];
               }
           }
           //solving sytem of linear equations
           //
           //
           //some GE or sth
           for(i = pairs.size()-1; i >= 0; i--){
               naturalCoefs.add(linearMatrix[i][i]);
           }
           return new Polynomial(naturalCoefs);
    }
}
