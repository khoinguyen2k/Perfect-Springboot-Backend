class GatherToGatherConfirm {
    constructor(formData) {
        this.id = formData.get('gather-to-gather-deliver-list');
        this.deliverState = formData.get('state-list');
        this.gatherEmployeeUsername = gatherEmployeeUsername;
    }
}

class TransactionToGatherConfirm {
    constructor(formData) {
        this.id = formData.get('transaction-to-gather-deliver-list');
        this.deliverState = formData.get('state-list');
        this.gatherEmployeeUsername = gatherEmployeeUsername;
    }
}


document.getElementById("to-other-gather-confirm").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);
    let confirmDTO = new GatherToGatherConfirm(formData);

    let url = BASE_URL + "deliver/gather-to-gather/confirm-by/employee/gather";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(confirmDTO)
    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error("Error:", error);
        });

});

document.getElementById("transaction-to-gather-confirm").addEventListener("submit", function (event) {
    event.preventDefault();


    let formData = new FormData(this);
    let confirmDTO = new TransactionToGatherConfirm(formData);

    let url = BASE_URL + "deliver/transaction-to-gather/confirm-by/employee/gather";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(confirmDTO)
    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});