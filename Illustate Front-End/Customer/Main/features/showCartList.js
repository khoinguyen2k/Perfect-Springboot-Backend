function showCartListCustomerSend(carts, itemListBody) {
    carts.forEach(cart => {
        let tableRow = document.createElement('tr');
        let senderData = document.createElement('td');
        let receiverData = document.createElement('td');
        let itemData = document.createElement('td');
        let stateData = document.createElement('td');
        let sendTimeData = document.createElement('td');
        let receiveTimeData = document.createElement('td');
        let priceData = document.createElement('td');

        senderData.innerHTML = cart.sendCustomer.fullName;
        receiverData.innerHTML = cart.receiveCustomer.fullName;
        stateData.innerHTML = cart.state;
        sendTimeData.innerHTML = cart.sendTime;
        receiveTimeData.innerHTML = cart.receiveTime;
        priceData.innerHTML = cart.price;

        let box = cart.packageBox;
        let img = document.createElement("img");
        img.classList.add('img-fluid');
        img.src = box.imageUrl;
        img.width = 300;
        img.height = 200;
        itemData.appendChild(img);

        tableRow.appendChild(senderData);
        tableRow.appendChild(receiverData);
        tableRow.appendChild(itemData);
        tableRow.appendChild(stateData);
        tableRow.appendChild(sendTimeData);
        tableRow.appendChild(receiveTimeData);
        tableRow.appendChild(priceData);

        itemListBody.appendChild(tableRow);
    });
}

let customerUsername = sessionStorage.getItem("customerUsername");

/**
 * Handle show carts
 */
document.getElementById("btn-show-cart-list").addEventListener("click", function (event) {
    event.preventDefault();

    let cartListBody = document.querySelector(".cart-list .table tbody");
    cartListBody.innerHTML = "";

    let url = "http://localhost:8080/cart/customer/send";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: customerUsername
    };

    //send carts
    fetch(url, options)
        .then(response => response.json())
        .then(carts => {

            showCartListCustomerSend(carts, cartListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });

    //receive carts
    url = "http://localhost:8080/cart/customer/receive";
    fetch(url, options)
        .then(response => response.json())
        .then(carts => {

            showCartListCustomerSend(carts, cartListBody);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

