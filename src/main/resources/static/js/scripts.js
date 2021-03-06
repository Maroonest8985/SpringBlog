$(function(){

    $("#homeBtn").click(function (){
        sessionStorage.setItem("cachePage", 1);
    })
    var index = location.pathname;
    if(index.indexOf("home") != -1){
        $("#navbarHome").addClass("active");
        var cachePage = sessionStorage.getItem("cachePage");
        if(cachePage == "null"){
            cachePage = 1;
            sessionStorage.setItem("cachePage", 1);
        }
        console.log(cachePage);
        pager(cachePage);

    }else if(index.indexOf("about") != -1){
        $("#navbarAbout").addClass("active");
    }else if(index.indexOf("contact") != -1){
        $("#navbarContact").addClass("active");
    }else if(index.indexOf("post") != -1){
        $("#navbarPost").addClass("active");
    }else {
        $("#navbarHome").addClass("active");
    }
    //navbar indicator toggler===================================================================


        var isDark = localStorage.getItem("isDark");
        if (isDark == null) {
            var isDark = localStorage.setItem("isDark", 0);
        } else if (isDark == 1) {//darkmode on when page loaded
            $("body").toggleClass("bg-darkmode font-darkmode");
            $("header").toggleClass("bg-darkmode font-darkmode");
            $(".modal-content").toggleClass("modal-darkmode font-darkmode");
            $("#darkmodeBtn").toggleClass("active");
            $("label").each(function () {
                $(this).addClass("input-font-darkmode");
            });
            $(".card").each(function () {
                $(this).toggleClass("card-darkmode");
                $(this).toggleClass("card-lightmode");
            });
        } else {
            //do nothing
        }



    $("#darkmodeBtn").click(function(e){
        var isDark = localStorage.getItem("isDark");
        if(isDark == 0){ //darkmode disabled when clicking button
            localStorage.clear();
            localStorage.setItem("isDark", "1");//darkmode on
            $("body").toggleClass("bg-darkmode font-darkmode", 200, "swing");
            $("header").toggleClass("bg-darkmode font-darkmode", 200, "swing");
            $("label").each(function(){
                $(this).addClass("input-font-darkmode", 200, "swing");
            });
            $(".modal-content").toggleClass("modal-darkmode font-darkmode");
            $("#darkmodeBtn").toggleClass("active", 200, "swing");
            $(".card").each(function() {
                $(this).toggleClass("card-darkmode", 100, "swing");
                $(this).toggleClass("card-lightmode", 100, "swing");
            });
        }else if(isDark == 1){
            localStorage.clear();
            localStorage.setItem("isDark", "0");//darkmode off
            $("body").toggleClass("bg-darkmode font-darkmode", 200, "swing");
            $("header").toggleClass("bg-darkmode font-darkmode", 200, "swing");
            $("label").each(function(){
                $(this).removeClass("input-font-darkmode", 200, "swing");
            });
            $(".modal-content").toggleClass("modal-darkmode font-darkmode");
            $(".card").each(function() {
                $(this).toggleClass("card-darkmode", 100, "swing");
                $(this).toggleClass("card-lightmode", 100, "swing");
            });
            $("#darkmodeBtn").toggleClass("active", 200, "swing");
        }
    });

   //=============Darkmode========================================================================


    var idCheckOk = 0;
    $("#idCheck").click(function(){
            var userid = $("#idInput").val(); // input userid????????? ???

            $("#checkIdResult").html("<p style='font-size : 12px;'>?????????...</p>");
            if (userid.length > 1) {
                $.ajax({
                    type: 'POST',
                    data: {userid: userid},// checkid.do?userid=userid ??????
                    url: "./checkid",
                    success: function (result) { // ???????????? function ??????
                        if (result == 0) {
                            $("#checkIdResult").html("<p style='font-size : 12px;'>???????????? ??????????????????.</p>");
                            idCheckOk = 0;
                        } else if(result ==1){
                            $("#checkIdResult").html("<p style='font-size : 12px;'>?????? ????????? ??????????????????.</p>");
                            $("#isValidationOk").html("");
                            idCheckOk = 1;
                        }else{
                            $("#checkIdResult").html("<p style='font-size : 12px;'>error</p>");
                            idCheckOk = 0;
                        }
                    }
                });
            } else {
                $("#checkIdResult").html("<p style='font-size : 12px;'>???????????? ????????? ?????????.</p>");
                return 0;
            }
        });

    var oldVal;
    $("#idInput").on( "propertychange change keyup paste input", function(){
        var currentVal = $(this).val();
        if(currentVal == oldVal) {
            return;
        }
        oldVal = currentVal;
       idCheckOk = 0;
       $("#checkIdResult").html("<p style='font-size : 12px;'></p>");
    });

    var pwdOk;
    var oldValPwd1;
    $("#pwdInput1").on( "propertychange change keyup paste input", function(){
        var currentVal = $(this).val();
        if(currentVal == oldValPwd1) {
            return;
        }
        oldValPwd1 = currentVal;

        var pwd1 = $("#pwdInput").val();
        if(currentVal.length < 2){
            $("#checkPwdResult").html("<p style='font-size : 12px;'></p>");
            pwdOk = null;
        }else if (pwd1 != currentVal) {
                $("#checkPwdResult").html("<p style='font-size : 12px;'>??????????????? ???????????? ????????????.</p>");
                pwdOk = null;
            } else {
                $("#checkPwdResult").html("<p style='font-size : 12px;'>??????????????? ???????????????.</p>");
                pwdOk = 1;
            }

    });

    var oldValPwd;
    $("#pwdInput").on( "propertychange change keyup paste input", function(){
        var currentVal = $(this).val();
        if(currentVal == oldValPwd) {
            return;
        }
        oldValPwd = currentVal;
        var pwd = $("#pwdInput1").val();

        if(currentVal.length < 2){
            $("#checkPwdResult").html("<p style='font-size : 12px;'></p>");
            pwdOk = null;
        }else if(pwd != currentVal){
            $("#checkPwdResult").html("<p style='font-size : 12px;'>??????????????? ???????????? ????????????.</p>");
            pwdOk = null;
        }else{
            $("#checkPwdResult").html("<p style='font-size : 12px;'>??????????????? ???????????????.</p>");
            pwdOk = 1;
        }
    });

    $("#signupSubmit").click(function(){
        var userid = $("#idInput").val(); // input userid????????? ???
        var email = $("#emailInput").val();
        var pwd = $("#pwdInput").val();
        var pwd1 = $("#pwdInput1").val();
        if(userid && email && pwd && pwdOk != null){ //3?????? ?????? null ?????????
            if(idCheckOk == 0){//???????????? x
                $("#signupMsg").html("<p style='font-size : 12px;'>????????? ??????????????? ??? ?????????.</p>");
            }else{
                $("#signupForm").submit();
            }
        }else{
            $("#signupMsg").html("<p style='font-size : 12px;'>?????? ????????? ????????? ?????????.</p>");
        }
    });

    //Sign Up JS=================================================================================

    $("#btnSignin").click(function(){
        var id = $("#loginIdInput").val();
        var pwd = $("#loginPwdInput").val();

        if(id && pwd != null){ //?????? null ?????????
            $.ajax({
                type: 'POST',
                data: {userid: id, password: pwd},
                url: './checklogin',
                success: function (result){
                    if(result == 1){
                        $("#loginForm").submit();
                    }else{
                        $("#loginMsg").html("<p style='font-size : 12px;'>???????????? ??????????????? ????????? ?????????.</p>");
                    }
                }
            })
        }
    });

    //==============Sign in JS =================================================================
        $("#addComment").click(function(){
        var text = $("textarea#commentText").val();
        var str = $(location).attr('search');
        var post_no = str.split("=");
        var post_no1 = post_no[1];
        $.ajax({
        type: 'POST',
        data: {text : text, post_no : post_no1},// form data ??????
        url: "./addcomment",
        success: function (commentDTO) { // ???????????? function ??????
        $("textarea#commentText").val("");
        if (commentDTO != null){
        var text = commentDTO.text;
        var name = commentDTO.member_name;
        var comment_no = commentDTO.no;
        var commentHtml = "<div id='commentCard"+comment_no+"' class=\"d-flex mb-4\">\n" +
            "                                    <!-- Parent comment-->\n" +
            "                                    <div class=\"flex-shrink-0\"><img class=\"rounded-circle\" src=\"https://dummyimage.com/50x50/ced4da/6c757d.jpg\" alt=\"...\" /></div>\n" +
            "                                    <div class=\"ms-3\">\n" +
            "                                        <div class=\"fw-bold\">"+name+"</div>\n" +
            "                                        <p>"+text+"</p>\n" +
            "                                    </div>\n" +
            "    <div th:if=\"${comments.member_no} == ${session.member_no}\" class=\"ms-auto\">"+
            "    <button id=\"callDelete"+comment_no+"\"data-bs-toggle=\"modal\" onclick='deleteComment(this.id)' data-bs-target=\"#commentDeleteModal\" class=\"btn btn-danger btn-sm\">??????</button>"+
            "    </div>"+
            "                                </div>";


        $("#ajaxComment").prepend($(commentHtml).hide().fadeIn(2000));
        }
    }
    });
    });
});
//Add Comment JS=================================================================================
function deleteComment(id){
    var btnId = id.substr(10);
    $("#deleteComment").attr("id", "deleteComment"+btnId);
    $("#deleteComment"+btnId).click(function(){
        $.ajax({
            type: "POST",
            data: {comment_no: btnId},
            url: "./deletecomment",
            success: function(result){
                if(result > 0){
                    $("#commentCard"+btnId).remove();
                    $("#deleteComment"+btnId).attr("id", "deleteComment");
                }
            }
        });
    })
}
//Delete Comment JS ============================================================================
$(function(){
    var index = location.search;
    var indexHome = location.pathname;
    var scrollY = localStorage.getItem("scrolly");
    if(index == ""){
        scrollY = 0;
    }else if(indexHome == "/home"){
        if(scrollY != null && scrollY != '' && scrollY > 0) {
            window.scrollTo({top: scrollY, left: "0", behavior: "instant"});
        }
    }
});
//AutoScroll JS=================================================================================
$(window).on('popstate', function(event) {
    var data = event.originalEvent.state; // pushState?????? ????????? state data
    console.log(data);
});

