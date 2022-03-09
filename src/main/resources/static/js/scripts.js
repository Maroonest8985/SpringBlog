
$(function(){
    var index = location.pathname;
    console.log(index.indexOf("home"));
    if(index.indexOf("home") != -1){
        $("#navbarHome").addClass("active");
    }else if(index.indexOf("about") != -1){
        $("#navbarAbout").addClass("active");
    }else if(index.indexOf("contact") != -1){
        $("#navbarContact").addClass("active");
    }else if(index.indexOf("blog") != -1){
        $("#navbarBlog").addClass("active");
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
    //darkmode toggler script
    //--------------------------------------------------------------------------------

})
