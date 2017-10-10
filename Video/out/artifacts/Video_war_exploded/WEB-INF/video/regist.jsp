<%--
  Created by IntelliJ IDEA.
  User: matioyoshitoki
  Date: 2017/8/29
  Time: 上午9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">

    <title>KoolTube</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css"  type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/easydialog.css">

    <!-- Owl Carousel Assets -->
    <link href="owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="owl-carousel/owl.theme.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link rel="stylesheet" href="font-awesome-4.4.0/css/font-awesome.min.css"  type="text/css">

    <!-- jQuery and Modernizr-->
    <script src="js/jquery-2.1.1.js"></script>

    <!-- Core JavaScript Files -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/easydialog.min.js"></script>
    <script src="js/easydialog.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<header>
    <!--Top-->
    <nav id="top">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6">
                    <strong>Welcome to KOOLTUBE!</strong>
                </div>
                <div class="col-md-6 col-sm-6">
                    <ul class="list-inline top-link link">
                        <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
                        <li><a href="/contact"><i class="fa fa-comments"></i> Contact</a></li>
                        <li><a href="#"><i class="fa fa-question-circle"></i> FAQ</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>

    <!--Navigation-->
    <nav id="menu" class="navbar">
        <div class="container">
            <div class="navbar-header"><span id="heading" class="visible-xs">Categories</span>
                <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Account</a>
                        <div class="dropdown-menu">
                            <div class="dropdown-inner">
                                <ul class="list-unstyled">
                                    <li><a href="archive">Login</a></li>
                                    <li><a href="archive">Register</a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-play-circle-o"></i> Video</a>
                        <div class="dropdown-menu">
                            <div class="dropdown-inner">
                                <ul class="list-unstyled">
                                    <li><a href="archive">Text 201</a></li>
                                    <li><a href="archive">Text 202</a></li>
                                    <li><a href="archive">Text 203</a></li>
                                    <li><a href="archive">Text 204</a></li>
                                    <li><a href="archive">Text 205</a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-list"></i> Category</a>
                        <div class="dropdown-menu" style="margin-left: -203.625px;">
                            <div class="dropdown-inner">
                                <ul class="list-unstyled">
                                    <li><a href="archive">Text 301</a></li>
                                    <li><a href="archive">Text 302</a></li>
                                    <li><a href="archive">Text 303</a></li>
                                    <li><a href="archive">Text 304</a></li>
                                    <li><a href="archive">Text 305</a></li>
                                </ul>
                                <ul class="list-unstyled">
                                    <li><a href="archive">Text 306</a></li>
                                    <li><a href="archive">Text 307</a></li>
                                    <li><a href="archive">Text 308</a></li>
                                    <li><a href="archive">Text 309</a></li>
                                    <li><a href="archive">Text 310</a></li>
                                </ul>
                                <ul class="list-unstyled">
                                    <li><a href="archive">Text 311</a></li>
                                    <li><a href="archive">Text 312</a></li>
                                    <li><a href="archive#">Text 313</a></li>
                                    <li><a href="archive#">Text 314</a></li>
                                    <li><a href="archive">Text 315</a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li><a href="archive"><i class="fa fa-cubes"></i> Blocks</a></li>
                    <li><a href="/contact"><i class="fa fa-envelope"></i> Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="header-slide">
        <div id="owl-demo" class="owl-carousel">
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/2.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/3.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/4.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/5.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/6.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/7.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/8.jpg" />
                </div>
            </div>
            <div class="item">
                <div class="zoom-container">
                    <div class="zoom-caption">
                        <span>Video's Tag</span>
                        <a href="single">
                            <i class="fa fa-play-circle-o fa-5x" style="color: #fff"></i>
                        </a>
                        <p>Video's Name</p>
                    </div>
                    <img src="images/9.jpg" />
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header -->

