$(function(){
    var navbarContainer = document.getElementById("navbarSupportedContent");
    var navbarBtn = navbarContainer.getElementsByClassName("nav-link");
    for(i=0; i< navbarBtn.length; i++){
        navbarBtn[i].addEventListener('click', function(){
                var current = document.getElementsByClassName("active");
                current[0].className = current[0].className.replace(" active");
                this.className += " active";
            }
        );
    }
})
