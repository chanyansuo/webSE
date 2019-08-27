<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>首页</title>

	<meta name="description" content="Heera HTML5 Template by Jewel Theme" >

	<meta name="author" content="Jewel Theme">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->

	<!-- Bootstrap  -->
	<link href="/webSE/assets/css/bootstrap.min.css" rel="stylesheet">

	<!-- icon fonts font Awesome -->
	<link href="/webSE/assets/css/font-awesome.min.css" rel="stylesheet">

	<!-- Import Magnific Pop Up Styles -->
	<link rel="stylesheet" href="/webSE/assets/css/magnific-popup.css">

	<!-- Import Custom Styles -->
	<link href="/webSE/assets/css/style.css" rel="stylesheet">

	<!-- Import Animate Styles -->
	<link href="/webSE/assets/css/animate.min.css" rel="stylesheet">

	<!-- Import owl.carousel Styles -->
	<link href="/webSE/assets/css/owl.carousel.css" rel="stylesheet">

	<!-- Import Custom Responsive Styles -->
	<link href="/webSE/assets/css/responsive.css" rel="stylesheet">
	
	<link rel="stylesheet" href="/webSE/resource/css/jquery.cxdialog.css" type="text/css" media="all" />
	
	<link rel="stylesheet" href="/webSE/resource/css/loginN.css" type="text/css" media="all" />
	
	<link rel="stylesheet" href="/webSE/assets/css/zalert.css" type="text/css" media="all" />
	
	


<script src="/webSE/resource/js/jquery.min.js"></script>
<script src="/webSE/resource/js/modernizr.custom.js"></script>
<script src="/webSE/resource/js/responsiveslides.min.js"></script>
<script src="/webSE/resource/js/jquery.cxdialog.min.js"></script>
<script src="/webSE/assets/js/zalert.js"></script>

	
  		<!--<script src="assets/js/html5shiv.js"></script>-->
        <!--<script src="assets/js/respond.min.js"></script>-->
  		

  	</head>
  	<body class="header-fixed-top">

  		<div id="page-top" class="page-top"></div><!-- /.page-top -->

  		<section id="site-banner" class="site-banner text-center">
  			<div class="container">
  				<div class="site-logo">
  					<a href="./"><img src="/webSE/resource/images/logo.png" alt="Site Logo"></a>
  				</div><!-- /.site-logo -->
  			</div><!-- /.container -->
  		</section><!-- /#site-banner -->



  		<header id="main-menu" class="main-menu">
  			<div class="container">
  				<div class="row">
  					<div class="col-sm-12">
  						<div class="navbar-header">
  							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu">
  								<i class="fa fa-bars"></i>
  							</button>
              </div>
              <nav id="menu" class="menu collapse navbar-collapse">
               <ul id="headernavigation" class="menu-list nav navbar-nav" style="float: right;">
<!--                 	<li><button onclick="document.getElementById('Login').style.display='block'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button></li> -->
                	<li><a href=# onclick="document.getElementById('Login').style.display='block'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
              </ul><!-- /.menu-list -->
            </nav><!-- /.menu-list -->
          </div>
      </div><!-- /.row -->
    </div><!-- /.container -->
  </header><!-- /#main-menu -->

<div id="Login">
	<div id="loginModal" class="modal show">
		<div class="modal-dialog" style="margin: 200px auto;">
		    <div class="modal-content">
		    	<div class="modal-header" style="border-bottom: none;">
			        <button type="button" class="close" onclick="document.getElementById('Login').style.display='none'">×</button>
			        <h1 class="text-center text-primary">登录</h1>
			    </div>
			    <div class="modal-body">
			    	<form action="" class="form col-md-12 center-block" onsubmit="return false">
			    	<div class="form-group">
			        	<input id="accountControl" type="text" class="form-control input-lg" placeholder="员工工号">
			    	</div>
			    	<div class="form-group">
			    		<input id="passwordControl" type="password" class="form-control input-lg" placeholder="登录密码">
			   		</div>
