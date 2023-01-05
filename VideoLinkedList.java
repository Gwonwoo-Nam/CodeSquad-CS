public class VideoLinkedList {
    private VideoNode head = null;

    public void add(VideoNode videoNode) {
        if (addHead(videoNode))
            return;
        VideoNode currentNode = head;
        while(currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(videoNode);
    }

    private boolean addHead(VideoNode videoNode) {
        if(head == null) {
            head = videoNode;
            return true;
        }
        return false;
    }

    public void insert(VideoNode videoNode, int index) {
        if(addHead(videoNode)) { //빈 리스트인 경우
            return ;
        }
        VideoNode currentNode = head;
        if (index == 0) {
            videoNode.setNext(currentNode);
            head = videoNode;
            return ;
        }
        while(--index!=0 && currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        videoNode.setNext(currentNode.getNext());
        currentNode.setNext(videoNode);
    }

    public void delete(VideoNode videoNode) {
        hasNothing();
        VideoNode currentNode = head;
        String deleteNodeId = videoNode.getId();
        if (deleteNodeId.equals(head.getId())) {
            head = currentNode.getNext();
            return ;
        }
        while(currentNode.getNext() != null) {
            String nextNodeId = currentNode.getNext().getId();
            if (nextNodeId.equals(videoNode.getId())) {//다음 노드가 삭제하려는 노드의 이름과 같다면
                currentNode.setNext(currentNode.getNext().getNext());//다다음 노드를 다음으로 설정
                return ;
            }
            currentNode = currentNode.getNext();
        }
        throw new IllegalArgumentException("node not found");
    }

    private void hasNothing() {
        if (head == null) { //head가 null이면 종료
            throw new IllegalArgumentException("node not found");
        }
    }

    public void render() {
        int numberOfClip = 0;
        int totalPlayTime = 0;
        VideoNode currentNode = head;
        if (head != null) {
            numberOfClip++;
            totalPlayTime += head.getPlayTime();
        }
        while(currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            numberOfClip++;
            totalPlayTime += currentNode.getPlayTime();
        }
        System.out.println("영상클립: "+numberOfClip+"개");
        System.out.println("전체길이: "+totalPlayTime+"sec");
    }

    public VideoNode get(int index) {
        VideoNode currentNode = head;
        while (index != 0 && --index != 0) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

}
