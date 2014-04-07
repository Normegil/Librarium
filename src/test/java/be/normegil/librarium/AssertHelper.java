package be.normegil.librarium;

public class AssertHelper {

    public static void assertDifferentInstance(Object o1, Object o2){
        if(o1 == o2){
            throw new AssertionError("The two objects are the same instance");
        }
    }

}
