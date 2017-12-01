<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="shared" tagdir="/WEB-INF/tags"%>
<%@page import="assetc.model.*"%>



<!DOCTYPE html>
<html>
<head>
    <title></title>
    <spring:url value="/resources/kendo/styles/kendo.common.min.css" var="kendoCommonCss" />
    <spring:url value="/resources/kendo/styles/kendo.default.min.css" var="kendoDefaultCss" />
    <spring:url value="/resources/kendo/styles/kendo.default.mobile.min.css" var="kendoMobileCss" />

    <link href="${kendoCommonCss}" rel="stylesheet" />
    <link href="${kendoDefaultCss}" rel="stylesheet" />
    <link href="${kendoMobileCss}" rel="stylesheet" />
    
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <spring:url value="/resources/kendo/js/jquery.min.js" var="jqueryJs" />
    <spring:url value="/resources/kendo/js/kendo.all.min.js" var="kendoJs" />
    <script src="${jqueryJs}"></script>
    <script src="${kendoJs}"></script>
    
    
</head>
<body>
<kendo:grid name="products" pageable="true" editable="true">
    
    <kendo:grid-pageable pageSizes="true" refresh="true" buttonCount="3"></kendo:grid-pageable>
    <kendo:grid-editable mode="popup"/>
    <kendo:grid-toolbar>
        <kendo:grid-toolbarItem name="create" />
    </kendo:grid-toolbar>
                                     
    <kendo:dataSource serverPaging="true" pageSize="3">
						<kendo:dataSource-transport>
							<kendo:dataSource-transport-read url="api/products"></kendo:dataSource-transport-read>
							<kendo:dataSource-transport-create url="api/products?create" type="POST"></kendo:dataSource-transport-create>
							<kendo:dataSource-transport-update url="api/products?update" type="POST"></kendo:dataSource-transport-update>
							<kendo:dataSource-transport-destroy url="api/products?delete" type="POST"></kendo:dataSource-transport-destroy>
						</kendo:dataSource-transport>
						<kendo:dataSource-schema data="Data" total="Total">
							<kendo:dataSource-schema-model id="ProductID">
								<kendo:dataSource-schema-model-fields>
									<kendo:dataSource-schema-model-field name="ProductName">
										<kendo:dataSource-schema-model-field-validation required="true"/>
									</kendo:dataSource-schema-model-field>
									<kendo:dataSource-schema-model-field name="Supplier" defaultValue="<%= new Supplier() %>"></kendo:dataSource-schema-model-field>
									<kendo:dataSource-schema-model-field name="Category" defaultValue="<%= new Category() %>"></kendo:dataSource-schema-model-field>
									<kendo:dataSource-schema-model-field name="UnitPrice" type="number"></kendo:dataSource-schema-model-field>
									<kendo:dataSource-schema-model-field name="UnitsInStock" type="number"></kendo:dataSource-schema-model-field>
									<kendo:dataSource-schema-model-field name="Discontinued" type="boolean"></kendo:dataSource-schema-model-field>
								</kendo:dataSource-schema-model-fields>
							</kendo:dataSource-schema-model>
						</kendo:dataSource-schema>
    </kendo:dataSource>
          
    <kendo:grid-columns>
						<kendo:grid-column field="ProductName" title="Product"></kendo:grid-column>
						<kendo:grid-column field="Supplier" title="Supplier" editor="supplierEditor" template="#: Supplier.SupplierName #"></kendo:grid-column>
						<kendo:grid-column field="Category" title="Category" width="150px" editor="categoryEditor" template="#: Category.CategoryName #"></kendo:grid-column>
						<kendo:grid-column field="UnitPrice" title="Price" format="{0:c}" width="75px"></kendo:grid-column>
						<kendo:grid-column field="UnitsInStock" title="# In Stock" width="80px"></kendo:grid-column>
						<kendo:grid-column field="Discontinued" title="Discontinued" width="100px"></kendo:grid-column>
						<kendo:grid-column>
							<kendo:grid-column-command>
								<kendo:grid-column-commandItem name="edit"></kendo:grid-column-commandItem>
								<kendo:grid-column-commandItem name="destroy"></kendo:grid-column-commandItem>
							</kendo:grid-column-command>
						</kendo:grid-column>
</kendo:grid-columns>
  </kendo:grid>
       
          <script>
	
		function categoryEditor(container, options) {
			$("<input data-text-field='CategoryName' data-value-field='CategoryID' data-bind='value:" + options.field + "' />")
			.appendTo(container)
			.kendoDropDownList({
				dataSource: {
					transport: {
						read: "api/categories"
					}
				}
			});
		};
		
		function supplierEditor(container, options) {
			$("<input data-text-field='SupplierName' data-value-field='SupplierID' data-bind='value:" + options.field + "' />")
			.appendTo(container)
			.kendoDropDownList({
				dataSource: {
					transport: {
						read: "api/suppliers"
					}
				}
			});
		}
	
	</script>
        
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</body>
</html>



    