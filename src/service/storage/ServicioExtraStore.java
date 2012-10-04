package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Servicioextra;
public class ServicioextraStore {
private static String[][] data = {
{"1","Servicio Extra Cliente 1","1"},{"2","Servicio Extra Cliente 2","2"}};

private static Map<String,Servicioextra> store;
	private static ServicioextraStore instance = null;
	private ServicioextraStore() {
		store = new HashMap<String,Servicioextra>();
		initStore();
	}
	public static Map<String,Servicioextra> getStore() {
		if(instance==null) {
			instance = new ServicioextraStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Servicioextra());
    for (int i=0;i<data.length;i++) {
      Servicioextra temp = new Servicioextra();
      temp.setid(data[i][0]);
      temp.setnombre(data[i][1]);
      temp.setservicio(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
