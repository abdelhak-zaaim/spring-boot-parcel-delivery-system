/*
 *
 *  * @project : DeliX
 *  * @created : 19/05/2024, 16:40
 *  * @modified : 19/05/2024, 16:40
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */
function expend_timeligh_result() {
    $('#track-div').addClass('expended')
    $("#header-suivi").addClass("hiddentitle")
    $(".suivi-result-div").addClass("expended")
    var div = document.querySelector('.suivi-result-div');
    div.scrollTop = div.scrollHeight;
}

