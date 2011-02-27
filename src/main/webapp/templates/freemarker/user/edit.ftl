<#include "/header.ftl" >

<span class="large">Edit User</span>

<p>

</p>


<form action="${rc.getContextPath()}/ui/user/save" method="POST">

  <!-- TODO make form elemen macros -->
  <#if person.id??>
  <input type="hidden" name="id" value="${person.id}" />
  </#if>

  	<@sg.labelWithTextBox path="person.name" />
	<@sg.labelWithTextBox path="person.displayName" />

   <!-- expand to more fields -->
  <p>
   <input type="submit" value="save"/>
  </p>
</form>



<#include "/footer.ftl" >

