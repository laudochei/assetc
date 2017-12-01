<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    
    <c:url var="testUrl" value="/builds" scope="request" />
    
    
    
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
    
   

    
<style type="text/css">
        .auto-style1 {
            height: 26px;
        }
        .auto-style2 {
            width: 84%;
        }
        .auto-style3 {
            height: 26px;
            width: 240px;
        }
        .auto-style4 {
            width: 240px;
        }
        .auto-style8 {
            text-align: center;
        }
</style>





</head>
<body style="width: 701px">
    <table class="auto-style2">
        <tr>
            <td class="auto-style1"><strong>TreeView</strong></td>
            <td class="auto-style3">
            <strong>Operations</strong>
            <div id="javaquery" style="display:none;">
                   <table style="width:100%;">
                    <tr>
                        <td class="auto-style5">
                           
                            <input type="hidden" name="t1" id="t1"/>
                            
                        </td>
                        <td class="auto-style5">
                            
                            <input type="hidden" name="t2" id="t2"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="auto-style9" colspan="2">
                            
                            Enter name of node: <table style="width:100%;">
                                <tr>
                                    <td class="auto-style5">Firstname</td>
                                    <td class="auto-style5">
                                        <input type="text" name="t3" id="t3" class="l-textbox" value="first-name" /></td>
                                    <td class="auto-style5">Lastname</td>
                                    <td class="auto-style5">
                                        <input type="text" name="t4" id="t4" class="l-textbox" value="last-name" /></td>
                                </tr>
                                </table>
                        </td>
                    </tr>
                    
                    
                    <tr>
                        <td class="auto-style8" colspan="2">
                            <input name="addnode" type="button" value="Add Node" id ="addnode" onclick="AddNode()"/>
                            <input name="updatenode" type="button" value="Update Node" id ="updatenode" onclick="UpdateNode()"/>
                            <input name="deletenode" type="button" value="Delete Node" id ="deletenode" onclick="DeleteNode()"/>
                            <input name="testnode" type="button" value="Test Node" id ="testnode" onclick="TestNode()"/>
                            <input type="hidden" name="firstname" value="laud" />
                        </td>
                    </tr>
                    </table>       
                </div>
            </td>
        </tr>
        <tr>
            <td>
                
                <div class="demo-section k-content">
<kendo:treeView name="employees" dataTextField="FullName" dragAndDrop="true" dragend="handle_dragend" select="handle_select" change="handle_change">
  <kendo:dataSource>
      <kendo:dataSource-transport read="api/employees" update="api/dragdrop">
      <kendo:dataSource-transport-parameterMap>
        <script>
          function parameterMap(options, operation) {
            if (operation === "read") {
              return { EmployeeID: options.EmployeeID };
            } 
            if (operation === "update") {
              return { EmployeeID: options.EmployeeID };
            } 
            
            return options;
            
            
            
          }
        </script>
      </kendo:dataSource-transport-parameterMap>
    </kendo:dataSource-transport>     
    <kendo:dataSource-schema>
      <kendo:dataSource-schema-hierarchical-model hasChildren="HasChildren" id="EmployeeID"></kendo:dataSource-schema-hierarchical-model>
    </kendo:dataSource-schema>
  </kendo:dataSource>
 </kendo:treeView>
