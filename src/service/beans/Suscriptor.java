package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Suscriptor {
  String id;
  String nombre;
  String identificacion;
  String telefono;
  String direccion;
  public  Suscriptor() {
	}
 public Suscriptor(
  String id,  String nombre,  String identificacion,  String telefono,  String direccion) {
  this.id=id;
  this.nombre=nombre;
  this.identificacion=identificacion;
  this.telefono=telefono;
  this.direccion=direccion;
	}
  public void setid(String id){this.id=id;}
  public void setnombre(String nombre){this.nombre=nombre;}
  public void setidentificacion(String identificacion){this.identificacion=identificacion;}
  public void settelefono(String telefono){this.telefono=telefono;}
  public void setdireccion(String direccion){this.direccion=direccion;}
  public String getid() { return id;}
  public String getnombre() { return nombre;}
  public String getidentificacion() { return identificacion;}
  public String gettelefono() { return telefono;}
  public String getdireccion() { return direccion;}
}
