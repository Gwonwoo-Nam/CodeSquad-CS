public class View {
    
    public static String printAddress(int address) {
        int base = address;
        int offset = address % 16;
        return (toHex(base).append(" + ").append(toHex(offset))).toString();
    }

    private static StringBuffer toHex(int base) {
        StringBuffer hex = new StringBuffer(Integer.toHexString(base));
        while (hex.length() != 4) {
            hex.insert(0,"0");
        }
        hex.insert(0,"0x");
        return hex;
    }

}
