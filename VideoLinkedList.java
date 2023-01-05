public class VideoLinkedList {
    private VideoNode head = null;

    public void add(VideoNode videoNode) {
        if(head == null) {
            head = videoNode;
            return ;
        }
        VideoNode currentNode = head;
        while(currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(videoNode);
    }

    public void delete() {
    }

    public VideoNode get(int index) {
        VideoNode currentNode = head;
        while (index != 0 && --index != 0) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

}
