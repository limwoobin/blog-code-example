package immutableObject;

public class Test {

    public static void main(String[] args) {
        ImmutableObj o1 = new ImmutableObj(5);
        ImmutableObj o2 = new ImmutableObj(5);

        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
    }
}
