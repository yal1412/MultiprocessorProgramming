import java.util.concurrent.BlockingQueue;

public class TokenQueue {
    private final BlockingQueue<Token> blockingQueue;

    public TokenQueue(BlockingQueue<Token> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void push(Token token) throws InterruptedException {
        blockingQueue.put(token);
    }

    public Token poll() throws InterruptedException {
        return blockingQueue.take();
    }
}
