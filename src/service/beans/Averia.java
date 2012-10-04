package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Averia {
  String id;
  String descripcion;
  String fecha;
  String servicio;
  public  Averia() {
	}
 public Averia(
  String id,  String descripcion,  String fecha,  String servicio) {
  this.id=id;
  this.descripcion=descripcion;
  this.fecha=fecha;
  this.servicio=servicio;
	}
  public void setid(String id){this.id=id;}
  public void setdescripcion(String descripcion){this.descripcion=descripcion;}
  public void setfecha(String fecha){this.fecha=fecha;}
  public void setservicio(String servicio){this.servicio=servicio;}
  public String getid() { return id;}
  public String getdescripcion() { return descripcion;}
  public String getfecha() { return fecha;}
  public String getservicio() { return servicio;}
}
