<#setting date_format="yyyyMMdd">
<#setting datetime_format="iso">
<#setting time_format="HH:mm:ss">

<#--date转换字符串-->
<span>日期转字符串:${dateTime?date}</span>
<span>时间转字符串:${dateTime?time}</span>
<span>日期+时间转字符串:${dateTime?datetime}</span>
<span>未知日期转字符串:${unknownDate?date_if_unknown}</span>
<span>未知日期转字符串:${unknownDate?time_if_unknown}</span>
<span>未知日期转字符串:${unknownDate?datetime_if_unknown}</span>

<#--字符串转换日期-->
<span>字符串转日期:${dateStr1?date}</span>
<span>字符串转日期:${dateStr2?date('yyyy-MM-dd')}</span>
<span>字符串转时间:${timeStr?time}</span>

<#--内置格式转换-->
<span>xs字符串转日期:${xsDateStr?date.xs}</span>
<span>xs字符串转时间:${xsTimeStr?time.xs}</span>
<span>xs字符串转日期时间:${xsDateTimeStr?datetime.xs}</span>
<span>iso字符串转日期:${isoDateStr?date.iso}</span>
<span>iso字符串转时间:${isoTimeStr?time.iso}</span>
<span>iso字符串转日期时间:${isoDateTimeStr?datetime.iso}</span>
<#--<span>字符串转日期:${workLogDateTimeStr?datetime.@worklog}</span>-->
