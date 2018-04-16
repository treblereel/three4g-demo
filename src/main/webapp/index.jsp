<!DOCTYPE html>
<%
   String contextPath = getServletContext().getContextPath();
%>
<html lang="en">
<head>
     <title>Three.js Example</title>
     <meta charset="UTF-8">
     <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, minimal-ui" name="viewport" />
     <meta name="mobile-web-app-capable" content="yes">
     <meta name="apple-mobile-web-app-capable" content="yes">
     <link rel="stylesheet" href="<%=contextPath%>/css/application.css">
     <link rel="stylesheet" href="<%=contextPath%>/css/dat.gui.css">


     <script type="text/javascript" src="<%=contextPath%>/app/app.nocache.js"></script>

     <script type="text/javascript" src="<%=contextPath%>/js/aframe-master.js"></script>
</head>

<body>
<script>
  erraiBusRemoteCommunicationEnabled = false;
</script>

</body>
</html>
