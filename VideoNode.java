public class VideoNode {

    private static int nodeCounter = 1;
    private String id; // 고유한 id 값(8자리)
    private String name; //제목 문구(8자리)
    private int playTime; //영상 재생 시간(8자리 이내 정수형, 초단위)
    private VideoNode next = null; //다음 영상 정보에 대한 연결

    public VideoNode() {
        setId();
        setRandomPlayTime();
        setName();

    }
    private void setId() { //Random 방식으로 id 할당, (a~f 중)4개의 random을 뽑고 중복이 없으면 return
        while (true) {
            StringBuffer randomID = new StringBuffer();
            for (int i =0; i<4;i++) {
                randomID.append((char)(Math.random()*6 + 'a'));
            }
            if (!VideoRepository.getIdList().contains(randomID)) {
                id = randomID.toString();
                VideoRepository.addIdList(id);
                break ;
            }
        }
    }

    private void setRandomPlayTime() {
        this.playTime = (int)(Math.random()*15.0 + 1);
    }

    private void setName() {
        this.name = "제목"+VideoNode.nodeCounter;
        nodeCounter++;
    }

    public void setNext(VideoNode videoNode) {
        this.next = videoNode;
    }

    public String getName() {
        return name;
    }

    public int getPlayTime() {
        return playTime;
    }

    public String getId() {
        return id;
    }

    public VideoNode getNext() {
        return next;
    }
}
