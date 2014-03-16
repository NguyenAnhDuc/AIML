<!doctype html>
<!--[if lt IE 7]>
  <html class="no-js lt-ie9 lt-ie8 lt-ie7">
  <![endif]-->
  <!--[if IE 7]>
    <html class="no-js lt-ie9 lt-ie8">
    <![endif]-->
    <!--[if IE 8]>
      <html class="no-js lt-ie9">
      <![endif]-->
      <!--[if gt IE 8]>
        <!-->
        <html class="no-js">
        <!--<![endif]-->
        <!-- Built with Divshot - http://www.divshot.com -->
        
        <head>
          <title>
            New Page
          </title>
          <meta name="viewport" content="width=device-width">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
          <link rel="stylesheet" href="resources/css/bootstrap.min.css">
          <style>
            body { padding-top: 50px; padding-bottom: 20px; }
          </style>
          <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
          <link rel="stylesheet" href="resources/css/main.css">
          <script type="text/javascript" src="resources/js/vendor/jquery-1.10.1.min.js">
                                                                                                                                                                                                                                                                                                                      
          </script>
          <script type="text/javascript" src="resources/js/vendor/bootstrap.min.js">
                                                                                                                                                                                                                                                                                                                      
          </script>
        </head>
        
        <body>
          <div class="container">
            <div class="row">
              <div class="container">
                <div class="panel panel-default">
                  <div class="panel-heading">
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-md-4">
                        <div class="panel panel-default">
                          <div class="panel-heading">
                            ENDPOINT
                          </div>
                          <div class="panel-body">
                            <div class="panel panel-default">
                              <div class="panel-heading">
                                CHAT
                              </div>
                              <div class="panel-body">
                                GET: &nbsp; &nbsp; &nbsp; &nbsp;
                                <a class="btn btn-success" id="btnGetToken">Access Token</a>
                              </div>
                            </div>
                            <div class="panel panel-default">
                              <div class="panel-heading">
                                bot
                              </div>
                              <div class="panel-body">
                                GET: &nbsp; &nbsp; &nbsp; &nbsp;
                                <a class="btn btn-success" id="bots">bots</a>
                              </div>
                              <div class="panel-body">
                                GET: &nbsp; &nbsp; &nbsp; &nbsp;
                                <a class="btn btn-success" id="botId">bot/id</a>
                              </div>
                            </div>
                            <div class="panel panel-default">
                              <div class="panel-heading">
                                CHAT
                              </div>
                              <div class="panel-body">
                                POST: &nbsp; &nbsp; &nbsp;
                                <a class="btn btn-success" id="chatResponse">chat/response</a>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-8">
                        <div class="panel panel-default">
                          <div class="panel-heading">
                            API DETAIL
                          </div>
                          <div>
                          </div>
                          <div class="panel-body">
                            <span class="label label-success" id="methodType">Important</span>
                            <span class="label label-success" id="apiName">Important</span>
                          </div>
                          <div class="panel-body">
                            <form id="apiForm">
                              <table class="table">
                                <thead>
                                  <tr>
                                    <th>
                                      Paramater
                                    </th>
                                    <th>
                                      Description
                                    </th>
                                    <th>
                                      Test Console
                                    </th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr id="usernameRow">
                                    <td>
                                      UserId
                                    </td>
                                    <td>
                                      User name
                                    </td>
                                    <td>
                                      <input id="txtUserId" name="userId" type="text" class="form-control" required="required">
                                    </td>
                                  </tr>
                                  <tr id="passwordRow">
                                    <td>
                                      password
                                    </td>
                                    <td>
                                      Password
                                    </td>
                                    <td>
                                      <input id="txtPassword" name="password" type="password" class="form-control"
                                      required="required">
                                    </td>
                                  </tr>
                                  <tr id="botIdRow">
                                    <td>
                                      botid
                                    </td>
                                    <td>
                                      bot ID
                                    </td>
                                    <td>
                                      <input id="txtbotId" type="text" name="botId" class="form-control" >
                                    </td>
                                  </tr>
                                  <tr id="tokenRow">
                                    <td>
                                      token
                                    </td>
                                    <td>
                                      Access Token
                                    </td>
                                    <td>
                                      <input id="txtToken" type="text" class="form-control" >
                                    </td>
                                  </tr>
                                  <tr id="dataRow">
                                    <td>
                                      request
                                    </td>
                                    <td>
                                      Request Data
                                    </td>
                                    <td>
                                      <input id="txtData" type="text" class="form-control" >
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>
                                    </td>
                                    <td>
                                    </td>
                                    <td>
                                      <button id="submitbtn" class="btn btn-info">
                                        Test Console
                                      </button>
                                      <input id="submitFormBtn" type="submit" value="" hidden="true">
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                            </form>
                          </div>
                          <div class="panel-body">
                            Response Status:
                          </div>
                          <div class="panel-footer">
                            <textarea id="txtResult" class="form-control"></textarea>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="panel-footer">
                    FTI - API Test Site
                  </div>
                </div>
              </div>
            </div>
          </div>
        </body>
        <script type="text/javascript" src="resources/js/main.js">
                                                                                                                                                                                                                                                        
        </script>
        
        </html>