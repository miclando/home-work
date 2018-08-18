import java.util.*;

public class StringsTransformer {
    private List<String> data = new ArrayList<>();

    public StringsTransformer(List<String> startingData) {
        this.data = startingData;
    }

    private void forEach(StringFunction function) {
        List<String> newData = new ArrayList<>();
        for (String str : data) {
            newData.add(function.transform(str));
        }
        data = newData;
    }

    public List<String> transform(List<StringFunction> functions) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (final StringFunction f : functions) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    forEach(f);
                }
            }));
        }
        for (Thread t : threads) {
            t.join();
        }
        return data;
    }

    public static interface StringFunction {
        public String transform(String str);
    }

    public static void main(String [] args)  {
        List<Thread> threads = new ArrayList<>();

        threads.add(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1");
            }
        }));
        threads.add(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        }));
        threads.add(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3");
            }
        }));

        threads.forEach((t)-> t.start());

        try {
            threads.get(0).join();
            System.out.println("1 join");
            threads.get(1).join();
            System.out.println("2 join");
            threads.get(2).join();
            System.out.println("3 join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        for (Thread t : threads) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
