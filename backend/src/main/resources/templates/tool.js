class DeliverRoute {
    constructor(formData) {
        this.sendCustomerUsername = formData.get('send-customer');
        this.firstTransactionPointCode = formData.get('first-transaction');
        this.firstGatherPointCode = formData.get('first-gather');
        this.secondGatherPointCode = formData.get('second-gather');
        this.secondTransactionPointCode = formData.get('second-transaction');
        this.receiveCustomerUsername = formData.get('receive-customer');
        this.packageBoxId = formData.get('package-box');
        this.state = formData.get('state');
    }

}

const BASE_URL = "http://localhost:8080/";

function getPointList(entity, selectBoxId) {

  fetch(BASE_URL + entity + '/tools/list')
    .then(response => response.json())
    .then(entities => {
      const selectBox = document.getElementById(selectBoxId);
      console.log(entities);

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

function getItemList(entity, selectBoxId) {

  fetch(BASE_URL + entity + '/tools/list')
    .then(response => response.json())
    .then(entities => {
      const selectBox = document.getElementById(selectBoxId);
      console.log(entities);

      entities.forEach(entity => {
        const option = document.createElement('option');
        option.value = entity.id;
        option.text = entity.id;
        selectBox.appendChild(option);
      });
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

function getStateList(entity, selectBoxId) {

  fetch(BASE_URL + entity + '/tools/list')
    .then(response => response.json())
    .then(entities => {
      const selectBox = document.getElementById(selectBoxId);
      console.log(entities);

      entities.forEach(entity => {
        const option = document.createElement('option');
        option.value = entity;
        option.text = entity;
        selectBox.appendChild(option);
      });
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

function loadSelectData() {
getPointList('customer', 'send-customer');
getPointList('point-leader/transaction', 'first-transaction');
getPointList('point-leader/gather', 'first-gather');
getPointList('point-leader/gather', 'second-gather');
getPointList('point-leader/transaction', 'second-transaction');
getPointList('customer', 'receive-customer');
getItemList('item', 'package-box');
getStateList('constant', 'state');
}

loadSelectData();

document.getElementById("create-deliver-route").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);
    let deliverRoute = new DeliverRoute(formData);

    let url = BASE_URL + 'tools/deliver-route/create';

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(deliverRoute)
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