function notifySuccess(msg) {
    UIkit.notification({
        message: msg,
        status: 'success',
        pos: 'top-center',
        timeout: 5000
    });
}

function notifyWarning(msg) {
    UIkit.notification({
        message: msg,
        status: 'warning',
        pos: 'top-center',
        timeout: 5000
    });
}

function notifyDanger(msg) {
    UIkit.notification({
        message: msg,
        status: 'danger',
        pos: 'top-center',
        timeout: 5000
    });
}