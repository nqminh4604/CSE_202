import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class EITREHE1 {

    public static void main(String[] args) {
      
    }

    static byte[] inbuf = new byte[2 << 23];
    static int lenbuf = 0, ptrbuf = 0;
    static InputStream is = System.in;

    static boolean hasNext() throws IOException {
        if (skip() >= 0) {
            ptrbuf--;
            return true;
        }
        return false;
    }

    static String nextLine() throws IOException {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static String next() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                    // != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return inbuf[ptrbuf++];
    }

    static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    static double nextDouble() {
        return Double.parseDouble(next());
    }

    static Character nextChar() {
        return skip() >= 0 ? (char) skip() : null;
    }

    static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b))
            ;
        return b;
    }

    static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    static long nextLong() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
}
