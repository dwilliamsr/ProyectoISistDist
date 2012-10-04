package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Averia;
public class AveriaStore {
private static String[][] data = {
{"1","Averia  de prueba","01/09/2012","1"}};

private static Map<String,Averia> store;
	private static AveriaStore instance = null;
	private AveriaStore() {
		store = new HashMap<String,Averia>();
		initStore();
	}
	public static Map<String,Averia> getStore() {
		if(instance==null) {
			instance = new AveriaStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Averia());
    for (int i=0;i<data.length;i++) {
      Averia temp = new Averia();
      temp.setid(data[i][0]);
      temp.setdescripcion(data[i][1]);
      temp.setfecha(data[i][2]);
      temp.setservicio(data[i][3]);
      store.put(temp.getid(),temp);
    }
  }
}
