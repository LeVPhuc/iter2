/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var itemLinks = document.querySelectorAll('.item-link');
var windowPathname = window.location.pathname;

for (var i = 0; i < itemLinks.length; i++) {
    var itemLinkPathname = new URL(itemLinks[i].href).pathname;
    if (windowPathname === itemLinkPathname) {
        itemLinks[i].classList.add('active');
    }
}


var btnOpenModal = document.querySelector('.btn-open-modal');
var btnCancel = document.querySelector('.btn-cancel');
var modal = document.querySelector('.modal-subject');

btnOpenModal.onclick = function () {
    modal.classList.add('open');
}

btnCancel.onclick = function () {
    modal.classList.remove('open');
}
