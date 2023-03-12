package internship;

public class RateLimitedPrinter {
    private int interval;
    private long lastTime = System.currentTimeMillis();
    public RateLimitedPrinter(int interval) {
        this.interval = interval;
    }
    public void print(String message) {
        if(System.currentTimeMillis() - lastTime >= interval)
        {System.out.println(message);
        lastTime = System.currentTimeMillis();}
    }
}