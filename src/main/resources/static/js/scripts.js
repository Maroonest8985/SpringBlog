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