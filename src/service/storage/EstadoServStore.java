package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Estadoserv;
public class EstadoservStore {
private static String[][] data = {
{"1","Servicio Premium","1"}};

private static Map<String,Estadoserv> store;
	private static EstadoservStore instance = null;
	private EstadoservStore() {
		store = new HashMap<String,Estadoserv>();
		initStore();
	}
	public static Map<String,Estadoserv> getStore() {
		if(instance==null) {
			instance = new EstadoservStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Estadoserv());
    for (int i=0;i<data.length;i++) {
      Estadoserv temp = new Estadoserv();
      temp.setid(data[i][0]);
      temp.setnombre(data[i][1]);
      temp.setservicio(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
