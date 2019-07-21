<#function avg x y>
    <#return (x+y)/2>
</#function>
两个数的平均数:${avg(1,2)}
<#function avg nums...>
    <#local sum = 0>
    <#list nums as num>
        <#local sum = sum + num>
    </#list>
    <#if nums?size != 0>
        <#return sum / nums?size>
    </#if>
</#function>
一组数的平均数:${avg(10, 20, 30, 40)}
${avg()!'报错/没有返回值'}