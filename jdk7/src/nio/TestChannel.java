package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestChannel {
    public static void main(String[] args) throws IOException {

        FileChannel channel = FileChannel.open(Paths.get("D:\\test.txt"),
                new StandardOpenOption[]{StandardOpenOption.READ});
//        RandomAccessFile fromFile = new RandomAccessFile("D:\\test.txt", "rw");


        ByteBuffer buffer = ByteBuffer.allocate(1);

        int reader = channel.read(buffer);

        while (reader != -1) {


            System.out.print("读到了：" + reader);

            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            System.out.println();

            buffer.clear();

            reader = channel.read(buffer);
        }

//        file.close();

    }
}
