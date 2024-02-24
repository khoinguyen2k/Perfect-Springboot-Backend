class GatherToGatherDeliver {
    constructor(formData) {
        this.sendGatherEmployeeUsername = gatherEmployeeUsername;
        this.receiveGatherPointUsername = formData.get('receive-gather-point');
        this.packageBoxId = formData.get('stock-box');
    }
}

document.getElementById("create-gather-to-gather-deliver").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);
    let deliverForm = new GatherToGatherDeliver(formData);

    let url = BASE_URL + "deliver/gather-to-gather/create";

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