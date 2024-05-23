import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIPEOYMK {
    static InputReader rd;
    static StringBuilder sb = new StringBuilder();
    static Queue<Vertex> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        Vertex[] graph = readGraph();
        int u = rd.nextInt();
        int tc = rd.nextInt();
        if (bfsLevel(graph[u], rd.nextInt())) {
            sb.append("\n");
        } else {
            sb.append(-1 + "\n");
        }
        while (tc-- > 1) {
            
        }
        System.out.println(sb);
    }

    public static boolean bfsLevel(Vertex beginVertex, int level) {
        beginVertex.visit();
        beginVertex.setLevel(0);
        boolean flag = false;
        q.add(beginVertex);
        while (!q.isEmpty()) {
            Vertex v1 = q.poll();
            if (v1.level == level) {
                sb.append(v1.id + " ");
                flag = true;
            }
            int l = v1.adjList.size();
            for (int i = 0; i < l; i++) {
                Vertex v2 = v1.adjList.get(i);
                if (v2.visited == false) {
                    v2.visit();
                    v2.setLevel(v1.level + 1);
                    q.add(v2);
                }
            }
        }
        return flag;
    
    }
    public static boolean bfs(Vertex beginVertex, int level) {
        beginVertex.visit();
        beginVertex.setLevel(0);
        boolean flag = false;
        q.add(beginVertex);
        while (!q.isEmpty()) {
            Vertex v1 = q.poll();
            if (v1.level == level) {
                sb.append(v1.id + " ");
                flag = true;
            }
            int l = v1.adjList.size();
            for (int i = 0; i < l; i++) {
                Vertex v2 = v1.adjList.get(i);
                if (q.contains(v2)) {
                    v2.visit();
                    v2.setLevel(v1.level + 1);
                    q.add(v2);
                }
            }
        }
        return flag;
    }

    public static Vertex[] readGraph() {
        int nVertex = rd.nextInt();
        int nEdges = rd.nextInt();
        Vertex[] graph = new Vertex[nVertex];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vertex(i);
        }

        for (int i = 0; i < nEdges; i++) {
            Vertex v1 = graph[rd.nextInt()];
            Vertex v2 = graph[rd.nextInt()];

            v1.sort();
            v2.sort();

            v1.addAdjecentVertex(v2);
            v2.addAdjecentVertex(v1);
        }

        return graph;
    }

    static class Vertex {

        int id;
        int level;
        Boolean visited = false;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjecentVertex(Vertex v) {
            this.adjList.add(v);
        }

        public void visit() {
            this.visited = true;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void sort() {
            this.adjList.sort((v1, v2) -> {
                return v1.id - v2.id;
            });
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
