import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;

class EIBIPARTITE {

    static Queue<Vertex> q = new ArrayDeque<>();

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int tc = ni();
        while (tc-- > 0) {
            Vertex[] graph = readGraph(ni(), ni());
            boolean flag = false;
            for (int i = 0; i < graph.length; i++) {
                Vertex v = graph[i];
                if (!v.isVisited) {
                    v.setGroup(1);
                    if (dfs(v)) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean dfs(Vertex vertex) {
        vertex.visited();
        for (Vertex v : vertex.adjacentVertices) {
            if (!v.isVisited) {
                if (vertex.group == 1) {
                    v.setGroup(2);
                } else if (vertex.group == 2) {
                    v.setGroup(1);
                }
                boolean check = dfs(v);
                if (check == false) {
                    return false;
                }
            } else {
                if (vertex.group == v.group) {
                    return false;
                }
            }
        }
        return true;
    }

    static Vertex[] readGraph(int numberOfVertices, int numberOfEdges) {
        Vertex[] vertices = new Vertex[numberOfVertices];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = new Vertex(i);
        }

        // Read all edges
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
        int group;
        boolean isVisited;
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

        public void setGroup(int group) {
            this.group = group;
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
