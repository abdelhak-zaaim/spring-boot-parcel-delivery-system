/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 16:04
 *  * @modified : 13/05/2024, 16:01
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 16:01
 *  * @modified : 13/05/2024, 16:01
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

$('.order').click(function (e) {

    let button = $(this);

    if (!button.hasClass('animate')) {

        setTimeout(() => {
            button.removeClass('animate');
        }, 10000);
    }

});


function startTrackAnimated() {
    const trackanimated = document.createElement("p");
    trackanimated.className = "trackanimated";
    trackanimated.setAttribute("role", "status");
    trackanimated.style.setProperty("--yTransform", `-20px`);

    trackanimated.innerHTML = `
        <div data-v-5e9121d4="" class="opt-wrap">
            <button id="btn-start-track-animated" class="order ">
                <span class="success">Order Placed successfully
                    <svg viewBox="0 0 12 10">
                        <polyline points="1.5 6 4.5 9 10.5 1"></polyline>
                    </svg>
                </span>
                <div class="box"></div>
                <div class="truck">
                    <div class="back text--center">
                        <img style="height: 12px" src="/home/assets/images/logo/small_logo.png">
                    </div>
                    <div class="front">
                        <div class="window"></div>
                    </div>
                    <div class="light top"></div>
                    <div class="light bottom"></div>
                </div>
                <div class="lines"></div>
            </button>
        </div>`;

    document.body.insertAdjacentElement("beforeend", trackanimated);


    setTimeout(() => {
        $("#btn-start-track-animated").addClass('animate');
    }, 500);


    setTimeout(() => {
        $(".trackanimated").addClass('hidetraack');
        setTimeout(() => {
            document.querySelectorAll(".trackanimated").forEach((el) => {
        el.remove();
            });
        }, 2000);
    }, 10000);



}



