package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Tipovivienda;
public class TipoviviendaStore {
private static String[][] data = {
{"1","Vivienda Estandar","1"}};

private static Map<String,Tipovivienda> store;
	private static TipoviviendaStore instance = null;
	private TipoviviendaStore() {
		store = new HashMap<String,Tipovivienda>();
		initStore();
	}
	public static Map<String,Tipovivienda> getStore() {
		if(instance==null) {
			instance = new TipoviviendaStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Tipovivienda());
    for (int i=0;i<data.length;i++) {
      Tipovivienda temp = new Tipovivienda();
      temp.setid(data[i][0]);
      temp.setnombre (data[i][1]);
      temp.setservicio(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
