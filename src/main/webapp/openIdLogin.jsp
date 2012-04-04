<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>

<head>
    <title>
    	<spring:message code="loginPageTitle"/>
    </title>

    <c:url value="/resources/" var="resources"/>
    <link rel="stylesheet" href="${resources}/css/openid.css" />
	<c:url value="/resources/css/common.css" var="commonCssUrl"/>
    <link rel="stylesheet" type="text/css" href="${commonCssUrl}" />
    <script type="text/javascript" src="${resources}/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${resources}/js/jquery.openid.js"></script>
</head>

<body>

	<div id="container">
		<div id="leftPanel">
			<div class="loginPanel">
				<c:if test="${not empty param.login_error}">
				  <font color="red">
				    Your login attempt was not successful, try again.<br/><br/>
				    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
				  </font>
				</c:if>
			
				<form class="openid" method="post" action="<c:url value='j_spring_openid_security_check'/>">
					<div>
						<ul class="providers">
							<li id="openId" class="openid" title="OpenID">
								<img src="${resources}/img/openidW.png"	alt="icon" /> 
								<span><strong>http://{your-openid-url}</strong></span>
							</li>
							<li id="google" class="direct" title="Google">
								<img src="${resources}/img/googleW.png"	alt="icon" />
								<span>https://www.google.com/accounts/o8/id</span></li>
							<li class="direct" title="Yahoo">
								<img src="${resources}/img/yahooW.png" alt="icon" />
								<span>http://yahoo.com/</span>
							</li>
							<li class="username" title="AOL screen name">
								<img src="${resources}/img/aolW.png" alt="icon" />
								<span>http://openid.aol.com/<strong>username</strong></span>
							</li>
							<li id="myOpenId" class="username" title="MyOpenID user name">
								<img src="${resources}/img/myopenid.png" alt="icon" />
								<span>http://<strong>username</strong>.myopenid.com/</span>
							</li>
							<li class="username" title="Flickr user name">
								<img src="${resources}/img/flickr.png" alt="icon" />
								<span>http://flickr.com/<strong>username</strong>/</span>
							</li>
							<li class="username" title="Technorati user name">
								<img src="${resources}/img/technorati.png" alt="icon" />
								<span>http://technorati.com/people/technorati/<strong>username</strong>/</span>
							</li>
							<li class="username" title="Wordpress blog name">
								<img src="${resources}/img/wordpress.png" alt="icon" />
								<span>http://<strong>username</strong>.wordpress.com</span>
							</li>
							<li class="username" title="Blogger blog name">
								<img src="${resources}/img/blogger.png" alt="icon" />
								<span>http://<strong>username</strong>.blogspot.com/</span>
							</li>
							<li class="username" title="LiveJournal blog name">
								<img src="${resources}/img/livejournal.png" alt="icon" />
								<span>http://<strong>username</strong>.livejournal.com</span>
							</li>
							<li class="username" title="ClaimID user name">
								<img src="${resources}/img/claimid.png" alt="icon" />
								<span>http://claimid.com/<strong>username</strong></span>
							</li>
							<li class="username" title="Vidoop user name">
								<img src="${resources}/img/vidoop.png" alt="icon" />
								<span>http://<strong>username</strong>.myvidoop.com/</span>
							</li>
							<li class="username" title="Verisign user name">
								<img src="${resources}/img/verisign.png" alt="icon" />
								<span>http://<strong>username</strong>.pip.verisignlabs.com/</span>
							</li>
						</ul>
					</div>
		            <spring:message code="loginButton" var="loginButtonText"/>
					<fieldset>
						<label for="openid_username">
							<spring:message code="login.hint.username"/>
						</label>
						<div>
							<span></span>
							<input type="text" name="openid_username" />
							<span></span>
							<input name="login" type="submit" value="${loginButtonText}" />
						</div>
					</fieldset>
					<fieldset>
						<label for="openid_identifier">
							<spring:message code="login.hint.identifier"/>
						</label>
						<div>
							<input type="text" name="openid_identifier" /> 
							<input type="submit" value="${loginButtonText}" />
						</div>
					</fieldset>
				</form>
				<script type="text/javascript">  $(function() { $("form.openid:eq(0)").openid(); });</script>
			</div>
		</div>
		
		<div id="rightPanel">
			<div class="loginPanel">
			    <a href='<c:url value="/"/>' class="idCardLogin">
		  			<label for="idcard">
		                <spring:message code="login.hint.idcard"/>
					</label>
		  			<img src="${resources}/img/idkaart.gif" alt="icon" name="idcard"/>
		        </a>
			</div>
		</div>
		
		<div class="clear"></div>
	</div>
</body>
</html>