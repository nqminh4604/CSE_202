    import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIUMLMK2 {
    static InputReader reader;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        reader = new InputReader(System.in);
        Vertex[] g = readGraph();
        long orgPrice = reader.nextLong();
        DFS(g[0], orgPrice);

        for (Vertex vertex : g) {
            sb.append(vertex.consumingPro).append(" ");
        }

        System.out.println(sb);
    }

    public static void DFS(Vertex input, long price) {
        input.visited = true;
        if (input.limitPrice >= price) {
            input.consumingPro++;
        } else {
            return;
        }
        for (Vertex adjVertex : input.adjList) {
            if (adjVertex.visited == false) {
                if (adjVertex.limitPrice < (long) (price * 1.1)) {
                    count = 0;
                    countOddPro(adjVertex);
                    input.consumingPro += count;
                } else {
                    DFS(adjVertex, (long) (price * 1.1));
                }
            }
        }
    }

    static int count = 0;
    public static void countOddPro(Vertex inputVertex) {
        inputVertex.visited = true;
        count++;
        for (Vertex adjV : inputVertex.adjList) {
            if (adjV.visited == false) {
                countOddPro(adjV);
            }
        }
    }

    public static Vertex[] readGraph() {
        int numV = reader.nextInt();
        int numE = numV - 1;
        Vertex[] g = new Vertex[numV];

        for (int i = 0; i < numV; i++) {
            g[i] = new Vertex(i);
        }

        for (int i = 0; i < numE; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();

            g[a].addAdj(g[b]);
            g[b].addAdj(g[a]);
        }

        for (Vertex vertex : g) {
            vertex.setLimitPrice(reader.nextLong());
        }

        return g;
    }

    static class Vertex {

        int id;
        boolean visited = false;
        long limitPrice;
        int consumingPro = 0;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdj(Vertex v) {
            this.adjList.add(v);
        }

        public void setLimitPrice(long limitPrice) {
            this.limitPrice = limitPrice;
        }

    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
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

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        @SuppressWarnings("unused")
        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
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

        public long nextLong() {
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
}
