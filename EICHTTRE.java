import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

class EICHTTRE {

    static StringBuilder sb = new StringBuilder();
    static int edges = 0;
    static int vertice = 0;

    public static void main(String[] args) {
        int tc = ni();
        while (tc-- > 0) {
            int numVertices = ni();
            int numEdges = ni();
            Vertex[] graph = readGraph(numVertices, numEdges);
            if (!(numVertices - 1 == numEdges)) {
                sb.append("NO\n");
                continue;
            }
            dfs(graph[0]);
            if (isConnected(graph)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v) {
        v.Visited();
        for (Vertex vertex : v.adjList) {
            if (vertex.isVisited == false) {
                dfs(vertex);
            }
        }
    }

    public static boolean isConnected(Vertex[] graph) {
        for (Vertex vertex : graph) {
            if (vertex.isVisited == false) {
                return false;
            }
        }
        return true;
    }

    public static Vertex[] readGraph(int numVertices, int numEdges) {
        Vertex[] g = new Vertex[numVertices];

        for (int i = 0; i < numVertices; i++) {
            g[i] = new Vertex(i);
        }

        for (int i = 0; i < numEdges; i++) {
            int a = ni();
            int b = ni();

            g[a].addEdge(g[b]);
            g[b].addEdge(g[a]);

        }

        return g;
    }

    static class Vertex {
        int id;
        boolean isVisited = false;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addEdge(Vertex v) {
            adjList.add(v);
        }

        public void Visited() {
            this.isVisited = true;
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
