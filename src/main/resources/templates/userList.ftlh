<#import "parts/Common.ftlh" as p>
<@p.page>
<div class="col-sm-7 mx-auto">
<table class="table table-striped">
    <thead class="table-primary">
    <th scope="col">Name</th>
    <th scope="col">Role</th>
    <th scope="col"></th>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td >${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/users/${user.id}">Edit</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</div>
</@p.page>