package item44;

public class Main {
    public static void main(String[] args) {
        LinkedHashMapUnderHundred map = new LinkedHashMapUnderHundred();
        for (int i = 0; i < 200; i++) {
            map.put("key" + i, "value" + i);
        }
        System.out.println(map.size());
    }
}
