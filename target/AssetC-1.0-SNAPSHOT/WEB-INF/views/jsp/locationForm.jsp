<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Add Energy</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
                    
<body>
<spring:url value="/locations/locationlist" var="urlHomeLocation" />
<spring:url value="/locations/add" var="urlAddLocation" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHomeLocation}">Location List</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddLocation}">Add Location</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="container">

	<c:choose>
		<c:when test="${locationForm['new']}">
			<h1>Add Location</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Location</h1>
		</c:otherwise>
	</c:choose>
	<br />

	

	<form:form class="form-horizontal" method="post" modelAttribute="locationForm">
		<form:hidden path="locationno" />

                <spring:bind path="locationid">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Location ID</label>
				<div class="col-sm-10">
					<form:input path="locationid" type="text" class="form-control " id="locationid" placeholder="locationid" />
					<form:errors path="locationid" class="control-label" />
				</div>
			</div>
		</spring:bind>
                
                
                <spring:bind path="parentname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Parent Name</label>
				<div class="col-sm-10">
					<form:input path="parentname" class="form-control" id="parentname" placeholder="parentname" />
					<form:errors path="parentname" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="description">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Short Description</label>
				<div class="col-sm-10">
					<form:input path="description" class="form-control" id="description" placeholder="description" />
					<form:errors path="description" class="control-label" />
				</div>
			</div>
		</spring:bind>	
        
        
                 <spring:bind path="longdescription">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Long Description</label>
				<div class="col-sm-10">
					<form:input path="longdescription" class="form-control" id="longdescription" placeholder="longdescription" />
					<form:errors path="longdescription" class="control-label" />
				</div>
			</div>
		</spring:bind>	
        
		<spring:bind path="parentcraft">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Parent Craft</label>
				<div class="col-sm-10">
					<form:input path="parentcraft" class="form-control" id="parentcraft" placeholder="parentcraft" />
					<form:errors path="parentcraft" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="craft">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Craft</label>
				<div class="col-sm-10">
					<form:input path="craft" class="form-control" id="craft" placeholder="craft" />
					<form:errors path="craft" class="control-label" />
				</div>
			</div>
		</spring:bind>	
				
		<spring:bind path="equipmenttype">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Equipment Type</label>
				<div class="col-sm-10">
					<form:input path="equipmenttype" class="form-control" id="equipmenttype" placeholder="equipmenttype" />
					<form:errors path="equipmenttype" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="failurecode">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Failure Code</label>
				<div class="col-sm-10">
					<form:input path="failurecode" class="form-control" id="failurecode" placeholder="failurecode" />
					<form:errors path="failurecode" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		
	<spring:bind path="systemstatus">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">System Status</label>
				<div class="col-sm-10">
					<form:input path="systemstatus" class="form-control" id="systemstatus" placeholder="systemstatus" />
					<form:errors path="systemstatus" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="userstatus">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">User Status</label>
				<div class="col-sm-10">
					<form:input path="userstatus" class="form-control" id="userstatus" placeholder="userstatus" />
					<form:errors path="userstatus" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="criticality">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Criticality</label>
				<div class="col-sm-10">
					<form:input path="criticality" class="form-control" id="criticality" placeholder="criticality" />
					<form:errors path="criticality" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="co">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">CO</label>
				<div class="col-sm-10">
					<form:input path="co" class="form-control" id="co" placeholder="co" />
					<form:errors path="co" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="sud">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">SUD</label>
				<div class="col-sm-10">
					<form:input path="sud" class="form-control" id="sud" placeholder="sud" />
					<form:errors path="sud" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="planningplant">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Planning Plant</label>
				<div class="col-sm-10">
					<form:input path="planningplant" class="form-control" id="planningplant" placeholder="planningplant" />
					<form:errors path="planningplant" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="maintenanceplant">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Maintenance Plant</label>
				<div class="col-sm-10">
					<form:input path="maintenanceplant" class="form-control" id="maintenanceplant" placeholder="maintenanceplant" />
					<form:errors path="maintenanceplant" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		
		<spring:bind path="physicallocation">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Physical Location</label>
				<div class="col-sm-10">
					<form:input path="physicallocation" class="form-control" id="physicallocation" placeholder="physicallocation" />
					<form:errors path="physicallocation" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="manufacturer">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Manufacturer</label>
				<div class="col-sm-10">
					<form:input path="manufacturer" class="form-control" id="manufacturer" placeholder="manufacturer" />
					<form:errors path="manufacturer" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="partnum">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Part Num</label>
				<div class="col-sm-10">
					<form:input path="partnum" class="form-control" id="partnum" placeholder="partnum" />
					<form:errors path="partnum" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="modelnum">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Model Num</label>
				<div class="col-sm-10">
					<form:input path="modelnum" class="form-control" id="modelnum" placeholder="modelnum" />
					<form:errors path="modelnum" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		<spring:bind path="serialnum">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Serial Num</label>
				<div class="col-sm-10">
					<form:input path="serialnum" class="form-control" id="serialnum" placeholder="serialnum" />
					<form:errors path="serialnum" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		
		
		
		<spring:bind path="customfield">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Custom Field</label>
				<div class="col-sm-10">
					<form:input path="customfield" class="form-control" id="customfield" placeholder="customfield" />
					<form:errors path="customfield" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
	
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${locationForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
                
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />       
	</form:form>

</div>

<div class="container">
	<hr>
	<footer>
		<p>&copy; AddEnergy.no 2017</p>
	</footer>
</div>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>

</body>
</html>