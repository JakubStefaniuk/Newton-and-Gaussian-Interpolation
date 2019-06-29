package interpolate;

import java.util.HashMap;

/**
 *
 * interface implemented by NewtonInterpolation and GaussianInterpolation 
 * 
 */
public interface Interpolation {
    public Polynomial interpolate(HashMap<Float, Float>XYvalues);
}
