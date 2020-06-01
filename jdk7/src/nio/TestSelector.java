package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class TestSelector {
    public static void main(String[] args) throws IOException {
        int[] portArr = {80, 443};
        Selector selector = Selector.open();
//        FileChannel fileChannel = FileChannel.open();
        for (int port : portArr) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ServerSocket ss = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("开始监听端口：" + port);
        }

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isAcceptable()) {
                    System.out.println("isAcceptable");
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(key.selector(), SelectionKey.OP_READ);
                } else if (key.isConnectable()) {
                    System.out.println("isConnectable");
                } else if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    clientChannel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()));
                } else if (key.isWritable()) {
                    System.out.println("isWritable");
                }
            }
        }
    }
}
