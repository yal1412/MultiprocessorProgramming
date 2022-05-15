public class Node {
    private final TokenQueue queue;

    public Node(TokenQueue queue) {
        this.queue = queue;
    }

    public void receive(Token token) throws InterruptedException {
        queue.push(token);
    }

    public int getId() { return ThreadID.get(); }
}
