/open Logger.java
/open LoggerImpl.java
Logger.make(5).printlog()
Logger.make(5).flatMap(x -> Logger.make(x))
Logger.make(5).flatMap(x -> Logger.make(x)).printlog()
Logger.make(5).flatMap(x -> Logger.make(x)).equals(Logger.make(5))
Logger<Integer> a = Logger.make(5).flatMap(x -> Logger.make(x).map(y -> y + 2)).flatMap(y -> Logger.make(y).map(z -> z * 10))
Logger<Integer> b = Logger.make(5).flatMap(x -> Logger.make(x).map(y -> y + 2).flatMap(y -> Logger.make(y).map(z -> z * 10)))
a.printlog()
b.printlog()
a.equals(b)
Logger<Integer> c = Logger.make(5).map(x -> x + 2).map(x -> x * 10)
a.equals(c)
Function<Object, Logger<Boolean>> f = x -> Logger.make(x).map(y -> y.equals(y))
Logger.make("hello").flatMap(f)
Function<String, Logger<Number>> g = x -> Logger.make(x).map(y -> y.length())
Logger<Number> lognum = Logger.make("hello").flatMap(g)
lognum