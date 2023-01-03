import java.util.Arrays;

class TestApplication {
    static int dec2binTest1 = 10;
    static int dec2binTest2 = 173;
    static boolean[] dec2binTestAnswer1 = {false, true, false, true};
    static boolean[] dec2binTestAnswer2 = {true, false, true, true, false, true, false, true};
    static boolean[] bin2decTest1 = {false, true, true, true};
    static boolean[] bin2decTest2 = {true, true, true, true, false, true, false, true};
    static int bin2decTestAnswer1 = 14;
    static int bin2decTestAnswer2 = 175;

    public static void main(String[] args) {
        Convertor convertor = new Convertor();

        boolean[] dec2binMyAnswer1 = convertor.dec2bin(dec2binTest1);
        boolean[] dec2binMyAnswer2 = convertor.dec2bin(dec2binTest2);
        convertor.printBin(dec2binMyAnswer1);
        convertor.printBin(dec2binTestAnswer1);
        convertor.printBin(dec2binMyAnswer2);
        convertor.printBin(dec2binTestAnswer2);

        int bin2decMyAnswer1 = convertor.bin2dec(bin2decTest1);
        int bin2decMyAnswer2 = convertor.bin2dec(bin2decTest2);
        convertor.printDec(bin2decMyAnswer1);
        convertor.printDec(bin2decTestAnswer1);
        convertor.printDec(bin2decMyAnswer2);
        convertor.printDec(bin2decTestAnswer2);

        boolean[] a = convertor.dec2bin(91);//{true, true, false, true, true, false, true, false}
        boolean[] b = convertor.dec2bin(205);//{true, false, true, true, false, false, true, true}
        convertor.printBin(convertor.sumBinary(a, b));
        System.out.println(convertor.bin2dec(convertor.sumBinary(a, b)));

        ClassicConvertor classicConvertor = new ClassicConvertor();
        convertor.printBin(classicConvertor.sumBinary(a,b));

        boolean[] c = convertor.dec2bin(33);
        System.out.println(convertor.bin2Byte(b));

    }


}
