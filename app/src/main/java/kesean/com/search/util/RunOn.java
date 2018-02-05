package kesean.com.search.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Kesean on 2/5/18.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOn {
    SchedulerType value() default SchedulerType.IO;
}