<!-- 			        <div class="form-group"> -->
<!--  			           	<p>管理员登录<input name="loginChoice" type="checkbox"/ style="width:30px;"></p>  -->
<!-- 			        </div> -->
			        <div class="form-group">
			            <button class="btn btn-primary btn-lg btn-block" onclick="doLogin()">登录</button>
			       
			        
			        </div>
			        </form>
			    </div>
			    <div class="modal-footer" style="border-top: none;">
			        
			    </div>
			</div>
		</div>
	</div>
</div>



  <section id="main-slider" class="main-slider carousel slide" data-ride="carousel">

   <!-- Wrapper for slides -->
   <div class="carousel-inner" role="listbox">
    <div class="item item-1 active">
      <img src="/webSE/assets/images/1.jpg" alt=""/>
<!--       <div class="carousel-caption"> -->
<!-- 		<h3 class="carousel-title" style="font-size: 80px;font-weight: 800;">舰&nbsp;船&nbsp;分&nbsp;所 <span style="font-size: 100px;">培&nbsp;训&nbsp;考&nbsp;试&nbsp;系&nbsp;统</span></h3> -->
<!--      </div> -->
   </div>

   <div class="item item-2">
    <img src="/webSE/assets/images/2.jpg" alt=""/>
<!--     <div class="carousel-caption">    -->
<!--       <h3 class="carousel-title" style="padding: 0px 650px 0px 150px;font-size: 90px;font-weight: 800;">能战斗&nbsp;&nbsp;敢担当&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 90px;padding-top: 30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;肯奉献&nbsp;&nbsp;有激情</span></h3> -->
<!--     </div> -->
  </div>
</div>

<!-- Controls -->
<a class="left carousel-control" href="#main-slider" role="button" data-slide="prev">
  <i class="fa fa-angle-left"></i>
</a>
<a class="right carousel-control" href="#main-slider" role="button" data-slide="next">
  <i class="fa fa-angle-right"></i>
</a>

</section><!-- /#main-slider -->




<section id="about" class="about">
 <div class="container">
  <div class="about-area">
   <div class="title-area text-center">
    <h2 class="about-title">舰船自动化系统事业部</h2>
    <p class="title-description">Shanghai Ship and Shipping Research Institute</p>
  </div>
  <div class="about-items" style="padding-left: 70px;">
    <div class="col-sm-4">
     <div class="item">
      <div class="item-top">
       <h3 class="item-title">精&nbsp;&nbsp;神</h3>
       <h4 class="sub-title">Spirit</h4>
     </div><!-- /.item-top -->
     <p class="item-description">
	★团结、求实、创新、奉献<br>
	★国内一流、国际先进<br>
	★能战斗、敢担当、肯奉献、有激情<br>
     </p>
   </div><!-- /.item -->
 </div>
 <div class="col-sm-4">
   <div class="item">
    <div class="item-top">
     <h3 class="item-title">使&nbsp;&nbsp;命</h3>
     <h4 class="sub-title">Mission</h4>
   </div><!-- /.item-top -->
   <p class="item-description">
	★为船舶制造业科技进步和国防建设发展作贡献。<br>
   </p>
 </div><!-- /.item -->
 </div>
 <div class="col-sm-4">
   <div class="item">
    <div class="item-top">
     <h3 class="item-title">目&nbsp;&nbsp;标</h3>
     <h4 class="sub-title">Target</h4>
   </div><!-- /.item-top -->
   <p class="item-description">
	★为客户创造满意的产品，为员工创造成长的空间。<br>
   </p>
 </div><!-- /.item -->
</div>
<!-- <div class="col-sm-4"> -->
<!--  <div class="item"> -->
<!--   <div class="item-top"> -->
<!--    <h3 class="item-title">考&nbsp;试&nbsp;信&nbsp;息</h3> -->
<!--    <h4 class="sub-title">Examination Information</h4> -->
<!--  </div>/.item-top -->
<!--  <p class="item-description"> -->
<!-- 	★请在规定时间内参加考试。<br> -->
<!-- 	★考试期间请勿作弊。<br> -->
<!--  </p> -->
<!-- </div>/.item -->
<!-- </div> -->
</div><!-- /.about-items -->
</div><!-- /.about-area -->
</div><!-- /.container -->
</section><!-- /#about -->


