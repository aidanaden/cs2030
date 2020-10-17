import java.util.function.Function;

public class LoggerImpl<T> implements Logger<T> {

    private final T obj;
    private final String log;

    LoggerImpl(T obj) {
    
        this.log = "Value initialized. Value = " + obj;
        this.obj = obj;
    }

    LoggerImpl(T obj, String log) {

        this.log = log;
        this.obj = obj;
    }

    public T getObj() {
        return this.obj;
    }

    public String getLog() {
        return this.log;
    }
    
    public void printlog() {
        System.out.println(this.log);    
    }

    public String toString() {
        return "Logger[" + this.obj + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof LoggerImpl) {

            LoggerImpl<?> loggerObj = (LoggerImpl<?>) obj;

            if ((loggerObj.getObj().equals(getObj())) && (loggerObj.getLog().equals(getLog()))) {
                
                return true;

            } else {

                return false;
            }
        
        } else {

            return false;
        }
    }

    public <U> LoggerImpl<U> map(Function<? super T, ? extends U> mapper) {
        
        U result = mapper.apply(getObj());

        String logStr = this.log;

        if (result.equals(getObj())) {

            logStr += "\nValue unchanged. Value = " + result;
        
        } else {
            
            logStr += "\nValue changed! New value = " + result;
        }

        return new LoggerImpl<U>(result, logStr);
    }

    public <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> mapper) {

        Logger<? extends U> result = mapper.apply(this.obj);

        if (result instanceof LoggerImpl) {

            LoggerImpl<? extends U> loggerResult = (LoggerImpl<? extends U>) result;

            LoggerImpl<U> newLoggerResult = new LoggerImpl<U>(loggerResult.getObj(), loggerResult.getLog());

            return newLoggerResult;
        }

        return null;
    }
}
