<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
User dash<br/>

    <div class ="row" id = "row0">
        <div class ="col-md-4" id = "r0c1">
<a href="My-Teams">View and update my teams</a><br/>
<a href="My-Events"> View My Events</a><br/>
<a href="all-Facilitys"> View the League Facilities</a><br/>
        </div>
        <div class ="col-md-8" id = "r0c2">
            <%@include file="/WEB-INF/personal-project/partialEvents.jsp"%>
        </div>
        <div class ="row" id = "row1">
        <div class ="col-md-4" id = "r1c1">

        </div>
        <div class ="col-md-8" id = "r1c2">
            <%@include file="/WEB-INF/personal-project/partialTeams.jsp"%>
        </div>
        </div>
</div>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>