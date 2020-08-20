<%-- 
    Document   : form-builder
    Created on : Aug 20, 2020, 2:52:17 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>  <%= "Edit : " + request.getParameter("title") %> </title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src="form-builder-js.js"></script>

	<style type="text/css">

		body {
			background-image: url("check_bg.png");
			background-repeat: repeat;
		}

		.inner {
			width: 100%;
			padding: 16px;
		}

		#builder-top-nav {
			background-color: white;
			box-shadow:  0 4px 8px rgb(184,184,184);
			margin-bottom: 24px;
		}

		#toolbox {
			float: left;
			width: 22vw;
			margin-left: 2vw;
			border: 1px solid rgb(184,184,184);
			background-color: white;
		}

		.tool {
			width: 100%;
			padding: 4px 8px;
			border: 1px solid rgb(184,184,184);
			border-radius: 4px;
			margin-bottom: 8px;
			cursor: pointer;
		}

		.tool:hover {
			padding: 8px;
		}

		#properties-box {
			float: right;
			width: 22vw;
			height: 20vh;
			margin-right: 2vw;
			border: 1px solid rgb(184,184,184);
			background-color: white;			
		}

		#main-form {
			width: 50vw;
			margin-left: 25vw;
			border: 1px solid rgb(184,184,184);
			background-color: white;				
		}

		.question {
			padding: 8px;
			margin: 8px;
			border: 1px dashed rgb(0,122,255);
		}

		.question .full-inp{
			display: inline-block;
			width: 100%;
		}

		.question textarea {
			display: inline-block;
			width: 100%;
		}

		.question .editable-input {
			display: block;
			font-size: 24px;
			font-weight: bold;
			color: rgb(30,30,30);
			border: none;
			outline: none;
			margin-bottom: 4px;
			width: 100%;
		}

	</style>
</head>

<body>

	<nav class="navbar navbar-light" id="builder-top-nav">
            <span class="navbar-brand mb-0 h1"><%= request.getParameter("title") %></span>	
	</nav>

	<div id="toolbox">
		<div class="inner" id="toolbox-inner">
			<h5>Toolbox</h5>
			<div class="tool" id="tool-name" draggable="true">
				Name
			</div>
			<div class="tool" id="tool-slt" draggable="true">
				Single Line Text
			</div>
			<div class="tool" id="tool-mlt" draggable="true">
				Multi Line Text
			</div>
<!-- 			<div class="tool" id="tool-rb" draggable="true">
				Radio button
			</div> -->
			<div class="tool" id="tool-s" draggable="true">
				Slider
			</div>
<!-- 			<div class="tool" id="tool-submit" draggable="true">
				Submit
			</div> -->
		</div>
	</div>

	<div id="properties-box">
	</div>

	<div id="main-form">
		<form class="inner" id="main-form-inner"  ondrop="drop(event)" ondragover="allowDrop(event)" action="form-handler" method="post">
                    
                    <input type="hidden" name="fid" value="<%= request.getParameter("fid") %>" />
                    <input type="hidden" name="uid" value="<%= request.getParameter("uid") %>" />
                    
		</form>
	</div>

</body>
</html>