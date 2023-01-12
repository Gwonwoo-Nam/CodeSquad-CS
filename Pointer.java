public class Pointer {

    /**
     * 4Byte이며, heap의 객체(Variable)를 참조, value는 stack의 address를 갖는다.
     */

    public Pointer (Variable variable, int pointingAddr) {
        this.variable = variable;
        this.pointingAddr = pointingAddr;
    }

    public Pointer (String callStackName) {
        this.callStackName = callStackName;
        this.call = true;
    }

    public int pointingAddr; //heap 변수의 주소 값을 가지고 있다.
    public int size = 4;
    public Variable variable; //pointing object
    public String callStackName = "";

    public boolean call = false;


}
