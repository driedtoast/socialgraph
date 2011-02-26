<#include "/header.ftl" >
<#import "/spring.ftl" as spring >

<span class="large">Edit User</span>

<p>

</p>


<form action="${rc.getContextPath()}/ui/user/save" method="POST">

  <!-- TODO make form elemen macros -->
  <#if person.id??>
  <input type="hidden" name="id" value="${person.id}" />
  </#if>

  <p>
  <label for="person.name.label"><@spring.message "person.name"/></label><br />
  <@spring.bind "person.name" />
  <input type="text"
    id="${spring.status.expression}"
    name="${spring.status.expression}"
    value="${spring.status.value?default("")}"
    class="text" />
    <!-- make fancier errors -->
<#list spring.status.errorMessages as error> <b>${error}</b> <br /> </#list>
  </p>

  <p>
  <label for="person.displayName.label"><@spring.message "person.displayName"/></label><br />
  <@spring.bind "person.displayName" />
  <input type="text"
    id="${spring.status.expression}"
    name="${spring.status.expression}"
    value="${spring.status.value?default("")}"
    class="text" />
  </p>

  <!-- expand to more fields -->
  <p>
   <input type="submit" value="save"/>
  </p>
</form>



<#include "/footer.ftl" >