<section id="portfolio" class="portfolio text-center">
 <div class="container">
  <div class="portfolio-area">
    <div class="portfolio-top">
      <h2 class="portfolio-title">精&nbsp;彩&nbsp;船&nbsp;研</h2>
      <p class="title-description">Shanghai Ship and Shipping Research Institute</p>
      <div class="slide-nav-container">
        <a class="slide-nav left slide-left post-prev" data-slide="post-prev"><i class="fa fa-angle-left" style="padding-top: 4px;"></i></a>
        <a class="slide-nav right slide-right post-next" data-slide="post-next"><i class="fa fa-angle-right" style="padding-top: 4px;"></i></a>
      </div>
    </div>

    <div id="portfolio-slider" class="portfolio-slider owl-carousel owl-theme">
<!--       <div class="item"> -->
<!--         <div class="item-image"> -->
<!--           <img src="/webSE/assets/images/portfolio/1.jpg" alt="Portfolio Image"> -->
<!--         </div> -->

<!--       </div> -->
      <div class="item">
        <div class="item-image">
          <img src="/webSE/assets/images/portfolio/2.jpg" alt="Portfolio Image">
        </div>

      </div>
      <div class="item">
        <div class="item-image">
          <img src="/webSE/assets/images/portfolio/4.jpg" alt="Portfolio Image">
        </div>

      </div>
<!--       <div class="item"> -->
<!--         <div class="item-image"> -->
<!--           <img src="/webSE/assets/images/portfolio/5.jpg" alt="Portfolio Image"> -->
<!--         </div> -->

<!--       </div> -->
      <div class="item">
        <div class="item-image">
          <img src="/webSE/assets/images/portfolio/6.jpg" alt="Portfolio Image">
        </div>

      </div>
      <div class="item">
        <div class="item-image">
          <img src="/webSE/assets/images/portfolio/7.jpg" alt="Portfolio Image">
        </div>

      </div>
      <div class="item">
        <div class="item-image">
          <img src="/webSE/assets/images/portfolio/8.jpg" alt="Portfolio Image">
        </div>

      </div>
      <div class="item">
        <div class="item-image">
          <img src="/webSE/assets/images/portfolio/9.jpg" alt="Portfolio Image">
        </div>

      </div>
      
    </div>
  </div>
</div>
</section>


<!-- 

