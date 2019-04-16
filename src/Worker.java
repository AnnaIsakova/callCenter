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

    void endCall(Runnable runnable) {
        System.out.println(this.name + " isEnding the call \"" + this.call.text + "\"");
        this.busy = false;
        runnable.run();
    }

    enum Type {
        SECRETARY ("s"),
        MANAGER ("m"),
        DIRECTOR ("d");

        String value;

        Type(String v) {
            value = v;
        }

        public static Type fromValue(String v) {
            for (Type c : Type.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                '}';
    }
}