<!-- /////////////////////////////////////////Content -->
<div id="page-content" class="archive-page container-fluid" style="width: 1000px;">
    <div class="art-content row">
        <div id="contact_form" class="col-md-12">
            <form name="loginForm" id="loginForm" method="post">
                <div class="form-group">
                    <label class="control-label">Nick Name:</label>
                    <input class="form-control" type="text"  name="user_name" id="name" required>
                </div>
                <div class="form-group">
                    <label class="control-label">Password:</label>
                    <input class="form-control" type="password"  name="user_pwd" id="password" required>
                </div>
                <div class="form-group">
                    <label class="control-label">Birthday:</label>
                    <input class="form-control" id="birthday" name="birthday" type="date" value="2014-01-13"/>
                </div>
                <div>
                    <label class="control-label">Gender:</label>
                    <label class="radio-inline" style="margin-left: 20px;">
                        <input type="radio" name="gender" value="0" checked="checked"> male
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="gender" value="1"> female
                    </label>
                </div>
            </form>
            <button onclick="postForm()" name="Submit" value="Submit" class="submit_btn" style="margin: 0 auto; display: block; height: 40px;">Submit</button>
        </div>
    </div>
</div>
<footer>
    <div class="top-footer">
        <ul class="footer-social list-inline">
            <li><a href="#"><i class="fa fa-twitter"></i> <span>Twitter</span></a></li>
            <li><a href="#"><i class="fa fa-facebook"></i> <span>Facebook</span></a></li>
            <li><a href="#"><i class="fa fa-google-plus"></i> <span>Google+</span></a></li>
            <li><a href="#"><i class="fa fa-youtube"></i> <span>Video's Tag</span></a></li>
            <li><a href="#"><i class="fa fa-vimeo-square"></i> <span>Vimeo</span></a></li>
            <li><a href="#"><i class="fa fa-pinterest"></i> <span>Pinterest</span></a></li>
            <li><a href="#"><i class="fa fa-rss"></i> <span>Rss</span></a></li>
        </ul>
    </div>
    <div class="wrap-footer">
        <div class="container">
            <div class="row">
                <aside class="col-footer col-md-3">
                    <h2 class="footer-title">About Us</h2>
                    <div class="textwidget">Aenean feugiat in ante et blandit. Vestibulum posuere molestie risus, ac interdum magna porta non. Pellentesque rutrum fringilla elementum. Curabitur tincidunt porta lorem vitae accumsan. <br> <br>
                        Aenean feugiat in ante et blandit. Vestibulum posuere molestie risus, ac interdum magna porta non. Pellentesque rutrum fringilla elementum. Curabitur tincidunt porta lorem vitae accumsan.</div>
                </aside>
                <aside class="col-footer col-md-3 widget_recent_entries">
                    <h2 class="footer-title">Recent Posts</h2>
                    <ul>
                        <li><a href="#">MOST VISITED COUNTRIES</a></li>
                        <li><a href="#">5 PLACES THAT MAKE A GREAT HOLIDAY</a></li>
                        <li><a href="#">PEBBLE TIME STEEL IS ON TRACK TO SHIP IN JULY</a></li>
                        <li><a href="#">STARTUP COMPANY&#8217;S CO-FOUNDER TALKS ON HIS NEW PRODUCT</a></li>
                    </ul>
                </aside>
                <aside class="col-footer col-md-3">
                    <h2 class="footer-title">NEWS LETTER</h2>
                    If you want to receive our latest news send directly to your email, please leave your email address bellow. Subscription is free and you can cancel anytime.
                    <form action="#" method="post">
                        <input type="text" name="your-name" value="" size="40" placeholder="Your Email" />
                        <input type="submit" value="SUBSCRIBE" class="btn btn-3" />
                    </form>
                </aside>
                <aside class="col-footer col-md-3 wptt_TwitterTweets">
                    <h2 class="footer-title">Twitter</h2>
                    <ul class="fetched_tweets light">
                        <li class="tweets_avatar">
                            <div class="tweet_wrap">
                                <div class="wdtf-user-card ltr">
                                    <div class="clear"></div>
                                </div>
                                <div class="tweet_data"> Check out 'NewsTube - Magazine Blog &amp; Video' on <a href="#" target="_blank" rel="nofollow">#EnvatoMarket</a> <a href="#" target="_blank" rel="nofollow">#themeforest</a> <a href="http://t.co/jQV1MrQQKY" target="_blank" rel="nofollow">http://t.co/jQV1MrQQKY</a></div> <br/>
                                <div class="clear"></div>
                                <div class="times"> <em> <a href="#" target="_blank" title="Follow cactusthemes on Twitter [Opens new window]"> 2 months ago </a> </em></div>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                        </li>
                        <li class="tweets_avatar">
                            <div class="tweet_wrap">
                                <div class="wdtf-user-card ltr"><div class="clear"></div></div>
                                <div class="tweet_data"> Our latest theme 'Nano - Simple Magazine WordPress Theme' on <a href="#" target="_blank" rel="nofollow">#EnvatoMarket</a> <a href="http://twitter.com/search?q=%23themeforest" target="_blank" rel="nofollow">#themeforest</a> <a href="http://t.co/IfZTbJTk06" target="_blank" rel="nofollow">http://t.co/IfZTbJTk06</a></div> <br/>
                                <div class="clear"></div>
                                <div class="times"> <em> <a href="#" target="_blank" title="Follow cactusthemes on Twitter [Opens new window]"> 5 months ago </a> </em></div>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                        </li>
                    </ul>
                </aside>
            </div>
        </div>
    </div>
    <div class="bottom-footer">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 copyright">
                    <span>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></span>
                </div>
                <div class="col-md-6 col-sm-6 link">
                    <div class="menu-footer-menu-container">
                        <ul id="menu-footer-menu" class="menu list-inline">
                            <li><a href="#"><i class="fa fa-home"></i> Home</a></li>
                            <li><a href="#"><i class="fa fa-comments"></i> Contact</a></li>
                            <li><a href="#"><i class="fa fa-question-circle"></i> FAQ</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer -->

