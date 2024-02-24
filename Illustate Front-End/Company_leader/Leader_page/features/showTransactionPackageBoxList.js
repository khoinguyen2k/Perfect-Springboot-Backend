function showTransactionBoxes(transactionBoxDTOList, itemListBody) {

    transactionBoxDTOList.forEach(transactionBoxDTO => {
        let tableRow = document.createElement('tr');
        let collectPointData = document.createElement('td');
        let imageData = document.createElement('td');
        let weightData = document.createElement('td');
        let typeData = document.createElement('td');
        let descriptionData = document.createElement('td');

        let box = transactionBoxDTO.packageBox;
        collectPointData.innerHTML = transactionBoxDTO.transactionPointLeaderUsername;

        let img = document.createElement("img");
        img.src = box.imageUrl;
        img.width = 300;
        img.height = 200;
        imageData.appendChild(img);

        weightData.innerHTML = box.weight;
        typeData.innerHTML = box.itemType;
        descriptionData.innerHTML = box.description;

        tableRow.appendChild(collectPointData);
        tableRow.appendChild(imageData);
        tableRow.appendChild(weightData);
        tableRow.appendChild(typeData);
        tableRow.appendChild(descriptionData);

        itemListBody.appendChild(tableRow);
    });
}

/**
 * Handle send boxes
 */
document.getElementById("btn-show-list-transaction-send-package").addEventListener("click", function (event) {
    event.preventDefault();

    let itemListBody = document.querySelector("#transaction-send-packages-list-info table tbody");
    itemListBody.innerHTML = "";

    let url = "http://localhost:8080/delivering-item/list/send/transaction/all";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: companyLeaderUsername
    };

    fetch(url, options)
        .then(response => response.json())
        .then(boxes => {

            showTransactionBoxes(boxes, itemListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

/**
 * Handle receive boxes
 */
document.getElementById("btn-show-list-transaction-receive-package").addEventListener("click", function (event) {
    event.preventDefault();

    let itemListBody = document.querySelector("#transaction-receive-packages-list-info table tbody");
    itemListBody.innerHTML = "";


    let url = "http://localhost:8080/delivering-item/list/receive/transaction/all";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: companyLeaderUsername
    };

    fetch(url, options)
        .then(response => response.json())
        .then(boxes => {

            showTransactionBoxes(boxes, itemListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});