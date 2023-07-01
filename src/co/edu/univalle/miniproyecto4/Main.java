/**
 *
 * @author Julian Puyo
 * @author Sebastian Orrego
 * @author Juan David Rodriguez
 * @author Manuel Cardoso
 * @author Luis Carlos Lucero
 */

package co.edu.univalle.miniproyecto4;

import javax.swing.text.View;

import co.edu.univalle.miniproyecto4.models.Ingenio;
import co.edu.univalle.miniproyecto4.views.ViewIncio;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ingenio ingenio = new Ingenio();
        ViewIncio viewIncio = new ViewIncio(ingenio);
        
        
    }
    
}
