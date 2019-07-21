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
调用一个参数的指令
<@title1 pageType="更新"></@title1>
调用多参数的指令
<@title2 pageType="更新" target="人员"></@title2>

