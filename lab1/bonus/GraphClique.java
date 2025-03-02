public class GraphClique {
    private static int[][] adjMatrix;
    private static int n, k;
    private static boolean foundClique = false;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use: java GraphClique <n> <k>");
            return;
        }

        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        adjMatrix = new int[n][n];
        generateRandomGraph();
        System.out.println(getStringRep());

        int[] subset = new int[k];
        backtrack(subset,0, 0);
        System.out.println(foundClique ? "found a clique" : "didn't find a clique");

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                adjMatrix[i][j] = Math.abs(adjMatrix[i][j] - 1);
            }
        }
        backtrack(subset,0, 0);
        System.out.println(foundClique ? "found a stable set" : "didn't find a stable set");
    }

    private static void generateRandomGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                adjMatrix[i][j] = (Math.random() < 0.5) ? 1 : 0;
                adjMatrix[j][i] = adjMatrix[i][j];
            }
        }
    }

    private static String getStringRep() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(adjMatrix[i][j] == 1 ? "\u25A0 " : "\u25A1 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void backtrack(int[] subset, int subsetSize, int start) {
        if (foundClique) return;

        if (subsetSize == k) {
            if (isClique(subset)) {
                foundClique = true;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            subset[subsetSize] = i;
            backtrack(subset,subsetSize + 1, i + 1);
        }
    }

    private static boolean isClique(int[] subset) {
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                if (adjMatrix[subset[i]][subset[j]] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}


