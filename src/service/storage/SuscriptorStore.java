package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Suscriptor;
public class SuscriptorStore {
private static String[][] data = {
{"1","Suscriptor de Prueba","123456789","2222-2222","No Disponible"}};

private static Map<String,Suscriptor> store;
	private static SuscriptorStore instance = null;
	private SuscriptorStore() {
		store = new HashMap<String,Suscriptor>();
		initStore();
	}
	public static Map<String,Suscriptor> getStore() {
		if(instance==null) {
			instance = new SuscriptorStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Suscriptor());
    for (int i=0;i<data.length;i++) {
      Suscriptor temp = new Suscriptor();
      temp.setid(data[i][0]);
      temp.setnombre(data[i][1]);
      temp.setidentificacion(data[i][2]);
      temp.settelefono(data[i][3]);
      temp.setdireccion(data[i][4]);
      store.put(temp.getid(),temp);
    }
  }
}
