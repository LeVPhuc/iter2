/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var listAnswer = document.querySelectorAll('.result');

for(var i = 0; i < listAnswer.length; i++) {
    if(listAnswer[i].value === "1") {
        document.getElementById('icon_' + (i+1)).innerHTML = '<i style="color: green" class="far fa-check-circle"></i>';
    }
    if (listAnswer[i].checked === true && listAnswer[i].value === "0") {
        document.getElementById('icon_' + (i+1)).innerHTML = '<i style="color: red" class="far fa-times-circle"></i>';
    }
}
