
$(function(){
    var index = location.pathname;
    console.log(index.indexOf("home"));
    if(index.indexOf("home") != -1){
        $("#navbarHome").addClass("active");
    }else if(index.indexOf("about") != -1){
        $("#navbarAbout").addClass("active");
    }else if(index.indexOf("contact") != -1){
        $("#navbarContact").addClass("active");
    }else if(index.indexOf("post") != -1){
        $("#navbarPost").addClass("active");
    }else {
        $("#navbarHome").addClass("active");
    }
    //navbar indicator toggler
    //--------------------------------------------------------------------------------
    var isDark = localStorage.getItem("isDark");
    if(isDark == null){
        var isDark = localStorage.setItem("isDark", 0);
    }else if(isDark == 1){//darkmode on when page loaded
        $("body").toggleClass("bg-darkmode font-darkmode");
        $("header").toggleClass("bg-darkmode font-darkmode");
        $(".modal-content").toggleClass("modal-darkmode font-darkmode");
        $("#darkmodeBtn").toggleClass("active");
        $(".card").each(function() {
            $(this).toggleClass("card-darkmode");
        });
    }else{
        //do nothing
    }
    $("#darkmodeBtn").click(function(e){
        var isDark = localStorage.getItem("isDark");
        if(isDark == 0){ //darkmode disabled when clicking button
            localStorage.clear();
            localStorage.setItem("isDark", "1");//darkmode on
            $("body").toggleClass("bg-darkmode font-darkmode", 400, "swing");
            $("header").toggleClass("bg-darkmode font-darkmode", 400, "swing");
            $(".modal-content").toggleClass("modal-darkmode font-darkmode");
            $("#darkmodeBtn").toggleClass("active", 400, "swing");
            $(".card").each(function() {
                $(this).toggleClass("card-darkmode", 400, "swing");
            });
        }else if(isDark == 1){
            localStorage.clear();
            localStorage.setItem("isDark", "0");//darkmode off
            $("body").toggleClass("bg-darkmode font-darkmode", 400, "swing");
            $("header").toggleClass("bg-darkmode font-darkmode", 400, "swing");
            $(".modal-content").toggleClass("modal-darkmode font-darkmode");
            $(".card").each(function() {
                $(this).toggleClass("card-darkmode", 400, "swing");
            });
            $("#darkmodeBtn").toggleClass("active", 400, "swing");
        }
    });

    var idCheckOk = 0;
    $("#idCheck").click(function(){
            var userid = $("#idInput").val(); // input userid부분의 값

            $("#checkIdResult").html("<p style='font-size : 12px;'>검색중...</p>");
            if (userid.length > 1) {
                $.ajax({
                    type: 'POST',
                    data: {userid: userid},// checkid.do?userid=userid 형식
                    url: "./checkid",
                    success: function (result) { // 성공하면 function 실행
                        if (result == 0) {
                            $("#checkIdResult").html("<p style='font-size : 12px;'>사용중인 아이디입니다.</p>");
                            idCheckOk = 0;
                        } else if(result ==1){
                            $("#checkIdResult").html("<p style='font-size : 12px;'>사용 가능한 아이디입니다.</p>");
                            $("#isValidationOk").html("");
                            idCheckOk = 1;
                        }else{
                            $("#checkIdResult").html("<p style='font-size : 12px;'>error</p>");
                            idCheckOk = 0;
                        }
                    }
                });
            } else {
                $("#checkIdResult").html("<p style='font-size : 12px;'>아이디를 입력해 주세요.</p>");
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

    $("#signupSubmit").click(function(){
        var userid = $("#idInput").val(); // input userid부분의 값
        var email = $("#emailInput").val();
        var pwd = $("#pwdInput").val();

        if(userid && email && pwd != null){ //3개가 전부 null 아니면
            if(idCheckOk == 0){//중복체크 x
                $("#signupMsg").html("<p style='font-size : 12px;'>아이디 중복체크를 해 주세요.</p>");
            }else{
                $("#signupForm").submit();
            }
        }else{
            $("#signupMsg").html("<p style='font-size : 12px;'>모든 항목을 입력해 주세요.</p>");
        }
    });

    //darkmode toggler script
    //--------------------------------------------------------------------------------

});