<section id="services" class="services">
  <div class="container">
    <div class="service-area">

     <div class="row">
      <div class="service-items">
        <div class="col-sm-6">
          <div class="item item-1">
            <div class="row">
              <div class="col-sm-6">
                <h3 class="item-title">Web Design</h3>
                <span class="sub-title">Risus id mediou soimto usce pelle nitesqu it nunc sed egesta ante gravids peses </span>
              </div>
              <div class="col-sm-6">
                <p class="item-description">
                  Sed semper lorem at felis. Vestibulum volutpat, lacus a ultrices sagittis, mi neque euismod dui, eu pulvinar nunc sapien ornare nisl. 
                </p>
                <a class="btn" href="#">
                  <span class="btn-icon"><i class="fa fa-arrow-circle-right"></i></span>
                  More
                </a>
              </div>
            </div>
          </div>

          <div class="item item-2">
            <div class="row">
              <div class="col-sm-6">
                <h3 class="item-title">Web Developement</h3>
                <span class="sub-title">Risus id mediou soimto usce pelle nitesqu it nunc sed egesta ante gravids peses </span>
              </div>
              <div class="col-sm-6">
                <p class="item-description">
                  Sed semper lorem at felis. Vestibulum volutpat, lacus a ultrices sagittis, mi neque euismod dui, eu pulvinar nunc sapien ornare nisl. 
                </p>
                <a class="btn" href="#">
                  <span class="btn-icon"><i class="fa fa-arrow-circle-right"></i></span>
                  More
                </a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="service-accordion">
           <div class="accordion">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
              <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                  <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                      Tetuer adipiscing elit lorem ipsum
                    </a>
                  </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                  <div class="panel-body">
                    <span class="service-image"><img src="images/services/1.jpg" alt="Service Image"></span>
                    <span class="accordion-title">Lorem ipsum dolor sit amet</span>
                    <p>
                      consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi Sed egestas, ante et vulputate volutpat is, gravida id
                    </p>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                  <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                      Sed egestas, ante et vulputate volutp
                    </a>
                  </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                  <div class="panel-body">
                    <span class="service-image"><img src="images/services/2.jpg" alt="Service Image"></span>
                    <p>
                      consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi Sed egestas, ante et vulputate volutpat is, gravida id
                    </p>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                  <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                      Integer vitae libero ac risus egestas
                    </a>
                  </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                  <div class="panel-body">
                    <span class="service-image"><img src="images/services/3.jpg" alt="Service Image"></span>
                    <span class="accordion-title">Lorem ipsum dolor sit amet</span>
                    <p>
                      consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi Sed egestas, ante et vulputate volutpat is, gravida id
                    </p>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                  <h4 class="panel-title">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                      Sed egestas, ante et vulputate volutp
                    </a>
                  </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                  <div class="panel-body">
                    <span class="service-image"><img src="images/services/2.jpg" alt="Service Image"></span>
                    <p>
                      consectetuer adipiscing elit. Phasellus hendrerit. Pellentesque aliquet nibh nec urna. In nisi Sed egestas, ante et vulputate volutpat is, gravida id
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>
</div>
</section>

-->

<!--
 <section id="latest-post" class="latest-post">
  <div class="container">
    <div class="post-area">
      <div class="post-area-top text-center">
        <h2 class="post-area-title">Latest Blog Post</h2>
        <p class="title-description">Vestibulum auctor dapibus nequ</p>
      </div>

      <div class="row">
        <div class="latest-posts">
          <div class="col-sm-6">
            <div class="item">
              <article class="post type-post">
                <div class="post-top">
                  <div class="post-thumbnail">
                    <img src="images/blog/1.jpg" alt="post Image">
                  </div> 
                  <div class="post-meta">
                    <div class="entry-meta">
                      <span class="entry-date">
                        <time datetime="2015-01-15">15 Jan 2015</time>
                      </span>
                      <span class="author-name">
                        <a href="#">John Doe</a>
                      </span>
                      <span class="post-tags">
                        <ul class="tag-list">
                          <li><a href="#">web-design</a></li>
                          <li><a href="#">html5</a></li>
                          <li><a href="#">css3</a></li>
                        </ul>
                      </span>
                    </div>
                  </div>
                </div>
                <div class="post-content">
                  <h2 class="entry-title"><a href="blog-single.html">Standard Blog post Title</a></h2>
                  <p class="entry-text">
                    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris in erat justo. Nullam ac urna eu felis dapibus condimentum sit amet a augue. Sed non neque elit. Sed ut imperdiet nisi. Proin condimentum fermentum nunc. Etiam pharetra, erat sed fermentum feugiat, velit mauris egestas quam, ut aliquam massa nisl quis neque. Suspendisse in orci enim.
                  </p>
                  <a class="btn" href="#">
                    <span class="btn-icon"><i class="fa fa-arrow-circle-right"></i></span>
                    Read More
                  </a>
                </div>
              </article>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="item">
              <article class="post type-post">
                <div class="post-top">
                  <div class="post-thumbnail">
                   <img src="images/blog/2.jpg" alt="post Image">
                 </div> 
                 <div class="post-meta">
                  <div class="entry-meta">
                    <span class="entry-date">
                      <time datetime="2015-01-15">15 Jan 2015</time>
                    </span>
                    <span class="author-name">
                      <a href="#">John Doe</a>
                    </span>
                    <span class="post-tags">
                      <ul class="tag-list">
                        <li><a href="#">web-design</a></li>
                        <li><a href="#">html5</a></li>
                        <li><a href="#">css3</a></li>
                      </ul>
                    </span>
                  </div>
                </div>
              </div>
              <div class="post-content">
                <h2 class="entry-title"><a href="blog-single.html">Standard Blog post Title</a></h2>
                <p class="entry-text">
                  Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris in erat justo. Nullam ac urna eu felis dapibus condimentum sit amet a augue. Sed non neque elit. Sed ut imperdiet nisi. Proin condimentum fermentum nunc. Etiam pharetra, erat sed fermentum feugiat, velit mauris egestas quam, ut aliquam massa nisl quis neque. Suspendisse in orci enim.
                </p>
                <a class="btn" href="#">
                  <span class="btn-icon"><i class="fa fa-arrow-circle-right"></i></span>
                  Read More
                </a>
              </div>
            </article>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</section>


 -->   




