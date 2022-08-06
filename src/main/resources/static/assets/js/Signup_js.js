// jQuery(document).ready(function($) {
//     tab = $('.tabs h3 a');
//
//     tab.on('click', function(event) {
//         event.preventDefault();
//         tab.removeClass('active');
//         $(this).addClass('active');
//
//         tab_content = $(this).attr('href');
//         $('div[id$="tab-content"]').removeClass('active');
//         $(tab_content).addClass('active');
//     });
// });

var acc = document.getElementsByClassName("accordion");
var i;
for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
        } else {
            panel.style.maxHeight = panel.scrollHeight + "px";
        }
    });
}

$('.accordion').click(function(){
    $(this).toggleClass('my');
})