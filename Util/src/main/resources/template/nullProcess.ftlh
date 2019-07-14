<#--null-->
<#--不为null才显示-->
<#if nullStr??>
<span>字符串不为null:${nullStr}</span>
<#else>
字符串为null
</#if>
<#--为null时给默认值-->
<span>${nullStr!"字符串为null"}</span>

<#--空字符串-->
<#--不为才显示-->
<#if emptyStr??>
<span>字符串不为null:${emptyStr}</span>
<#else>
字符串为null或没有emptyStr-key
</#if>
<#--为null时给默认值-->
<span>${emptyStr!"字符串为null"}</span>

<#--父子都不为null才显示-->
<#if (grandFather.father.name)??>
<span>字符串不为null:${grandFather.father.name}</span>
<#else>
存在父子值为null或没有grandFather/father/name -key
</#if>
<#--父子可能为null-->
<span>${(grandFather.father.name)!"存在父子值为null或没有grandFather/father/name -key"}</span>
