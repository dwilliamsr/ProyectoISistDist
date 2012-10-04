package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cuadra {
  String id;
  String nombre;
  String region;
  public  Cuadra() {
	}
 public Cuadra(
  String id,  String nombre,  String region) {
  this.id=id;
  this.nombre=nombre;
  this.region=region;
	}
  public void setid(String id){this.id=id;}
  public void setnombre(String nombre){this.nombre=nombre;}
  public void setregion(String region){this.region=region;}
  public String getid() { return id;}
  public String getnombre() { return nombre;}
  public String getregion() { return region;}
}
