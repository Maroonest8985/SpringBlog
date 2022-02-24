$(function(){
    if(index === "home"){
        $("#navbarHome").addClass("active");
    }else if(index === "about"){
        $("#navbarAbout").addClass("active");
    }else if(index === "contact"){
        $("#navbarContact").addClass("active");
    }else{
        $("#navbarBlog").addClass("active");
    }
});