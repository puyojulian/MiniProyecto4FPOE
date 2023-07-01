package co.edu.univalle.miniproyecto4.util;

public class PairClassUtil {
    private int first;
    private int second;
    
    public PairClassUtil(int first, int second) {
        this.first = first;
        this.second = second;
    }
    
    public int getFirst() {
        return first;
    }
    
    public int getSecond() {
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
        int result = 17;
        result = 31 * result + first;
        result = 31 * result + second;
        return result;
    }
}
