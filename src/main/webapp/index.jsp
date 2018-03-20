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

     <script type="text/javascript" src="<%=contextPath%>/js/three.js"></script>
     <script type="text/javascript" src="<%=contextPath%>/js/FirstPersonControls.js"></script>
     <script type="text/javascript" src="<%=contextPath%>/js/OrbitControls.js"></script>

     <script type="text/javascript" src="webjars/datguivr/0.1/datguivr.js"></script>
     <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.js"></script>

     <script type="text/javascript" src="<%=contextPath%>/app/app.nocache.js"></script>
</head>

<body>
<script>
  erraiBusRemoteCommunicationEnabled = false;
</script>

</body>
</html>
