import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class EIPEOYMK {

    static Queue<Vertex> q = new ArrayDeque<>();
    static Map<Integer, Set<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        Vertex[] graph = readGraph(ni(), ni());
        Vertex root = graph[ni()];
        int tc = ni();
        bfs(root);
        while (tc-- > 0) {
            int level = ni();
            Set<Integer> set = map.get(level);
            if (set == null) {
                s.append(-1 + " ");
            } else {
                for (Integer integer : set) {
                    s.append(integer + " ");
                }
            }
            s.append("\n");
        }
        System.out.println(s);
    }

    public static void bfs(Vertex beginVertex) {
        beginVertex.setLevel(0);
        q.add(beginVertex);
        while (!q.isEmpty()) {
            Vertex v1 = q.poll();
            v1.visited();
            Set<Integer> set = map.getOrDefault(v1.level, new TreeSet<>());
            set.add(v1.id);
            map.put(v1.level, set);
            for (Vertex v2 : v1.adjacentVertices) {
                if (v2.isVisited == false) {
                    if (v2.level == -1) {
                        v2.setLevel(v1.level + 1);
                    }
                    q.add(v2);
                }
            }
        }
    }

    static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
        Vertex[] vertices = new Vertex[numberOfVertices];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < numberOfEdges; ++i) {
            int u = ni();
            int v = ni();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }

        return vertices;
    }

    static class Vertex {
        int id;
        boolean isVisited;
        int level = -1;
        List<Vertex> adjacentVertices = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            this.isVisited = false;
        }

        public void addNeighbor(Vertex v) {
            adjacentVertices.add(v);
        }

        public void visited() {
            isVisited = true;
        }

        public void unVisited() {
            isVisited = false;
        }

        public void setLevel(int n) {
            this.level = n;
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
