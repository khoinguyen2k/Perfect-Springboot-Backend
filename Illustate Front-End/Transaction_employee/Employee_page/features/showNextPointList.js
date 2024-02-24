function getPointList(entity, selectBoxId) {

    fetch(BASE_URL + entity + '/tools/list')
        .then(response => response.json())
        .then(entities => {
            
            const selectBox = document.getElementById(selectBoxId);

            entities.forEach(entity => {
                const option = document.createElement('option');
                option.value = entity.username;
                option.text = entity.fullName + ' - ' + entity.address;
                selectBox.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

getPointList('point-leader/gather', 'receive-gather-point');
getPointList('point-leader/transaction', 'receive-transaction-point');
