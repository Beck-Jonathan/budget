<%@ taglib prefix="sessionScope" uri="http://java.sun.com/jsp/jstl/core" %>

<div class = "container">
    <c:if test="${empty User}">
    <form method="post" action="${appURL}/signin" id = "JoinUs" >

<!-- User_Name -->
        <div class ="row" id = "Loginrow0">
            <label for="inputuserUser_Name" class="form-label">User_Name</label>
            <div class="input-group input-group-lg">
                <input type="hidden" id="currentpage" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>
                <input type="text" class="<c:if test="${not empty results.userUser_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_Name" id="inputuserUser_Name" name="inputuserUser_Name" value="${results.User_Name}">
                <c:if test="${not empty results.userUser_Nameerror}">
                    <div class="invalid-feedback">${results.userUser_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- User_PW -->
        <div class ="row" id = "Loginrow1">
            <label for="inputuserUser_PW" class="form-label">User_PW</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.userUser_PWerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_PW" id="inputuserUser_PW" name="inputuserUser_PW" value="${results.User_PW}">
                <c:if test="${not empty results.userUser_PWerror}">
                    <div class="invalid-feedback">${results.userUser_PWerror}</div>
                </c:if>
            </div>
        </div>
        <!-- button -->

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign in!</button></div>

        </div>


    </form>
        <c:if test="${not empty dbStatus}">
            <div class="invalid-feedback">${sessionScope.dbStatus}</div>
        </c:if>

    </c:if>
        <c:if test="${not empty User}">


    <p>${User.email}</p>
    <form method="post" action="${appURL}/signout" id = "signout" >
            <div class="align-items-center mt-0">
                <input type="hidden" id="currentpage1" name="currentpage" value=${requestScope['javax.servlet.forward.request_uri']}/>
                <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign out</button></div>

            </div>
    </form>
</c:if>

</div>



