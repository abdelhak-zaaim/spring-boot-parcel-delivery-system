/*
 *
 *  * @project : DeliX
 *  * @created : 09/05/2024, 16:00
 *  * @modified : 09/05/2024, 16:00
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

const showAlert = (data) => {
    let { type, message } = data
    let autoClose
    data.autoClose == undefined ? autoClose = 3000 : autoClose = data.autoClose

    let toastContainer = document.createElement('div')
    toastContainer.classList.add('toast-container')
    var container = document.querySelector('.toast-container')
    if (typeof (container) != 'undefined' && container != null) return
    document.body.appendChild(toastContainer)
    let icon
    if(type == 'error') {
        icon = 'fa-circle-exclamation'
    } else if(type == 'success'){
        icon = 'fa-circle-check'
    } else if(type == 'warning'){
        icon = 'fa-triangle-exclamation'
    } else if(type == 'info'){
        icon = 'fa-circle-info'
    }

    let alert = `<div class="inAlert ${type}">
                    <div class="wrapper">
                        <div class="icon">
                        <i class="fa-solid ${icon}"></i>
                        </div>
                        <div class="details">
                            <div class="title">${type}</div>
                            <div class="message">${message}</div>
                        </div>
                    </div>
                    <i class="fa-solid fa-xmark closeAlert"></i>
                </div>`
    toastContainer.insertAdjacentHTML('afterbegin', alert)
    setTimeout(() => {
        var isAlert = document.querySelector('.inAlert')
        if (typeof (isAlert) != 'undefined' && isAlert != null) isAlert.classList.add('slide-in')
    }, 100)

    setTimeout(() => {
        var isAlert = document.querySelector('.inAlert')
        if (typeof (isAlert) != 'undefined' && isAlert != null) isAlert.classList.remove('slide-in')
        setTimeout(() => {
            document.querySelector('.inAlert').remove()
            removeToast()
        }, 100)
    }, autoClose)

    let closeBtn = document.querySelector('.closeAlert')
    closeBtn.addEventListener('click', () => {
        document.querySelector('.inAlert').classList.remove('slide-in')
        setTimeout(() => {
            document.querySelector('.inAlert').remove()
            removeToast()
        }, 100)
    })
}

const removeToast = () => {
    var container = document.querySelector('.toast-container')
    if (!container.hasChildNodes()) container.remove()
}
/*
let btn = document.querySelector('.btn')

btn.addEventListener('click', () => {
    let toastType = document.querySelector('#toastType').value
    let toastMessage = document.querySelector('#toastMessage').value.trim()
    if(toastMessage == '') showAlert({
        type: 'error',
        message: 'Toast Message cannot be empty',
    })

    showAlert({
        type: toastType,
        message: toastMessage,
    })
})*/