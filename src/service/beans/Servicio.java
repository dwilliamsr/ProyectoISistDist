package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Servicio {
  String id;
  String fechainst;
  String direccion;
  String region;
  String suscriptor;
  public  Servicio() {
	}
 public Servicio(
  String id,  String fechainst,  String direccion,  String region,  String suscriptor) {
  this.id=id;
  this.fechainst=fechainst;
  this.direccion=direccion;
  this.region=region;
  this.suscriptor=suscriptor;
	}
  public void setid(String id){this.id=id;}
  public void setfechainst(String fechainst){this.fechainst=fechainst;}
  public void setdireccion(String direccion){this.direccion=direccion;}
  public void setregion(String region){this.region=region;}
  public void setsuscriptor(String suscriptor){this.suscriptor=suscriptor;}
  public String getid() { return id;}
  public String getfechainst() { return fechainst;}
  public String getdireccion() { return direccion;}
  public String getregion() { return region;}
  public String getsuscriptor() { return suscriptor;}
}
