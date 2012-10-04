package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Region {
  String id;
  String encargado;
  String sucursal;
  public  Region() {
	}
 public Region(
  String id,  String encargado,  String sucursal) {
  this.id=id;
  this.encargado=encargado;
  this.sucursal=sucursal;
	}
  public void setid(String id){this.id=id;}
  public void setencargado(String encargado){this.encargado=encargado;}
  public void setsucursal(String sucursal){this.sucursal=sucursal;}
  public String getid() { return id;}
  public String getencargado() { return encargado;}
  public String getsucursal() { return sucursal;}
}
