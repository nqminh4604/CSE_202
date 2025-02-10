import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Set;
import java.lang.StringBuilder;
import java.util.TreeSet;

class EICONP3 {

    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    static int smallestV = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        Vertex[] graph = readGraph();
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].visited == false) {
                int nV = dfs(graph[i], 0);
                sb.append(smallestV + " " + nV + " \n");
                smallestV = Integer.MAX_VALUE;
            }
        }
        System.out.println(sb);
    }

    public static int dfs(Vertex v, int nVertex) {
        v.visited();
        if (smallestV > v.id) {
            smallestV = v.id;
        }
        nVertex++;
        for (Vertex vertex : v.adjSet) {
            if (vertex.visited == false) {
                nVertex = dfs(vertex, nVertex);
            }
        }
        return nVertex;
    }

    public static Vertex[] readGraph() {
        int nVertex = rd.nextInt();
        int nEdge = rd.nextInt();
        Vertex[] graph = new Vertex[nVertex];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdge; i++) {
            Vertex a = graph[rd.nextInt()];
            Vertex b = graph[rd.nextInt()];

            a.addAdj(b);
            b.addAdj(a);
        }
        return graph;
    }

    static class Vertex {

        int id;
        Boolean visited = false;
        Set<Vertex> adjSet = new TreeSet<>((v1, v2) -> {
            return v1.id - v2.id;
        });

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdj(Vertex v) {
            this.adjSet.add(v);
        }

        public void visited() {
            this.visited = true;
        }
    }

    @SuppressWarnings("unused")
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
