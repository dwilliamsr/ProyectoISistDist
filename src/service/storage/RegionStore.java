package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Region;
public class RegionStore {
private static String[][] data = {
{"1","Encargado de Region 01","1"}};

private static Map<String,Region> store;
	private static RegionStore instance = null;
	private RegionStore() {
		store = new HashMap<String,Region>();
		initStore();
	}
	public static Map<String,Region> getStore() {
		if(instance==null) {
			instance = new RegionStore();
		}
		return store;
	}
	private void initStore() {
    store.put("0",new Region());
    for (int i=0;i<data.length;i++) {
      Region temp = new Region();
      temp.setid(data[i][0]);
      temp.setencargado(data[i][1]);
      temp.setsucursal(data[i][2]);
      store.put(temp.getid(),temp);
    }
  }
}
