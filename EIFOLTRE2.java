import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class EIFOLTRE2 {

    static InputReader rd;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        rd = new InputReader(System.in);
        Map<String, Vertex> tree = readGraph();
        String name = rd.next();
        Vertex v = tree.get(name);
        v.setRank(0);
        dfs(v);
        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.visit();
        addTree(v.rank);
        sb.append(v.name + "\n");
        for (int i = 0; i < v.adjList.size(); i++) {
            Vertex vertex = v.adjList.get(i);
            if (vertex.visited == false) { 
                vertex.setRank(v.rank + 1);
                dfs(vertex);
            }
        }
    }

    public static void addTree(int rank) {
        for (int i = 0; i < rank; i++) {
            sb.append("   ");
        }
        sb.append("├---");
    }

    public static HashMap<String, Vertex> readGraph() {
        int nVertex = rd.nextInt();
        HashMap<String, Vertex> graph = new HashMap<>();
        for (int i = 0; i < nVertex - 1; i++) {
            String name1 = rd.next();
            String name2 = rd.next();

            Vertex a = graph.getOrDefault(name1, new Vertex(name1));
            Vertex b = graph.getOrDefault(name2, new Vertex(name2));

            a.addAdjecentVertex(b);
            b.addAdjecentVertex(a);
            
            a.sort();
            b.sort();

            graph.put(name1, a);
            graph.put(name2, b);
        }

        return graph;
    }

    static class Vertex {

        String name;
        int rank;
        Boolean visited = false;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(String name) {
            this.name = name;
        }

        public void addAdjecentVertex(Vertex v) {
            this.adjList.add(v);
        }

        public void visit() {
            this.visited = true;
        }

        public void setRank(int i) {
            this.rank = i;
        }

        public void sort() {
            this.adjList.sort((v1, v2) -> {
                return v1.name.compareToIgnoreCase(v2.name);
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
