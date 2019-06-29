/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 99xku
 */
public class Interpolate {
      public static void main(String [] args){
          HashMap<Float,Float>XYvalues = new HashMap<>();
          XYvalues.put(0.0f, 0.0f);
          XYvalues.put(-1.0f, 1.0f);
          XYvalues.put(1.0f, 1.0f);
          GaussianInterpolation p = new GaussianInterpolation();
          Polynomial neww = p.interpolate(XYvalues);
      }
}
