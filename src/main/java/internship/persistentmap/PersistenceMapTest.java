package io.ylab.intensive.lesson04.persistentmap;

import io.ylab.intensive.lesson04.DbUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


public class PersistenceMapTest {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDb();
        PersistentMap persistentMap = new PersistentMapImpl(dataSource);
        persistentMap.init("IT");
        persistentMap.put("Valeria", "middle");
        persistentMap.put("Aleksey", "junior");
        persistentMap.put("Aleksey", "middle");
        persistentMap.put("Vladimir", "junior");
        System.out.println("ключ Aleksey в базе данных: " + persistentMap.containsKey("Aleksey"));
        System.out.println("ключ Vasiliy в базе данных: " + persistentMap.containsKey("Vasiliy"));
        List<String> keys = persistentMap.getKeys();
        System.out.println("-----------------------------------------------");
        System.out.println("Все ключи из БД: ");
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Значение по ключу Aleksey: " + persistentMap.get("Aleksey"));
        System.out.println("Значение по ключу Maria: " + persistentMap.get("Maria"));
        persistentMap.remove("Aleksey");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Создание новой мапы");
        PersistentMap persistentMapSecond = new PersistentMapImpl(dataSource);
        persistentMapSecond.init("Design");
        persistentMapSecond.put("Olesya", "junior");
        persistentMapSecond.put("Olesya", "middle");
        persistentMapSecond.put("Olesya", "senior");
        persistentMapSecond.put("Vladimir", "junior");
        persistentMapSecond.put("Konstantin", "middle");
        System.out.println("ключ Olesya в базе данных: " + persistentMapSecond.containsKey("Olesya"));
        System.out.println("ключ Vasiliy в базе данных: " + persistentMapSecond.containsKey("Vasiliy"));
        List<String> keysSecond = persistentMapSecond.getKeys();
        System.out.println("-----------------------------------------------");
        System.out.println("Все ключи из БД: ");
        for (String key : keysSecond) {
            System.out.println(key);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Значение по ключу Vladimir: " + persistentMapSecond.get("Vladimir"));
        System.out.println("Значение по ключу Maria: " + persistentMapSecond.get("Maria"));
        persistentMapSecond.remove("Vladimir");
        persistentMap.clear();
    }

    public static DataSource initDb() throws SQLException {
        String createMapTable = ""
                + "drop table if exists persistent_map; "
                + "CREATE TABLE if not exists persistent_map (\n"
                + "   map_name varchar,\n"
                + "   KEY varchar,\n"
                + "   value varchar\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMapTable, dataSource);
        return dataSource;
    }
}
