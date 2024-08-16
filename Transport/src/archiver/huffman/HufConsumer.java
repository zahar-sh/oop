package archiver.huffman;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

interface HufConsumer extends Consumer<NodeBase>, IntConsumer {
}
