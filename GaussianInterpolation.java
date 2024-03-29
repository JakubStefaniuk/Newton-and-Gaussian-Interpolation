package interpolate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * interpolation solving the system of linear equations, for polynomials of 
 * bigger degrees and node values, consider using Newton interpolation instead
 * 
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
           if(!gaussElimination(linearMatrix)){
               System.out.println("Linear quation system is not row independent");
               return new Polynomial();
           } 
           //saving polynomial coefficients in natural form to ArrayList
           for(i = pairs.size()-1; i >= 0; i--){
               naturalCoefs.add(linearMatrix[i][pairs.size()]/linearMatrix[i][i]);
           }
           return new Polynomial(naturalCoefs);
    }
    //helper function to reduce the matrix
    public boolean gaussElimination(float [][] Matrix){
        int k;
        int r = Matrix.length;
        int c = Matrix[0].length;
        for(int i = 0; i < r; i++){
                if(Matrix[i][i]!=0.0f){     
                    for(int j = 0; j < r; j++){
                        if(i!=j){
                            //factor used in reducing the matrix rows
                            float eliminationCoef=Matrix[j][i]/Matrix[i][i];
                            for(k = 0; k < c; k++){
                                Matrix[j][k]-=Matrix[i][k]*eliminationCoef;
                            }
                        }
                    }
                }
                //swapping the row with the other from the matrix, if the 
                //number on main diagonal is equal to 0
                else{
                    for(k = i; k < r; k++){
                        if(Matrix[k][i]==0.0f) continue;
                        else break;
                    }
                    //all the numbers below are 0.0f, cant reduce, return false
                    if(k>=r){
                        return false;
                    }
                    else{
                        //swapping rows
                        float tmp[] = Matrix[i];
                        Matrix[i] = Matrix[k];
                        Matrix[k] = tmp;
                        i--;
                    }
                }
        }
        return true;
    }
}