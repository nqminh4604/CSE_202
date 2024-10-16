import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class SPANNINGTREE_PRIM {
    static InputReader reader;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        reader = new InputReader(System.in);
        nodes = readGraph();
        prim();
        long sum = 0;
        for (int i = 1; i < nodes.length; i++) {
            Node n = nodes[i];
            if (n.prevNode != null) {
                sum += n.distance;
            } else {
                sum = -1;
                break;
            }
        }
        System.out.println(sum);
    }

    public static void prim() {
        Queue<Edge> edgeQ = new PriorityQueue<>((e1, e2) -> Long.compare(e1.weight, e2.weight));
        Queue<Node> nodeQ = new PriorityQueue<>((n1, n2) -> Long.compare(n1.distance, n2.distance));

        int count = 0;
        nodes[0].distance = 0;
        nodes[0].visited = true;
        for (int i = 0; i < nodes.length; i++) {
            nodeQ.add(nodes[i]);
        }
        
        while (count < nodes.length || !nodeQ.isEmpty()) {
            Node curNode = nodeQ.poll();
            if (curNode == null) {
                return;
            }
            for (Edge edge : curNode.adjNodes) {
                if (nodes[edge.next.id].visited == false) {
                    edgeQ.add(edge);
                }
            }
            if (edgeQ.isEmpty()) {
                break;
            }
            Edge selectedEdge = edgeQ.poll();
            int id = selectedEdge.next.id;
            if (nodes[id].distance > selectedEdge.weight) {
                nodes[id].distance = selectedEdge.weight;
                nodes[id].prevNode = curNode;
                nodes[id].visited = true;
                nodeQ.add(nodes[id]);
                count++;
            }
        }
    }

    public static Node[] readGraph() {
        int numN = reader.nextInt();
        int numE = reader.nextInt();
        Node[] g = new Node[numN];

        for (int i = 0; i < g.length; i++) {
            g[i] = new Node(i);
        }

        for (int i = 0; i < numE; i++) {
            Node a = g[reader.nextInt()];
            Node b = g[reader.nextInt()];

            Long w = reader.nextLong();
            Edge e1 = new Edge(w, b);
            Edge e2 = new Edge(w, a);

            a.addAdj(e1);
            b.addAdj(e2);
        }

        return g;
    }

    static class Node {
        int id;
        long distance = Long.MAX_VALUE;
        boolean visited = false;
        Node prevNode;
        List<Edge> adjNodes = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public void addAdj(Edge node) {
            this.adjNodes.add(node);
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }

    }

    static class Edge {
        long weight;
        Node next;

        public Edge(long weight, Node next) {
            this.weight = weight;
            this.next = next;
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
