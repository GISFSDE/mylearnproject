package pers.lxl.mylearnproject.frame.spring.ioc_di.pojo;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*装配一些复杂的东西，比如 Set、Map、List、Array 和 Properties 等*/
public class ComplexAssembly {
    private Long id;
    private List<String> list;
    private Map<String, String> map;
    private Properties properties;
    private Set<String> set;
    private String[] array;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
