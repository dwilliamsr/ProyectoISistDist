package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Instalacion;
public class InstalacionStore {
private static String[][] data = {
{"1","1","10","S","2"}};

private static Map<String,Instalacion> store;
	private static InstalacionStore instance = null;
	private InstalacionStore() {
		store = new HashMap<String,Instalacion>();
		initStore();
	}
	public static Map<String,Instalacion> getStore() {
		if(instance==null) {
			instance = new InstalacionStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Instalacion());
    for (int i=0;i<data.length;i++) {
      Instalacion temp = new Instalacion();
      temp.setid(data[i][0]);
      temp.setnumconcent(data[i][1]);
      temp.setcantmetcable(data[i][2]);
      temp.setinspropia(data[i][3]);
      temp.setcanttel(data[i][4]);
      store.put(temp.getid(),temp);
    }
  }
}
