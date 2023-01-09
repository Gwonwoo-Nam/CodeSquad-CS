public class ControlUnit {
    private Register accumulator = new Register();

    public void setAccumulator(char value) {
        accumulator.set(value);
    }

}
