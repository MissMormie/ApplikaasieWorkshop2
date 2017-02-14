/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {

  $.getJSON("/greeting?name=marco", function(data, status) {
    $("#content").text(data.content);

  });
});