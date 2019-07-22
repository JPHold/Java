------------无参
<#macro title>
<span>新增页面</span>
</#macro>
<#--
<@title2>
<span>新增页面</span>

</@title2>-->
<#--<@box title="Attention!">
  Too much copy-pasting may leads to
  maintenance headaches.
</@box>-->

调用通过#macro定义的指令
<@title/>
调用通过@xxx定义的指令
<#--<@title2/>-->

------------有参
<#macro title1 pageType>
<span>${pageType}页面</span>
</#macro>
<#macro title2 pageType target>
<span>${pageType}${target}</span>
</#macro>
<#macro title3 pageType target="人员">
<span>${pageType}${target}</span>
</#macro>
<#macro title4 calc="${3/4}">
<span>计数:${calc}</span>
</#macro>
调用一个参数的指令
<@title1 pageType="更新"></@title1>
调用多参数的指令(顺序无关)
<@title2 pageType="更新" target="人员"></@title2>
<@title2 target="人员" pageType="更新"></@title2>
<@title3 pageType="更新"></@title3>
<@title4></@title4>

嵌套内容
<#macro title5 pageType>
<span>${pageType}页面</span>
<#nested>
<#nested>
</#macro>

<@title5 pageType="更新">
<p>更新budd</p>
<@title4></@title4>
</@title5>

<#macro title6 count>
    <#local y = "test">
    <#list 1..count as x>
${y} ${count}/${x}: <#nested>
    </#list>
</#macro>
<@title6 count=3>${y!"?"} ${x!"?"} ${count!"?"}</@title6>

宏与循环变量
<#macro do_thrice>
    <#nested 1>
    <#nested 2>
    <#nested 3>
</#macro>
<@do_thrice ; x>
    ${x} Anything.
</@do_thrice>

<#macro repeat count>
    <#list 1..count as x>
        <#nested x, x/2, x==count>
    </#list>
</#macro>
<@repeat count=4 ; c, halfc, last>
    ${c}. ${halfc}<#if last> Last!</#if>
</@repeat>