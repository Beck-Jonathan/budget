<%--************
Create the JSP  For Viewing All of The  Picture table
 Created By Jonathan Beck 10/10/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container" id="addr" addr="${appURL}" objectType="picture">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Pictures</h1>
            <p>There ${Pictures.size() eq 1 ? "is" : "are"}&nbsp;${Pictures.size()} Picture${Pictures.size() ne 1 ? "s" : ""}</p>
            Add Picture   <a href="addPicture">Add</a>
            <c:if test="${Pictures.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Picture_ID</th>
                        <th scope="col">Album_Name</th>
                        <th scope="col">Contributor_Name</th>
                        <th scope="col">description</th>
                        <th scope="col">Is_Active</th>
                        <th scope="col">is_Approved</th>
                        <th scope="col">Edit</th>
                       <!-- <th scope="col">Delete</th> -->
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Pictures}" var="picture">
                        <tr>
                            <td><a href = "editPicture?pictureid=${picture.picture_ID}&mode=view">${fn:escapeXml(picture.picture_ID)}</a></td>
                            <td>${fn:escapeXml(picture.album.album_Name)}</td>
                            <td>${fn:escapeXml(picture.contributor.first_Name)}&nbsp${fn:escapeXml(picture.contributor.last_Name)}</td>
                            <td>${fn:escapeXml(picture.description)}</td>


                            <td><input type="checkbox" id="${picture.picture_ID}" class="Activation_Box" <c:if test="${picture.is_Active}">checked</c:if>> <p  id="${picture.picture_ID}_status"></p></td>
                            <td><input type="checkbox" id="${picture.picture_ID}_a" class="Approval_Box" <c:if test="${picture.is_Approved}">checked</c:if>><p  id="${picture.picture_ID}_a_status"></p></td>
                            <td><a href = "editPicture?pictureid=${picture.picture_ID}&mode=edit" > Edit </a></td>
                            <%-- <td><a href = "deletepicture?pictureid=${picture.picture_ID}&mode=<c:choose><c:when test="${picture.is_Active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!picture.is_Active}">un</c:if>Delete </a></td> --%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/crrg/foot.jsp"%>
