function showStockBoxes(boxes, boxSection) {
    boxes.forEach(box => {
        const option = document.createElement('option');
        option.value = box.id;
        option.text = box.description + ' - ' + box.itemType + ' - ' + box.weight;

        boxSection.appendChild(option);
    })
}

document.getElementsByName("stock-box").forEach(boxSection => {
    let url = BASE_URL + "stock-item/list/by/employee/gather/" + gatherEmployeeUsername;

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    };

    fetch(url, options).then(response => response.json())
        .then(boxes => showStockBoxes(boxes, boxSection))
        .catch(error => {
            console.error('Error:', error);
        });
});