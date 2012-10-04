package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Sucursal;
public class SucursalStore {
private static String[][] data = {
{"1","3333-3333","San Jose","Calle X, Avenida Y","Encargado de Prueba 02"}};

private static Map<String,Sucursal> store;
	private static SucursalStore instance = null;
	private SucursalStore() {
		store = new HashMap<String,Sucursal>();
		initStore();
	}
	public static Map<String,Sucursal> getStore() {
		if(instance==null) {
			instance = new SucursalStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Sucursal());
    for (int i=0;i<data.length;i++) {
      Sucursal temp = new Sucursal();
      temp.setid(data[i][0]);
      temp.settelefono(data[i][1]);
      temp.setciudad(data[i][2]);
      temp.setdireccion(data[i][3]);
      temp.setencargado(data[i][4]);
      store.put(temp.getid(),temp);
    }
  }
}
