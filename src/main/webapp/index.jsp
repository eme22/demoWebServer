<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/login.css">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#dismissalert").click(function (event) {
                $("#nouseralert").addClass('d-none');
            });


            $("#submit").click(function (event) {
                var wsUrl = "http://localhost:8080/demoWebServer-1.0-SNAPSHOT/usuario?wsdl";
                var usr=$('#email').val();
                var pss=$('#psw').val();
                //console.log("Usuario: " + usr);
                //console.log("Contrasenia: " + pss);
                var soapRequest =
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dem=\"http://demoWebServer.eme22.com/\">" +
                        "<soapenv:Header/>" +
                        "<soapenv:Body>" +
                            "<dem:login>" +
                                "<usuario>" +
                                    "<contrasenia>" +
                                        pss +
                                    "</contrasenia>" +
                                    "<id>" +
                                        "0" +
                                    "</id>" +
                                    "<nombre>" +
                                        "" +
                                    "</nombre>" +
                                    "<usuario>" +
                                        usr +
                                    "</usuario>" +
                                "</usuario>" +
                            "</dem:login>" +
                        "</soapenv:Body>" +
                    "</soapenv:Envelope>";

                //alert(soapRequest)
                $.ajax({
                    type: "POST",
                    url: wsUrl,
                    contentType: "text/xml",
                    dataType: "xml",
                    data: soapRequest,
                    success: processSuccess,
                    error: processError
                });

            });
        });

        function processSuccess(data, status, req) {
            var usr=$('#email').val();
            var login = req.responseXML.getElementsByTagName("return");
            var valid = (login[0].childNodes[0].nodeValue === 'true');

            if (valid){
                loginsucess(usr);
            } else {
                loginerror();
            }
        }
        function processError(data, status, req) {
            alert('Ha ocurrido un error: '+data.state);
        }

        function loginsucess(usr) {
            //console.log("SUCCESS");
            var userid = btoa(usr);
            window.location = generateUrl("http://localhost:8080/demoWebServer-1.0-SNAPSHOT/usuario.jsp",{
                sessioncode: userid
            });
        }

        function loginerror() {
            //console.log("ERROR");
            $("#nouseralert").removeClass('d-none');
        }

        function generateUrl(url, params) {
            var i = 0, key;
            for (key in params) {
                if (i === 0) {
                    url += "?";
                } else {
                    url += "&";
                }
                url += key;
                url += '=';
                url += params[key];
                i++;
            }
            return url;
        }
    </script>
</head>

<body class="flex-center">

<div class="container px-4 py-5 mx-auto">
    <div class="card card0">
        <div class="d-flex flex-lg-row flex-column-reverse">
            <div class="card card1">
                <div class="row justify-content-center my-auto">
                    <div class="col-md-8 col-10 my-5">
                        <div class="row justify-content-center px-3 mb-3">
                            <img id="logo" draggable="false" src="assets/img/logo-utp.png">
                        </div>
                        <h3 class="mb-5 text-center heading">Bienvenido</h3>
                        <h6 class="msg-info">Por favor, ingrese sus datos</h6>
                        <div class="form-group">
                            <label class="form-control-label text-muted">Email</label>
                            <input type="text" id="email" name="email" placeholder="Email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="form-control-label text-muted">Password</label>
                            <input type="password" id="psw" name="psw" placeholder="Password" class="form-control">
                        </div>
                        <br>
                        <div class="alert alert-warning alert-dismissible fade show d-none" id="nouseralert" role="alert">
                            <strong>¡El usuario o contrasenia no existen!</strong>
                            <button type="button" class="btn-close" id="dismissalert" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <div class="row justify-content-center my-3 px-3">
                            <button type="submit" class="btn-block btn-color" id="submit">Ingresar</button>
                        </div>
                    </div>
                </div>
                <div class="bottom text-center mb-5">
                    <p href="#" class="sm-text mx-auto mb-3">No tienes una cuenta? <button class="btn btn-white ml-2">Registrate</button></p>
                </div>
            </div>
            <div class="card card2">
                <div class="right d-flex align-items-center">
                    <img draggable="false" src="./assets/img/portada.png" alt="Alumnos universidad tecnológica del Perú">
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>