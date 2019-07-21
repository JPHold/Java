<#--全部大写-->
<span>${upperCase?upper_case}</span>
<#--首字符大写-->
<span>${capFirst?cap_first}</span>
<#--字符串长度-->
<span>${strLength?length}</span>
<#--字符串是否以xx开头-->
<span>${startsWith?starts_with("start")?string('是','否')}</span>
<#--Boolean转人性化的字符串-->
<span>${booleanStr?string('是','否')}</span>
<#--list的方法-->
<#list contentList>
<span>输出分隔符连接的子项:${contentList?join(",")}</span>
<#--list长度-->
<span>list长度:${contentList?size}</span>
<#items as l>
<span>当前下标:${l?index},真实下标:${l?counter},奇偶:${l?item_parity};${l?item_cycle('lightRow','darkRow')}</span>
</#items>
</#list>