<!--
<section id="subscribe-section" class="subscribe-section text-center">
  <div class="container">
    <form class="news-letter" method="post">
      <p class="alert-success"></p>
      <p class="alert-warning"></p>

      <div class="subscribe-hide">
        <input class="form-control" type="email" id="subscribe-email" name="subscribe-email" placeholder="Email Address"  required>
        <button  type="submit" id="subscribe-submit" class="btn btn-md">Subscribe</button>
        <div class="subscribe-error"></div>
      </div>
      <div class="subscribe-message"></div>
    </form>
  </div>
</section>

 -->



<!--
<section id="contact" class="contact">
  <div class="contact-area">


    <div id="message-details" class="message-details">
      <div class="container">
        <form action="email.php" method="post" id="myForm" class="message-form">
          <div class="row">
            <div class="col-sm-6">
              <input id="author" class="form-control" name="author" type="text" value="" size="30" aria-required="true" placeholder="Name*" title="Name" required>
              <input id="email" class="form-control" name="email" type="email" value="" size="30" aria-required="true" placeholder="Email*" title="Email"  required>
              <input id="subject" class="form-control" name="subject" type="subject" value="" size="30" aria-required="true" placeholder="Subject*" title="Subject"  required>
            </div>
            <div class="col-sm-6">
              <textarea id="message" class="form-control" name="message" cols="45" rows="3" aria-required="true" placeholder="Message" title="Message"  required></textarea>
              <button name="submit" class="btn btn-lg full-width" type="submit" id="submit">Submit</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</section>

-->


<footer>
  <div id="footer-top" class="footer-top">
    <div class="container">
      <div class="row">
        <div id="tweet" class="tweet text-left"  style="width: 100%;">
          <div class="col-xs-4 about-tweet">
            <p class="tweet-details">
				地址：<br>
				上海市浦东新区民生路600号。 <br>
            </p><!-- /.tweet-details -->
          </div>
          <div class="col-xs-4 about-tweet">
            <p class="tweet-details">
				网站信箱：<br>
				cys@sssri.com<br>
            </p><!-- /.tweet-details -->
          </div>
          <div class="col-xs-4 about-tweet">
            <p class="tweet-details">
				电话：<br>
				021-58856638<br>
				传真：<br>
				021-58855073<br>
            </p><!-- /.tweet-details -->
          </div>
        </div><!-- /#tweet -->
      </div><!-- /.row -->
    </div><!-- /.container -->
  </div><!-- /#footer-top -->

  <div id="footer-bottom" class="footer-bottom text-center">
    <div class="container">
      <div id="copyright" class="copyright">
        &copy;  2017 - <a href="http://www.sssri.com/" target="_blank">上海船舶运输科学研究所</a>
      </div><!-- /#copyright -->
    </div>
  </div><!-- /#footer-bottom -->
</footer>



<div id="scroll-to-top" class="scroll-to-top">
  <span>
    <i class="fa fa-chevron-up"></i>    
  </span>
</div><!-- /#scroll-to-top -->



