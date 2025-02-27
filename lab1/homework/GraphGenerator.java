public class GraphGenerator {
    static int[][] adjMatrix;
    static int n, k;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        if (args.length < 2) {
            System.out.println("Use: java GraphGenerator <n> <k>");
            return;
        }

        n = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);
        adjMatrix = new int[n][n];
        generateGraph();
        printGraphProp();
        long endTime = System.currentTimeMillis();
        if (n <= 30_000) {
            System.out.println(getStringRep());
        }
        else {
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
        }
    }

    private static void generateGraph() {
        int[] cliqueNodes = new int[k];
        for (int i = 0; i < k; i++) {
            cliqueNodes[i] = i;
        }
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                adjMatrix[i][j] = 1;
                adjMatrix[j][i] = 1;
            }
        }
        int[] stableSet = new int[k];
        for (int i = 0; i < k; i++) {
            stableSet[i] = k + i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!((i >= 2*k && i <= 2*k-1) && (j >= 2*k && j <= 2*k-1) || adjMatrix[i][j] == 1)) {
                    adjMatrix[i][j] = (int) Math.floor(Math.random() * 2);
                    adjMatrix[j][i] = adjMatrix[i][j];
                }
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

    private static void printGraphProp() {
        int edges = 0;
        int[] degrees = new int[n];
        int maxDegree = 0, minDegree = n, sumOfDegrees = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] == 1) {
                    degrees[i]++;
                    if (i < j) edges++;
                }
            }
            maxDegree = Math.max(maxDegree, degrees[i]);
            minDegree = Math.min(minDegree, degrees[i]);
            sumOfDegrees += degrees[i];
        }

        System.out.println("m: " + edges);
        System.out.println("max degree: " + maxDegree);
        System.out.println("min degree: " + minDegree);
        System.out.println("sum of degrees: " + sumOfDegrees);
        System.out.println((sumOfDegrees == 2 * edges) ? "sum of degrees = 2*m" : "sum of degrees != 2*m");
    }
}