function pager(pageNo) {
    sessionStorage.setItem("cachePage", pageNo);
    var search = $("#searchPost").val();
    console.log(search);
    $.ajax({
        type: "GET",
        data: {pageNo: pageNo, search: decodeURIComponent(search)},
        url: "./posts",
        success: function (posts) {
            window.scrollTo({top: 190, left: 0, behavior: "smooth"});
            if (pageNo == 1 && search == "") {
                $("#featMain").show();
                var featPost = posts[0];
                posts.splice(0, 1);
                var textHtml = '<p>????????? : '+featPost.member_name+'</p>' +
                    '<p>'+featPost.date+'</p>';
                $("#featMain").find("a").attr("href", "./readpost?post_no="+featPost.no);
                $("#featMain").find(".text-muted").html(textHtml);
                $("#featMain").find(".card-title").text(featPost.title);
                $("#featMain").find(".card-text").text(featPost.text);

                $.each(posts, function (index, item) {
                    var textHtml = '<p>????????? : '+item.member_name+'</p>' +
                        '<p>'+item.date+'</p>';
                    $("#post"+(index+1)).find("a").attr("href", "./readpost?post_no="+item.no);
                    $("#post"+(index+1)).find(".text-muted").html(textHtml);
                    $("#post"+(index+1)).find(".card-title").text(item.title);
                    $("#post"+(index+1)).find(".card-text").text(item.text);
                    console.log(index);
                });
            } else if(search == ""){
                $("#featMain").hide();
                var totalIndex = [];
                $.each(posts, function (index, item) {
                    var textHtml = '<p>????????? : '+item.member_name+'</p>' +
                        '<p>'+item.date+'</p>';
                    $("#post"+(index+1)).show();
                    $("#post"+(index+1)).find("a").attr("href", "./readpost?post_no="+item.no);
                    $("#post"+(index+1)).find(".text-muted").html(textHtml);
                    $("#post"+(index+1)).find(".card-title").text(item.title);
                    $("#post"+(index+1)).find(".card-text").text(item.text);

                    totalIndex.push(index);
                        console.log(index);

                    }
                    );
                var numPosts = totalIndex.length;
                for(var k = 4; k > numPosts; k--){
                    $("#post"+k).hide();
                }
            }else{
                $("#featMain").hide();
                var totalIndex = [];
                $.each(posts, function (index, item) {
                        var textHtml = '<p>????????? : '+item.member_name+'</p>' +
                            '<p>'+item.date+'</p>';
                        $("#post"+(index+1)).show();
                        $("#post"+(index+1)).find("a").attr("href", "./readpost?post_no="+item.no);
                        $("#post"+(index+1)).find(".text-muted").html(textHtml);
                        $("#post"+(index+1)).find(".card-title").text(item.title);
                        $("#post"+(index+1)).find(".card-text").text(item.text);

                        totalIndex.push(index);
                        console.log(index);

                    }
                );
                var numPosts = totalIndex.length;
                for(var k = 4; k > numPosts; k--){
                    $("#post"+k).hide();
                }

            }
            console.log(posts);
        }
    });

    $.ajax({
        type: "GET",
        url: "./pages",
        data: {pageNo : pageNo, search: search},
        success: function(pages){
            var nowPage = pages.nowPage;
            var maxPage = pages.maxPage;
            var nowPageCounter = pages.nowPageCounter;
            $("#ajaxPage").empty();
            if((maxPage-nowPageCounter) > 10){
                for (var i = nowPageCounter + 1; i <= nowPageCounter + 10; i++) {
                    if (i == nowPage) {
                        var htmlPage = "<li class=\"page-item active\" aria-current=\"page\">" +
                            "                                    <button type=\"button\" class=\"page-link\" onclick=\"pager(" + i + ")\" >"
                            + i + "                                        </button>\n" +
                            "                                </li>"
                        $("#ajaxPage").append(htmlPage);
                    } else {
                        var htmlPage = "<li class=\"page-item\" aria-current=\"page\">" +
                            "                                    <button type=\"button\" class=\"page-link\" onclick=\"pager(" + i + ")\" >"
                            + i + "                                        </button>\n" +
                            "                                </li>"
                        $("#ajaxPage").append(htmlPage);
                    }
                }
            }else{
                for (var i = nowPageCounter + 1; i <= maxPage; i++) {
                    if (i == nowPage) {
                        var htmlPage = "<li class=\"page-item active\" aria-current=\"page\">" +
                            "                                    <button type=\"button\" class=\"page-link\" onclick=\"pager(" + i + ")\" >"
                            + i + "                                        </button>\n" +
                            "                                </li>"
                        $("#ajaxPage").append(htmlPage);
                    } else {
                        var htmlPage = "<li class=\"page-item\" aria-current=\"page\">" +
                            "                                    <button type=\"button\" class=\"page-link\" onclick=\"pager(" + i + ")\" >"
                            + i + "                                        </button>\n" +
                            "                                </li>"
                        $("#ajaxPage").append(htmlPage);

                    }
                }
            }

            if((maxPage-nowPageCounter) > 10){
                var htmlPageNext = "<li class=\"page-item\" aria-current=\"page\">" +
                    "<button type=\"button\" class=\"page-link\" onclick=\"pager("+(nowPageCounter+11)+ ")\">" +
                    "<i class=\"fas fa-angle-right\"></i></button></li>\n"
                $("#ajaxPage").append(htmlPageNext);
            }
            if(nowPage > 10){
                var htmlPagePrev = "<li class=\"page-item\" aria-current=\"page\">" +
                    "<button type=\"button\" class=\"page-link\" onclick=\"pager("+nowPageCounter+ ")\" >" +
                    "<i class=\"fas fa-angle-left\"></i></button></li>\n"
                $("#ajaxPage").prepend(htmlPagePrev);
            }
        }
    });
}
//Home Pager JS==================================================================================




