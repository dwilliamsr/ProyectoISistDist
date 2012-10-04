package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sucursal {
  String id;
  String telefono;
  String ciudad;
  String direccion;
  String encargado;
  public  Sucursal() {
	}
 public Sucursal(
  String id,  String telefono,  String ciudad,  String direccion,  String encargado) {
  this.id=id;
  this.telefono=telefono;
  this.ciudad=ciudad;
  this.direccion=direccion;
  this.encargado=encargado;
	}
  public void setid(String id){this.id=id;}
  public void settelefono(String telefono){this.telefono=telefono;}
  public void setciudad(String ciudad){this.ciudad=ciudad;}
  public void setdireccion(String direccion){this.direccion=direccion;}
  public void setencargado(String encargado){this.encargado=encargado;}
  public String getid() { return id;}
  public String gettelefono() { return telefono;}
  public String getciudad() { return ciudad;}
  public String getdireccion() { return direccion;}
  public String getencargado() { return encargado;}
}
