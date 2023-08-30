var button = document.getElementById('open');


var modal = document.getElementById('modal');
var bar = document.getElementById('horizon');
var content = document.getElementById('contents');



button.addEventListener('click', openModal)
function openModal(){

    var modal = document.getElementById('modal');
    var bar = document.getElementById('horizon');
    var content = document.getElementById('contents');

    
    content.classList.remove('contentnonDisp');
    
    content.style.display = "block";


    bar.classList.remove('barnondisplay');
    bar.classList.add('bardisplay');

    modal.classList.remove('modalnotactive');
    modal.classList.add('modalactive');
}



window.onclick = function(event) {
    if ((event.target == bar)|| (event.target == document.querySelector('span'))) {
        modal.classList.remove('modalactive');
        modal.classList.add('modalnotactive');

        bar.classList.remove('bardisplay');
        bar.classList.add('barnondisplay');


        content.classList.remove('contentDisp');
        content.classList.add('contentnonDisp');
        
    }
}