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
        Vertex root = tree.get(rd.next());
        root.setLast();
        root.setRank(0);
        dfs(root);
        System.out.println(sb);
    }

    public static void dfs(Vertex root) {
        root.setVisited();
        if (root.rank > 0) {
            draw(root);
        }
        sb.append(root.name).append("\n");

        for (Vertex adj : root.adjList) {
            if (adj.visited == false) {
                adj.setVisited();
                root.check.add(adj);
            }
        }

        int size = root.check.size();
        for (int i = 0; i < size; i++) {
            Vertex check = root.check.get(i);
                check.setRank(root.rank + 1);
                if (root.isLast == false) {
                    check.setPreFile(root);
                }
                if (i == size - 1) {
                    check.setLast();
                }
                dfs(check);
        }
    }

    public static void draw(Vertex v) {
        Stack<Integer> stack = new Stack<>();
        Vertex pre = v.preFile;
        while (pre != null) {
            stack.add(pre.rank);
            pre = pre.preFile;
        }

        int temp = 0;
        while (!stack.isEmpty()) {
            int save = temp;
            temp = stack.pop() - save;
            drawSpace(temp++);
            sb.append("│   ");
        }

        drawSpace(v.rank - temp);

        if (v.isLast) {
            sb.append("└───");
        } else {
            sb.append("├───");
        }
    }

    public static void drawSpace(int rank) {
        if (rank <= 1) {
            return;
        }
        for (int i = 0; i < rank - 1; i++) {
            sb.append("    ");
        }
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
        Vertex preFile;
        boolean visited = false;
        boolean isLast = false;
        List<Vertex> adjList = new ArrayList<>();
        List<Vertex> check = new ArrayList<>();

        public Vertex(String name) {
            this.name = name;
        }

        public void setPreFile(Vertex preFile) {
            this.preFile = preFile;
        }

        public void setLast() {
            this.isLast = true;
        }

        public void addAdjecentVertex(Vertex v) {
            this.adjList.add(v);
        }

        public void setVisited() {
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
