import java.io.*;
import java.util.*;

public class KevinBaconGame {

    // 存储演员之间的关系
    private static Map<String, Set<String>> actorGraph = new HashMap<>();
    private static Map<String, Set<String>> actorToMovies = new HashMap<>();

    // 读取电影数据
    public static void readMovieData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            String movieTitle = parts[0];
            String[] actors = Arrays.copyOfRange(parts, 1, parts.length);

            // 将每个演员与电影的关系存储
            for (String actor : actors) {
                actorToMovies.putIfAbsent(actor, new HashSet<>());
                actorToMovies.get(actor).add(movieTitle);
            }

            // 创建演员之间的边
            for (int i = 0; i < actors.length; i++) {
                for (int j = i + 1; j < actors.length; j++) {
                    actorGraph.putIfAbsent(actors[i], new HashSet<>());
                    actorGraph.putIfAbsent(actors[j], new HashSet<>());
                    actorGraph.get(actors[i]).add(actors[j]);
                    actorGraph.get(actors[j]).add(actors[i]);
                }
            }
        }
        br.close();
    }

    // 广度优先搜索计算凯文·贝肯数，并返回父节点关系
    public static Map<String, Integer> bfsKevinBacon(String startActor, Map<String, String> parents) {
        Map<String, Integer> distances = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        distances.put(startActor, 0);
        visited.add(startActor);
        queue.add(startActor);

        while (!queue.isEmpty()) {
            String currentActor = queue.poll();

            for (String neighbor : actorGraph.getOrDefault(currentActor, new HashSet<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distances.put(neighbor, distances.get(currentActor) + 1);
                    parents.put(neighbor, currentActor); // 存储父节点关系
                    queue.add(neighbor);
                }
            }
        }

        // 对于无法连接的演员，设置一个特殊的值（例如 Integer.MAX_VALUE）
        for (String actor : actorGraph.keySet()) {
            if (!distances.containsKey(actor)) {
                distances.put(actor, Integer.MAX_VALUE); // 无法连接的演员标记为 infinity
            }
        }

        return distances;
    }

    // 打印凯文·贝肯数分布
    public static void printBaconNumberDistribution(Map<String, Integer> distances) {
        Map<Integer, Integer> distribution = new HashMap<>();
        int infinityCount = 0;

        for (int baconNumber : distances.values()) {
            if (baconNumber == Integer.MAX_VALUE) {
                infinityCount++;
            } else {
                distribution.put(baconNumber, distribution.getOrDefault(baconNumber, 0) + 1);
            }
        }

        System.out.println("Bacon number    Frequency");
        System.out.println("-------------------------");
        for (int i = 0; i <= 6; i++) {
            System.out.printf("%12d        %d\n", i, distribution.getOrDefault(i, 0));
        }
        System.out.printf("%12s        %d\n", "infinity", infinityCount);
    }

    // 打印演员到凯文·贝肯的最短路径，并显示连接电影
    public static void printShortestChain(
            String actor,
            Map<String, String> parents,
            Map<String, Set<String>> actorToMovies
    ) {
        if (!parents.containsKey(actor)) {
            System.out.println(actor + " is not connected to Kevin Bacon.");
            return;
        }

        List<String> chain = new ArrayList<>();
        String curr = actor;
        while (!curr.equals("Bacon, Kevin")) {
            String parent = parents.get(curr);

            // 找出 curr 和 parent 共同出演的电影
            Set<String> movies = new HashSet<>(actorToMovies.get(curr));
            movies.retainAll(actorToMovies.get(parent));

            // 取其中一部（通常就只有一两部）
            String movie = movies.iterator().next();
            chain.add(String.format("%s was in \"%s\" with %s", curr, movie, parent));
            curr = parent;
        }

        for (String step : chain) {
            System.out.println(step);
        }
    }

    public static void main(String[] args) throws IOException {
        // 输入文件名
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the movie data filename: ");
        String filename = scanner.nextLine();

        // 读取电影数据并构建图
        readMovieData(filename);

        // 定义 parents 用于追溯路径
        Map<String, String> parents = new HashMap<>();

        // 计算凯文·贝肯数并获取父节点关系
        Map<String, Integer> distances = bfsKevinBacon("Bacon, Kevin", parents);

        // 打印凯文·贝肯数分布
        printBaconNumberDistribution(distances);

        // 对每个输入的演员，打印凯文·贝肯数和最短电影链
        while (true) {
            System.out.print("Enter an actor's name (or type 'E' to exit): ");
            String actor = scanner.nextLine();

            if (actor.equals("E")) break;

            if (distances.containsKey(actor)) {
                System.out.println(actor + " has a Bacon number of " + distances.get(actor));
                printShortestChain(actor, parents, actorToMovies);
            } else {
                System.out.println(actor + " is not connected to Kevin Bacon.");
            }
        }

        scanner.close();
    }
}

