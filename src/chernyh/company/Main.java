package chernyh.company;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    
        private final static Random RAND = new Random();
        private final static Integer HYDRATE_LIMIT = 1000000;
        private final static Integer EXTRACT_LIMIT = 10000;

        public static void main(String[] args) {
            first();
            second();
            third();
            fourth();
        }

        public static void first() {
            System.out.println(uniq(List.of(1, 1, 2, 3)));
        }

        public static void second() {
            LocalDateTime dStart = LocalDateTime.now();
            List<Integer> arrayList = hydrate(new ArrayList<>());
            System.out.printf("%s : Заполнение - %s;%n", arrayList.getClass().getName(), dStart.until(LocalDateTime.now(), ChronoUnit.MILLIS));

            dStart = LocalDateTime.now();
            extract(arrayList);
            System.out.printf("%s : Получение - %s;%n", arrayList.getClass().getName(), dStart.until(LocalDateTime.now(), ChronoUnit.MILLIS));

            dStart = LocalDateTime.now();
            List<Integer> linkedList = hydrate(new LinkedList<>());
            System.out.printf("%s : Заполнение - %s;%n", linkedList.getClass().getName(), dStart.until(LocalDateTime.now(), ChronoUnit.MILLIS));

            dStart = LocalDateTime.now();
            extract(linkedList);
            System.out.printf("%s : Получение - %s;%n", linkedList.getClass().getName(), dStart.until(LocalDateTime.now(), ChronoUnit.MILLIS));

            System.out.println("ArrayList основан на массиве, а LinkedList на связном списке. Т.е. операции get и add будут происходить быстрее в ArrayList.");
        }

        public static void third() {
            Map<User, Integer> map = new HashMap<>();
            map.put(new User("Pavel"), 40);
            map.put(new User("Sergey"), 120);
            map.put(new User("Igor"), 230);

            Scanner in = new Scanner(System.in);
            System.out.print("Введите имя игрока: ");
            String userName = in.next();

            Integer points = getPoints(map, userName);
            if (points == null)
                System.out.printf("Пользователь \"%s\" не найден", userName);
            else
                System.out.printf("Игрок - %s, кол-во очков - %s.", userName, points);
        }

        public static void fourth() {
            Map<Integer, Integer> intMap = arrayToMap(new Integer[]{1, 2, 3, 4, 4, 5, 6, 6});
            System.out.println(intMap);

            Map<String, Integer> strMap = arrayToMap(new String[]{"a", "b", "c", "b", "a"});
            System.out.println(strMap);
        }

        private static <T> Collection<T> uniq(Collection<T> collection) {
            if (collection == null)
                throw new NullPointerException("Параметр не определен");

            return Set.copyOf(collection);
        }
        private static <T extends List<Integer>> T hydrate(List<Integer> list) {
            if (list == null)
                throw new NullPointerException("Параметр не определен");

            for (int i = 0; i < HYDRATE_LIMIT; i++) {
                list.add(RAND.nextInt());
            }

            return (T) list;
        }

        private static Integer getPoints(Map<User, Integer> map, String userName) {
            final User user = map.keySet().stream().filter(set -> set.getName().equals(userName)).findFirst().orElse(null);
            if (user == null)
                return null;

            return map.get(user);
        }
        private static <K> Map<K, Integer> arrayToMap(K[] ks) {
            Map<K, Integer> map = new HashMap<>();
            Arrays.stream(ks).distinct().forEach(item -> map.put(item, (int) Arrays.stream(ks).filter(k -> k.equals(item)).count()));

            return map;

        // write your code here
        }
    }
}
