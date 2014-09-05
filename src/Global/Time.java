package Global;

import java.util.Calendar;
import java.util.Date;

public class Time {

    private static final long secondInMillis = 1000;
    private static final long minuteInMillis = secondInMillis * 60;
    private static final long hourInMillis = minuteInMillis * 60;
    private static final long dayInMillis = hourInMillis * 24;
    private static final long yearInMillis = dayInMillis * 365;

    public static long getPeriod(Calendar startTime, Calendar endTime) {
        return endTime.getTimeInMillis() - startTime.getTimeInMillis();
    }

    public static long getSeconds(long period) {
        return period / secondInMillis;
    }

    public static long getMinutes(long period) {
        return period / minuteInMillis;
    }

    public static long getHours(long period) {
        return period / hourInMillis;
    }

    public static long getDays(long period) {
        return period / dayInMillis;
    }

    public static long getYears(long period) {
        return period / yearInMillis;
    }

    public static String periodToFormat(long period) {
        long elapsedYears = getYears(period);
        period = period % yearInMillis;
        long elapsedDays = getDays(period);
        period = period % dayInMillis;
        long elapsedHours = getHours(period);
        period = period % hourInMillis;
        long elapsedMinutes = getMinutes(period);
        period = period % minuteInMillis;
        long elapsedSeconds = getSeconds(period);

        String ret = "";
        if (elapsedYears != 0) {
            ret += elapsedYears + " years ";
        }

        if (elapsedDays != 0) {
            ret += elapsedDays + " days ";
        }

        if (elapsedHours != 0) {
            if (elapsedMinutes > 9) {
                ret += elapsedHours + ":";
            } else {
                ret += "0" + elapsedHours + ":";
            }
        } else {
            ret += "00:";
        }

        if (elapsedMinutes != 0) {
            if (elapsedMinutes > 9) {
                ret += elapsedMinutes + ":";
            } else {
                ret += "0" + elapsedMinutes + ":";
            }
        }
        else {
            ret += "00:";
        }

        if (elapsedSeconds != 0) {
            if (elapsedSeconds > 9) {
                ret += elapsedSeconds + " hrs";
            } else {
                ret += "0" + elapsedSeconds + " hrs";
            }
        }
        else {
            ret += "00 hrs";
        }

        return ret;
    }
}