<!-- JS -->
<script src="owl-carousel/owl.carousel.js"></script>
<script>
    $(document).ready(function() {
        $("#owl-demo").owlCarousel({
            autoPlay: 3000,
            items : 5,
            itemsDesktop : [1199,4],
            itemsDesktopSmall : [979,4]
        });

    });
</script>
<script type="text/javascript">

    function postForm() {
        console.log("testRegister");
        $.ajax({
            cache: true,
            type: "POST",
            url:"/reg",
            data:$('#loginForm').serialize(),
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                //var json = JSON.parse(data);
                var json = data;
                if (json.status == "0") {
                    document.cookie="session_id="+json.session_id;
                    var btnFn = function() {
                        location.href = "/";
                        return true;
                    };

                    easyDialog.open({
                        container : {
                            header : 'Message',
                            content : 'Regist Success~',
                            yesFn : btnFn,
                            noFn : true
                        }
                    });

//						RebindTJ.LogStat.login($.cookie("userid"), RebindTJ.LogStat.LogType.nameAndPwd, RebindTJ.LogStat.LogScene.web, RebindTJ.LogStat.LogSource.direct, RebindTJ.LogStat.LogErr.pwdErr);
                } else if (json.status == "1") {
//						RebindTJ.LogStat.login($.cookie("userid"), RebindTJ.LogStat.LogType.nameAndPwd, RebindTJ.LogStat.LogScene.web, RebindTJ.LogStat.LogSource.direct, RebindTJ.LogStat.LogErr.success);

                    var btnFn = function() {
//                        location.href = "/";
                        return true;
                    };

                    easyDialog.open({
                        container : {
                            header : 'Message',
                            content : json.info,
                            yesFn : btnFn,
                            noFn : true
                        }
                    });

                    //location.href = "/review";
                }
            }
        });
    }
    var form_success = false;
</script>

</body>
</html>

