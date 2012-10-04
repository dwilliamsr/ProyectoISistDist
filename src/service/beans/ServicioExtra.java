package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Servicioextra {
  String id;
  String nombre;
  String servicio;
  public  Servicioextra() {
	}
 public Servicioextra(
  String id,  String nombre,  String servicio) {
  this.id=id;
  this.nombre=nombre;
  this.servicio=servicio;
	}
  public void setid(String id){this.id=id;}
  public void setnombre(String nombre){this.nombre=nombre;}
  public void setservicio(String servicio){this.servicio=servicio;}
  public String getid() { return id;}
  public String getnombre() { return nombre;}
  public String getservicio() { return servicio;}
}
