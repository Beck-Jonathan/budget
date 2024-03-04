/******************
Create the JSP  For adding to The  Status table
Created By Jonathan Beck3/3/2024

***************/
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addStatus" id = "addStatus" >"
        <!-- Name -->
        <div class ="row" id = "row0">
            <label for="inputstatusName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.statusNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" id="inputstatusName" name="inputstatusName" value="${results.Name}">
                <c:if test="${not empty results.statusNameerror}">
                    <div class="invalid-feedback">${results.statusNameerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign Up</button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

