<list>
    <#--为空时,不显示外层结构ul,默认提示列表为空-->
    <#list emptyList>
     <ul>
         <#items as l>
            <li>${l}</li>
         </#items>
     </ul>
    <#else>列表为空
    </#list>

    <#--始终显示外层结构-->
    <ul>
    <#list emptyList as l>
        <li>${l}</li>
    </#list>
    </ul>

    <#--分隔符-->
    <#list contentList>
    <span>
     <#items as l>${l}<#sep>,</#items>
    </span>
    </#list>
</list>