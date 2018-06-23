import mapper.DirectMapByClass;
import mapper.IndirectMapByClass;

public class ClassMappersTestMain {
    public static void main(String[] args) {
        System.out.println("Hi, it's on ...");

        DirectMapByClass map = new DirectMapByClass();

        map.addObject(String.class, "hi");
        map.addObject(String.class, "hello");
        map.addObject(String.class, "buenas dias");

        System.out.println(map.toString());

        System.out.println("Getters: " + map.get(String.class).get(2));

        map.addObject(Integer.class, -43);
        map.addObject(Integer.class, 7777);

        System.out.println(map.toString());

        System.out.println("Getters: " + map.get(String.class).get(2));
        System.out.println("Getters: " + map.get(Integer.class).get(0));

        System.out.println("\nLast          "+map.getLast(String.class));
        System.out.println("\nLast          "+map.getLast(Integer.class));


        System.out.println("###\n###\n###\n###\n");


        IndirectMapByClass map2 = new IndirectMapByClass();

        map2.addObject(Long.class, "hi", String.class);
        map2.addObject(Long.class, "hello", String.class);
        map2.addObject(Long.class, "buenas dias", String.class);

        System.out.println(map2.toString());

        System.out.println("Getters: " + map2.get(Long.class).get(2));

        map2.addObject(Double.class, -43, Integer.class);
        map2.addObject(Double.class, 7777, Integer.class);

        System.out.println(map2.toString());

        System.out.println("Getters: " + map2.get(Long.class).get(2));
        System.out.println("Getters: " + map2.get(Double.class).get(0));

        System.out.println("\nLast          "+map2.getLast(Long.class));
        System.out.println("\nLast          "+map2.getLast(Double.class));

        System.out.println("\nTargetClass   "+map2.getLast(Long.class).getClass().getSimpleName());
        System.out.println("\nTargetClass   "+map2.getLast(Double.class).getClass().getSimpleName());



        System.out.println("Trying to mix types in a list");
        map2.addObject(Double.class,  new StringBuilder("!"), StringBuilder.class);
    }
}
