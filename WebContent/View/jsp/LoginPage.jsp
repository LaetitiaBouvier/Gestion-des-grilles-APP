<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,gestiondesgrillesapp.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/connexion.css" rel="stylesheet">
<!-- Force Mobile device not to zoom -->
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Login</title>

<style type="text/css">
/*
 * Specific styles of signin component
 */
/*
 * General styles
 */
 
html {
    height: 100%;
}
 
body, html {
 	background-image: url("http://commons.studyrama.com/data/bc2e/152/photo_806.jpg");
 	background-size: cover;
}

.card-container.card {
    max-width: 350px;
    padding: 40px 40px;
}

.btn {
    font-weight: 700;
    height: 36px;
    -moz-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    cursor: default;
}

/*
 * Card component
 */
.card {
    background-color: #F7F7F7;
    /* just in case there no content*/
    padding: 20px 25px 30px;
    margin: 0 auto 25px;
    margin-top: 50px;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
    width: auto; // 96px;
    height: 96px;
    margin: 0 auto 10px;
    display: block;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
}

/*
 * Form styles
 */
.profile-name-card {
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    margin: 10px 0 0;
    min-height: 1em;
}

.reauth-email {
    display: block;
    color: #404040;
    line-height: 2;
    margin-top: 10px;
/*     margin-bottom: 10px; */
    font-size: smaller;
    text-align: left;
/*     overflow: hidden; */
    text-overflow: ellipsis;
    white-space: nowrap;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

.form-signin #inputEmail,
.form-signin #inputPassword {
    direction: ltr;
    height: 44px;
    font-size: 16px;
}

.form-signin input[type=email],
.form-signin input[type=password],
.form-signin input[type=text],
.form-signin button {
    width: 100%;
    display: block;
    margin-bottom: 10px;
    z-index: 1;
    position: relative;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

.form-signin .form-control:focus {
    border-color: rgb(104, 145, 162);
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
}

.btn.btn-signin {
    /*background-color: #4d90fe; */
    background-color: rgb(104, 145, 162);
    /* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
    padding: 0px;
    font-weight: 700;
    font-size: 14px;
    height: 36px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    border: none;
    -o-transition: all 0.218s;
    -moz-transition: all 0.218s;
    -webkit-transition: all 0.218s;
    transition: all 0.218s;
}

.btn.btn-signin:hover,
.btn.btn-signin:active,
.btn.btn-signin:focus {
    background-color: rgb(12, 97, 33);
}

.false-information  {
    color: red;
}
</style>
</head>

<body>


    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="http://www.financetesetudes.com/wp-content/uploads/2013/08/logo-isep.jpg" />
            <p id="profile-name" class="profile-name-card">ISEP Evaluation APP Login</p>
            <form class="form-signin" action="/Gestion_des_grilles_APP/LoginServlet" method="post" id="login-form">
                <span id="reauth-email" class="reauth-email">Utilisez vos informations de connexion moodle</span>
                <input type="text" name="ISEPid" id="ISEPid" class="form-control" placeholder="ISEP id" required autofocus>
                <input type="password" name="ISEPpassword" id="ISEPpassword" class="form-control" placeholder="ISEP Password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">connexion</button>
            </form><!-- /form -->
        </div><!-- /card-container -->
    </div><!-- /container -->


</body>
</html>