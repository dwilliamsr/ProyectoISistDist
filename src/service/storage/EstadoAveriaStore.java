package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Estadoaveria;
public class EstadoaveriaStore {
private static String[][] data = {
{"1","Estado de Averia de prueba","1"}};

private static Map<String,Estadoaveria> store;
	private static EstadoaveriaStore instance = null;
	private EstadoaveriaStore() {
		store = new HashMap<String,Estadoaveria>();
		initStore();
	}
	public static Map<String,Estadoaveria> getStore() {
		if(instance==null) {
			instance = new EstadoaveriaStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Estadoaveria());
    for (int i=0;i<data.length;i++) {
      Estadoaveria temp = new Estadoaveria();
      temp.setid(data[i][0]);
      temp.setnombre(data[i][1]);
      temp.setaveria(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
