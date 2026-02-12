package pattern;

public final class Singleton {
    private Singleton() {
    }

    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton instance() {
        return InstanceHolder.INSTANCE;
    }
}
