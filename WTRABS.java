import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

class WTRABS {

    static StringBuilder sb = new StringBuilder();
    static List<Result> results = new ArrayList<>();

    public static void main(String[] args) {
        Vertex[] g = readGraph(ni());
        for (Vertex vertex : g) {
            if (vertex.isVisited == false) {
                dfs(vertex, 0);
            }
        }
        results.sort((r1, r2) -> {
            return r1.id - r2.id;
        });
        for (Result result : results) {
            sb.append(String.format(result.id + " %.4f \n", result.contain));
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v, double water) {
        v.Visited();
        v.contain += water;
        if (v.adjList.size() == 0) {
            results.add(new Result(v.id, v.contain));
            return;
        }
        double splitWater = (v.contain == 0) ? 0 : v.contain / v.adjList.size();
        for (Vertex vertex : v.adjList) {
            if (vertex.isVisited == false) {
                dfs(vertex, splitWater);
            }
        }
    }

    static class Result {
        int id;
        double contain;

        public Result(int id, double contain) {
            this.id = id;
            this.contain = contain;
        }

    }

    public static Vertex[] readGraph(int numVertices) {
        int numEdges = numVertices - 1;
        Vertex[] g = new Vertex[numVertices];

        for (int i = 0; i < numVertices; i++) {
            g[i] = new Vertex(i);
        }

        for (int i = 0; i < numVertices; i++) {
            g[i].setContain(nd());
        }

        for (int i = 0; i < numEdges; i++) {
            int a = ni();
            int b = ni();

            g[b].addEdge(g[a]);

            g[b].adjList.sort((v1, v2) -> {
                return v1.id - v2.id;
            });
        }

        return g;
    }

    static class Vertex {
        int id;
        double contain;
        boolean isVisited = false;
        List<Vertex> adjList = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void setContain(double contain) {
            this.contain = contain;
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
