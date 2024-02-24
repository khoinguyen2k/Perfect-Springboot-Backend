class GatherToTransactionDeliver {
    constructor(formData) {
        this.gatherEmployeeUsername = gatherEmployeeUsername;
        this.transactionPointLeaderUsername = formData.get('receive-transaction-point');
        this.packageBoxId = formData.get('stock-box');
    }
}

document.getElementById("create-gather-to-transaction-deliver").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);
    let deliverForm = new GatherToTransactionDeliver(formData);

    let url = BASE_URL + "deliver/transaction-to-gather/create/by/employee/gather";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(deliverForm)
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