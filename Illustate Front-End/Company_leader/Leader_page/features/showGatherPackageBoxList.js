function showGatherBoxes(gatherBoxDTOList, itemListBody) {

    gatherBoxDTOList.forEach(gatherBoxDTO => {
        let tableRow = document.createElement('tr');
        let collectPointData = document.createElement('td');
        let imageData = document.createElement('td');
        let weightData = document.createElement('td');
        let typeData = document.createElement('td');
        let descriptionData = document.createElement('td');

        let box = gatherBoxDTO.packageBox;
        collectPointData.innerHTML = gatherBoxDTO.gatherPointLeaderUsername;

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
document.getElementById("btn-show-list-gather-send-package").addEventListener("click", function (event) {
    event.preventDefault();

    let itemListBody = document.querySelector("#gather-send-packages-list-info .table tbody");
    itemListBody.innerHTML = "";

    let url = "http://localhost:8080/delivering-item/list/send/gather/all";

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

            showGatherBoxes(boxes, itemListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

/**
 * Handle receive boxes
 */
document.getElementById("btn-show-list-gather-receive-package").addEventListener("click", function (event) {
    event.preventDefault();

    let itemListBody = document.querySelector("#gather-receive-packages-list-info .table tbody");
    itemListBody.innerHTML = "";

    let url = "http://localhost:8080/delivering-item/list/receive/gather/all";

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

            showGatherBoxes(boxes, itemListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});