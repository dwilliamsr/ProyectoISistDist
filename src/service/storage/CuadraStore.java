package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Cuadra;
public class CuadraStore {
private static String[][] data = {
{"1","Cuadra 001","1"}};

private static Map<String,Cuadra> store;
	private static CuadraStore instance = null;
	private CuadraStore() {
		store = new HashMap<String,Cuadra>();
		initStore();
	}
	public static Map<String,Cuadra> getStore() {
		if(instance==null) {
			instance = new CuadraStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Cuadra());
    for (int i=0;i<data.length;i++) {
      Cuadra temp = new Cuadra();
      temp.setid(data[i][0]);
      temp.setnombre(data[i][1]);
      temp.setregion(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
