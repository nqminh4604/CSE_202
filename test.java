import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * test
 */
public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Node[] g = readGraph(ni(), ni());
        djs(g[0]);
        for (int i = 1; i < g.length; i++) {
            Node node = g[i];
            if (node.prevNode == null) {
                sb.append(-1).append(" ");
            } else {
                sb.append(node.distance).append(" ");
            }
        }
        System.out.println(sb);
    }

    public static void djs(Node startNode) {
        Queue<Node> q = new PriorityQueue<>((n1, n2) -> Long.compare(n1.distance, n2.distance));
        startNode.prevNode = startNode;
        startNode.distance = 0;
        q.add(startNode);
        while (!q.isEmpty()) {
            Node curNode = q.poll();
            if (curNode.visited) {
                continue;
            }
            curNode.setVisitedTrue();
            for (Edge e : curNode.adjList) {
                if (e.next.visited == false) {
                    if (e.distance + curNode.distance < e.next.distance) {
                        e.next.distance = e.distance + curNode.distance;
                        e.next.prevNode = curNode;
                        q.add(e.next);
                    }
                }
            }

        }
    }

    public static Node[] readGraph(int numNode, int numEdge) {
        Node[] g = new Node[numNode];

        for (int i = 0; i < g.length; i++) {
            g[i] = new Node(i);
        }

        for (int i = 0; i < numEdge; i++) {
            Node a = g[ni()];
            Node b = g[ni()];
            long weight = nl();

            Edge e1 = new Edge(b, weight);
            Edge e2 = new Edge(a, weight);

            a.addAdj(e1);
            b.addAdj(e2);
        }

        return g;
    }

    static class Edge {

        long distance;
        Node next;

        public Edge(Node next, long distance) {
            this.next = next;
            this.distance = distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

    }

    /**
     * static Innertest
     */
    public static class Node {
        int id;
        boolean visited = false;
        long distance = Long.MAX_VALUE;
        Node prevNode;
        List<Edge> adjList = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }

        public void addAdj(Edge edge) {
            this.adjList.add(edge);
        }

        public void setVisitedTrue() {
            this.visited = true;
        }
    }

    static InputStream is = System.in;
    static byte[] inbuf = new byte[1 << 24];
    static int lenbuf = 0, ptrbuf = 0;

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

    static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b))
            ;
        return b;
    }

    static double nd() {
        return Double.parseDouble(ns());
    }

    static char nc() {
        return (char) skip();
    }

    static String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
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

    static long nl() {
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