<!-- Include modernizr-2.8.3.min.js -->
<!--<script src="assets/js/modernizr-2.8.3.min.js"></script>-->

<!-- Include jquery.min.js plugin -->
<script src="/webSE/assets/js/jquery-2.1.0.min.js"></script>

<!-- Include magnific-popup.min.js -->
<script src="/webSE/assets/js/jquery.magnific-popup.min.js"></script>



<!-- Gmap3.js For Static Maps -->
<script src="/webSE/assets/js/gmap3.js"></script>

<!-- Javascript Plugins  -->
<script src="/webSE/assets/js/plugins.js"></script>

<!-- Custom Functions  -->
<script src="/webSE/assets/js/functions.js"></script>

<script src="/webSE/assets/js/wow.min.js"></script>

<script type="text/javascript" src="/webSE/assets/js/jquery.ajaxchimp.min.js"></script>

<script src="/webSE/resource/js/jquery.cxdialog.min.js"></script>
			       <script type="text/javascript">
			       function searchLogin(){
			   		var v = document.getElementsByName("loginChoice");
			   		if(v[0].checked){
			   			doLoginAdmin();
			   		}
			   		else{
			   			doLogin();
			   		}
			   	}
			   	
			   	function doLogin(){
			   		var name = document.getElementById("accountControl").value;
			   		var pwd = document.getElementById("passwordControl").value;
			   		$.ajax({
			   	        cache: true,
			   	        type: "POST",
			   	        dataType: "json",
			   	        url: "/webSE/rest/user/login",
			   	        data:{"userid":name, "password":pwd},
			   	        async: false,
			   	        error: function(errCode, errMsg) {
			   	            alert("Connection error: "+errCode);
			   	        },
			   	        success: function(data) {
			   	        	switch (data){
			   	        		case 0:
			   	        			show1();
			   	        			break;
			   	        		case 1:
			   	        			document.getElementById("Login").style.display="none";
				   	            	window.sessionStorage.setItem('login_user', name);
				   	            	setTimeout(function(){
				   	            		window.location.href = '/webSE/USER/index_Logined.jsp';
				   	            	},1);
				   	            	//window.open('/webSE/USER/index_Logined.jsp','_blank');
				   	            	break;
			   	        		case 2:
			   	        			window.sessionStorage.setItem('login_user', name);
				   	            	setTimeout(function(){
				   	            		window.location = '/webSE/ADMIN/admin_index.jsp';
				   	            	},1);
				   	            	break;
			   	        	}
			   	        }
			   	    });
			   	}
			   	
			   	function show1(){
			   		$.cxDialog.defaults.baseClass = 'ios';
       				$.cxDialog({
       					title: '提示',
       					info: '用户名、密码不正确！',
       					ok: function(){
       							
       					}
       				});
			   	}
			   	
// 			   	function doLoginAdmin(){
// 			   		var name = document.getElementById("accountControl").value;
// 			   		var pwd = document.getElementById("passwordControl").value;
// 			   		$.ajax({
// 			   	        cache: true,
// 			   	        type: "POST",
// 			   	        dataType: "json",
// 			   	        url: "/webSE/rest/user/loginAdmin",
// 			   	        data:{"userid":name, "password":pwd},
// 			   	        async: false,
// 			   	        error: function(errCode, errMsg) {
// 			   	            alert("Connection error: "+errCode);
// 			   	        },
// 			   	        success: function(data) {
// 			   	        	if(data==false || data=='false' ){
// 			   	        		show2();
// 			   	            } else {
// 			   	            	window.sessionStorage.setItem('login_user', name);
// 			   	            	setTimeout(function(){
// 			   	            		window.location = '/webSE/ADMIN/admin_index.jsp';
// 			   	            	},1);
// 			   	            }
// 			   	        }
// 			   	    });
// 			   	}
			   	
			   	function show2(){
// 			   		z.alert('用户名、密码不正确！');
			   		alert('用户名、密码不正确！');
			   	}
						</script>


</body>
</html>