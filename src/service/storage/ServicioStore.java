package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Servicio;
public class ServicioStore {
private static String[][] data = {
{"1","05/08/2012","Calle X, Avenida Y, Casa 123","1","1"},{"2","02/08/2012","Calle X, Avenida Y, Casa 222","2","2"}};

private static Map<String,Servicio> store;
	private static ServicioStore instance = null;
	private ServicioStore() {
		store = new HashMap<String,Servicio>();
		initStore();
	}
	public static Map<String,Servicio> getStore() {
		if(instance==null) {
			instance = new ServicioStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Servicio());
    for (int i=0;i<data.length;i++) {
      Servicio temp = new Servicio();
      temp.setid(data[i][0]);
      temp.setfechainst(data[i][1]);
      temp.setdireccion(data[i][2]);
      temp.setregion(data[i][3]);
      temp.setsuscriptor(data[i][4]);
      store.put(temp.getid(),temp);
    }
  }
}
