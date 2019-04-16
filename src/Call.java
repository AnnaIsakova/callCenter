import java.util.Objects;

public class Call implements Comparable{

    String text;

    public Call(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(o.hashCode(), this.hashCode());
    }

    @Override
    public String toString() {
        return "Call{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return Objects.equals(text, call.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
