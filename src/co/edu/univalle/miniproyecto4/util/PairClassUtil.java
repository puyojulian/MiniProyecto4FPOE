package co.edu.univalle.miniproyecto4.util;

public class PairClassUtil {
    private int first;
    private float second;
    
    public PairClassUtil(Integer first, float second) {
        this.first = first;
        this.second = second;
    }
    
    public Object getFirst() {
        return first;
    }
    
    public Object getSecond() {
        return second;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        PairClassUtil other = (PairClassUtil) obj;
        return first == other.first && second == other.second;
    }
    
    @Override
    public int hashCode() {
        Integer result = 17;
        result = 31 * result + first;
        result = 31 * result + (int) second;
        return result;
    }
}
