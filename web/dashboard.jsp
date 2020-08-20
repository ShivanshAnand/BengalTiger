<%-- 
    Document   : dashboard
    Created on : Aug 19, 2020, 3:02:58 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <style type="text/css">
            #user-info {
                padding : 16px;
                border : 1px dashed black;
            }
            #past-forms {
                padding : 16px;
                border : 1px dashed black;
            }
        </style>
    </head>
    <body>
        <div id="user-info">
            <label>${first_name}</label>
            <label>${last_name}</label>
            <label>${email}</label>
        </div>
        <ol id="past-forms">
            
        </ol>
    </body>
</html>
