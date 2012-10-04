package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Tipoaveria;
public class TipoaveriaStore {
private static String[][] data = {
{"1","Tipo de Averia de Prueba","1"}};

private static Map<String,Tipoaveria> store;
	private static TipoaveriaStore instance = null;
	private TipoaveriaStore() {
		store = new HashMap<String,Tipoaveria>();
		initStore();
	}
	public static Map<String,Tipoaveria> getStore() {
		if(instance==null) {
			instance = new TipoaveriaStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Tipoaveria());
    for (int i=0;i<data.length;i++) {
      Tipoaveria temp = new Tipoaveria();
      temp.setid(data[i][0]);
      temp.setnombre(data[i][1]);
      temp.setaveria(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
