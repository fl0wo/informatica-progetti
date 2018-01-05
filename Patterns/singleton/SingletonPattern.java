package singleton;


// Singleton

public class SingletonPattern {
    public static void main(String[] args)  {
        Singleton s1 = Singleton.getReference();
        System.out.println(s1.getValue());
        Singleton s2 = Singleton.getReference();
        s2.setValue(3);
        System.out.println(s1.getValue());
        try {
            // Singleton s3 = (Singleton)s2.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
