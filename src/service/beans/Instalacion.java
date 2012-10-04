package service.beans;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Instalacion {
  String id;
  String numconcent;
  String cantmetcable;
  String inspropia;
  String canttel;
  public  Instalacion() {
	}
 public Instalacion(
  String id,  String numconcent,  String cantmetcable,  String inspropia,  String canttel) {
  this.id=id;
  this.numconcent=numconcent;
  this.cantmetcable=cantmetcable;
  this.inspropia=inspropia;
  this.canttel=canttel;
	}
  public void setid(String id){this.id=id;}
  public void setnumconcent(String numconcent){this.numconcent=numconcent;}
  public void setcantmetcable(String cantmetcable){this.cantmetcable=cantmetcable;}
  public void setinspropia(String inspropia){this.inspropia=inspropia;}
  public void setcanttel(String canttel){this.canttel=canttel;}
  public String getid() { return id;}
  public String getnumconcent() { return numconcent;}
  public String getcantmetcable() { return cantmetcable;}
  public String getinspropia() { return inspropia;}
  public String getcanttel() { return canttel;}
}
