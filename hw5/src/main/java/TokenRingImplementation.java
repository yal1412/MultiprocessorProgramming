import java.util.List;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

public class TokenRingImplementation implements TokenRing{
    int size;
    List<TokenQueue> queues;
    List<Node> nodes;
    List<Thread> nodeThreads;

    public TokenRingImplementation(int size, List<TokenQueue> queues, List<Node> nodes, List<Thread> nodeThreads) {
        this.size = size;
        this.queues = queues;
        this.nodes = nodes;
        this.nodeThreads = nodeThreads;
    }

    public static TokenRingImplementation factory(List<TokenQueue> queues) {
        int count = queues.size();

        Consumer<Token> tokenConsumer = token -> {};

        List<Node> nodes = queues.stream()
                .map(Node::new)
                .peek(Node::getId)
                .collect(toList());

        List<Thread> nodeRoutineThreads = nodes.stream()
                .map(n -> {
                    int prevId = (nodes.indexOf(n) + 1) % count;
                    return new NodeExecution(n, queues.get(prevId));
                }).map(Thread::new)
                .collect(toList());
        return new TokenRingImplementation(count, queues, nodes, nodeRoutineThreads);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void start() {
        for (Thread thread : nodeThreads) {
            thread.start();
        }
    }

    @Override
    public void stop() {
        nodeThreads.stream()
                .peek(Thread::interrupt)
                .forEach(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
