package mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IndirectMapByClass {
    private Map<Class<?>, List<Object>> m_mapByClass = new HashMap<>();
    private Map<Class<?>, Class<?>> m_mapListClasses = new HashMap<>();

    public <K, T> void addObject(Class<K> keyClass, T toAdd, Class<T> addedClass) {
        if (m_mapByClass.containsKey(keyClass)) {

            if (m_mapListClasses.get(keyClass) != addedClass) {
                // AssertUtils ---> here
                System.out.println("ERRORR YOU FOOOOOL don't mix types in list ...");
            }

            m_mapByClass.get(keyClass).add(toAdd);
        } else {
            m_mapListClasses.put(keyClass, addedClass);

            List<Object> newList = new ArrayList<>();
            newList.add(toAdd);
            m_mapByClass.put(keyClass, newList);
        }
    }

    @SuppressWarnings("unchecked")
    private <K, T> List<K> extractList(Class<T> keyClass) {
        if (m_mapByClass.containsKey(keyClass)) {
            List<Object> returns = m_mapByClass.get(keyClass);
            Class<?> targetClass = m_mapListClasses.get(keyClass);
            List<K> targetList = new ArrayList<>();
            returns.forEach(x -> targetList.add((K) targetClass.cast(x)));
            return targetList;
        }
        return new ArrayList<>();
    }

    public <K, T> List<K> get(Class<T> keyClass) {
        return extractList(keyClass);
    }

    @SuppressWarnings("unchecked")
    public <K, T> K getLast(Class<T> keyClass) {
        return (K) extractList(keyClass).get(0);
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
