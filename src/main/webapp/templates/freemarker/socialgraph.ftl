<#import "spring.ftl" as spring />

<#macro label key required=false>
	<label for="lbl.${key}"><@spring.messageText key key /><#if required><span class="required">*</span></#if></label>
</#macro>


<#macro textBox path>
	<@spring.bind path />
  	<input type="text" id="${spring.status.expression}" name="${spring.status.expression}" value="${spring.status.value?default("")}" class="text" />
    <!-- make fancier errors -->
	<#list spring.status.errorMessages as error> <span class="">${error}</span> <br /> </#list>
</#macro>


<#macro labelWithTextBox path key="" required=false>
	<#assign lblKey=key />
	<#if key="">
		<#assign lblKey=path />
	</#if>

	<p>
		<@label lblKey required /> <br />
		<@textBox path />
	</p>
</#macro>