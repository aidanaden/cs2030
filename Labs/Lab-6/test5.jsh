/open Logger.java
/open LoggerImpl.java

Logger<Integer> sum(int n) {
    if (n == 0) {
        return Logger.make(0);
    } else {
        return add(sum(n - 1), n);
    }
}

LoggerImpl<Integer> add(Logger<Integer> a, int b) {
    return a.map(x -> x + b);
}

Logger<Integer> f(int n) {

    Logger<Integer> logger = Logger.make(n);

    if (n == 1) {

        return logger;
           
    } else if (n % 2 == 0) {

        logger = logger.map(y -> n / 2);
        return logger.flatMap(x -> f(n / 2));
        
    } else {
        
        logger = logger.map(y -> 3 * n);
        logger = logger.map(z -> z + 1);
        return logger.flatMap(x -> f(3 * n + 1));
    } 
}


add(Logger.make(5), 6)
add(Logger.make(5), 6).printlog()
add(Logger.make(5).map(x -> x * 2), 6)
add(Logger.make(5).map(x -> x * 2), 6).printlog()
sum(0)
sum(0).printlog()
sum(5)
sum(5).printlog()
Logger.make(5).test(x -> x == 5)
Logger.make(5).map(x -> x + 1).test(x -> x % 2 != 0)
Logger.make("hello").test(x -> x.length() == 5)
f(16)
f(16).printlog()
f(10)
f(10).printlog()
