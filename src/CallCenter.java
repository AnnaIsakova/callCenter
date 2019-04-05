import java.util.*;

public class CallCenter {

    static Worker secretary1 = new Worker("Sec1", Worker.Type.SECRETARY);
    static Worker secretary2 = new Worker("Sec2", Worker.Type.SECRETARY);
    static Worker manager1 = new Worker("Man1", Worker.Type.MANAGER);
    static Worker manager2 = new Worker("Man2", Worker.Type.MANAGER);
    static Worker dir1 = new Worker("Dir1", Worker.Type.DIRECTOR);
    static Worker dir2 = new Worker("Dir2", Worker.Type.DIRECTOR);

    static List<Worker> freeSecretaries = new LinkedList<>(Arrays.asList(secretary1, secretary2));
    static List<Worker> freeManagers = new LinkedList<>(Arrays.asList(manager1, manager2));
    static List<Worker> freeDirs = new LinkedList<>(Arrays.asList(dir1, dir2));

    static Queue<Call> calls = new PriorityQueue<>();

    public static void main(String[] args) {

        System.out.println(freeSecretaries);
        System.out.println(freeManagers);
        System.out.println(freeDirs);

        Call call = new Call("Where is my avocado?");
        processCall(call);

        System.out.println(freeSecretaries);
        System.out.println(freeManagers);
        System.out.println(freeDirs);

        secretary1.endCall(() -> freeSecretaries.add(secretary1));

        System.out.println(freeSecretaries);
        System.out.println(freeManagers);
        System.out.println(freeDirs);
    }

    static void processCall(Call call){
        if (freeSecretaries.size() > 0){
            freeSecretaries.get(0).answerCall(call);
            freeSecretaries.remove(0);
        } else if (freeManagers.size() > 0) {
            freeManagers.get(0).answerCall(call);
            freeManagers.remove(0);
        } else if (freeDirs.size() > 0) {
            freeDirs.get(0).answerCall(call);
            freeDirs.remove(0);
        } else {
            calls.add(call);
        }
    }

}
