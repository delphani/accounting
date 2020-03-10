<%--
  Created by IntelliJ IDEA.
  User: abdoli
  Date: 3/2/2020
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<html>
<head>
    <style>
        span.error {
            color: red;
            display: inline-block;
        }
    </style>
</head>
<body>

<h3> Registration Form <h3>
    <br/>
    <frm:form action="adress/save" method="post" modelAttribute="adress">
    <pre>
                  province <frm:input path="province" />
                       <frm:errors path="province" cssClass="error" />

                   city <frm:input path="city" />
                       <frm:errors path="city" cssClass="error" />

                    street <frm:input path="street" />
                       <frm:errors path="street" cssClass="error" />
                    pelak <frm:input path="pelak" />
                       <frm:errors path="pelak" cssClass="error" />

                                  <input type="submit" value="Submit" />
  </pre>
    </frm:form>
</body>
</html>
