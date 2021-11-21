<#import "parts/Common.ftlh" as p>
<@p.page>
Edit user
<div class="form-row">
    <div class="form-group col-md-6">
        <form action="/user" method="post">
            <input type="text" name="userName" value="${user.username}">
            <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Save</button>
        </form>
    </div>
</div>
</@p.page>