package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tipoaveria {
  String id;
  String nombre;
  String averia;
  public  Tipoaveria() {
	}
 public Tipoaveria(
  String id,  String nombre,  String averia) {
  this.id=id;
  this.nombre=nombre;
  this.averia=averia;
	}
  public void setid(String id){this.id=id;}
  public void setnombre(String nombre){this.nombre=nombre;}
  public void setaveria(String averia){this.averia=averia;}
  public String getid() { return id;}
  public String getnombre() { return nombre;}
  public String getaveria() { return averia;}
}
