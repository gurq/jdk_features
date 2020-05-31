package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TestTransferChannel {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\fromFile.txt");
//        RandomAccessFile fromFile = new RandomAccessFile("D:\\fromFile.txt", "rw");
        FileChannel fromChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\toFile.txt");
//        RandomAccessFile toFile = new RandomAccessFile("D:\\toFile.txt", "rw");
        FileChannel toChannel = fileOutputStream.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);

    }
}
