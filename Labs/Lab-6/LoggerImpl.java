import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class LoggerImpl<T> implements Logger<T> {

    private final T obj;
    private final ArrayList<Object> lastObjs;
    private final String log;

    LoggerImpl(T obj) {
    
        this.obj = obj;
        this.lastObjs = new ArrayList<>();
        this.log = createLog();
    }

    LoggerImpl(T obj, ArrayList<Object> objs) {

        this.obj = obj;
        this.lastObjs = objs;
        this.log = createLog();
    }

    // LoggerImpl(T obj, String log) {
        
    //     this.obj = obj;
    //     this.lastObjs = new ArrayList<>();
    //     this.lastObjs.add(this.obj);
    //     this.log = log;
    // }

    public T getObj() {
        return this.obj;
    }

    public String getLog() {
        return this.log;
    }

    public ArrayList<Object> getLastObjs() {
        return this.lastObjs;
    }
    
    public void printlog() {
        System.out.println(this.log);    
    }

    public String toString() {
        return "Logger[" + this.obj + "]";
    }

    public String createLog() {

        String log = "";

        for (int i = 0; i < this.lastObjs.size(); i++) {

            Object obj = this.lastObjs.get(i);

            if (i == 0) {

                log += "Value initialized. Value = " + obj;

            } else {

                Object prevObj = this.lastObjs.get(i - 1);

                if (obj.equals(prevObj)) {

                    log += "\nValue unchanged. Value = " + obj;
    
                } else {
    
                    log += "\nValue changed! New value = " + obj;
                }
            }
        }

        return log;
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
        this.lastObjs.add(result);

        return new LoggerImpl<U>(result, this.lastObjs);
    }

    public <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> mapper) {

        Logger<? extends U> result = mapper.apply(this.obj);

        if (result instanceof LoggerImpl<?>) {

            LoggerImpl<? extends U> loggerResult = (LoggerImpl<? extends U>) result;

            ArrayList<Object> combinedObjs = new ArrayList<>();
    
            combinedObjs.addAll(this.lastObjs);

            // remove copy of this.lastObjs.
            loggerResult.getLastObjs().remove(0);
            
            combinedObjs.addAll(loggerResult.getLastObjs());

            LoggerImpl<U> newLoggerResult = new LoggerImpl<U>(loggerResult.getObj(), combinedObjs);

            return newLoggerResult;
        }

        return null;
    }

    public boolean test(Predicate<? super T> pred) {

        return pred.test(this.getObj());
    }
}
