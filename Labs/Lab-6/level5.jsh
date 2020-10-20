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