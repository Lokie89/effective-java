package item9;

public class TryWithResourcesTest implements AutoCloseable{

    @Override
    public void close() {
        System.out.println("닫혔어!");
    }
}