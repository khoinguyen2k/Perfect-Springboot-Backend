function showBoxes(boxes, itemListBody) {
    boxes.forEach(box => {
        let tableRow = document.createElement('tr');
        let idData = document.createElement('td');
        let imageData = document.createElement('td');
        let weightData = document.createElement('td');
        let typeData = document.createElement('td');
        let descriptionData = document.createElement('td');

        idData.innerHTML = box.id;

        let img = document.createElement("img");
        img.src = box.imageUrl;
        img.width = 300;
        img.height = 200;
        imageData.appendChild(img);

        weightData.innerHTML = box.weight;
        typeData.innerHTML = box.itemType;
        descriptionData.innerHTML = box.description;

        tableRow.appendChild(idData);
        tableRow.appendChild(imageData);
        tableRow.appendChild(weightData);
        tableRow.appendChild(typeData);
        tableRow.appendChild(descriptionData);

        itemListBody.appendChild(tableRow);
    });
}

let gatherLeaderUsername = sessionStorage.getItem("gatherLeaderUsername");

/**
 * Handle send boxes
 */
document.getElementById("btn-show-list-send-package").addEventListener("click", function (event) {
    event.preventDefault();

    let itemListBody = document.querySelector("#send-packages-list-info table tbody");
    itemListBody.innerHTML = "";

    let gatherLeaderUsername = sessionStorage.getItem("gatherLeaderUsername");
    let url = "http://localhost:8080/delivering-item/list/send/gather/" + gatherLeaderUsername;

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    fetch(url, options)
        .then(response => response.json())
        .then(boxes => {

            showBoxes(boxes, itemListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

/**
 * Handle receive boxes
 */
document.getElementById("btn-show-list-receive-package").addEventListener("click", function (event) {
    event.preventDefault();

    let itemListBody = document.querySelector("#receive-packages-list-info table tbody");
    itemListBody.innerHTML = "";


    let url = "http://localhost:8080/delivering-item/list/receive/gather/" + gatherLeaderUsername;

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    fetch(url, options)
        .then(response => response.json())
        .then(boxes => {

            showBoxes(boxes, itemListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});