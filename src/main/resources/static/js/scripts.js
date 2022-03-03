$(function(){
    if(index === "home"){
        $("#navbarHome").addClass("active");
    }else if(index === "about"){
        $("#navbarAbout").addClass("active");
    }else if(index === "contact"){
        $("#navbarContact").addClass("active");
    }else if(index === "blog"){
        $("#navbarBlog").addClass("active");
    }else {
        $("#navbarHome").addClass("active");
    }
});
var index = "[[${navbar}]]";//navbar indicator
$(function(){
    localStorage.setItem("isDark", 0);
    var isDark = localStorage.getItem("isDark");
    if(isDark == 1){//darkmode on when page loaded
        $("body").toggleClass("bg-darkmode font-darkmode");
        $("header").toggleClass("bg-darkmode font-darkmode");
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
            $("#darkmodeBtn").toggleClass("active", 400, "swing");
            $(".card").each(function() {
                $(this).toggleClass("card-darkmode", 400, "swing");
            });
        }else if(isDark == 1){
            localStorage.clear();
            localStorage.setItem("isDark", "0");//darkmode off
            $("body").toggleClass("bg-darkmode font-darkmode", 400, "swing");
            $("header").toggleClass("bg-darkmode font-darkmode", 400, "swing");
            $(".card").each(function() {
                $(this).toggleClass("card-darkmode", 400, "swing");
            });
            $("#darkmodeBtn").toggleClass("active", 400, "swing");
        }
    });
})
