<#include "/header.ftl" >


<span class="large">Group List</span>

</div>
<div class="span-24 last" style="margin-top: 1em;">

<table class="display" cellpadding="0" cellspacing="0" border="0" id="orgstable" > 

<thead> 
	<tr> 
		<th>user</th>
		<th>actions</th>
	</tr>
</thead>

<tbody>
<#list list as org>
        <tr>
		<td>${org.name!"N/A"}</td>
		<td><a href="${rc.getContextPath()}/ui/group/edit/${org.id}">edit</a></td>
	</tr>
</#list>
</tbody>
</table>

<script type="text/javascript">
$(function() {
        $("#orgstable").dataTable({"bJQueryUI": true, "sPaginationType": "full_numbers" });
});
</script>

<#include "/footer.ftl" >

