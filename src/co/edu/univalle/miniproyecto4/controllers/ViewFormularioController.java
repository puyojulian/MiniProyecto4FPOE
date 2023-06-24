package co.edu.univalle.miniproyecto4.controllers;

import co.edu.univalle.miniproyecto4.models.Ingenio;
import co.edu.univalle.miniproyecto4.views.ViewFormulario;

public class ViewFormularioController {
  private ViewFormulario vista;
  private Ingenio ingenio;
  

  public ViewFormularioController(ViewFormulario vista) {
    this.vista = vista;
    ingenio = new Ingenio();
  
  }

}
