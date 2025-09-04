class Model implements Cloneable {
    String name;
    Model(String n) { name = n; }
    public Object clone() throws CloneNotSupportedException { return super.clone(); }
}

public class ClonePrototype {
    public static void main(String[] args) throws Exception {
        Model m1 = new Model("Prototype");
        Model m2 = (Model) m1.clone();
        System.out.println(m1.name);
        System.out.println(m2.name);
    }
}
