public class Alu {
    public int add(int a, int b) { //나중에 Gate를 이용해 재구현
        return a+b;
    }
    public int sub(int a, int b) {
        return a-b;
    }
    public int and(int a, int b) {
        return a&b;
    }
    public int or(int a, int b) {
        return a|b;
    }
}
