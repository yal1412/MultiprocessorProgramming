public class NodeExecution implements Runnable {
    private final Node node;
    private final TokenQueue queue;

    public NodeExecution(Node node, TokenQueue queue) {
        this.node = node;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
        }
    }

    private void work() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            Token token = queue.poll();
            node.receive(token);
        }
    }
}
