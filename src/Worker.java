public class Worker {

    String name;
    Type type;
    boolean busy;
    Call call;

    public Worker(String name, Type type) {
        this.name = name;
        this.type = type;
        this.busy = false;
    }

    void answerCall(Call call) {
        this.call = call;
        this.busy = true;
        System.out.println(this.name + " is answering the call \"" + call.text + "\"");
    }

    void endCall() {
        System.out.println(this.name + " isEnding the call \"" + this.call.text + "\"");
        this.busy = false;
    }

    enum Type {
        SECRETARY,
        MANAGER,
        DIRECTOR
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                '}';
    }
}
