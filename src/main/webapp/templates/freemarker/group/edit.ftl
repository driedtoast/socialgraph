<#include "/header.ftl" >
<#import "/spring.ftl" as spring >

<span class="large">Edit Group</span>

<p>

</p>


<form action="${rc.getContextPath()}/ui/group/save" method="POST">

  <#if org.id??>
  	<input type="hidden" name="id" value="${org.id}" />
  </#if>
  
  <!-- TODO make form elemen macros -->

  <p>
  <label for="org.name.label">name</label><br />
  <@spring.bind "org.name" /> 
  <input type="text"
    id="${spring.status.expression}" 
    name="${spring.status.expression}" 
    value="${spring.status.value?default("")}" 
    class="text" />
    <!-- make fancier errors -->
<#list spring.status.errorMessages as error> <b>${error}</b> <br /> </#list>
  </p> 
  
  <!-- expand to more fields -->
  <p>  
   <input type="submit" value="save"/>
  </p>
</form>



<#include "/footer.ftl" >

