<#include "/header.ftl" >


<span class="large">User List</span>

</div>
<div class="span-24 last" style="margin-top: 1em;">

<table class="display" cellpadding="0" cellspacing="0" border="0" id="userstable" > 

<thead> 
	<tr> 
		<th>display name</th>
		<th>user</th>
		<th>actions</th>
	</tr>
</thead>

<tbody>
<#list list as person>
        <tr>
		<td>${person.displayName!"N/A"}</td>
		<td>${person.name!"N/A"}</td>
		<td><a href="${rc.getContextPath()}/ui/user/edit/${person.id}">edit</a> | <a href="${rc.getContextPath()}/ui/user/delete/${person.id}">delete</a></td>
	</tr>
</#list>
</tbody>
</table>

<script type="text/javascript">
$(function() {
        $("#userstable").dataTable({"bJQueryUI": false, "sPaginationType": "full_numbers" });
});
</script>

<#include "/footer.ftl" >

