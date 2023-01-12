public class Heap {

    private byte[] heap; //4Byte Pointer + 1,2,4,8,16,32Byte Type
    public int pointer = 0;

    public Heap(int size) {
        heap = new byte[size];
    }

    public int add(String type, int count) {
        int typeSize = UserTypes.getSize(type);
        if (typeSize == 0) {
            UserTypes.add(type, 4);
        }
        int objSize = typeSize*count;
        while (count-- > 0) {
            if (typeSize != 8) {
                for (int offset = typeSize; offset < 8; offset++) {
                    heap[pointer + offset] = 2; //패딩 넣어주기
                }
            }
            for (int offset = 0; offset < typeSize; offset++) {
                heap[pointer + offset] = 1; //type 값 넣어주기
            }
            pointer += 8;
        }
        return objSize;
    }


}
