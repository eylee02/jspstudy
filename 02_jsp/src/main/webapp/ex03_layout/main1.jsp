<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8");%>
	
	<jsp:include page="header.jsp">
		<jsp:param value="정치" name="title"/>
	</jsp:include>
    
   
    <div class="main">
    	<h1>정치</h1>
    	<div>
    		Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
    		Maecenas eros enim, molestie ac semper sed, tincidunt ac ligula. 
    		Curabitur rutrum nibh eget est pulvinar auctor. 
    		Duis fermentum nunc eleifend pellentesque ullamcorper. 
    		Duis sit amet ullamcorper nunc. Ut eget congue ipsum, 
    		quis scelerisque tortor. 
    		Morbi dapibus lacus odio, et pharetra sapien condimentum eu.
    		 Vivamus lacinia lobortis ipsum, sit amet viverra erat placerat eget.
    		  Fusce tristique, neque eu tempor viverra, nisi leo hendrerit arcu, 
    		  a lobortis augue felis nec lectus. Curabitur cursus metus justo, 
    		  tempus bibendum dui venenatis sed. Etiam nunc dolor, 
    		  cursus in volutpat nec, aliquet in urna. Nunc semper auctor 
    		  ullamcorper. Nulla ornare risus eget ex vulputate, a vehicula magna imperdiet.
    	</div>
    </div>
    
   <%@ include file="footer.jsp"%>