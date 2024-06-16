import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class EIGREENCITY {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int numU = ni();
        int root = ni();
        Unit[] g = readUnits(numU);

        for (Unit unit : g) {
            if (unit.adjList.size() == 0) {
                g[ni()].trees = ni();
            }
        }

        DFS(g[root]);

        for (Unit unit : g) {
            sb.append(unit.id).append(" ").append(unit.trees).append("\n");
        }

        System.out.println(sb);
    }

    public static void DFS(Unit u) {
        u.visited = true;
        for (Unit unit : u.adjList) {
            if (unit.visited == false) {
                DFS(unit);
                u.trees += unit.trees;
            }
        }
    }

    public static Unit[] readUnits(int numU) {
        int numE = numU - 1;
        Unit[] graph = new Unit[numU];

        for (int i = 0; i < numU; i++) {
            graph[i] = new Unit(i);
        }

        for (int i = 0; i < numE; i++) {
            Unit a = graph[ni()];
            Unit b = graph[ni()];

            a.addAdj(b);
        }

        return graph;
    }

    static class Unit {

        int id;
        boolean visited = false;
        List<Unit> adjList = new ArrayList<>();
        long trees = 0;

        public Unit(int id) {
            this.id = id;
        }

        public void setTrees(long trees) {
            this.trees = trees;
        }

        public void addAdj(Unit u) {
            this.adjList.add(u);
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
