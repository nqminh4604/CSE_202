import java.util.*;
import java.io.*;

class Stolen {

    public static class Folder {
        public String name;
        public List<Folder> children = new ArrayList<>();
        public List<Folder> adjFolders = new ArrayList<>();
        public boolean visited = false;
        public boolean hasFile = false;
        public int numFiles = 0;

        public Folder(String name) {
            this.name = name;
        }

    }

    static InputReader reader;
    static StringBuilder sb = new StringBuilder();
    static String keyWord = "";
    static Folder root = null;

    public static void main(String[] args) throws Exception {
        reader = new InputReader(System.in);

        Folder[] treeFolder = readGraph();
        keyWord = reader.next();
        for (Folder folder : treeFolder) {
            folder.adjFolders.sort((c1, c2) -> c1.name.compareToIgnoreCase(c2.name));
        }
        dfs(root);
        for (Folder folder : treeFolder) {
            folder.visited = false;
        }
        dfisFile(root);
        System.out.println(sb);
    }

    private static Folder[] readGraph() throws IOException {
        Map<String, Folder> storedFolderMap = new HashMap<>();
        int num_folder = reader.nextInt();
        for (int i = 0; i < num_folder - 1; i++) {
            String nameFile1 = reader.next();
            String nameFile2 = reader.next();

            Folder folder1 = storedFolderMap.getOrDefault(nameFile1, new Folder(nameFile1));
            Folder folder2 = storedFolderMap.getOrDefault(nameFile2, new Folder(nameFile2));
            folder1.adjFolders.add(folder2);
            folder2.adjFolders.add(folder1);
            storedFolderMap.put(nameFile1, folder1);
            storedFolderMap.put(nameFile2, folder2);
        }
        String nameRootFolder = reader.next();
        Folder RootFolder = storedFolderMap.getOrDefault(nameRootFolder, new Folder(nameRootFolder));
        root = RootFolder;
        storedFolderMap.put(nameRootFolder, RootFolder);
        Folder[] treeFolder = new Folder[storedFolderMap.size()];
        List<Folder> listFolder = new ArrayList<>(storedFolderMap.values());
        treeFolder = listFolder.toArray(treeFolder);
        return treeFolder;
    }

    public static void dfs(Folder fd) {
        fd.visited = true;
        for (Folder child : fd.adjFolders) {
            if (!child.visited) {
                fd.children.add(child);
                dfs(child);
            }
        }

    }

    public static void dfisFile(Folder fd) {
        fd.visited = true;
        for (Folder child : fd.children) {
            if (child.visited == false) {
                dfisFile(child);
                if ((child.children.isEmpty() && child.name.toLowerCase().contains(keyWord.toLowerCase()))
                        || (child.hasFile)) {
                    if ((child.children.isEmpty() && child.name.toLowerCase().contains(keyWord.toLowerCase()))) {
                        child.numFiles++;
                    }
                    fd.hasFile = true;
                    fd.numFiles += child.numFiles;
                }
            }

        }
        if (fd.hasFile) {
            sb.append(fd.name + " " + fd.numFiles).append("\n");
        }
    }

    static InputStream is = System.in;

    static class InputReader {
        private byte[] inbuf = new byte[1 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {
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
            while (b != -1 && b != '\n') {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1) {
                throw new InputMismatchException();
            }
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

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() throws IOException {
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

        public long nextLong() throws IOException {
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
