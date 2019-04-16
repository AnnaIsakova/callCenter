import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CallCenter {

    static Worker secretary1 = new Worker("Sec1", Worker.Type.SECRETARY);
    static Worker secretary2 = new Worker("Sec2", Worker.Type.SECRETARY);
    static Worker manager1 = new Worker("Man1", Worker.Type.MANAGER);
    static Worker manager2 = new Worker("Man2", Worker.Type.MANAGER);
    static Worker dir1 = new Worker("Dir1", Worker.Type.DIRECTOR);
    static Worker dir2 = new Worker("Dir2", Worker.Type.DIRECTOR);

    static List<Worker> freeSecretaries = new LinkedList<>(Arrays.asList(secretary1));
    static List<Worker> freeManagers = new LinkedList<>(Arrays.asList(manager1));
    static List<Worker> freeDirs = new LinkedList<>(Arrays.asList(dir1));

    static List<Worker> busySecretaries = new LinkedList<>();
    static List<Worker> busyManagers = new LinkedList<>();
    static List<Worker> busyDirs = new LinkedList<>();

    static Queue<Call> calls = new PriorityQueue<>();

    //args:
    // -q Text -> create call with text
    // -e workerType index -> end call for worker type at index
    // -exit -> exit
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            printFreeWorkers();

            System.out.println();

            System.out.print("Enter command : ");
            String input = scanner.nextLine();

            if ("-exit".equals(input)) {
                System.out.println("Exit!");
                break;
            } else if (input.startsWith("-q")) {
                Call call = new Call(input.substring(3));
                processCall(call);
            }
            else if (input.startsWith("-e")) {
                String[] attrs = input.split(" ");
                if (Worker.Type.fromValue(attrs[1]).equals(Worker.Type.SECRETARY)) {
                    endCall(busySecretaries.get(Integer.parseInt(attrs[2])));
                } else if (Worker.Type.fromValue(attrs[1]).equals(Worker.Type.MANAGER)) {
                    endCall(busyManagers.get(Integer.parseInt(attrs[2])));
                } else if (Worker.Type.fromValue(attrs[1]).equals(Worker.Type.DIRECTOR)) {
                    endCall(busyDirs.get(Integer.parseInt(attrs[2])));
                }
            }

            printBusyWorkers();
            System.out.println();
            System.out.println("Calls in queue: " + calls);
            System.out.println("==============================================================");
        }

        scanner.close();
    }

    static void processCall(Call call){
        if (freeSecretaries.size() > 0){
            answer(call, freeSecretaries, busySecretaries);
        } else if (freeManagers.size() > 0) {
            answer(call, freeManagers, busyManagers);
        } else if (freeDirs.size() > 0) {
            answer(call, freeDirs, busyDirs);
        } else {
            calls.add(call);
        }
    }

    static void answer(Call call, List<Worker> freeWorkers, List<Worker> busyWorkers){
        freeWorkers.get(0).answerCall(call);
        busyWorkers.add(freeWorkers.get(0));
        freeWorkers.remove(0);
    }

    static void endCall(Worker worker){
        if (worker.type.equals(Worker.Type.SECRETARY)) {
            worker.endCall(() -> {
                freeSecretaries.add(worker);
                busySecretaries.remove(worker);
            });
        } else if (worker.type.equals(Worker.Type.MANAGER)) {
            worker.endCall(() -> {
                freeManagers.add(worker);
                busyManagers.remove(worker);
            });
        } else if (worker.type.equals(Worker.Type.DIRECTOR)) {
            worker.endCall(() -> {
                freeDirs.add(worker);
                busyDirs.remove(worker);
            });
        }
        if (calls.size() > 0) {
            processCall(calls.poll());
        }
    }

    static void printFreeWorkers(){
        System.out.println("Free secretaries: " + freeSecretaries);
        System.out.println("Free managers: " + freeManagers);
        System.out.println("Free dirs: " + freeDirs);
    }

    static void printBusyWorkers(){
        System.out.println("Busy secretaries: " + busySecretaries);
        System.out.println("Busy managers: " + busyManagers);
        System.out.println("Busy dirs: " + busyDirs);
    }
}
