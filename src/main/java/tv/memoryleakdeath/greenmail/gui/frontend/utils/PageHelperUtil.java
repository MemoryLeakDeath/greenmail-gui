package tv.memoryleakdeath.greenmail.gui.frontend.utils;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class PageHelperUtil {
    private static final String LONG_DATE_FORMAT = "ccc d LLLL yyyy K:mm:ss a zzz";
    private static final String SHORT_DATE_FORMAT = "L/dd/yyyy K:mm:ss a zzz";

    public String formatDateLong(Date date, TimeZone timeZone, Locale locale) {
        return DateFormatUtils.format(date, LONG_DATE_FORMAT, timeZone, locale);
    }

    public String formatDateLong(Date date) {
        return formatDateLong(date, TimeZone.getDefault(), Locale.US);
    }

    public String formatDateShort(Date date, TimeZone timeZone, Locale locale) {
        return DateFormatUtils.format(date, SHORT_DATE_FORMAT, timeZone, locale);
    }

    public String formatDateShort(Date date) {
        return formatDateShort(date, TimeZone.getDefault(), Locale.US);
    }

    public String htmlEscape(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    public String jsEscape(String js) {
        return StringEscapeUtils.escapeEcmaScript(js);
    }
}
