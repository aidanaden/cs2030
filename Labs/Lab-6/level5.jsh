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

        return logger.map(x -> x = 1);
           
    } else if (n % 2 == 0) {

        return logger.flatMap(x -> f(n / 2).map(y -> n / 2));
        
    } else {
        
        return logger.flatMap(x -> f(3 * n + 1));
    } 
}