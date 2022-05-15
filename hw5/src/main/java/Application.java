import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.Thread.sleep;

public class Application {
    public static List<Token> createNMessages(int N) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokens.add(new Token(i));
        }
        return tokens;
    }

    public static List<TokenQueue> createNQueues(int N, List<Token> tokens) throws InterruptedException {
        List<TokenQueue> queues = new ArrayList<>();
        TokenQueue q1 = new TokenQueue(new ArrayBlockingQueue<>(100));
        for (Token token : tokens) {
            q1.push(token);
        }
        queues.add(q1);
        for (int i = 1; i < N; i++) {
            queues.add(new TokenQueue(new ArrayBlockingQueue<>(100)));
        }
        return queues;
    }

    public static void main(String[] args) throws InterruptedException {

        List<Token> messages = createNMessages(1);
        List<TokenQueue> queues = createNQueues(20, messages);
        TokenRingImplementation t = TokenRingImplementation.factory(queues);
        t.start();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            t.stop();
        }
    }
}
