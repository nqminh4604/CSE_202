import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Queue;

public class EITREORD {
    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> preorder = new ArrayDeque<>();
    static Map<Integer, Integer> inorder = new HashMap<>();
    static Vertex[] vertice;

    public static void main(String[] args) {
        int numberOfNode = ni();
        vertice = new Vertex[numberOfNode];

        for (int i = 0; i < numberOfNode; i++) {
            vertice[i] = new Vertex(i);
            preorder.add(ni());
        }

        for (int i = 0; i < numberOfNode; i++) {
            inorder.put(ni(), i);
        }
        Vertex root = vertice[preorder.poll()];
        dfs(root, 0, numberOfNode);
        System.out.println(sb);
    }

    public static void dfs(Vertex root, int start, int end) {
        root.visit();
        int rootIndex = inorder.get(root.id);
        if (!preorder.isEmpty() && rootIndex > start) {
            Vertex leftVertex = vertice[preorder.poll()];
            int leftIndex = inorder.get(leftVertex.id);
            if (leftIndex < rootIndex && leftIndex >= start && leftVertex.visited == false) {
                dfs(leftVertex, start, rootIndex);
                root.left = leftVertex;
            }
        }

        if (!preorder.isEmpty() && rootIndex < end - 1) {
            Vertex rightVertex = vertice[preorder.poll()];
            int rightIndex = inorder.get(rightVertex.id);
            if (rightIndex > rootIndex && rightIndex < end && rightVertex.visited == false) {
                dfs(rightVertex, rootIndex + 1, end);
                root.right = rightVertex;
            }
        }

        sb.append(root.id + " ");

    }

    static class Vertex {
        int id;
        boolean visited;
        Vertex left;
        Vertex right;

        public Vertex(int id) {
            this.id = id;
            this.visited = false;
        }

        public void visit() {
            this.visited = true;
        }

        public void setLeft(Vertex left) {
            this.left = left;
        }

        public void setRight(Vertex right) {
            this.right = right;
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
