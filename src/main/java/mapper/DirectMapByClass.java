package mapper;

import java.util.*;
import java.util.stream.Collectors;

public class DirectMapByClass {
    private Map<Class<?>, List<Object>> m_mapByClass = new HashMap<>();

    public <T> void addObject(Class<T> addedClass, T toAdd) {
        if (m_mapByClass.containsKey(addedClass)) {
            m_mapByClass.get(addedClass).add(toAdd);
        } else {
            List<Object> newList = new ArrayList<>();
            newList.add(toAdd);
            m_mapByClass.put(addedClass, newList);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> extractList(Class<T> keyClass) {
        return (List<T>) m_mapByClass.getOrDefault(keyClass, new ArrayList<>());
    }

    public <T> List<T> get(Class<T> keyClass) {
        return extractList(keyClass);
    }

    public <T> T getLast(Class<T> keyClass) {
        return extractList(keyClass).get(0);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("\n\n-----------\nAll MapByClass : \n");
        m_mapByClass.forEach((k, v) -> {
            str.append(k.getSimpleName()).append(" => ");
            String list = v.stream().map(Object::toString).collect(Collectors.joining(", "));
            str.append(list);
        });
        str.append("\n-----------------------\n\n\n");
        return str.toString();
    }
}
