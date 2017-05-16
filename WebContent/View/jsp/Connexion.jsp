<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/connexion.css" rel="stylesheet">
<!-- Force Mobile device not to zoom -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Connexion</title>
</head>
<body>

    <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="..\ressources\logo_isep.png" />
            <p id="profile-name" class="profile-name-card">ISEP grille</p>
            <form class="form-signin">
                <span id="reauth-email" class="reauth-email">Utilisez vos informations de connexion moodle</span>
                <input type="email" id="inputEmail" class="form-control" placeholder="Addresse mail" required autofocus>
                <input type="password" id="inputPassword" class="form-control" placeholder="Mot de passe" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">connexion</button>
            </form><!-- /form -->
        <span class="false-information">Login ou mot de passe invalide.</span>
        </div><!-- /card-container -->
    </div><!-- /container -->

</body>
</html>