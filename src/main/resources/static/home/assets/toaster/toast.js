/*
 *
 *  * @project : DeliX
 *  * @created : 09/05/2024, 17:52
 *  * @modified : 09/05/2024, 17:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */



// VARIABLES:
let toastTimeouts = [];


document.addEventListener("visibilitychange", (event) => {
    clearToasts();
    if (document.visibilityState === "visible") {
        gimmeToast();
    }
});

// FUNCTIONS:
function toast(text, type) {
    const toasts = document.querySelectorAll(".toasty");
    let toastsHeight = 30;
    toasts.forEach((toast) => {
        toastsHeight += toast.offsetHeight;
    });
    const toasty = document.createElement("p");
    toasty.innerText = text;
    toasty.style.setProperty(
        "--yTransform",
        `-${toastsHeight + 20 * (toasts.length + 1)}px`
    );
    switch (type) {
        case "warning":
            toasty.className = "toasty warning";
            toasty.setAttribute("role", "status");
            console.warn("toast:", text);
            break;
        case "error":
            toasty.className = "toasty error";
            toasty.setAttribute("role", "alert");
            toasty.setAttribute("aria-live", "assertive");
            console.error("toast:", text);
            break;
        default:
            toasty.className = "toasty";
            toasty.setAttribute("role", "log");
            console.log("toast:", text);
    }
    document.body.insertAdjacentElement("beforeend", toasty);
    toasty.addEventListener("animationend", () => {
        toasty.remove();
    });
}



function clearToasts() {
    document.querySelectorAll(".toasty").forEach((el) => {
        el.remove();
    });
    toastTimeouts.forEach((timeout) => {
        window.clearTimeout(timeout);
    });
    toastTimeouts = [];
}
