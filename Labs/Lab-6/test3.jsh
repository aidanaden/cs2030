/open Logger.java
/open LoggerImpl.java
Logger.make(5)
Logger.make(5).printlog()
Logger.make(5).map(x -> x + 1)
Logger.make(5).map(x -> x + 1).printlog()
Logger<Integer> a = Logger.make(5)
a.printlog()
a.map(x -> x + 1)
a.printlog()
Logger.make(5).map(x -> x)
Logger.make(5).map(x -> x).printlog()
Logger.make(5).equals(Logger.make(5).map(x -> x))
Logger.make(5).map(x -> x).equals(Logger.make(5))
Logger.make(5).map(x -> x + 1).map(x -> x - 1)
Logger.make(5).map(x -> x + 1).map(x -> x - 1).printlog()
Logger.make(5).map(x -> x + 1).map(x -> x - 1).equals(Logger.make(5))
Logger.make("hello").map(String::length)
Logger.make("hello").map(String::length).printlog()
Function<Object, Boolean> f = x -> x.equals(x)
Logger.make("hello").map(f)
Function<String, Number> g = x -> x.length();
Logger<Number> lognum = Logger.make("hello").map(g)
lognum
