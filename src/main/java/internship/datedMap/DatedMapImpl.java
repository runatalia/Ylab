package internship.datedMap;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class DatedMapImpl implements DatedMap {
  private Map<String, String> map = new HashMap<>();
  private Map<String, Date> dateMap = new WeakHashMap<>();

  @Override
  public void put(String key, String value) {
    map.put(key, value);
    dateMap.put(key, new Date());
  }

  @Override
  public String get(String key) {
    if (!map.containsKey(key)) {
      return null;
    } else {
      return map.get(key);
    }
  }

  @Override
  public boolean containsKey(String key) {
    return map.containsKey(key);
  }

  @Override
  public void remove(String key) {
    dateMap.remove(key);
    map.remove(key);
  }

  @Override
  public Set<String> keySet() {
    return map.keySet();
  }

  @Override
  public Date getKeyLastInsertionDate(String key) {
    if (!dateMap.containsKey(key)) {
      return null;
    } else {
      return dateMap.get(key);
    }
  }
}
