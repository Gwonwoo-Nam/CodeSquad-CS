public class Elements <K>{
    public K element;
    public Integer count;

    public Elements (K element, Integer count) {
        this.element = element;
        this.count = count;
    }
    public String toStr() {
        return element.toString() + " : " + count.toString();
    }

}