</div>
 
            </td>
            <td class="auto-style4">   
                
            </td>
        </tr>
        
    </table>
    
    
 <script>
    function handle_dragend(e) {
        // Code to handle the drop event.
        
        var originDataItem = $("#employees").data('kendoTreeView').dataItem(e.sourceNode);
        var originNodeFullname = originDataItem.FullName;
        var originNodeEmployeeID = originDataItem.EmployeeID;
        var originNodeManagerID = originDataItem.ManagerID;
        
        var destinationDataItem = $("#employees").data('kendoTreeView').dataItem(e.destinationNode);
        var destinationNodeFullname = destinationDataItem.Fullname;
        var destinationNodeEmployeeID = destinationDataItem.EmployeeID;
        var destinationNodeManagerID = destinationDataItem.ManagerID;
        //destinationDataItem.add(originDataItem);
        var dropPosition = e.dropPosition;
        alert("Source EmployeeID: " + originNodeEmployeeID + " Source ManagerID: " + originNodeManagerID + "  Destination EmployeeID: " + destinationNodeEmployeeID + "Destination ManagerID: " + destinationNodeManagerID + " Drop position: " + dropPosition);
        
        $(document).ready(function(){
            var value1 = originNodeEmployeeID; 
            var value2 = destinationNodeManagerID;
            $.get("${testUrl}/asset/dragdrop",{p:value1, q:value2},function(data){
            $("#javaquery").html(data);
            });       
        }); 
    
        //$("#employees").data('kendoTreeView').dataSource.read();
        //$("#employees").data('kendoTreeView').refresh();
    }
    
    function handle_select(e) {
         
        // Code to handle the select event.
        //alert("Laud Charles!!" + this.text(e.node)); 
        var originDataItem = $("#employees").data('kendoTreeView').dataItem(e.node);
        var originNodeEmployeeID = originDataItem.EmployeeID;
        var originNodeManagerID = originDataItem.ManagerID;
        
        //alert("Laud Charles!!" + originNodeEmployeeID);
        //alert("Laud Charles!!" + this.text(e.node));
        
        
        //$('#t1').val(originNodeEmployeeID);
        //$('#t2').val(originNodeManagerID);
        //$('#javaquery').show();  
        
        
        
        $(document).ready(function(){
            $('#t1').val(originNodeEmployeeID);
            $('#t2').val(originNodeManagerID);
           
        });
        
         $('#javaquery').show();
        /*
        //$('#javaquery').show();
        //$("#t1").prop("disabled", bool);
        //$("#t2").prop("disabled", bool);
       */
      
    }
    
    function handle_change(e) {
        // Code to handle the change event.
        $('#javaquery').show();
        //$("#employees").data('kendoTreeView').dataSource.read();
        //$("#t1").prop("disabled", bool);
        //$("#t2").prop("disabled", bool);
        
        
    }
    
    
    $(document).ready(function(){
        $('#addnode').click(function(){
            //$val=$('#t4').val();
            var value1=$('#t1').val();
            var value2=$('#t2').val();
            var value3=$('#t3').val();
            var value4=$('#t4').val();
            
            //alert($val);
            //alert(value1);
            //alert(value2);
            //alert(value3);
            //$.get("${pageContext.request.contextPath}" + "/addnode.jsp",{p:value1, q:value2, r:value3, s:value4},function(data){
            
           
            $.get("${testUrl}/asset/addnode",{p:value1, q:value2, r:value3, s:value4},function(data){   
                
            $("#javaquery").html(data);
            }); 
        //refresh the treeview to show the added node    
        //$("#employees").data('kendoTreeView').dataSource.read();
        location.reload();
        
       
            
	});
    });
    
   
   
   $(document).ready(function(){
        $('#deletenode').click(function(){
            //$val=$('#t4').val();
            var value1=$('#t1').val();

            //alert($val);
            alert(value1);
            
            
            
                
            //$.get("${pageContext.request.contextPath}" + "/deletenode.jsp",{p:value1},function(data){ 
                
                
                                
            $.get("${testUrl}/asset/deletenode",{p:value1},function(data){                     
            $("#javaquery").html(data);
            }); 

            //clear textbox value containing managerID and employeeID
            $('#t1').val(''); 
            $('#t2').val(''); 

            //refresh the treeview to show the added node    
            //$("#employees").data('kendoTreeView').dataSource.read();
            //location.reload();
            
            
	});
    });
    
   
       
    
    /*
    $(document).ready(function(){
        $('#deletenode').click(function(){
            //$val=$('#t4').val();
            var value1=$('#t1').val();
            
             $.ajax({  
                type : "Get",   
                url : "/asset/deletenode",   
                data : "name=" + name + "&gender=" + gender + "&email="  
                  + email + "&phone=" + phone + "&city=" + city,  
                success : function(response) {  
                 alert(response);   
                },  
                error : function(e) {  
                 alert('Error: ' + e);   
                }  
               });  

            //clear textbox value containing managerID and employeeID
            $('#t1').val(''); 
            $('#t2').val(''); 

            //refresh the treeview to show the added node    
            //$("#employees").data('kendoTreeView').dataSource.read();
            //location.reload();
            
            
	});
    });
    */
   
   
   
    
    
    
    
    $(document).ready(function(){
        $('#updatenode').click(function(){
            var value1=$('#t1').val();
            var value2=$('#t3').val();
            var value3=$('#t4').val();

            //alert($val);
            alert(value1);

            //$.get("${pageContext.request.contextPath}" + "/updatenode.jsp",{p:value1, q:value2, r:value3},function(data){
                
            $.get("${testUrl}/asset/updatenode",{p:value1, q:value2, r:value3},function(data){
            $("#javaquery").html(data);
            }); 

            //clear textbox value containing managerID and employeeID
            $('#t1').val(''); 
            $('#t2').val(''); 
            $('#t3').val('');
            $('#t4').val('');

            //refresh the treeview to show the added node    
            //$("#employees").data('kendoTreeView').dataSource.read();
            location.reload();
	});
    });
    
    
    
    function GetValue1()
    {
     var str = document.getElementById('t4').value;
     alert(str);
    }
    
    
    $(document).ready(function() {
	    var treeview = $("#employees").data("kendoTreeView"),	
	        handleTextBox = function(callback) {
	            return function(e) {
	                if (e.type != "keypress" || kendo.keys.ENTER == e.keyCode) {
	                    callback(e);
	                }
	            };
	        };
	
	
	    $("#disableNode").click(function() {
	        var selectedNode = treeview.select();
	
	        treeview.enable(selectedNode, false);
	    });
	
	    $("#enableAllNodes").click(function() {
	        var selectedNode = treeview.select();
	
	        treeview.enable(".k-item");
	    });
	
	    $("#removeNode").click(function() {
	        var selectedNode = treeview.select();
	
	        treeview.remove(selectedNode);
	    });
	
	    $("#expandAllNodes").click(function() {
	        treeview.expand(".k-item");
	    });
	
	    $("#collapseAllNodes").click(function() {
	        treeview.collapse(".k-item");
	    });
	
	    var append = handleTextBox(function(e) {
	            var selectedNode = treeview.select();
	
	            // passing a falsy value as the second append() parameter
	            // will append the new node to the root group
	            if (selectedNode.length == 0) {
	                selectedNode = null;
	            }
	
	            treeview.append({
	                text: $("#appendNodeText").val()
	            }, selectedNode);
	        });
	
	    $("#appendNodeToSelected").click(append);
	    $("#appendNodeText").keypress(append);
	
	    // datasource actions
	
	    var ascending = false;
	
	    $("#sortDataSource")
	        .text(ascending ? "Sort ascending" : "Sort descending")
	        .click(function() {
	            treeview.dataSource.sort({
	                field: "text",
	                dir: ascending ? "asc" : "desc"
	            });
	
	            ascending = !ascending;
	
	            $(this).text(ascending ? "Sort ascending" : "Sort descending")
	        });
	
	    var filter = handleTextBox(function(e) {
	        var filterText = $("#filterText").val();
	
	        if (filterText !== "") {
	            treeview.dataSource.filter({
	                field: "text",
	                operator: "contains",
	                value: filterText
	            });
	        } else {
	            treeview.dataSource.filter({});
	        }
	    });
	
	    $("#filterDataSource").click(filter);
	    $("#filterText").keypress(filter);
	});
    
    
    
</script>

</body>
</html>



    